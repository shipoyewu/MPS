package com.zzu.service;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
@WebServlet(name="ShowIcon",urlPatterns={"/jsp/Icontest"})
public class ShowIcon extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor of the object.
	 */
	public ShowIcon() {
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
		ServletContext sc = null;
		String savePath = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("show icon!");
		DiskFileItemFactory factory = new DiskFileItemFactory();//上传文件
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
		List items = (List) upload.parseRequest(request);//返回含有多个文件的List
		Iterator itr = ((java.util.List<FileItem>) items).iterator();
		while (itr.hasNext()) {
		FileItem item = (FileItem) itr.next();
		if (item.isFormField()) {//判断该表单项是否是普通类型
		System.out.println("表单参数名:" + item.getFieldName() + "，表单参数值:" + item.getString("UTF-8"));
		} else {//非普通类型，即file类型.
		if (item.getName() != null && !item.getName().equals("")) {
		System.out.println("上传文件的大小:" + item.getSize());
		System.out.println("上传文件的类型:" + item.getContentType());
		System.out.println("上传文件的名称:" + item.getName());
		File tempFile = new File(item.getName());// item.getName()返回上传文件在客户端的完整路径名称
	           //上传文件的保存路径
		File file = new File(sc.getRealPath("/") + savePath, tempFile.getName());
		item.write(file);
		request.setAttribute("upload.message", "上传文件成功！");
		}else{
		request.setAttribute("upload.message", "没有选择上传文件！");
		}
		}
		}
		}catch(FileUploadException e){
		e.printStackTrace();
		} catch (Exception e) {
		e.printStackTrace();
		request.setAttribute("upload.message", "上传文件失败！");
		}
		request.getRequestDispatcher("/uploadResult.jsp").forward(request, response);
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

			doGet(request,response);
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
