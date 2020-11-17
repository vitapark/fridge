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
	 * �����ǿ� ���� ��� �̸�
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
	 * ���� �̸�
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
	 * ������ ������ȣ
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
	 * �����ǿ� ���� ��� ��ȣ
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
	 * ���� ��ȣ
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
	 * ������ ������ȣ / �����ǿ� ���� ��� ��ȣ / �����ǿ� ���� ��� �̸� / ���� ��ȣ / ���� �̸�
	 */
	@Override
	public String toString() {
		return recipeNo + "/" + recNo + "/" + ingname + "/" + fNo + "/" + fname;
	}
	
	/**
	 * @param param
	 * ������ ������ȣ (����)
	 * �����ǿ� ���� ��� ��ȣ (����)
	 */
	public void CopyData(RecingVo param) {
		this.recipeNo = param.getRecipeNo();
		this.recNo = param.getRecNo();
	}
}