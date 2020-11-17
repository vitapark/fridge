package fridge;

/**
 * FridgeVo
 * 
 * @author User
 *
 */
public class FridgeVo {

	private Integer friNo;

	private Integer ingreNo;

	private String name;

	/**
	 * @return 
	 * 재료 이름
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name 
	 * 재료 이름 (문자열)
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return 
	 * 냉장고 고유번호
	 */
	public Integer getFriNo() {
		return friNo;
	}

	/**
	 * @param friNo
	 */
	public void setFriNo(Integer friNo) {
		this.friNo = friNo;
	}

	/**
	 * @return 
	 * 재료 번호
	 */
	public Integer getIngreNo() {
		return ingreNo;
	}

	/**
	 * @param ingreNo
	 */
	public void setIngreNo(Integer ingreNo) {
		this.ingreNo = ingreNo;
	}

	/**
	 * @return 
	 * 냉장고 고유번호 / 재료 번호 / 재료 이름
	 */
	@Override
	public String toString() {
		return "No. " + friNo + " / 재료 No. " + ingreNo + " / " + name;
	}

	/**
	 * @param param 
	 * 냉장고 고유번호 (정수) 
	 * 재료 번호 (정수)
	 */
	public void CopyData(FridgeVo param) {
		this.friNo = param.getFriNo();
		this.ingreNo = param.getIngreNo();
	}
}