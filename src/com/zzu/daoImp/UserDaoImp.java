package com.zzu.daoImp;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.zzu.dao.UserDao;
import com.zzu.modle.User;

import databaseconnection.DataBase;

public class UserDaoImp implements UserDao {

	
	public User getUser(int userid) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		User user = null;
		String sql = "select * from user where userid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, userid);
			res = pstmt.executeQuery();
			while(res.next()){
				user.setUsername( res.getString("username"));
				user.setBirthday(res.getDate("birthday"));
				user.setEmail(res.getString("email"));
				user.setRegistertime(res.getString("registertime"));
				user.setTel(res.getString("tel"));
				user.setPicture(res.getString("picture"));
			}
				return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("failed to find User by userid!");
		}
			
			DataBase.free(res, con, pstmt);
		
		return null;
	}

	@Override
	public User getUser(String email) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		User user = null;
		String sql = "select * from user where email=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			res = pstmt.executeQuery();
			while(res.next()){
				user.setUsername( res.getString("username"));
				user.setBirthday(res.getDate("birthday"));
				user.setUserid(Integer.parseInt(res.getString("userid")));
				user.setRegistertime(res.getString("registertime"));
				user.setTel(res.getString("tel"));
				user.setPicture(res.getString("picture"));
			}
				return user;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("failed to find User by email!");
		}
			
		DataBase.free(res, con, pstmt);
		return null;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		String sql = "update user set username=?,birthday=?,email=?,registertime=?,tel=?,picture=? where userid=?";
		
		try {
			pstmt.setString(1, user.getUsername());
			pstmt.setDate(2, (Date) user.getBirthday());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getRegistertime());
			pstmt.setString(5, user.getTel());
			pstmt.setString(6, user.getPicture());
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("failed to update user!");
		}
			
			DataBase.freeStatement(con, pstmt);
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into user (username,birthday,email,registertime,tel) "
				+ "values('username'=?,'birthday'=?,'email'=?,'registertime'=?,'tel'=?)";
		
		try {
			pstmt.setString(1, user.getUsername());
			pstmt.setDate(2, (Date) user.getBirthday());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getRegistertime());
			pstmt.setString(5, user.getTel());
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("failed to update user!");
		}
			
		DataBase.freeStatement(con, pstmt);
	}
	

	@Override
	public boolean isUser(String email) {  //是否存在该用户
		// TODO Auto-generated method stub
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		User user = null;
		String sql = "select * from user where email=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			res = pstmt.executeQuery();
			while(!res.next())
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("failed to find User by userid!");
		}
			
			DataBase.free(res, con, pstmt);
		return false;
	}

	@Override
	public boolean isUser(int userid) {  //是否存在该用户
		// TODO Auto-generated method stub
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		User user = null;
		String sql = "select * from user where userid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, userid);
			res = pstmt.executeQuery();
			while(!res.next())
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("failed to find User by userid!");
		}
			
			DataBase.free(res, con, pstmt);
			System.out.println("不存在该用户!");
		return false;
	}
		

	@Override
	public int getId(String str, String type) {  //获得用户ID
		// TODO Auto-generated method stub
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = null;
		int userid = 0;
		if(type.equals("email")){
			try {
				sql = "select userid from user where email=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, str);
				res = pstmt.executeQuery();
				if(res.next()){
					userid = res.getInt("userid");
				}
				else {
					System.out.println("无该用户！");
					return 0;
				}
				return userid;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else if(type.equals("tel")){
			try {
				sql = "select userid from user where tel=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, str);
				res = pstmt.executeQuery();
				if(res.next()){
					userid = res.getInt("userid");
				}
				else {
					System.out.println("无该用户！");
					return 0;
				}
				return userid;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("Please check Input!");
		 }
		DataBase.free(res, con, pstmt);
		return 0;
	}

	@Override
	public boolean confUser(int userid, String password) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		User user = null;
		if(!isUser(userid)){
			System.out.println("无该用户！");
			return false;
		}
		String sql = "select password from user where userid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, userid);
			res = pstmt.executeQuery();
			if(!res.next()){
				if(password.equals(res.getString("password")) ){
					return true;
				}
				else{
					System.out.println("密码匹配错误！");
					return false;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("failed to Config User by userid!");
		}
			
			DataBase.freeStatement(con, pstmt);
		return false;
		
	}

	@Override
	public boolean confUser(String email, String password) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		User user = null;
		String sql = "select * from user where email=?";
		try {
			pstmt = con.prepareStatement(sql);
			res = pstmt.executeQuery();
			if(!res.next()){
				System.out.println("不存在该用户！");
				return false;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sql = "select password from user where email=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			res = pstmt.executeQuery();
			if(!res.next()){
				if(password.equals(res.getString("password")) ){
					return true;
				}
				else{
					System.out.println("密码匹配错误！");
					return false;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("failed to Config User by email!");
		}
			
			DataBase.freeStatement(con, pstmt);
		return false;
		
	}

	@Override
	public ArrayList<Object> getAllSendMeg(int userid) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ArrayList<Object> getAllVote(int voteid) {
		// TODO Auto-generated method stub
		return null;
	}
	public static void main(String[] args){
		
	}

	@Override
	public void updateIcon(int userid) {
		// TODO Auto-generated method stub
		
	}
}


