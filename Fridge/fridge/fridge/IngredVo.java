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
	 * ��� ������ȣ
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
	 * ��� �����̸�
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
	 * ��� ������ȣ / ��� �����̸�
	 */
	@Override
	public String toString() {
		return "No. " + ingNo + " / " + ingName;
	}
	
	/**
	 * @param param
	 * ��� ������ȣ (����)
	 * ��� �����̸� (���ڿ�)
	 */
	public void CopyData(IngredVo param) {
		this.ingNo = param.getIngNo();
		this.ingName = param.getIngName();
	}
}