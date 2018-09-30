package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.StudentService;

public class DeleteStuInfoServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StudentService service1=new StudentService();
		service1.connectionSql();
		
		boolean r=service1.deleteStuById(req.getParameter("sid"));
		if (r) {
			System.out.println("删除成功");
			resp.sendRedirect("studentinfo.jsp");
		}else {
			System.out.println("删除失败");
		}
	}
}
