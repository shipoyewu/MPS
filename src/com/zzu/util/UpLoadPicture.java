package com.zzu.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
/**
 * 上传消息图片
 * 保存路径：/userdata/[用户id]/[唯一标识符].[后缀]
 * 上传头像
 * 保存路径： /userdata/[用户id]/icon.[后缀]
 * @author zongzan
 *
 */
public class UpLoadPicture extends UpLoad{

	public UpLoadPicture() {
		// TODO Auto-generated constructor stub
	}
	public String UpLoadIcon(long userid, HttpServletRequest request){
		String pp = null;
	try{
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
				
					String fileName = fis.getName();
					sc = request.getServletContext();
					String uploadPath = sc.getRealPath("/")+"userdata\\"+userid;//选定上传的目录,此处为当前目录 MPS\
					if(!new File(uploadPath).isDirectory())//选定上传的目录,此处为当前目录，没有则创建
						new File(uploadPath).mkdirs();
					fileName=fileName.substring(fileName.lastIndexOf("."));//获取从.之后的字符，即后缀
					//将时间转化为字符串用于给文件或者文件夹改名，防止传上来的图片名称相同
					BufferedInputStream in = new BufferedInputStream(fis.openStream());//获得文件输入流
					BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(uploadPath+"\\icon"+fileName)));//获得文件输出流	
					//pp为已经上传的文件
					 pp=uploadPath+"\\icon"+fileName;
					 pp = "\\MPS"+pp.split("MPS")[1];
					 Streams.copy(in, out, true);//开始把文件写到你指定的上传文件夹
				  }
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("UpLoad error!");
			}
		return pp;
	}
	public String UpLoadMsgPic(long userid, HttpServletRequest request){
		String pp = null;
		try{
		if(ServletFileUpload.isMultipartContent(request)){
			DiskFileItemFactory dff = new DiskFileItemFactory();//创建该对象
			dff.setRepository(tmpDir);//指定上传文件的临时目录
			dff.setSizeThreshold(10240000);//指定在内存中缓存数据大小,单位为byte
			ServletFileUpload sfu = new ServletFileUpload(dff);//创建该对象
			sfu.setFileSizeMax(50000000);//指定单个上传文件的最大尺寸
			sfu.setSizeMax(10000000);//指定一次上传多个文件的总尺寸
			FileItemIterator fii = sfu.getItemIterator(request);//解析request 请求,并返回FileItemIterator集合
			int i = 0;//多张图片同时时用来区分图片名
			while(fii.hasNext()){
				FileItemStream fis = fii.next();//从集合中获得一个文件流
				if(!fis.isFormField() && fis.getName().length()>0){//过滤掉表单中非文件域
		
					String fileName = fis.getName();
					sc = request.getServletContext();
					String uploadPath = sc.getRealPath("/")+"userdata\\"+userid;//选定上传的目录,此处为当前目录 MPS\
					if(!new File(uploadPath).isDirectory())//选定上传的目录,此处为当前目录，没有则创建
						new File(uploadPath).mkdirs();
		
					fileName=fileName.substring(fileName.lastIndexOf("."));//获取从.之后的字符，即后缀
					//将时间转化为字符串用于给文件或者文件夹改名，防止传上来的图片名称相同
					Date time=new Date();
					String dirTime=String.valueOf(time.getTime());
					BufferedInputStream in = new BufferedInputStream(fis.openStream());//获得文件输入流
					BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(uploadPath+"\\"
							+dirTime+fileName)));//获得文件输出流
					//pp为已经上传的文件
					 pp=uploadPath+"\\"+dirTime+fileName;
					 pp = "\\MPS"+pp.split("MPS")[1];
					System.out.println("pp="+pp);
					Streams.copy(in, out, true);//开始把文件写到你指定的上传文件夹
					}
				}
			}
		}catch(Exception e){
		e.printStackTrace();
		System.out.println("UpLoad error!");
		}
		return pp;
	
	}
	
}