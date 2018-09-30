package conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MySQLConnection {
	//JDBC驱动名及数据库URL
	//数据库的用户名和密码
	String driverName = "com.mysql.jdbc.Driver";
	static String userName = "root";
	static String userPwd = "970817";
	static String dbName = "work";
	static String url1 = "jdbc:mysql://localhost:3306/"+dbName;
	static String url2 = "?user="+userName+"&password="+userPwd;
	static String url3 = "&useUnicode=true&characterEncoding=UTF-8&useSSL=false";
	static String url = url1+url2+url3;
	Connection conn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	static{
		try {
			//注册JDBC驱动
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("�ɹ�����MySQL��������");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	//加载驱动打开连接
	public void connectionSql() {	
		//打开连接	
		try {	
			System.out.println("数据库连接。。。。");
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println("连接失败！");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	//关闭数据库
	public void connectionClose() {

		try {
			if (rs!=null) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

			try {
				if (ps!=null) {
					ps.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {			
				try {
					if (conn!=null) {
						conn.close();
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
	//执行sql语句查询

	public ResultSet queryInfo(String sql,String[]param) {
		try {
			ps=(PreparedStatement)conn.prepareStatement(sql);
			if(param!=null){
				for (int i = 0; i < param.length; i++) {
					ps.setString(i+1, param[i]);
				}
			}
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	//sql语句  增删改
	public int updataInfo(String sql,String[]param) {
		int num=0;
		try {
			ps=conn.prepareStatement(sql);
			if (ps!=null) {
				for (int i = 0; i < param.length; i++) {
					ps.setString(i+1, param[i]);
				}
			}
			num=ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

}
