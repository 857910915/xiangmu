package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.UserDao;

public class EditPwdServlet extends HttpServlet {

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
		//获取密码
		Cookie[]  cookies = request.getCookies();
		String username="";
		if( cookies != null&&cookies.length>0 ){
			int i=0;
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username")) {
					username=cookie.getValue();
				}
			}	
		}
		String newpwd=request.getParameter("newpwd");
		String oldpwd=request.getParameter("oldpwd");
		//初始化用户对象并赋值
		User user=new User();
		user.setUsername(username);
		user.setUserpwd(newpwd);
		//连接数据库
		UserDao dao=new UserDao();
		dao.connectionSql();
		String sql="SELECT * FROM user WHERE username = ? AND userpwd = ?";
		ResultSet rs=dao.queryInfo(sql, new String[]{username,oldpwd});
		try {
			if (newpwd!=null&&!newpwd.equals(oldpwd)&&rs!=null&&rs.next()) {
				if (dao.editUser(user)) {
					out.print("Edit Password success！");
					out.print("<br><a href = 'login.jsp'>Re login</a>");
				}else {
					out.print("EditPassword failure!");
					out.print("<br><a href = 'editpwd.jsp'>Re edit</a>");
				}
			}else {
				out.print("Please input the correct password");
				out.print("<br><a href = 'editpwd.jsp'>Re edit</a>");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
