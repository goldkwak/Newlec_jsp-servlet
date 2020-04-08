package com.kwak.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/session")
public class Session extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		
		int v = 0;
		
		if(!v_.equals("")) {
			v = Integer.parseInt(v_);
		}
		
		if(op.equals("=")) {
			int x = (Integer)session.getAttribute("value");
			
			int y = v;
			int result = 0;
			String oper = (String)session.getAttribute("op");
			if(oper.equals("+")) {
				result = x + y;
			} else {
				result = x - y;
			}
			
			response.getWriter().printf("result is %d\n", result);
		}else {
			response.getWriter().println(v);
			session.setAttribute("value", v);
			session.setAttribute("op", op);
		}
		
		
		
	}
}
