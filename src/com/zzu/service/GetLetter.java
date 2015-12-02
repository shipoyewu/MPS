package com.zzu.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.zzu.daoImp.LetterDaoImp;
import com.zzu.modle.Letter;

@WebServlet(name="GetLetter",loadOnStartup=1, urlPatterns="/GetLetter")
public class GetLetter extends HttpServlet {

	/**
	 * 
	 */

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
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		LetterDaoImp LD = new LetterDaoImp();
		ArrayList<Map<String, String>> list = new ArrayList<Map<String,String>>();
		ArrayList<Letter> unread = null;
		unread  = LD.getUnRead(Long.parseLong(request.getParameter("sender")),Long.parseLong( request.getParameter("recevier")));
		for(int i = 0;i < unread.size();i++){
			Map<String,String> ma = new HashMap<String, String>();
			ma.put("time",unread.get(i).getCreatetime().toString());
			ma.put("content",unread.get(i).getLettercontent());
			list.add(ma);
		}
		Gson gson = new Gson();
		String str = gson.toJson(list);
		System.out.println(str);
		out.print(str);
		
	}

}
