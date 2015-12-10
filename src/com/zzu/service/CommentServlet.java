package com.zzu.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzu.daoImp.CommentDaoImp;
import com.zzu.daoImp.MessageDaoImp;
import com.zzu.daoImp.ReceiveDaoImp;
import com.zzu.modle.Comment;

@WebServlet(name = "CommentServlet", urlPatterns = "/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CommentServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String comcontent = request.getParameter("comcontent");
		String messageid = request.getParameter("messageid");
		if ((comcontent != null && comcontent != "")
				&& (messageid != null && messageid != "")) {
			long mesid = Long.parseLong(messageid);
			long groupid = new MessageDaoImp().getMessage(mesid).getGroupid();
			Comment com = new Comment();
			com.setComcontent(comcontent);
			com.setGroupid(groupid);
			com.setMessageid(mesid);
			new CommentDaoImp().addComment(com);
			// 通过messageid,groupid将消息置为已读isvalue=true
			new ReceiveDaoImp().Read(mesid, groupid);

		} else {
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">alert(\"评论失败!\");</script>");
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
