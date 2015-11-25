package com.zzu.service;

import java.io.IOException;


import com.zzu.modle.User;
import com.zzu.dao.UserDao;
import com.zzu.daoImp.UserDaoImp;

import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class register extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public register() {
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
            processRequest(request, response);
		
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
		    processRequest(request, response);
		
	}

	private void processRequest(HttpServletRequest request,HttpServletResponse response) 
			   throws ServletException, IOException {
		// TODO Auto-generated method stub
				response.setContentType("text/html;charset=utf-8");
				request.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				try{
					User user = new User();
					long userid=Long.parseLong(request.getParameter("uesrid"));
					if(!((UserDao) user).isUser( userid))
				           user.setUserid( userid);//类型不一样
				    user.setUsername(request.getParameter("username"));
				    user.setPassword(request.getParameter("password"));
				    Object birthday =request.getAttribute("birthday");
				    user.setBirthday((Date)birthday);//同userid
				    user.setTel(request.getParameter("tel"));
				    String email=request.getParameter("email");
				    if(((UserDao) user).isUser(email))
				          user.setEmail(email);
				    Date time = new Date(System.currentTimeMillis());
				    user.setRegistertime(time);
				    user.setPicture(null);//图片上传问题
				   new UserDaoImp().addUser(user);
				}catch(Exception e){
					System.out.print("注册失败");
					e.printStackTrace();
				}
				RequestDispatcher dispatcher = request.getRequestDispatcher("mySystem.jsp");
			    dispatcher.include(request, response);
				
	}

	/**
	 * Returns information about the servlet, such as 
	 * author, version, and copyright. 
	 *
	 * @return String information about this servlet
	 */
	

}
