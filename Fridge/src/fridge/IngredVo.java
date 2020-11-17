package fridge;

/**
 * IngredVo
 * 
 * @author User
 *
 */

public class IngredVo {
	
	private Integer ingNo;
	
	private String ingName;

	/**
	 * @return
	 * 재료 고유번호
	 */
	public Integer getIngNo() {
		return ingNo;
	}
	
	/**
	 * @param ingNo
	 */
	public void setIngNo(Integer ingNo) {
		this.ingNo = ingNo;
	}

	/**
	 * @return
	 * 재료 고유이름
	 */
	public String getIngName() {
		return ingName;
	}
	
	/**
	 * @param ingName
	 */
	public void setIngName(String ingName) {
		this.ingName = ingName;
	}
	
	/**
	 * @return
	 * 재료 고유번호 / 재료 고유이름
	 */
	@Override
	public String toString() {
		return "No. " + ingNo + " / " + ingName;
	}
	
	/**
	 * @param param
	 * 재료 고유번호 (정수)
	 * 재료 고유이름 (문자열)
	 */
	public void CopyData(IngredVo param) {
		this.ingNo = param.getIngNo();
		this.ingName = param.getIngName();
	}
}