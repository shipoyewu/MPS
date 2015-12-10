package com.zzu.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzu.daoImp.GroupDaoImp;
import com.zzu.modle.Group;
@WebServlet(name = "ModifyGroup", urlPatterns = "/ModifyGroup")
public class ModifyGroup extends HttpServlet {

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
		long userid=Long.parseLong(request.getParameter("userid"));
		long groupid=Long.parseLong(request.getParameter("groupid"));
		String groupname=request.getParameter("groupname");
		boolean isneedagree=Boolean.parseBoolean(request.getParameter("isneedagree"));
		Group g=new Group();
		GroupDaoImp gdi=new GroupDaoImp();
		Group go=gdi.getGroup(groupid);
		g.setGroupid(groupid);
		g.setGroupname(groupname);
		g.setCreatetime(go.getCreatetime());
		g.setIsneedagree(isneedagree);
		g.setIsvalue(true);
		g.setUserid(userid);
		gdi.updateGroup(g);
		//System.out.println(g.getGroupid());
		//System.out.println(g.getGroupname());
		//System.out.println(g.getUserid());
		//System.out.println(g.getCreatetime());
		//System.out.println(g.isIsneedagree());
		//System.out.println(g.isIsvalue());
		response.sendRedirect("jsp/Group.jsp");		
	}

}
