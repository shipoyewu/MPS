package com.zzu.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzu.daoImp.GroupDaoImp;
import com.zzu.daoImp.RelationDaoImp;
@WebServlet(name = "Delete", urlPatterns = "/Delete")
public class Delete extends HttpServlet {

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
		request.setCharacterEncoding("utf-8");
		doPost(request,response);
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
		request.setCharacterEncoding("utf-8");
		RelationDaoImp r=new RelationDaoImp();
		String upid=request.getParameter("upid");
		long groupupid=Long.parseLong(upid);
		System.out.println(groupupid);
		String[] check=request.getParameterValues("groupdownid");		
		//System.out.println(check.length);		
	    for(int j=0;j<check.length;j++)
	    {
	    	long gid=Long.parseLong(check[j]);
	    	//System.out.println(gid);
			r.delRelation(groupupid, gid);
	    }
	    response.sendRedirect("jsp/Group.jsp");
	}

}
