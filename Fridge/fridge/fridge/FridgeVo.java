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
	 * ��� �̸�
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name 
	 * ��� �̸� (���ڿ�)
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return 
	 * ����� ������ȣ
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
	 * ��� ��ȣ
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
	 * ����� ������ȣ / ��� ��ȣ / ��� �̸�
	 */
	@Override
	public String toString() {
		return "No. " + friNo + " / ��� No. " + ingreNo + " / " + name;
	}

	/**
	 * @param param 
	 * ����� ������ȣ (����) 
	 * ��� ��ȣ (����)
	 */
	public void CopyData(FridgeVo param) {
		this.friNo = param.getFriNo();
		this.ingreNo = param.getIngreNo();
	}
}