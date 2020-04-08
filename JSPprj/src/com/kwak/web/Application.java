package com.kwak.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/application")
public class Application extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = request.getServletContext();
		PrintWriter out = response.getWriter();
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		
		int v = 0;
		if(!v_.equals("")) {
			v = Integer.parseInt(v_);
		}
		
		int y = v;
		
		if(op.equals("=")) {
			int x = (Integer)application.getAttribute("value");
			
			int result = x + y;
			out.print(result);
		} else {
			application.setAttribute("value", v);
			application.setAttribute("op", op);
		}
		
	}
}
