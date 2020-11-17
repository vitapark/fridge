package fridge;

/**
 * FoodVo
 * 
 * @author User
 *
 */
public class FoodVo {
	private Integer fNo;

	private String fName;

	/**
	 * @return 
	 * 음식 고유번호
	 */
	public Integer getFNo() {
		return fNo;
	}

	/**
	 * @param fNo
	 */
	public void setFNo(Integer fNo) {
		this.fNo = fNo;
	}

	/**
	 * @return 
	 * 음식 이름
	 */
	public String getFName() {
		return fName;
	}

	/**
	 * @param fName
	 */
	public void setFName(String fName) {
		this.fName = fName;
	}

	/**
	 * @return 
	 * 음식 고유번호 / 음식 이름
	 */
	@Override
	public String toString() {
		return "No. " + fNo + " / " + fName;
	}

	/**
	 * @param param 
	 * 음식 고유 번호 (정수) 
	 * 음식 이름 (문자열)
	 */
	public void CopyData(FoodVo param) {
		this.fNo = param.getFNo();
		this.fName = param.getFName();
	}
}
