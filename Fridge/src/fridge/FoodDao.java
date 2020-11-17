package fridge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeMap;

import javax.swing.JOptionPane;

/**
 * FoodDao
 * 
 * @author User
 *
 */
public class FoodDao {
	private static final String ID = "root";
	private static final String PW = "root";
	private static final String URL = "jdbc:mysql://127.0.0.1/fridge";
	private Connection conn;

	private static FoodDao instance;

	private FoodDao() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(URL, ID, PW);
	}

	/**
	 * @return Dao�� �̱��� ��������
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static FoodDao getInstance() throws ClassNotFoundException, SQLException {
		if (null == instance) {
			instance = new FoodDao();
		}
		return instance;
	}

	/**
	 * ������ ArrayList�� ���
	 * 
	 * @return ��� ���� ��ȣ, ���� �̸�
	 * @throws SQLException
	 */
	public ArrayList<FoodVo> selectAll() throws SQLException {
		String sql = "SELECT * FROM Food";

		java.sql.PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		ArrayList<FoodVo> flist = new ArrayList<>();

		while (rs.next()) {
			FoodVo fvo = new FoodVo();
			fvo.setFNo(rs.getInt(1));
			fvo.setFName(rs.getString(2));

			flist.add(fvo);
		}
		rs.close();
		ps.close();

		return flist;
	}

	/**
	 * ���� �̸��� ����� Ư�� ���� ��ȸ
	 * 
	 * @param name ���ڿ�
	 * @return ���� ��ȣ, ���� �̸�
	 * @throws SQLException
	 */
	public FoodVo select(String name) throws SQLException {
		String sql = "SELECT * FROM Food WHERE f_name = ?";

		java.sql.PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, name);

		ResultSet rs = ps.executeQuery();
		if (!rs.next()) {
			return null;
		}

		FoodVo fvo = new FoodVo();
		fvo.setFNo(rs.getInt(1));
		fvo.setFName(rs.getString(2));

		rs.close();
		ps.close();

		return fvo;
	}

	/**
	 * ��� ���İ� �ʿ��� ��� ��ȸ
	 * 
	 * @return ��� ����, ������ ����� ���� �ʿ��� ��� ���
	 * @throws SQLException
	 */
	public TreeMap<String, ArrayList<String>> p() throws SQLException {
		TreeMap<String, ArrayList<String>> foodMap = new TreeMap<>();

		String sql = "SELECT f.f_name, i.ing_name FROM recing r LEFT OUTER JOIN food f ON f.f_no = r.food_no LEFT OUTER JOIN ingred i ON r.rec_no = i.ing_no";
		java.sql.PreparedStatement p = conn.prepareStatement(sql);

		ResultSet rs = p.executeQuery();
		if (!rs.next()) {
			return null;
		}

		ArrayList<String> ri = new ArrayList<>();
		RecingVo rvo = new RecingVo();
		String a, b;

		while (rs.next()) {
			rvo.setFname(rs.getString(1));
			rvo.setIngname(rs.getString(2));

			ri.add(rvo.getIngname());

			foodMap.put(rvo.getFname(), ri);
		}
		System.out.println(ri);
		rs.close();
		p.close();
		JOptionPane.showMessageDialog(null, foodMap);
		return foodMap;
	}

	/**
	 * FridgeVo�� ��� ������ DB�� ����
	 * 
	 * @param fvo
	 * @return
	 * @throws Exception
	 */
	public boolean insert(FoodVo fvo) throws Exception {
		String sql = "INSERT INTO Food(f_no, f_name) VALUES(DEFAULT, ?)";
		java.sql.PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, fvo.getFName());
		return ps.executeUpdate() < 1;
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
