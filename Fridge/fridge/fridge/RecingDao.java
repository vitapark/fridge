package fridge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RecingDao {
	private static final String ID = "root";
	private static final String PW = "root";
	private static final String URL = "jdbc:mysql://127.0.0.1/fridge";
	private Connection conn;

	private static RecingDao instance;

	private RecingDao() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(URL, ID, PW);
	}

	/**
	 * @return Dao�� �̱��� ��������
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static RecingDao getInstance() throws ClassNotFoundException, SQLException {
		if (null == instance) {
			instance = new RecingDao();
		}
		return instance;
	}

	/**
	 * ���Ŀ� �ʿ��� ��� ������ ��Ḧ ArrayList�� ���
	 * 
	 * @return ���Ŀ� �ʿ��� ��� ������ ���
	 * @throws SQLException
	 */
	public ArrayList<RecingVo> selectAll() throws SQLException {
		String sql = "SELECT i.*, r.recipe_no FROM ingred AS i JOIN Recing AS r ON r.rec_no = i.ing_no";

		java.sql.PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		ArrayList<RecingVo> rlist = new ArrayList<>();

		while (rs.next()) {
			RecingVo rvo = new RecingVo();

			rvo.setRecipeNo(rs.getInt(1));
			rvo.setRecNo(rs.getInt(2));
			rvo.setIngname(rs.getString(3));
			rvo.setfNo(rs.getInt(4));
			rvo.setFname(rs.getString(5));

			rlist.add(rvo);
		}
		rs.close();
		ps.close();

		return rlist;
	}

	/**
	 * RecingVo�� ��� ������ DB�� ����
	 * 
	 * @param name
	 * @param food_no
	 * @return
	 * @throws SQLException
	 */
	public boolean insert(String name, int food_no) throws SQLException {
		String sql = "INSERT INTO recing(rec_no, food_no) SELECT ingred.ing_no, ? FROM ingred WHERE ing_name = ?";
		java.sql.PreparedStatement ps = conn.prepareStatement(sql);
		ps.setInt(1, food_no);
		ps.setString(2, name);

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
