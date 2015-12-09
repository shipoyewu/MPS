package com.zzu.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.zzu.daoImp.MessageDaoImp;
import com.zzu.daoImp.VoteDaoImp;
import com.zzu.modle.Message;
import com.zzu.modle.Vote;

@WebServlet(name="MessageInfo",urlPatterns="/MessageInfo")
public class MessageInfo extends HttpServlet {

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
		PrintWriter out = response.getWriter();
		pro(request, response);
	}
	public void pro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		MessageDaoImp MD = new MessageDaoImp();
		
		VoteDaoImp VD = new VoteDaoImp();
		HttpSession ses = request.getSession();
		ses.setAttribute("receiveid",request.getParameter("receive"));
		Message me =MD.getMessage(Long.parseLong(request.getParameter("messageid")));
		Vote v = VD.getVoteByMessage(me.getMessageid());
		ses.removeAttribute("messageid");
		ses.setAttribute("message",me);
		if(v!=null){
			ses.setAttribute("vote",v);
		}
		response.sendRedirect("jsp/MessageInfo.jsp");
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
		PrintWriter out = response.getWriter();
		pro(request,response);
	}

}
