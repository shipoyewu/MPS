package com.zzu.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
/**
 * 消息附件上传
 * 路径：/userdata/[用户id]/[消息id]file[i].[后缀]
 * @author zongzan
 *
 */
public class UpLoad {
	File tmpDir = null;//初始化上传文件的临时存放目录
	File saveDir = null;//初始化上传文件后的保存目录
	private static final long serialVersionUID = 1L;
	protected ServletContext sc = null;
	public UpLoad() {
		// TODO Auto-generated constructor stub
	}
	public boolean UpLoadFile(Long userid, Long messageid, HttpServletRequest request){
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
			int i = 0;//记录文件个数，修改文件名
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
			sc = request.getServletContext();
			//System.out.println("request.getRealPath()=="+sc.getRealPath("/"));
			String uploadPath = sc.getRealPath("/")+"userdata"+"\\"+userid.toString();//选定上传的目录,此处为当前目录 MPS\
			if(!new File(uploadPath).isDirectory())//选定上传的目录,此处为当前目录，没有则创建
			    new File(uploadPath).mkdirs();
			
			fileName=fileName.substring(fileName.lastIndexOf("."));//获取从.之后的字符，即后缀
			//将时间转化为字符串用于给文件或者文件夹改名，防止传上来的图片名称相同
			//Date time=new Date();
			//String dirTime=String.valueOf(time.getTime());
			BufferedInputStream in = new BufferedInputStream(fis.openStream());//获得文件输入流
			//BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(saveDir+"\\"+dirTime+fileName)));//获得文件输出流
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(uploadPath+"\\"
									   +messageid+"file"+i++ +fileName)));//获得文件输出流
			//pp为已经上传的文件
			 pp=uploadPath+messageid+"file"+i++ +fileName;
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
			
			System.out.println("File upload successfully!");
			}
			}catch(Exception e){
			e.printStackTrace();
			return false;
			}
			return true;
	}
	

}
