package com.zzu.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzu.dao.UserDao;
import com.zzu.daoImp.UserDaoImp;
import com.zzu.modle.User;
import com.zzu.util.EmailUtil;

/**
 * 找回密码,给注册邮箱发送密码
 */
@WebServlet(name = "FindPasswordServlet", urlPatterns = "/FindPasswordServlet")
public class FindPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FindPasswordServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String email =(String) request.getParameter("findPassword");// 需要找回密码的邮箱
		try{
			EmailUtil.sendEmail(email);
		}catch(Exception e){
			e.printStackTrace();
		}
		response.sendRedirect("jsp/login.jsp");
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
