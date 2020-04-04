package com.kwak.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hi")
public class Nana extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		int cnt = Integer.parseInt(request.getParameter("cnt"));
		
		PrintWriter out = response.getWriter();
		
		for(int i = 0; i<cnt; i++) {
			out.println((i+1) + ": гоюл~ servlet<br>");
		}
		
	}
}
