package com.zzu.daoImp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.Date;
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
				try{
					stream.close();
				}catch(Exception e){
					System.out.println("\nshihu:finduser stream");
					e.printStackTrace();
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
		String sql = "insert into user(userid,username,password,birthday,tel,email,registertime,picture) value(?,?,?,?,?,?,?,?)";
		Connection con = new DataBase().getConnection();
		PreparedStatement pre = null;
		try{
			pre = con.prepareStatement(sql);
			pre.setLong(1,user.getUserid());
			pre.setString(2,user.getUsername());
			pre.setString(3,user.getPassword());
			pre.setDate(4,(Date) user.getBirthday());
			pre.setString(5,user.getTel());
			pre.setString(6, user.getEmail());
			pre.setDate(7,(Date)user.getRegistertime());
			
		
			
			
			
			
			
			  
			
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.zzu.dao.UserDao#isUser(java.lang.String)
	 */
	@Override
	public boolean isUser(String email) {
		// TODO Auto-generated method stub
		String sql = "select * from user where email="+"\'"+email+"\';";
		
		return new DBtools().RowConf(sql);
	}

	@Override
	public boolean isUser(long userid) {
		// TODO Auto-generated method stub
		String sql = "select * from user where userid="+"\'"+userid+"\';";
		return new DBtools().RowConf(sql);
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
		}finally{
			new DataBase().free(res, conn, pre);
		}
		return ans;
	}

	@Override
	public boolean confUser(long userid, String password) {
		// TODO Auto-generated method stub
		String sql = "select * from user where userid="+"\'"+userid+"\'"+" and password="+"\'" +"password"+"\';";
		System.out.println(sql);
		return new DBtools().RowConf(sql);
	}

	@Override
	public boolean confUser(String email, String password) {
		// TODO Auto-generated method stub
		long a = getId(email,"email");
		return confUser(a, password);
	}

	@Override
	public ArrayList<Long> getAllSendMeg(long userid) {
		// TODO Auto-generated method stub
		RelationDaoImp R = new RelationDaoImp();
		String sql = "select voteid from (select * from fork where userid=?) as a,message,vote";
		ResultSet res = null;
		ArrayList<Long> li = new ArrayList<Long>();
		Connection con = null;
		PreparedStatement pre=null;
		try{
			con = new DataBase().getConnection();
			pre=con.prepareStatement(sql);
			pre.setLong(1, userid);
			res=pre.executeQuery();
			while(res.next()){
				li.add(res.getLong(1));
			}
 		}catch(Exception e){
 			System.out.println("\nshihu:getAllSendMsg\n");
 			e.printStackTrace();
 		}finally{
 			new DataBase().free(res, con, pre);
 		}
		return li;
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

