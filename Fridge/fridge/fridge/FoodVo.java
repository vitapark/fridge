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
	 * ���� ������ȣ
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
	 * ���� �̸�
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
	 * ���� ������ȣ / ���� �̸�
	 */
	@Override
	public String toString() {
		return "No. " + fNo + " / " + fName;
	}

	/**
	 * @param param 
	 * ���� ���� ��ȣ (����) 
	 * ���� �̸� (���ڿ�)
	 */
	public void CopyData(FoodVo param) {
		this.fNo = param.getFNo();
		this.fName = param.getFName();
	}
}
