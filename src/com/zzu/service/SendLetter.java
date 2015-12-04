package com.zzu.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzu.daoImp.LetterDaoImp;
import com.zzu.modle.Letter;


@WebServlet(name="SendLetter",loadOnStartup=1,urlPatterns={"/SendLetter"})
public class SendLetter extends HttpServlet {

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
		doPost(request, response);
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
		PrintWriter out = response.getWriter();
		Letter let = new Letter();
		let.setSenderuserid(Long.parseLong(request.getParameter("sender")));
		let.setReceiveuserid(Long.parseLong(request.getParameter("recevier")));
		let.setCreatetime(new Date());
		let.setLettercontent(request.getParameter("content"));
		let.setStatus(true);
		
		LetterDaoImp LD = new LetterDaoImp();
		LD.addLetter(let);
		System.out.println("sendletter");
		response.setContentType("application/json; charset=utf-8");
		out.print("{\"success\":true,\"msg\":false}");
	}

}
