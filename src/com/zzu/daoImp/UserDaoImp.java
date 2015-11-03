package com.zzu.daoImp;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import com.zzu.dao.UserDao;
import com.zzu.modle.User;
import com.zzu.util.DBtools;

import databaseconnection.DataBase;

public class UserDaoImp implements UserDao {

	/*
	 * (non-Javadoc)
	 * @see com.zzu.dao.UserDao#findUser(long)
	 * findUser根据userid得到user的modal但是这个里面只会根据已经确认过的用户进行返回，没有确认过的不能识别
	 * 
	 */
	@Override
	public User findUser(long userid) {
		// TODO Auto-generated method stub
		String sql="select * from user where userid=?";
		Connection conn = new DataBase().getConnection();
		PreparedStatement pre = null;
		ResultSet res = null;
		User u = new User()  ;
		
		try{
			pre = conn.prepareStatement(sql);
			pre.setLong(1, userid);
			System.out.println(pre);
			res = pre.executeQuery();
			if(res.next()){
				u.setBirthday(res.getDate("birthday"));
				u.setEmail(res.getString("email"));
				u.setPassword("password");
				InputStream stream = res.getBinaryStream("picture");
				int bit;
				Vector<Byte> vec = new Vector<Byte>();
				
				while((bit = stream.read()) != -1){
					vec.add(new Byte((byte)bit));
				}
				u.setPicture(vec);
				u.setRegistertime(res.getDate("regisertime"));
				u.setTel(res.getString("tel"));
				u.setUserid(res.getLong("userid"));
				u.setUsername(res.getString("username"));
			}
		}catch(Exception e){
			System.out.println("\nshihu: finduser\n");
			e.printStackTrace();
		}finally{
			try{
				new DataBase().free(res, conn, pre);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return u;
	}
	
	/*
	 * 根据邮箱找用户(non-Javadoc)
	 * @see com.zzu.dao.UserDao#findUser(java.lang.String)
	 */
	@Override
	public User findUser(String email) {
		// TODO Auto-generated method stub
		long userid= getId(email,"email");
		return findUser(userid);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * (non-Javadoc)
	 * @see com.zzu.dao.UserDao#isUser(java.lang.String)
	 */
	@Override
	public boolean isUser(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUser(long userid) {
		// TODO Auto-generated method stub
		return false;
	}
	
	/*
	 * 根据给定的串求一个id号，type="tel" || type="email" || type = userid
	 * (non-Javadoc)
	 * @see com.zzu.dao.UserDao#getId(java.lang.String, java.lang.String)
	 */
	@Override
	public long getId(String str, String type) {
		// TODO Auto-generated method stub
		String tel= "select userid from user where tel=?";
		String email="select userid from  user where email=?";
		Connection conn = new DataBase().getConnection();
		PreparedStatement pre = null;
		String sql = null;
		ResultSet res = null;
		long ans = 0;
		if(type.equals("tel")){
			sql = tel;
		}
		else if(type.equals("email")){
			 sql = email;
		}
		else{
			return Long.parseLong(str);
		}
		try{
			pre = conn.prepareStatement(sql);
			pre.setString(1, str);
			res = pre.executeQuery();
			if(res.next()){
				ans = res.getLong("userid");
			}
		}catch(Exception  e){
			System.out.println("\nshihu:getid");
			e.printStackTrace();
		}
		return ans;
	}

	@Override
	public boolean confUser(long userid, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean confUser(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Long> getAllSendMeg(long userid) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ArrayList<Long> getAllVote(long voteid) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	private void test(){
		System.out.println("test:start");
		findUser(100);
	}
	
	public static void main(String args[]){
		new UserDaoImp().test();
	}

}
//你是不是傻吊

