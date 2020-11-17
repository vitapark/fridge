package fridge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.JOptionPane;

public class FridgeDao {
	private static final String ID = "root";
	private static final String PW = "root";
	private static final String URL = "jdbc:mysql://127.0.0.1/fridge";
	private Connection conn;

	private static FridgeDao instance;

	private FridgeDao() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(URL, ID, PW);
	}

	/**
	 * @return Dao�� �̱��� ��������
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static FridgeDao getInstance() throws ClassNotFoundException, SQLException {
		if (null == instance) {
			instance = new FridgeDao();
		}
		return instance;
	}

	/**
	 * ������� ��� ��Ḧ ArrayList�� ���
	 * 
	 * @return ��� ����� ����ȣ, ����� ����̸�, ����� ������ȣ
	 * @throws SQLException
	 */
	public ArrayList<FridgeVo> selectAll() throws SQLException {
		String sql = "SELECT i.*, f.fri_no FROM ingred AS i JOIN fridge AS f " + "ON f.ingre_no = i.ing_no";

		java.sql.PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		ArrayList<FridgeVo> frlist = new ArrayList<>();

		while (rs.next()) {
			FridgeVo frvo = new FridgeVo();

			frvo.setIngreNo(rs.getInt(1));

			frvo.setName(rs.getString(2));
			frvo.setFriNo(rs.getInt(3));

			frlist.add(frvo);
		}
		rs.close();
		ps.close();

		return frlist;
	}

	/**
	 * ��� �̸����� ����� Ư�� ��� �˻�
	 * 
	 * @param name
	 * @return ����� ������ȣ, ��� �̸�, ��� ��ȣ
	 * @throws SQLException
	 */
	public FridgeVo select(String name) throws SQLException {
		String sql = "SELECT i.*, f.* FROM ingred AS i JOIN fridge AS f ON f.ingre_no = i.ing_no WHERE i.ing_name = ?";

		java.sql.PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, name);

		ResultSet rs = ps.executeQuery();
		if (!rs.next()) {
			return null;
		}

		FridgeVo frvo = new FridgeVo();

		frvo.setFriNo(rs.getInt(1));
		frvo.setName(rs.getString(2));
		frvo.setIngreNo(rs.getInt(3));

		rs.close();
		ps.close();

		return frvo;
	}

	/**
	 * FridgeVo�� ��� ������ DB�� ����
	 * 
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public boolean insert(String name) throws SQLException {
		String sql = "INSERT INTO fridge(ingre_no) SELECT ingred.ing_no FROM ingred WHERE ing_name = ?";
		java.sql.PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, name);

		return ps.executeUpdate() < 1;
	}

	/**
	 * ������� ��� ��� ��ȣ�� ArrayList�� ����
	 * 
	 * @return ����� ��� ��ȣ
	 * @throws SQLException
	 */
	public ArrayList first() throws SQLException {
		String sql = "SELECT ingre_no FROM fridge";

		java.sql.PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		ArrayList<Integer> ilist = new ArrayList<>();

		while (rs.next()) {
			FridgeVo frvo = new FridgeVo();

			frvo.setIngreNo(rs.getInt(1));

			ilist.add(frvo.getIngreNo());
		}
		rs.close();
		ps.close();
		return ilist;
	}

	/**
	 * ����� ��� ��ȣ�� ��� ���� ��ȣ�� ArrayList�� ����
	 * 
	 * @return ���� ��ȣ
	 * @throws SQLException
	 */
	public ArrayList second() throws SQLException {
		ArrayList<Integer> ilist = new ArrayList<>();
		HashSet<Integer> foodlist = new HashSet<>();
		ArrayList<Integer> flist = new ArrayList<>();
		ilist = first();

		for (int i = 0; i < ilist.size(); ++i) {
			String sql = "SELECT food_no FROM recing WHERE rec_no = ?";
			java.sql.PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, ilist.get(i));

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RecingVo rvo = new RecingVo();
				rvo.setfNo(rs.getInt(1));
				foodlist.add(rvo.getfNo());
			}
			rs.close();
			ps.close();
		}

		flist = new ArrayList<>(foodlist);
		return flist;
	}

	/**
	 * �� ������ �ʿ� ��� ��ȣ�� HashMap�� ����
	 * 
	 * @return �� ������ �ʿ� ��� ��ȣ
	 * @throws SQLException
	 */
	public HashMap third() throws SQLException {
		HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

		ArrayList<Integer> flist = new ArrayList<>();
		ArrayList<Integer> rlist;
		flist = second();

		for (int i = 0; i < flist.size(); ++i) {
			rlist = new ArrayList<>();
			String sql = "SELECT rec_no FROM recing WHERE food_no = ?";
			java.sql.PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, flist.get(i));

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RecingVo rvo = new RecingVo();
				rvo.setfNo(rs.getInt(1));

				int b = rvo.getfNo();
				rlist.add(rvo.getfNo());

			}
			map.put(flist.get(i), rlist);
			rs.close();
			ps.close();

		}

		JOptionPane.showMessageDialog(null, map);
		return map;

	}

	/** �ݱ� */
	public void close() {
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
