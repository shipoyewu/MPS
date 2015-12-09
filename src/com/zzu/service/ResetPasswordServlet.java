package com.zzu.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zzu.dao.UserDao;
import com.zzu.daoImp.UserDaoImp;
import com.zzu.modle.User;

/**
 * Servlet implementation class ResetPasswordServlet
 */
@WebServlet(name = "/ResetPasswordServlet", urlPatterns = "/jsp/ResetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ResetPasswordServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String psw1 = request.getParameter("resetPassword1");
		String psw2 = request.getParameter("resetPassword2");
		HttpSession ses = request.getSession();
		String mail = (String) ses.getAttribute("email");
		if (!psw1.equals(psw2) || mail == null) {
			response.sendRedirect(request.getContextPath() + "/jsp/error.jsp");
			return;
		} else {
			try {
				UserDao userInfo = new UserDaoImp();
				User user = userInfo.getUser(mail);// 通过邮箱返回用户信
				user.setPassword(psw1);// 修改密码
				userInfo.updateUser(user);// 更新密码
			} catch (Exception e) {
				e.printStackTrace();
				response.sendRedirect(request.getContextPath()
						+ "/jsp/error.jsp");
				return;
			}
		}
		response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");// 密码修改成功,转到登录页面
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
