package com.zzu.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import com.zzu.util.UpLoadPicture;
@WebServlet(name="uploadIcon",urlPatterns={"/uploadIcon"})
public class uploadIcon extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public uploadIcon() {
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

		response.setContentType("text/html;charset=utf-8");
		//PrintWriter out = response.getWriter();
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
	    request.setCharacterEncoding("UTF-8");
	    try{
	    	HttpSession sess =request.getSession();
	    	long userid=Long.parseLong((String)sess.getAttribute("userid"));
	    	System.out.print(sess.getAttribute("userid"));
	    	UpLoadPicture u=new UpLoadPicture();
	    	String temp = u.UpLoadIcon(userid,request);
	    	System.out .println(temp);
	    	request.getRequestDispatcher("jsp/manage.jsp").forward(request, response);
            System.out.println("liushuo@@@@");
	    
	    	
	    	System.out.println("上传成功");
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
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
