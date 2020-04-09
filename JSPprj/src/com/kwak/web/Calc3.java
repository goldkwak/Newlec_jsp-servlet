package com.kwak.web;

import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calc3")
public class Calc3 extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		
		//출력할 일 없으니 밑은 불필요하다.
		//response.setCharacterEncoding("utf-8");
		//response.setContentType("text/html; charset=utf-8");
		
		String value = request.getParameter("value");
		String operator = request.getParameter("operator");
		String dot = request.getParameter("dot");
	
		String exp = ""; //읽어온게 있으면 읽어오는 값을 하고 없으면 초기값을 사용한다.
		if(cookies != null) { //쿠키가 값이 비어있지 않다면 입력한 값이 exp에 입력된다.
			for(Cookie c : cookies) {
				if(c.getName().equals("exp")){ 
					exp = c.getValue();
					break;
				}
			}
		}
		
		if(operator != null && operator.equals("=")) {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn"); //nashorn 틀리지말자
			try {
				exp = String.valueOf(engine.eval(exp)); //입력된 값들을 계산해서 exp에 저장 
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(operator != null && operator.equals("C")) {
			exp = ""; //값을 화면에서 지우는 방법
			
		} else {
			exp += (value == null)? "":value;
			exp += (operator == null)? "":operator;
			exp += (dot == null)? "":dot;
		}
		
		Cookie expCookie = new Cookie("exp",exp);
		if(operator != null && operator.equals("C")) { //초기화
			expCookie.setMaxAge(0);
		}
		response.addCookie(expCookie);
		response.sendRedirect("calcpage"); //매핑된 이름을 쓴다.
			
	}	
		

}
