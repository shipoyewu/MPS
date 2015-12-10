package com.zzu.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.zzu.daoImp.GroupDaoImp;
import com.zzu.daoImp.RelationDaoImp;
import com.zzu.daoImp.UserDaoImp;
import com.zzu.util.JsonRelation;

@WebServlet(name="Show",loadOnStartup=1,urlPatterns={"/getUserTree"})
public class getUserTree extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public getUserTree() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

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
		response.setContentType("test/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		
		RelationDaoImp rdi = new RelationDaoImp();
		UserDaoImp udi = new UserDaoImp();
	
		/*if(it.hasNext()){
			response.getWriter().print(JSONArray.fromObject(rdi.getJsonRela(it.next())).toString());
		}*/
		//利用Json插件将Array转换成Json格式
		ArrayList<Long> gList = udi.findGroup(1);
		ArrayList<JsonRelation> JList = new ArrayList<JsonRelation>();
		for(int i = 0;i < gList.size(); i++){
			JList.addAll(rdi.getJsonRela(gList.get(i)));
		}
		Gson gson = new Gson();
		String str = gson.toJson(JList);
		response.getWriter().print(str);
		System.out.println(str);
		response.flushBuffer();
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
		  doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
