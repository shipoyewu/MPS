package com.zzu.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzu.daoImp.GroupDaoImp;
/**
 * 
 * @author xingjiali
 *
 */
@WebServlet(name = "DeleteGroup", urlPatterns = "/DeleteGroup")
public class DeleteGroup extends HttpServlet {
	

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
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
		GroupDaoImp g=new GroupDaoImp();
		String[] check=request.getParameterValues("groupid");		
		System.out.println(check.length);		
	    for(int j=0;j<check.length;j++)
	    {
	    	long gid=Long.parseLong(check[j]);
	    	System.out.println(gid);
			g.deleteGroup(gid);
	    }
	    response.sendRedirect("jsp/DeleteSucceed.jsp");
	}

}
