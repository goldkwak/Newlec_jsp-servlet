package com.kwak.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calc2")
public class Calc2 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = request.getServletContext();
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");

		int v = 0;
		if(!v_.equals("")) {
			v = Integer.parseInt(v_);
		}
		
		int result = 0;
		//계산
		if(op.equals("=")) {
			//int x = (Integer)application.getAttribute("value");
			
			//int x = (Integer)session.getAttribute("value");
			int x = 0;
			for(Cookie c : cookies) {
				if(c.getName().equals("value")){ //내가 심은 value가 있는지 
					x = Integer.parseInt(c.getValue());
					break;
				}
			}
			
			
			
			int y = v;
			//String operator = (String)application.getAttribute("op");
			
			//String operator = (String)session.getAttribute("op");
			
			String operator = "";
			for(Cookie c : cookies) {
				if(c.getName().equals("op")){ //내가 심은 value가 있는지 
					operator = c.getValue();
					break;
				}
			}
			if(operator.equals("+")) {
				result = x + y;
			} else {
				result = x - y;
			}
			
			response.getWriter().printf("result is %d\n", result);
		//저장
		} else {
			//application.setAttribute("value", v);
			//application.setAttribute("op", op);
			
			//session.setAttribute("value", v);
			//session.setAttribute("op", op);
			
			//클라이언트로 보내지게 된다.
			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			Cookie opCookie = new Cookie("op", op);
			response.addCookie(valueCookie);
			response.addCookie(opCookie);
			
		}	
		
//		int x = Integer.parseInt(request.getParameter("x"));
//		int y = Integer.parseInt(request.getParameter("y"));
//		
//		PrintWriter out = response.getWriter();
//		
//		out.print(x + y);
	}
}
