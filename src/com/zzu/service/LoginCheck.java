package com.zzu.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zzu.daoImp.UserDaoImp;


@WebServlet(name="LoginCheck",urlPatterns="/LoginCheck")
public class LoginCheck extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the GET method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String info =request.getParameter("info");
		String pword = request.getParameter("pword");
		boolean cc = true; 
		System.out.println(info+pword);
		for(int i = 0;i < info.length();i++){
			if(!(info.charAt(i) >= '0' &&  info.charAt(i) <= '9')){
				cc = false;
				break;
			}
		}
		UserDaoImp UD = new UserDaoImp();
		
		int loc = info.lastIndexOf("@");
		boolean flag = false;
		if(loc == -1){
			if(cc)
				flag = UD.confUser(Long.parseLong(info),pword);
		}
		else{
			flag = UD.confUser(info,pword);
		}
		System.out.println("asdasd");
		String json = "{\"msg\":"+flag+"}";
		System.out.println("shihu:"+json);
		out.write(json);
		out.flush();
		out.close();  
	}

}
