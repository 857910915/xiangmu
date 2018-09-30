package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClearCookieServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cookie[]  cookies = req.getCookies();
		if( cookies != null&&cookies.length>0 ){
			int i=0;
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username")) {
					cookie.setMaxAge(0);
					resp.addCookie(cookie);
					resp.sendRedirect("login.jsp");	
					break;
				}else if (i == cookies.length-1&&!cookie.getName().equals("username") ) {
					resp.sendRedirect("login.jsp");
				}
				i++;
			}	

		}

	}
}
