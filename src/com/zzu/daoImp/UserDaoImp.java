package com.zzu.daoImp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;

import com.mysql.jdbc.Connection;
<<<<<<< HEAD
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
=======
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
import com.zzu.dao.UserDao;
import com.zzu.modle.Message;
import com.zzu.modle.Relation;
import com.zzu.modle.User;
import com.zzu.modle.View;
<<<<<<< HEAD
import com.zzu.modle.Vote;
import com.zzu.util.baseTools;
=======
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5

import databaseconnection.DataBase;

public class UserDaoImp implements UserDao {

	
<<<<<<< HEAD
	public User getUser(long userid) {
=======
	public User getUser(Long userid) {
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
		// TODO Auto-generated method stub
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
<<<<<<< HEAD
		User user = new User();
		user.setUserid(userid);
=======
		User user = null;
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
		String sql = "select * from user where userid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, userid);
			res = pstmt.executeQuery();
			if(res.next()){
				user.setUsername( res.getString("username"));
				user.setBirthday(res.getDate("birthday"));
				user.setEmail(res.getString("email"));
<<<<<<< HEAD
				user.setPassword(res.getString("password"));
				user.setRegistertime(res.getDate("registertime"));
				user.setTel(res.getString("tel"));
				System.out.println("get user by userid");
				return user;
			}
			else{
				System.out.println("该用户不存在！");
				DataBase.free(res, con, pstmt);
			}
				
=======
				user.setRegistertime(res.getString("registertime"));
				user.setTel(res.getString("tel"));
				PictureDao pic = new PictureDao();
				user.setPicture(pic.getUserIcon(userid));	//可能会有问题
			}
				return user;
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("failed to find User by userid!");
		}
			DataBase.free(res, con, pstmt);
		return null;
	}

	@Override
	public User getUser(String email) {			//该方法获得的user将不包含头像
		// TODO Auto-generated method stub
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
<<<<<<< HEAD
		User user = new User();
		user.setEmail(email);
=======
		User user = null;
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
		String sql = "select * from user where email=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			res = pstmt.executeQuery();
<<<<<<< HEAD
			if(res.next()){
				user.setUsername( res.getString("username"));
				user.setBirthday(res.getDate("birthday"));
				user.setUserid(res.getLong("userid"));
				user.setRegistertime(res.getDate("registertime"));
				user.setTel(res.getString("tel"));
				user.setPassword(res.getString("password"));
				System.out.println("get user by email.");
				return user;
			}
			else{
					System.out.println("该用户不存在！");
					DataBase.free(res, con, pstmt);
			}
=======
			while(res.next()){
				user.setUsername( res.getString("username"));
				user.setBirthday(res.getDate("birthday"));
				user.setUserid(res.getLong("userid"));
				user.setRegistertime(res.getString("registertime"));
				user.setTel(res.getString("tel"));
				//PictureDao pic = new PictureDao();
				//user.setPicture(pic.getUserIcon(userid).getImage());	//可能会有问题
			}
				return user;
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("failed to find User by email!");
		}
			
		DataBase.free(res, con, pstmt);
		return null;
	}

	@Override
<<<<<<< HEAD
	public boolean updateUser(User user) {  				//更改用户信息，不包括头像,密码 ,需要填写的信息有：
		// TODO Auto-generated method stub					//username,birthday，email，tel都不能为空
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "select * from user where userid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1,user.getUserid());
			res = pstmt.executeQuery();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			if(res.next()){
					sql = "update user set username=?,birthday=?,email=?,tel=? where userid=?";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, user.getUsername());
					pstmt.setDate(2, (Date) user.getBirthday());
					pstmt.setString(3, user.getEmail());
					pstmt.setString(4, user.getTel());
					pstmt.setLong(5, user.getUserid());
					pstmt.executeUpdate();
			}
			else{
				System.out.println("该用户不存在,无法更新!");
				DataBase.freeStatement(con, pstmt);
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
					
			DataBase.freeStatement(con, pstmt);
			System.out.println("Update success!");
			return true; 
=======
	public void updateUser(User user) {  				//更改用户信息，不包括头像
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
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("failed to update user!");
		}
			
			DataBase.freeStatement(con, pstmt);
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
	}

	@Override
	/**
	 * 需要完整的信息，username，birthday，email，registertime，tel，password
	 * 当插入的email或tel和已有的用户重复时，抛出MySQLIntegrityConstraintViolationException异常
	 */
	public void addUser(User user) throws MySQLIntegrityConstraintViolationException {
		// TODO Auto-generated method stub
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
<<<<<<< HEAD
		String sql = "insert into user(username,birthday,email,registertime,tel,password) "
				+ "values(?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setDate(2, (Date) user.getBirthday());
			pstmt.setString(3, user.getEmail());
			pstmt.setDate(4, (Date) user.getRegistertime());
			pstmt.setString(5, user.getTel());
			pstmt.setString(6, user.getPassword());
			pstmt.executeUpdate();
			DataBase.freeStatement(con, pstmt);
			System.out.println("add user success.");
=======
		String sql = "insert into user (username,birthday,email,registertime,tel) "
				+ "values('username'=?,'birthday'=?,'email'=?,'registertime'=?,'tel'=?)";
		
		try {
			pstmt.setString(1, user.getUsername());
			pstmt.setDate(2, (Date) user.getBirthday());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getRegistertime());
			pstmt.setString(5, user.getTel());
			File image = new File(user.getPicture().getImageFile());
			FileInputStream fis;
			try {
				fis = new FileInputStream(image);
				pstmt.setBinaryStream(6, fis, (int) image.length());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
	
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("failed to update user!");
<<<<<<< HEAD
			System.out.println("UNIQUE属性的列插入了相同值");
		}
		
=======
		}
			
		DataBase.freeStatement(con, pstmt);
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
	}
	

	@Override
