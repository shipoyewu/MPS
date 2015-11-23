package com.zzu.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zzu.dao.VoteDao;
import com.zzu.daoImp.ChoiceDaoImp;
import com.zzu.daoImp.VoteDaoImp;
import com.zzu.modle.Choice;
import com.zzu.modle.Vote;
import com.zzu.util.baseTools;



@WebServlet(name="addVote",urlPatterns={"/addVote"})
public class addVote extends HttpServlet {

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
	public void addV(HttpServletRequest request, HttpServletResponse response,long messageid)
			throws ServletException, IOException {
		Vote v = new Vote();
		
		v.setVotecontent((String)request.getAttribute("votetitle"));
		String ifmuil =(String) request.getAttribute("anon");
		if(ifmuil.equals("multi")){
			v.setIsmul(true);
		}
		else{
			v.setIsmul(false);
		}
		
		v.setIsvalue(true);
		
		String endStr =(String) request.getAttribute("enddate");
		Date endtime = new baseTools().str2Date(endStr);
		v.setEndtime(endtime);
		v.setMessageid(messageid);
		
		v.setStarttime(new Date(new java.util.Date().getTime()));
		
		VoteDaoImp VD = new VoteDaoImp();
		long vid = 0;
		
		//long vid = VD.addVote(v);
		
		
		String[] cho = request.getParameterValues("option");
		
		ChoiceDaoImp CD = new ChoiceDaoImp();
		
		for(int i = 0;i < cho.length;i++){
			Choice ch = new Choice();
			ch.setChocontent(cho[i]);
			ch.setVoteid(vid);
			CD.addChoice(ch);
		}
	
		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		addV(request,response,1);
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
		addV(request,response,1);
	}

}
