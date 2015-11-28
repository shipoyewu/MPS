package com.zzu.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzu.daoImp.RelationDaoImp;
import com.zzu.modle.Relation;
import com.zzu.util.baseTools;
/**
 * 
 * @author xingjiali
 *
 */

public class AddGroupToGroup extends HttpServlet {

	private void AddGroupToGroup(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		RelationDaoImp r=new RelationDaoImp();
		Relation relation=new Relation();
		long groupupid=((Long) request.getAttribute("groupupid"));
		long groupdownid=((Long) request.getAttribute("groupdownid"));
		String StartStr =(String) request.getAttribute("startdate");
		Date Starttime = new baseTools().str2Date(StartStr);
			relation.setUp(groupupid);
			relation.setDown(groupdownid);
			relation.setIsvalid(true);
			relation.setJointime(Starttime);
			r.addRelation(relation);			
		
		
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
		request.setCharacterEncoding("utf-8");
		AddGroupToGroup(request,response);
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
		AddGroupToGroup(request,response);
	}

}
