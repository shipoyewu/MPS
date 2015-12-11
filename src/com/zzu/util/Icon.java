package com.zzu.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.ServletContext;
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








import java.io.File;  
import java.io.IOException;  
  
public  class Icon extends UpLoad{  
	public static void creatDir() {
		// TODO Auto-generated constructor stub
	}
     
   /* public static boolean createFile(String destFileName) {  
        File file = new File(destFileName);  
        if(file.exists()) {  
            System.out.println("创建单个文件" + destFileName + "失败，目标文件已存在！");  
            return false;  
        }  
        if (destFileName.endsWith(File.separator)) {  
            System.out.println("创建单个文件" + destFileName + "失败，目标文件不能为目录！");  
            return false;  
        }  
        //判断目标文件所在的目录是否存在  
        if(!file.getParentFile().exists()) {  
            //如果目标文件所在的目录不存在，则创建父目录  
            System.out.println("目标文件所在目录不存在，准备创建它！");  
            if(!file.getParentFile().mkdirs()) {  
                System.out.println("创建目标文件所在目录失败！");  
                return false;  
            }  
        }  
        //创建目标文件  
        try {  
            if (file.createNewFile()) {  
                System.out.println("创建单个文件" + destFileName + "成功！");  
                return true;  
            } else {  
                System.out.println("创建单个文件" + destFileName + "失败！");  
                return false;  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
            System.out.println("创建单个文件" + destFileName + "失败！" + e.getMessage());  
            return false;  
        }  
    }*/  
     
     
    public static boolean createDir(String destDirName) {  
        File dir = new File(destDirName);  
        if (dir.exists()) {  
            System.out.println("创建目录" + destDirName + "失败，目标目录已经存在");  
            return false;  
        }  
        if (!destDirName.endsWith(File.separator)) {  
            destDirName = destDirName + File.separator;  
        }  
        //创建目录  
        if (dir.mkdirs()) {  
            System.out.println("创建目录" + destDirName + "成功！");  
            return true;  
        } else {  
            System.out.println("创建目录" + destDirName + "失败！");  
            return false;  
        }  
    }  
    public static void copyFile(String oldPath, String newPath) { 
    	 try { 
    	 int bytesum = 0; 
    	 int byteread = 0; 
    	 File oldfile = new File(oldPath); 
    	 if (oldfile.exists()) { //文件存在时 
    	 InputStream inStream = new FileInputStream(oldPath); //读入原文件 
    	 FileOutputStream fs = new FileOutputStream(newPath); 
    	 byte[] buffer = new byte[500000]; 
    	 int length; 
    	 while ( (byteread = inStream.read(buffer)) != -1) { 
    	                bytesum += byteread; //字节数 文件大小 
    	                System.out.println(bytesum); 
    	                fs.write(buffer, 0, byteread); 
    	    } 
    	     inStream.close(); 
    	  } 
    	 }catch (Exception e) { 
    	    System.out.println("复制单个文件操作出错"); 
    	    e.printStackTrace(); 
         } 

    	 } 
     
     
    /*public static String createTempFile(String prefix, String suffix, String dirName) {  
        File tempFile = null;  
        if (dirName == null) {  
            try{  
                //在默认文件夹下创建临时文件  
                tempFile = File.createTempFile(prefix, suffix);  
                //返回临时文件的路径  
                return tempFile.getCanonicalPath();  
            } catch (IOException e) {  
                e.printStackTrace();  
                System.out.println("创建临时文件失败！" + e.getMessage());  
                return null;  
            }  
        } else {  
            File dir = new File(dirName);  
            //如果临时文件所在目录不存在，首先创建  
            if (!dir.exists()) {  
                if (!Icon.createDir(dirName)) {  
                    System.out.println("创建临时文件失败，不能创建临时文件所在的目录！");  
                    return null;  
                }  
            }  
            try {  
                //在指定目录下创建临时文件  
                tempFile = File.createTempFile(prefix, suffix, dir);  
                return tempFile.getCanonicalPath();  
            } catch (IOException e) {  
                e.printStackTrace();  
                System.out.println("创建临时文件失败！" + e.getMessage());  
                return null;  
            }  
        }  
    } */ 
     
    /*public static void main(long userid) {  
        //创建目录
    	//ServletContext sc = null;
        //String dirName = sc.getRealPath("/")+"userdata\\"+useid;//选定上传的目录,此处为当前目录 MPS\
    	String dirName="D:/刘硕/java/myeclipse/.metadata/.me_tcat7/webapps/MPS/userdata/"+userid;
        
        Icon.createDir(dirName);  
        //创建文件  
        //String fileName = dirName + "/temp2/tempFile.txt";  
        //Icon.createFile(fileName);  
        //创建临时文件  
      /*  String prefix = "temp";  
        String suffix = ".txt";  
        for (int i = 0; i < 10; i++) {  
            System.out.println("创建了临时文件："  
                    + Icon.createTempFile(prefix, suffix, dirName));  
        }  
        //在默认目录下创建临时文件  
        for (int i = 0; i < 10; i++) {  
            System.out.println("在默认目录下创建了临时文件："  
                    + Icon.createTempFile(prefix, suffix, null));  
        }
    } */
  
} 