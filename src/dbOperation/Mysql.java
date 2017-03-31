package dbOperation;

import java.sql.*;

import algorithmOperation.AlgorithmEntrance;


public class Mysql {
	
	private static Connection conn;
	private static Statement stmt;

	public static void getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
           System.out.println("成功加载MySQL驱动！");
           
           String url="jdbc:mysql://219.224.169.20:3306/processeddata";
           
           conn = DriverManager.getConnection(url, "publicuser", "publicuser");
           stmt = conn.createStatement();
           System.out.println("成功连接到数据库！");
	}
	
	public static void closeConnection() throws Exception {
		stmt.close();
		conn.close();
	}
	
	public static void updateSql(String tbl) throws Exception {
		int num = 1;
		
		String sql1 = "select ID, Content, Summary from " + tbl + " where Summary = null";
		String sql2 = "update " + tbl + " set Summary = ? where ID = ?";
		ResultSet rs = stmt.executeQuery(sql1);
		PreparedStatement pst = conn.prepareStatement(sql2);
		
		while(rs.next()) {
			if(rs.getString(3) == null) {
				String src = rs.getString(2);
				String abs = AlgorithmEntrance.get_abstract(src);
//				System.out.println(abs);
				pst.setString(1, abs);
				pst.setString(2, rs.getString(1));
				pst.executeUpdate();
				System.out.println(String.valueOf(num++) + " completed in " + tbl);
			}
		}
		System.out.println(tbl + " completed");
	}
	
	public static void main(String args[]) throws Exception {
		getConnection();
		updateSql("blogdetail");
		updateSql("newsdetail");
		updateSql("postdetail");		
		closeConnection();
	}
}
