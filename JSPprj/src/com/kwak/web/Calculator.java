package com.kwak.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")
public class Calculator extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		
		String exp = "0";
		if(cookies != null) { //��Ű�� ���� ������� �ʴٸ� �Է��� ���� exp�� �Էµȴ�.
			for(Cookie c : cookies) {
				if(c.getName().equals("exp")){ 
					exp = c.getValue();
					break;
				}
			}
		}
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.write("<!DOCTYPE html>");
		out.write("<html>");
		out.write("<head>");
		out.write("<meta charset=\"UTF-8\">");
		out.write("<title>Insert title here</title>");
		out.write("<style>");
		out.write("	input{");
		out.write("		width: 50px; height: 50px;");
		out.write("	}");
		out.write("	.output{");
		out.write("		height: 50px; background: #e9e9e9; font-size: 24px; font-weight: bold; text-align: right; padding: 0px 5px;"); 
		out.write("	}");
		out.write("</style>");
		out.write("</head>");
		out.write("<body>");
		out.write("	<form method=\"post\">");
		out.write("		<table>");
		out.write("			<tr>");
		out.printf("				<td class =\"output\" colspan=\"4\">%s</td>", exp);
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"CE\" /></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"C\" /></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"BS\" /></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"/\" /></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"7\" /></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"8\" /></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"9\" /></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"*\" /></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"4\" /></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"5\" /></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"6\" /></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"-\" /></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"1\" /></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"2\" /></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"3\" /></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"+\" /></td>");
		out.write("			</tr>");
		out.write("			<tr>");
		out.write("				<td></td>");
		out.write("				<td><input type=\"submit\" name=\"value\" value=\"0\" /></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" dot=\".\" /></td>");
		out.write("				<td><input type=\"submit\" name=\"operator\" value=\"=\" /></td>");
		out.write("			</tr>");
		out.write("		</table>");
		out.write("	</form>");
		out.write("</body>");
		out.write("</html>");
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
			
		//����� �� ������ ���� ���ʿ��ϴ�.
		//response.setCharacterEncoding("utf-8");
		//response.setContentType("text/html; charset=utf-8");
		
		String value = request.getParameter("value");
		String operator = request.getParameter("operator");
		String dot = request.getParameter("dot");
	
		String exp = ""; //�о�°� ������ �о���� ���� �ϰ� ������ �ʱⰪ�� ����Ѵ�.
		if(cookies != null) { //��Ű�� ���� ������� �ʴٸ� �Է��� ���� exp�� �Էµȴ�.
			for(Cookie c : cookies) {
				if(c.getName().equals("exp")){ 
					exp = c.getValue();
					break;
				}
			}
		}
		
		if(operator != null && operator.equals("=")) {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn"); //nashorn Ʋ��������
			try {
				exp = String.valueOf(engine.eval(exp)); //�Էµ� ������ ����ؼ� exp�� ���� 
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if(operator != null && operator.equals("C")) {
			exp = ""; //���� ȭ�鿡�� ����� ���
			
		} else {
			exp += (value == null)? "":value;
			exp += (operator == null)? "":operator;
			exp += (dot == null)? "":dot;
		}
		
		Cookie expCookie = new Cookie("exp",exp);
		if(operator != null && operator.equals("C")) { //�ʱ�ȭ
			expCookie.setMaxAge(0);
		}
		
		expCookie.setPath("/calculator"); //�ڱ� url�ܿ��� ��Ű�� ������ ���� �ʴ´�.
		response.addCookie(expCookie);
		//response.sendRedirect("calcpage"); //�̰� �ƴ�
		response.sendRedirect("calculator"); //�ڱ��ڽ�
	}
}
