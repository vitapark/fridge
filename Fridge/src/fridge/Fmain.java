package fridge;

import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * 냉장고 Main
 * 
 * @author User
 *
 */
public class Fmain {
	public static void main(String[] args) {
		FoodDao fdao = null;
		FridgeDao frdao = null;
		IngredDao idao = null;
		
		try {
			fdao = FoodDao.getInstance();
			frdao = FridgeDao.getInstance();
			idao = IngredDao.getInstance();

			String menu = "1. 재료 추가" + "\n" 
						+ "2. 특정 재료 검색" + "\n" 
						+ "3. 모든 재료 조회" + "\n" 
						+ "4. 음식과 필요 재료 추가" + "\n"
						+ "5. 모든 음식, 필요 재료 조회" + "\n" 
						+ "6. 냉장고 재료 추가" + "\n" 
						+ "7. 냉장고 모든 재료 조회" + "\n"
						+ "8. 냉장고 재료로 만들 수 있는 음식, 필요 재료 조회" + "\n" 
						+ "0. 재료 추가" + "\n";

			FoodVo fvo;
			IngredVo ivo;
			FridgeVo frvo;
			String message;
			String name;
			
			loop: while (true) {
				String select = JOptionPane.showInputDialog(menu);
				switch (select) {
				
				/** 재료 추가  */
				case "1":
					ivo = new IngredVo();
					ivo.setIngName(JOptionPane.showInputDialog("새 재료 이름"));
					idao.insert(ivo);
					break;
					
				/** 특정 재료 검색  */	
				case "2":
					name = JOptionPane.showInputDialog("검색할 재료 이름");
					ivo = idao.select(name);
					if (null == ivo) {
						JOptionPane.showMessageDialog(null, "미등록 재료");
						continue;
					}
					JOptionPane.showMessageDialog(null, ivo);
					break;
					
				/** 모든 재료 조회  */	
				case "3":
					ArrayList<IngredVo> ilist = idao.selectAll();
					if (ilist.isEmpty()) {
						JOptionPane.showMessageDialog(null, "재료가 없습니다.");
						continue;
					}
					message = "==== 재료 ====\n";
					for (IngredVo iv : ilist) {
						message += iv.toString() + "\n";
					}
					JOptionPane.showMessageDialog(null, message);
					break;
					
				/** 음식과 필요 재료 추가  */			
				case "4":
					fvo = new FoodVo();
					fvo.setFName(JOptionPane.showInputDialog("새 음식 이름"));
					fdao.insert(fvo);

					FoodVo vo = fdao.select(fvo.getFName());
					int fno = vo.getFNo();
					
					while (true) {
						RecingDao rdaoDao = RecingDao.getInstance();
						String s = JOptionPane.showInputDialog("음식에 필요한 재료 이름").trim();

						if (rdaoDao.insert(s, fno)) {
							JOptionPane.showMessageDialog(null, "존재하지 않는 재료입니다.");
							continue;
						}
						if (null == s || s.isEmpty()) {
							break;
						}
					}
					
				/** 모든 음식, 필요 재료 조회  */	
				case "5":
					fdao.p();
					
				/** 냉장고 재료 추가  */	
				case "6":
					name = JOptionPane.showInputDialog("냉장고에 들어갈 새 재료 이름");
					if (frdao.insert(name)) {
						JOptionPane.showMessageDialog(null, "존재하지 않는 재료입니다.");
						continue;
					}
					break;
					
				/** 냉장고 모든 재료 조회  */
				case "7":
					ArrayList<FridgeVo> frlist = frdao.selectAll();
					if (frlist.isEmpty()) {
						JOptionPane.showMessageDialog(null, "냉장고가 비었습니다.");
						continue;
					}
					message = "==== 냉장고 ====\n";
					for (FridgeVo frv : frlist) {
						message += frv.toString() + "\n";
					}
					JOptionPane.showMessageDialog(null, message);
					break;
					
				/** 냉장고 재료로 만들 수 있는 음식, 필요 재료 조회  */	
				case "8":
					frdao.third();
					
				/** 프로그램 종료  */
				case "0":
					break loop;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fdao.close();
		}
	}
}
