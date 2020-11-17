package fridge;

/**
 * RecingVo
 * 
 * @author User
 *
 */

public class RecingVo {
	
	private Integer recipeNo;
	
	private Integer recNo;
	
	private String ingname;
	
	private Integer fNo;
	
	private String fname;
	
	/**
	 * @return
	 * 레시피에 들어가는 재료 이름
	 */
	public String getIngname() {
		return ingname;
	}
	
	/**
	 * @param ingname
	 */
	public void setIngname(String ingname) {
		this.ingname = ingname;
	}
	
	/**
	 * @return
	 * 음식 이름
	 */
	public String getFname() {
		return fname;
	}
	
	/**
	 * @param fname
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}
	
	/**
	 * @return
	 * 레시피 고유번호
	 */
	public Integer getRecipeNo() {
		return recipeNo;
	}
	
	/**
	 * @param recipeNo
	 */
	public void setRecipeNo(Integer recipeNo) {
		this.recipeNo = recipeNo;
	}
	
	/**
	 * @return
	 * 레시피에 들어가는 재료 번호
	 */
	public Integer getRecNo() {
		return recNo;
	}
	
	/**
	 * @param recNo
	 */
	public void setRecNo(Integer recNo) {
		this.recNo = recNo;
	}
	
	/**
	 * @return
	 * 음식 번호
	 */
	public Integer getfNo() {
		return fNo;
	}

	/**
	 * @param fNo
	 */
	public void setfNo(Integer fNo) {
		this.fNo = fNo;
	}
	
	/**
	 * @return
	 * 레시피 고유번호 / 레시피에 들어가는 재료 번호 / 레시피에 들어가는 재료 이름 / 음식 번호 / 음식 이름
	 */
	@Override
	public String toString() {
		return recipeNo + "/" + recNo + "/" + ingname + "/" + fNo + "/" + fname;
	}
	
	/**
	 * @param param
	 * 레시피 고유번호 (정수)
	 * 레시피에 들어가는 재료 번호 (정수)
	 */
	public void CopyData(RecingVo param) {
		this.recipeNo = param.getRecipeNo();
		this.recNo = param.getRecNo();
	}
}