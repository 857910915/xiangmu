package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import service.StudentService;

public class AddStudentServlet extends HttpServlet {

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

		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		response.setLocale(new java.util.Locale("zh","CN"));
		//获取添加信息
		String sname=request.getParameter("stuname");
		String ssex=request.getParameter("stusex");
		String sage=request.getParameter("stuage");
		String smajor=request.getParameter("stumajor");
		String sphone=request.getParameter("stuphone");
		//初始化student对象
		Student stu=new Student();
		stu.setSname(sname);
		stu.setSsex(ssex);
		stu.setSage(sage);
		stu.setSmajor(smajor);
		stu.setSphone(sphone);
		//打开连接
		StudentService service=new StudentService();
		service.connectionSql();
		if (service.addStuInfo(stu)) {
			response.sendRedirect("studentinfo.jsp");
		}
	}

}
