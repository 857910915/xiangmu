package servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		// 获取cookies的数据,是一个数组
		Cookie[] cookies = request.getCookies();
		if( cookies != null ){
			String username="";
			HashMap<String, String>map=new HashMap<String, String>();
			for (Cookie cookie : cookies) {    
				if(cookie.getName().equals("username")){
					username=cookie.getValue();
					map.put(cookie.getName(),URLDecoder.decode(cookie.getValue(), "UTF-8"));
				}else{				   
					map.put(cookie.getName(),cookie.getValue());
				}
			}
			request.setAttribute("username", username);
			request.setAttribute("content", map);
			request.setAttribute("msg", "尊敬的用户:");
//			response.sendRedirect("index.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);		
		}else{
			request.setAttribute("msg", "没有cookie");
//			response.sendRedirect("index.jsp");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
	}
}
