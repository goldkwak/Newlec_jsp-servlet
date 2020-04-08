package com.kwak.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookies")
public class Cookies extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String v_ = request.getParameter("v");
		String op = request.getParameter("operator");
		
		int v = 0;
		if(!v_.equals("")) {
			v = Integer.parseInt(v_);
		}
		System.out.println("v: " + v);
		System.out.println("op: " + op);
		
		int result = 0;
		
		if(op.equals("=")) {
			int x = 0;
			for(Cookie c :cookies) {
				if(c.getName().equals("value")) {
					x = Integer.parseInt(c.getValue());
					break;
				}
			}
			System.out.println("x: " + x);
			int y = v;
			String operator = "";
			for(Cookie c : cookies) {
				if(c.getName().equals("op")){
					operator = c.getValue();
					break;
				}
			}
			System.out.println("op: " + op);
			if(operator.equals("+")) {
				result = x + y;
			} else {
				result = x - y;
			}
			System.out.println("result: " + result);
			response.getWriter().printf("result is %d\n", result);
		} else {
			Cookie valueCookie = new Cookie("value", String.valueOf(v));
			Cookie opCookie = new Cookie("op", op);
			response.addCookie(valueCookie);
			response.addCookie(opCookie);
		}
	}
}
