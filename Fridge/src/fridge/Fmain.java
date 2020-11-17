package fridge;

import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * ����� Main
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

			String menu = "1. ��� �߰�" + "\n" 
						+ "2. Ư�� ��� �˻�" + "\n" 
						+ "3. ��� ��� ��ȸ" + "\n" 
						+ "4. ���İ� �ʿ� ��� �߰�" + "\n"
						+ "5. ��� ����, �ʿ� ��� ��ȸ" + "\n" 
						+ "6. ����� ��� �߰�" + "\n" 
						+ "7. ����� ��� ��� ��ȸ" + "\n"
						+ "8. ����� ���� ���� �� �ִ� ����, �ʿ� ��� ��ȸ" + "\n" 
						+ "0. ��� �߰�" + "\n";

			FoodVo fvo;
			IngredVo ivo;
			FridgeVo frvo;
			String message;
			String name;
			
			loop: while (true) {
				String select = JOptionPane.showInputDialog(menu);
				switch (select) {
				
				/** ��� �߰�  */
				case "1":
					ivo = new IngredVo();
					ivo.setIngName(JOptionPane.showInputDialog("�� ��� �̸�"));
					idao.insert(ivo);
					break;
					
				/** Ư�� ��� �˻�  */	
				case "2":
					name = JOptionPane.showInputDialog("�˻��� ��� �̸�");
					ivo = idao.select(name);
					if (null == ivo) {
						JOptionPane.showMessageDialog(null, "�̵�� ���");
						continue;
					}
					JOptionPane.showMessageDialog(null, ivo);
					break;
					
				/** ��� ��� ��ȸ  */	
				case "3":
					ArrayList<IngredVo> ilist = idao.selectAll();
					if (ilist.isEmpty()) {
						JOptionPane.showMessageDialog(null, "��ᰡ �����ϴ�.");
						continue;
					}
					message = "==== ��� ====\n";
					for (IngredVo iv : ilist) {
						message += iv.toString() + "\n";
					}
					JOptionPane.showMessageDialog(null, message);
					break;
					
				/** ���İ� �ʿ� ��� �߰�  */			
				case "4":
					fvo = new FoodVo();
					fvo.setFName(JOptionPane.showInputDialog("�� ���� �̸�"));
					fdao.insert(fvo);

					FoodVo vo = fdao.select(fvo.getFName());
					int fno = vo.getFNo();
					
					while (true) {
						RecingDao rdaoDao = RecingDao.getInstance();
						String s = JOptionPane.showInputDialog("���Ŀ� �ʿ��� ��� �̸�").trim();

						if (rdaoDao.insert(s, fno)) {
							JOptionPane.showMessageDialog(null, "�������� �ʴ� ����Դϴ�.");
							continue;
						}
						if (null == s || s.isEmpty()) {
							break;
						}
					}
					
				/** ��� ����, �ʿ� ��� ��ȸ  */	
				case "5":
					fdao.p();
					
				/** ����� ��� �߰�  */	
				case "6":
					name = JOptionPane.showInputDialog("����� �� �� ��� �̸�");
					if (frdao.insert(name)) {
						JOptionPane.showMessageDialog(null, "�������� �ʴ� ����Դϴ�.");
						continue;
					}
					break;
					
				/** ����� ��� ��� ��ȸ  */
				case "7":
					ArrayList<FridgeVo> frlist = frdao.selectAll();
					if (frlist.isEmpty()) {
						JOptionPane.showMessageDialog(null, "����� ������ϴ�.");
						continue;
					}
					message = "==== ����� ====\n";
					for (FridgeVo frv : frlist) {
						message += frv.toString() + "\n";
					}
					JOptionPane.showMessageDialog(null, message);
					break;
					
				/** ����� ���� ���� �� �ִ� ����, �ʿ� ��� ��ȸ  */	
				case "8":
					frdao.third();
					
				/** ���α׷� ����  */
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
