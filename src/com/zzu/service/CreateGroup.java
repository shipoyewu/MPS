package com.zzu.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzu.daoImp.GroupDaoImp;
import com.zzu.modle.Group;
@WebServlet(name = "CreateGroup", urlPatterns = "/CreateGroup")
public class CreateGroup extends HttpServlet {

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
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		Group g=new Group();
		GroupDaoImp group= new GroupDaoImp();
		String groupname=request.getParameter("groupname");
		Boolean isneedagree=Boolean.parseBoolean( request.getParameter("isneedagree"));
		long userid=Long.parseLong(request.getParameter("userid"));
		g.setGroupname(groupname);
		g.setCreatetime(new java.util.Date());
		g.setIsneedagree(isneedagree);
		g.setIsvalue(true);
		g.setUserid(userid);
		long  gid=group.addGroup(g);
		System.out.println(gid);
		if(gid!=0)
		{
			response.sendRedirect("jsp/CreateSucceed.jsp");
		}
		else
		{
			response.sendRedirect("jsp/CreateFalse.jsp");
		}
		
	}

	
}
