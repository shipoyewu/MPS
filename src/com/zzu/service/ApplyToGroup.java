package com.zzu.service;

import java.io.IOException;
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
/**
 * 
 * @author xingjiali
 *
 */
@WebServlet(name = "ApplyToGroup", urlPatterns = "/ApplyToGroup")
public class ApplyToGroup extends HttpServlet {

	
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
		Apply ap = new Apply();
		
		ap.setGroupup(Long.parseLong(request.getParameter("groupupid")));
		System.out.println(ap.getGroupup());
		ap.setGroupdown(Long.parseLong(request.getParameter("groupdown")));
		ap.setApplytime(new java.util.Date());
		ap.setType(true);
		ap.setApplycontent(request.getParameter("applycontent"));
		System.out.println("up"+ap.getGroupup());
		System.out.println("down"+ap.getGroupdown());
		System.out.println("time"+ap.getApplytime());
		System.out.println("apply"+ap.getApplycontent());
		ApplyDaoImp AD = new ApplyDaoImp();
		GroupDaoImp GD = new GroupDaoImp();
		Group g = GD.getGroup(ap.getGroupup());
		RelationDaoImp RD = new RelationDaoImp();
		if(g!=null){
			if(g.isIsneedagree()){
				AD.addApply(ap);
				response.sendRedirect("jsp/ApplyWait.jsp");
			}
			else{
				Relation R = new Relation();
				R.setUp(ap.getGroupup());
				R.setDown(ap.getGroupdown());
				R.setIsvalid(true);
				R.setJointime(new java.util.Date());
				if(!RD.addRelation(R)){
					response.sendRedirect("jsp/ApplyError.jsp");
				}
				else{
					response.sendRedirect("jsp/index.jsp");
				}
			}
		}else{
			response.sendRedirect("jsp/ApplyError.jsp");
		}

	}

}
