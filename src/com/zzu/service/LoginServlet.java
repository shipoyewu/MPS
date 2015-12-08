package com.zzu.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zzu.dao.UserDao;
import com.zzu.daoImp.UserDaoImp;

@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {

	/**
	 * 继承了一个实现过序列化接口的类，要求声明一个
	 */
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String userInfo = request.getParameter("useridoreamil");// 得到登录用户信息
		String psw = request.getParameter("password");// 得到登录密码
		String rem = request.getParameter("remember");// 是否记住密码

		UserDao user = new UserDaoImp();
		int flag = 0;//0则是登录失败,如果为1，则是邮箱登录，2则是Id登录
		if (userInfo.indexOf("@") != -1) {// 用户邮箱登录
			if (user.confUser(userInfo, psw)) {
				flag = 1;
			}
		} else {// 用户号登录
			boolean boo = userInfo.matches("[0-9]+");// 判断是否全是数字
			if (boo) {
				Long userId = Long.parseLong(userInfo);// 转换成Long类型
				if (user.confUser(userId, psw)) {
					flag = 2;
				}
			}
		}

		if (flag == 1||flag==2) {// 用户和密码输入正确,转到个人主页
			if (rem != null && rem.equals("remember")) {// 如果记住密码就设置cookie记录用户名和密码
				Cookie userNameCookie = new Cookie("userInfo", userInfo);//
				Cookie passwordCookie = new Cookie("password", psw);
				userNameCookie.setMaxAge(60 * 60 * 24 * 7);// 生存周期为7天
				passwordCookie.setMaxAge(60 * 60 * 24 * 7);
				response.addCookie(userNameCookie);
				response.addCookie(passwordCookie);
			}
			String userid="";
			if(flag==1){
				userid=""+new UserDaoImp().getId(userInfo, "email");
			}else{
				userid=""+userInfo;
			}
			HttpSession sess=request.getSession();
			sess.setAttribute("userid",userid);
			response.sendRedirect("jsp/index.jsp");
		} 
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}