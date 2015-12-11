package com.zzu.service;

import java.io.IOException;









import com.zzu.modle.User;
import com.zzu.util.Icon;
import com.zzu.util.UpLoadPicture;
import com.zzu.util.baseTools;
import com.zzu.dao.UserDao;
import com.zzu.daoImp.UserDaoImp;

import java.io.PrintWriter;
import java.nio.channels.SeekableByteChannel;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name="register",urlPatterns={"/register"})
public class register extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public register() {
		super();
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
            processRequest(request, response);
		
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
		    processRequest(request, response);
		
	}

	private void processRequest(HttpServletRequest request,HttpServletResponse response) 
			   throws ServletException, IOException {
		// TODO Auto-generated method stub
				response.setContentType("text/html;charset=utf-8");
				request.setCharacterEncoding("utf-8");
				PrintWriter out = response.getWriter();
				try{
					User user = new User();
					/*long userid=Long.parseLong(request.getParameter("uesrid"));
					if(new UserDaoImp().isUser( userid))
				           user.setUserid( userid);//类型不一样
				           */
	     		    user.setUsername(request.getParameter("username"));
	     		    //System.out.println("shihu");
	     		    //System.out.println((String)request.getParameter("username"));
				    user.setPassword(request.getParameter("password"));
				    System.out.println(request.getParameter("birthday"));
				    System.out.println("liushuo");
				    String birthday=request.getParameter("birthday");
				    if(birthday == ""){
				    	user.setBirthday(null);
				    }
				    else{
				    	user.setBirthday(baseTools.str2Date(birthday));
				    }
				    user.setTel(request.getParameter("tel"));
				    String email=request.getParameter("email");
				    if(!(new UserDaoImp().isUser(email)))
				          user.setEmail(email);
				    Date time = new Date(System.currentTimeMillis());
				    user.setRegistertime(time);
				    //user.uploadIcon(userid,requset);
				   long newid=new UserDaoImp().addUser(user);
				   System.out.println(newid);
				   if(newid != -1){
					   HttpSession sess=request.getSession();
					   sess.setAttribute("userid",Long.toString(newid)); 
					   sess.setMaxInactiveInterval(60*60*24*3);

					   String dirName=request.getServletContext().getRealPath("/")+"userdata/";
					   System.out.println(dirName);
					   //String dirName="D:/刘硕/java/myeclipse/.metadata/.me_tcat7/webapps/MPS/userdata/";
					   String file=dirName+Long.toString(newid);
				       boolean bl=Icon.createDir(file);  
					   if(bl){
						   Icon.copyFile(dirName+"/pic/icon.jpg",file+"/icon.jpg" );
						   
					   }
				       System.out.println("shihu\n");
				      response.sendRedirect("jsp/index.jsp");
				   }
				}catch(Exception e){
					System.out.print("注册失败");
					e.printStackTrace();
				}
				
	}

	/**
	 * Returns information about the servlet, such as 
	 * author, version, and copyright. 
	 *
	 * @return String information about this servlet
	 */
	

}
