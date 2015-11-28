package com.zzu.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.spec.ECPrivateKeySpec;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzu.dao.UserDao;
import com.zzu.daoImp.UserDaoImp;
import com.zzu.modle.User;

public class manger extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public manger() {
		super();
	}

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
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		response.setContentType("UTF-8");
	    request.setCharacterEncoding("UTF-8");
	   // PrintWriter out=response.getWriter();
		try{
			User user = new User();
		    user.setUsername(request.getParameter("username"));
		    user.setPassword(request.getParameter("password"));
		    Object birthday =request.getAttribute("birthday");
		    user.setBirthday((Date)birthday);
		    user.setTel(request.getParameter("tel"));
		    String email=request.getParameter("email");
		    if(((UserDao) user).isUser(email))
		          user.setEmail(email);
		    user.setPicture(null);               //图片上传问题
		    new UserDaoImp().updateUser(user);
		}catch(Exception e){
			System.out.print("修改失败！");
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("mySystem.jsp");
	    dispatcher.include(request, response);
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
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		processRequest(request, response);
	}

	/**
	 * Returns information about the servlet, such as 
	 * author, version, and copyright. 
	 *
	 * @return String information about this servlet
	 */
	public String getServletInfo() {
		return "This is my default servlet created by Eclipse";
	}

}
