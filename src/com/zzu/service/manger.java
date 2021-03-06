package com.zzu.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.spec.ECPrivateKeySpec;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zzu.dao.UserDao;
import com.zzu.daoImp.UserDaoImp;
import com.zzu.modle.User;
import com.zzu.util.DBtools;
import com.zzu.util.baseTools;
@WebServlet(name="manger",urlPatterns={"/manger"})
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

		response.setContentType("text/html;charset=utf-8");
		//PrintWriter out = response.getWriter();
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
	    PrintWriter out=response.getWriter();
		try{
			HttpSession sess=request.getSession();
			long userid=Long.parseLong((String)sess.getAttribute("userid"));
			if(userid == 0  )  System.out.print("\n未能查找到该用户的个人信息");
			User user = new UserDaoImp().getUser(userid);
		    user.setUsername(request.getParameter("username"));
		    user.setPassword(request.getParameter("password"));
		    String birthday =request.getParameter("birthday");
		    user.setBirthday(baseTools.str2Date(birthday));//时间的插入错误 插入不进去格式不对  //已解决
		    user.setTel(request.getParameter("tel"));
		    String email=request.getParameter("email");
		    user.setEmail(email);
		    //user.setPicture(null);               //图片上传问题
		    boolean temp=new UserDaoImp().updateUser(user);
		    if(temp){
		    request.getRequestDispatcher("jsp/mySystem.jsp").forward(request, response);
		    System.out.println("liushuo");
		    }
		    else out.println("修改失败");
		    System.out.println("liushuo");
		}catch(Exception e){
			//System.out.println("修改失败！");
			out.println("修改失败!");
			e.printStackTrace();
		}
		
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

		response.setContentType("text/html;charset=utf-8");
		//PrintWriter out = response.getWriter();
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
