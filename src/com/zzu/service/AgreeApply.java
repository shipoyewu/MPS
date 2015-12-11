package com.zzu.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzu.daoImp.ApplyDaoImp;
import com.zzu.daoImp.RelationDaoImp;
import com.zzu.modle.Relation;

@WebServlet(name="AgreeApply",urlPatterns="/AgreeApply")
public class AgreeApply extends HttpServlet {

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
		long groupup=Long.parseLong(request.getParameter("groupup"));
		long groupdown=Long.parseLong(request.getParameter("groupdown"));	
		boolean flag=Boolean.getBoolean((String) request.getAttribute("flag"));
		//long groupup=32;
		//long groupdown=1;
		//boolean flag=true;
		ApplyDaoImp ad=new ApplyDaoImp();
		RelationDaoImp rd=new RelationDaoImp();
		Relation r=new Relation();
		System.out.println(groupup);
		System.out.println(groupdown);
		System.out.println(flag);
		if(flag)
		{
			r.setUp(groupup);
			r.setDown(groupdown);
			r.setJointime(new java.util.Date());
			r.setIsvalid(true);
			r.setDeletetime(null);
			rd.addRelation(r);
			ad.delete(groupup,groupdown);
			
		}else{
		    ad.delete(groupup,groupdown);
		}
				
		
	}

}
