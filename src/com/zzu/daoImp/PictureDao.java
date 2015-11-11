package com.zzu.daoImp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;

import com.mysql.jdbc.Connection;
import com.zzu.modle.User;
import com.zzu.modle.View;

import databaseconnection.DataBase;

/**
 * 对图片类View的操作
 * @author zongzan
 *
 */
public class PictureDao {

	public PictureDao() {
		// TODO Auto-generated constructor stub
	}

	public boolean setUserIcon(View view, long userid) throws SQLException{	//设置用户头像,view示例至少要有image属性和路径属性
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		User user = null;
		FileInputStream fis = null;
		
		String sql = "update user set picture=?";
		if (view.getImageFile() != null) {
            File image = new File(view.getImageFile());//图片在服务器的路径
            try {
				fis = new FileInputStream(image);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("File not found!");
				e.printStackTrace();
			}
            //image.length(),返回文件的大小
            pstmt.setBinaryStream(1, fis, (int) image.length());
        } else {
            pstmt.setBinaryStream(1, null, 0);
        }
        int count = pstmt.executeUpdate();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
        
	}
	public View getUserIcon(Long userid){//  返回用户头像
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		User user = null;
		
		String sql = "select picture from user where userid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, userid);
			res = pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while(res.next()){
				View view = null;
				ByteBuffer bb = ByteBuffer.allocate(1024 * 1024);
			    byte[] buffer = new byte[1];
			    InputStream is = res.getBinaryStream("picture");

			    while (is != null && is.read(buffer) > 0) {
			        bb.put(buffer);
			    }
			    ImageIcon icon = new ImageIcon(bb.array());
			    view.setImage(icon.getImage());
			    return view;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	
}
