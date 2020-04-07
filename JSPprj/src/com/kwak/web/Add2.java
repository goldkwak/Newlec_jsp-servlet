package com.kwak.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class Add2 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String[] num_ = request.getParameterValues("num");
		
		int result = 0;
		
		for(int i = 0; i<num_.length; i++) {
			int num = Integer.parseInt(num_[i]);
			result += num;
		}
		
		response.getWriter().printf("result is %d\n", result);
		
//		int x = Integer.parseInt(request.getParameter("x"));
//		int y = Integer.parseInt(request.getParameter("y"));
//		
//		PrintWriter out = response.getWriter();
//		
//		out.print(x + y);
	}
}
