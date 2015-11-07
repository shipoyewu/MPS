package com.zzu.util;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.stream.FileImageInputStream;
import javax.servlet.ServletOutputStream;

import com.mysql.jdbc.Blob;
import com.mysql.jdbc.PreparedStatement;

import databaseconnection.DataBase;

public class BlobOperate {

	public BlobOperate() {
		// TODO Auto-generated constructor stub
	}
	public static void setIcon(String fileInput) throws IOException {
		Connection con = DataBase.getConnection();
		PreparedStatement pstmt = null;
		FileInputStream fileIS = new FileInputStream(fileInput);
		try {
			String sql = "update user set picture=?";
			pstmt.setBinaryStream(1, fileIS, fileIS.available());
			con.prepareStatement(sql);
			pstmt.executeUpdate();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("图片插入不成功！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("图片插入不成功！");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("图片插入不成功！");
			e.printStackTrace();
		}finally{
			DataBase.freeStatement(con, pstmt);
			fileIS.close();
		}
		
	}
	public static void getIcon(int userid){
		Connection con = DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		FileOutputStream fos = null;
        InputStream is = null;
        byte[] Buffer = new byte[1024];
		String sql = "select picture from user where userid=?";
		
		try {
			pstmt.setInt(1, userid);
			res = pstmt.executeQuery(sql);
			if(!res.next()){
				File file = new File("C:/MPSDOWNLOADS/icon.jpg");//先将文件保存到C盘，再从中读取
				if(!file.exists()){        
					file.createNewFile();
				}
				fos = new FileOutputStream(file);//fileOutputStream
				is = res.getBinaryStream("picture");//fileInputStream
				int size = 0;
				while((size = is.read(Buffer)) != -1){
					fos.write(Buffer, 0, size);
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataBase.free(res, con, pstmt);
			try {
				fos.close();
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
