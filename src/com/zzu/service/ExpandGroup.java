package com.zzu.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzu.daoImp.ApplyDaoImp;
import com.zzu.daoImp.GroupDaoImp;
import com.zzu.daoImp.RelationDaoImp;
import com.zzu.modle.Apply;
import com.zzu.modle.Group;
import com.zzu.modle.Relation;
import com.zzu.util.baseTools;

@WebServlet(name = "ExpandGroup", urlPatterns = "/ExpandGroup")
public class ExpandGroup extends HttpServlet {

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
        Apply ap = new Apply();		
		ap.setGroupup(Long.parseLong((String)request.getParameter("groupup")));
		System.out.println(ap.getGroupup());
		ap.setGroupdown(Long.parseLong((String)request.getParameter("groupdownid")));
		ap.setApplytime(new java.util.Date());
		ap.setType(false);																																																																																																																																																																																														
		ap.setApplycontent((String)request.getParameter("applycontent"));
		System.out.println("up"+ap.getGroupup());
		System.out.println("down"+ap.getGroupdown());
		System.out.println("time"+ap.getApplytime());
		System.out.println("apply"+ap.getApplycontent());
		ApplyDaoImp AD = new ApplyDaoImp();
		GroupDaoImp GD = new GroupDaoImp();
		Group g = GD.getGroup(ap.getGroupdown());
		RelationDaoImp RD = new RelationDaoImp();
		if(g!=null){
			if(g.isIsneedagree()){
				if(AD.addApply(ap)){
					response.sendRedirect("jsp/ApplyWait.jsp");
				}
				else{
					response.sendRedirect("jsp/ExpandError.jsp");
				}
			}
			else{
				Relation R = new Relation();
				R.setUp(ap.getGroupup());
				R.setDown(ap.getGroupdown());
				R.setIsvalid(true);
				R.setJointime(new java.util.Date());
				if(!RD.addRelation(R)){
					response.sendRedirect("jsp/ExpandError.jsp");
				}
				else{
					response.sendRedirect("jsp/ApplyWait.jsp");
				}
			}
		}else{
			response.sendRedirect("jsp/ExpandError2.jsp");
		}

	}


}