<<<<<<< HEAD
	public boolean isUser(String email) {  //是否存在该用户			
=======
	public boolean isUser(String email) {  //是否存在该用户
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
		// TODO Auto-generated method stub
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
<<<<<<< HEAD
=======
		User user = null;
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
		String sql = "select * from user where email=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			res = pstmt.executeQuery();
<<<<<<< HEAD
			if(!res.next()){
				System.out.println("不存在该用户!");
				return false;
			}
			DataBase.free(res, con, pstmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
			return true;		
	}

	@Override
	public boolean isUser(long userid) {  //是否存在该用户
=======
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
	public boolean isUser(Long userid) {  //是否存在该用户
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
		// TODO Auto-generated method stub
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
<<<<<<< HEAD
=======
		User user = null;
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
		String sql = "select * from user where userid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, userid);
			res = pstmt.executeQuery();
<<<<<<< HEAD
		
			if(!res.next()){
				System.out.println("不存在该用户!");
				return false;
			}
			DataBase.free(res, con, pstmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	
		return true;
=======
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
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
	}
		

	@Override
<<<<<<< HEAD
	/*
	 * 第二个参数为"email"或"tel",第一个为具体值
	 * @see com.zzu.dao.UserDao#getId(java.lang.String, java.lang.String)
	 */
	public long getId(String str, String type) {  //获得用户ID
=======
	public int getId(String str, String type) {  //获得用户ID
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
		// TODO Auto-generated method stub
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = null;
<<<<<<< HEAD
		Long userid;
=======
		int userid = 0;
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
		if(type.equals("email")){
			try {
				sql = "select userid from user where email=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, str);
				res = pstmt.executeQuery();
				if(res.next()){
<<<<<<< HEAD
					userid = res.getLong("userid");
					return userid;
				}
				else {
					System.out.println("无该用户！");
				}
				
=======
					userid = res.getInt("userid");
				}
				else {
					System.out.println("无该用户！");
					return 0;
				}
				return userid;
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
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
<<<<<<< HEAD
					userid = res.getLong("userid");
					return userid;
				}
				else {
					System.out.println("无该用户！");
				}
				
=======
					userid = res.getInt("userid");
				}
				else {
					System.out.println("无该用户！");
					return 0;
				}
				return userid;
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
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
	public boolean confUser(Long userid, String password) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
<<<<<<< HEAD
		String sql = "select * from user where userid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, userid);
			res = pstmt.executeQuery();
			if(!res.next()){
				System.out.println("不存在该用户！");
				return false;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		sql = "select password from user where userid=?";
=======
		User user = null;
		if(!isUser(userid)){
			System.out.println("无该用户！");
			return false;
		}
		String sql = "select password from user where userid=?";
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, userid);
			res = pstmt.executeQuery();
<<<<<<< HEAD
			if(res.next()){
				if(!password.equals(res.getString("password")) ){
=======
			if(!res.next()){
				if(password.equals(res.getString("password")) ){
					return true;
				}
				else{
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
					System.out.println("密码匹配错误！");
					return false;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("failed to Config User by userid!");
		}
<<<<<<< HEAD
		DataBase.freeStatement(con, pstmt);
		return true;
=======
			
			DataBase.freeStatement(con, pstmt);
		return false;
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
		
	}

	@Override
	public boolean confUser(String email, String password) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
<<<<<<< HEAD
		String sql = "select * from user where email=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
=======
		User user = null;
		String sql = "select * from user where email=?";
		try {
			pstmt = con.prepareStatement(sql);
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
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
<<<<<<< HEAD
				if(!password.equals(res.getString("password")) ){
=======
				if(password.equals(res.getString("password")) ){
					return true;
				}
				else{
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
					System.out.println("密码匹配错误！");
					return false;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("failed to Config User by email!");
		}
<<<<<<< HEAD
		DataBase.freeStatement(con, pstmt);
		return true;
=======
			
			DataBase.freeStatement(con, pstmt);
		return false;
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
		
	}

	@Override
<<<<<<< HEAD
	public ArrayList<Message> getAllSendMeg(long userid) {
=======
	public ArrayList<Object> getAllSendMeg(Long userid) {
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
		// TODO Auto-generated method stub
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		ArrayList<Message> m = null;
		Message msg = new Message();
		Long groupid = findGroup(userid);
		String sql = "select * from message where groupid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, groupid);
			res = pstmt.executeQuery();
			while(res.next()){
				 msg.setMessageid(res.getLong("messageid"));
				 msg.setMessagetitle(res.getString("messagetitle"));
				 msg.setContentid(res.getLong("contentid"));
				 msg.setCreatetime(res.getDate("createtime"));
				 msg.setDeletetime(res.getDate("deletetime"));
				 msg.setGroupid(groupid);
				 msg.setIscomment(res.getBoolean("iscomment"));
				 msg.setIsremind(res.getBoolean("isremind"));
				 msg.setRemindtime(res.getDate("remindtime"));
				 msg.setIsvalue(res.getBoolean("isvalue"));
				 m.add(msg);
			}
			return m;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
<<<<<<< HEAD
	public long findGroup(long userid) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "select groupid from fork where userid=";
	    try {
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, userid);
			if(res.next()){
				return res.getLong("groupid");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public ArrayList<Vote> getAllVote(long voteid) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ArrayList<User> getHaveRelation(long userid) {
		// TODO Auto-generated method stub
		ArrayList<User> u = new ArrayList();
		ArrayList<Long> userList = null;
		ArrayList<ArrayList<Long>> up = null;
		ArrayList<ArrayList<Long>> down = null;
		ArrayList<Long> temp = new ArrayList<Long>();
		RelationDaoImp r = new RelationDaoImp();
		down = r.findDown(userid);
		int count1 = down.size();
		int count2 = 0;
		for( int i = 0; i < count1; i++){
			 temp = down.get(i);
			 count2 = temp.size();
			for(int j = 0; j < count2; j++){
				 userList.add(temp.get(j));
			}
		}
		count1 = count2 = 0;
		count1 = up.size();
		for(int i = 0; i< count1; i++){
			temp = down.get(i);
			 count2 = temp.size();
			for(int j = 0; j < count2; j++){
				 userList.add(temp.get(j));
			}
		}
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		Iterator<Long> it = userList.iterator();
		long uid = 0;
		/*while(){
			
		}*/
		//u.add();
		return null;
	}
=======
	public ArrayList<Object> getAllVote(Long voteid) {
		// TODO Auto-generated method stub
		return null;
	}
	
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
	@SuppressWarnings({ "null", "unused" })
	public void updateIcon(User user) { 			 //更新用户头像
		// TODO Auto-generated method stub
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		File image = new File(user.getPicture().getImageFile());
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(image);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String sql = "update user set picture=? where userid=?";
		try {
		  pstmt.setBinaryStream(1, fis, (int) image.length());
		  pstmt.setLong(2, user.getUserid());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args){
<<<<<<< HEAD
		//User u = new User();
		UserDaoImp udi = new UserDaoImp();
		/*User user = udi.getUser(l);
		System.out.println(user.getUserid());  //getUser----test
		System.out.println(user.getEmail());
		System.out.println(user.getPassword());
		System.out.println(user.getRegistertime());
		System.out.println(user.getTel());
		System.out.println(user.getBirthday());
		System.out.println(user.getUsername());
		System.out.println("getUser(userid)----test");
		User user2 = udi.getUser("yolen_@163.com");
		System.out.println(user2.getUsername());
		System.out.println(user2.getEmail());
		System.out.println(user2.getRegistertime());
		System.out.println(user2.getBirthday());*/
		/*User u = new User();		
		u.setEmail("yolen_zz@outlook.com");
		baseTools bt = new baseTools();
		u.setUsername("fuguo");
		u.setBirthday(bt.str2Date("1994-08-06"));
		u.setPassword("6666663");
		u.setTel("13027711597");*/
		//udi.updateUser(u); ----UpdateUser()test
		/*Date rt = bt.getNowTosql();
		u.setRegistertime(rt);
		try {
			udi.addUser(u);
		} catch (MySQLIntegrityConstraintViolationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		if(udi.isUser(1)){  //------isUser() test
			System.out.println("1存在！");
	}
		if(udi.isUser("yolenstark@outlook.com")){
			System.out.println("2存在！");
		}
		else System.out.println("2bucunzai !");
	
		//System.out.println(udi.getId("13027711597", "tel"));
		//if(udi.confUser(l, "6666663")) System.out.println("yes");

	}
		//System.out.println(udi.getId("13027711597", "tel"));
		//if(udi.confUser(l, "6666663")) System.out.println("yes");
	}


=======
		
	}
	
}
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5


	
