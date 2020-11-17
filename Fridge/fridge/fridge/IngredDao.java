package fridge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class IngredDao {
	private static final String ID = "root";
	private static final String PW = "root";
	private static final String URL = "jdbc:mysql://127.0.0.1/fridge";
	private Connection conn;

	private static IngredDao instance;

	private IngredDao() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(URL, ID, PW);
	}

	/**
	 * @return Dao를 싱글톤 패턴으로
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static IngredDao getInstance() throws ClassNotFoundException, SQLException {
		if (null == instance) {
			instance = new IngredDao();
		}
		return instance;
	}

	/**
	 * 모든 재료를 ArrayList에 담기
	 * 
	 * @return 모든 재료 고유번호, 재료 이름
	 * @throws SQLException
	 */
	public ArrayList<IngredVo> selectAll() throws SQLException {
		String sql = "SELECT * FROM Ingred ORDER BY ing_name";

		java.sql.PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		ArrayList<IngredVo> ilist = new ArrayList<>();

		while (rs.next()) {
			IngredVo ivo = new IngredVo();
			ivo.setIngNo(rs.getInt(1));
			ivo.setIngName(rs.getString(2));

			ilist.add(ivo);
		}
		rs.close();
		ps.close();

		return ilist;
	}

	/**
	 * 재료 이름으로 특정 재료 검색
	 * 
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public IngredVo select(String name) throws SQLException {
		String sql = "SELECT * FROM ingred WHERE ing_name = ?";

		java.sql.PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, name);

		ResultSet rs = ps.executeQuery();
		if (!rs.next()) {
			return null;
		}

		IngredVo ivo = new IngredVo();
		ivo.setIngNo(rs.getInt(1));
		ivo.setIngName(rs.getString(2));

		rs.close();
		ps.close();

		return ivo;
	}

	/**
	 * IngredVo에 담긴 정보를 DB에 저장
	 * 
	 * @param ivo
	 * @return
	 * @throws Exception
	 */
	public boolean insert(IngredVo ivo) throws Exception {
		String sql = "INSERT INTO Ingred(ing_no, ing_name) VALUES(DEFAULT, ?)";
		java.sql.PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, ivo.getIngName());
		return ps.executeUpdate() > 0;
	}

	/** 닫기 */
	public void close() {
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
