package com.zzu.service;

import java.awt.List;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
@WebServlet(name="ShowIcon",urlPatterns={"/Icontest"})
public class ShowIcon extends HttpServlet {

	File tmpDir = null;//初始化上传文件的临时存放目录
	File saveDir = null;//初始化上传文件后的保存目录
	private static final long serialVersionUID = 1L;
	private ServletContext sc = null;
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
	
@SuppressWarnings("deprecation")
public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter pout = response.getWriter();
		
	try{
		String pp = null;
		String upto = null;
		
		if(ServletFileUpload.isMultipartContent(request)){
		DiskFileItemFactory dff = new DiskFileItemFactory();//创建该对象
		dff.setRepository(tmpDir);//指定上传文件的临时目录
		dff.setSizeThreshold(10240000);//指定在内存中缓存数据大小,单位为byte
		ServletFileUpload sfu = new ServletFileUpload(dff);//创建该对象
		sfu.setFileSizeMax(50000000);//指定单个上传文件的最大尺寸
		sfu.setSizeMax(10000000);//指定一次上传多个文件的总尺寸
		FileItemIterator fii = sfu.getItemIterator(request);//解析request 请求,并返回FileItemIterator集合
		while(fii.hasNext()){
		FileItemStream fis = fii.next();//从集合中获得一个文件流
		if(!fis.isFormField() && fis.getName().length()>0){//过滤掉表单中非文件域
		/**FileItemStream.getName();
		 * Returns the original filename in the client's filesystem, 
			as provided by the browser (or other client software). In most cases, 
			this will be the base file name, without path information. However, some clients,
			such as the Opera browser, do include path information.
		 */
		//String fileName = fis.getName().substring(fis.getName().lastIndexOf("\\"));//获得上传文件的文件名
		String fileName = fis.getName();
		pout.println(fileName);
		sc = request.getServletContext();
		//System.out.println("request.getRealPath()=="+sc.getRealPath("/"));
		String uploadPath = sc.getRealPath("/")+"dataimages\\";//选定上传的目录,此处为当前目录 MPS\
		
		pout.println(uploadPath);
		if(!new File(uploadPath).isDirectory())//选定上传的目录,此处为当前目录，没有则创建
		    new File(uploadPath).mkdirs();
		pout.println("uploadPath="+uploadPath);
		fileName=fileName.substring(fileName.lastIndexOf("."));//获取从.之后的字符，即后缀
		
		//将时间转化为字符串用于给文件或者文件夹改名，防止传上来的图片名称相同
		Date time=new Date();
		String dirTime=String.valueOf(time.getTime());
		
		BufferedInputStream in = new BufferedInputStream(fis.openStream());//获得文件输入流
		
		//BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(saveDir+"\\"+dirTime+fileName)));//获得文件输出流
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(uploadPath+"\\"+"icon"+fileName)));//获得文件输出流
		//pp为已经上传的文件
		 pp=uploadPath+dirTime+fileName;
		 System.out.println("pp="+pp);
		 //upto为上传的路径
		 upto=uploadPath;
		 
		Streams.copy(in, out, true);//开始把文件写到你指定的上传文件夹
		}

		}
		/*//定义解压字符串，用于解压上传的rar文件，注意此处需要一个winrar.exe文件
		String jieya=request.getRealPath("/")+ "WinRAR.exe x -t -o+ -p- \""+pp+"\" \""+upto+"\"";
		//String jieya="D:\\Tomcat 5.5\\webapps\\fileupload\\WinRAR.exe x -t -o+ -p- \""+pp+"\" \""+upto+"\"";
		Process p=Runtime.getRuntime().exec(jieya);//将传输的rar文件解压*/
		
		response.getWriter().println("File upload successfully!!!");//终于成功了,还不到你的上传文件中看看,你要的东西都到齐了吗
		}
		}catch(Exception e){
		e.printStackTrace();
		}
		
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
