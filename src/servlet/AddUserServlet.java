package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import conn.MySQLConnection;
import dao.UserDao;

public class AddUserServlet extends HttpServlet {
	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		response.setLocale(new java.util.Locale("zh","CN"));
		PrintWriter out = response.getWriter();
		//获取输入框的值
		String username=request.getParameter("username");
		String userpwd=request.getParameter("userpwd");
		//初始化用户对象并赋值
		User user=new User();
		user.setUsername(username);
		user.setUserpwd(userpwd);
		//初始化UserDao对象
		UserDao dao=new UserDao();
		//连接数据库
		dao.connectionSql();
		String sql="SELECT username FROM user WHERE username = ?";
		ResultSet rs=dao.queryInfo(sql, new String[]{username});
		try {
			if (rs!=null&&rs.next()) {
				out.print("The username was existed!");
				out.print("<br><a href = 'addUser.jsp'>Re registration</a>");	
			}else {
				if(dao.addUser(user)){		
					out.print("register was successful!");
					out.print("<br><a href ='login.jsp'>Login</a>");
					
				}else {
					out.print("register was failed!");			
					out.print("<br><a href ='addUser.jsp'>Re registration</a>");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
