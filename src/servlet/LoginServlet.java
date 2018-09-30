package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.User;
import conn.MySQLConnection;

public class LoginServlet extends HttpServlet {

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
		PrintWriter out = response.getWriter();
		//获取输入框的值
		String username=request.getParameter("username");
		String userpwd=request.getParameter("userpwd");
		//初始化用户对象并赋值
		User user=new User();
		user.setUsername(username);
		user.setUserpwd(userpwd);
		//连接数据库
		MySQLConnection connection=new MySQLConnection();
		connection.connectionSql();
		String sql="SELECT * FROM user WHERE username = ? AND userpwd = ?";
		ResultSet rs=connection.queryInfo(sql, new String[]{username,userpwd});
		try {
			if (rs!=null&&rs.next()) {
		        //创建一个cookie对象
		        Cookie cookie=new Cookie("username",URLEncoder.encode(username, "utf-8"));
		        Cookie cookie2=new Cookie("userpwd", URLEncoder.encode(userpwd, "utf-8"));
		        cookie.setMaxAge(60*60*24);
		        cookie2.setMaxAge(60*60*24);	        
		        //设置cookie给客户端（浏览器）
		        response.addCookie(cookie);
		        response.addCookie(cookie2);
		        response.sendRedirect("CookieServlet");
			}else {
				out.print("Login failure");
				out.print("<br><a href = 'login.jsp'>Re landing</a>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
