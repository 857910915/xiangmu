package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import service.StudentService;

public class EditStuInfoServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("UTF-8");
		
		String sid=req.getParameter("sid");
		System.out.println(sid);
		StudentService service1=new StudentService();
		service1.connectionSql();	
		Student stu=service1.getStuById(sid);
		//获取添加信息
		String sname=req.getParameter("stuname");
		String ssex=req.getParameter("stusex");
		String sage=req.getParameter("stuage");
		String smajor=req.getParameter("stumajor");
		String sphone=req.getParameter("stuphone");
		stu.setSid(sid);
		stu.setSname(sname);
		stu.setSsex(ssex);
		stu.setSage(sage);
		stu.setSmajor(smajor);
		stu.setSphone(sphone);
		req.setAttribute("stu", stu);
		service1.connectionSql();
		boolean r=service1.editStuInfo(stu);
		if (r) {
			System.out.println("修改成功");
			resp.sendRedirect("studentinfo.jsp");
		}else {
			System.out.println("修改失败");
			resp.sendRedirect("studentinfo.jsp");
		}
	}
}
