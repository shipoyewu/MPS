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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ImageIcon;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.zzu.dao.UserDao;
import com.zzu.modle.Group;
import com.zzu.modle.Message;
import com.zzu.modle.Relation;
import com.zzu.modle.User;
import com.zzu.modle.View;
import com.zzu.modle.Vote;
import com.zzu.util.DBtools;
import com.zzu.util.baseTools;

import databaseconnection.DataBase;

public class UserDaoImp implements UserDao {

	public static void main(String[] args){
		//User u = new User();
		UserDaoImp udi = new UserDaoImp();
		udi.getUser(1l);
		ArrayList<User> a = udi.getHaveRelation(1l);
		if(a.size() == 0){
			System.out.println("A");
		}else{
			System.out.println(a.size());
			for(int i =0;i < a.size();i++){
				System.out.println(a.get(i).getUsername());
			}
		}
		
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
		

	public User getUser(long userid) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		User user = new User();
		user.setUserid(userid);
		String sql = "select * from user where userid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, userid);
			res = pstmt.executeQuery();
			if(res.next()){
				user.setUsername( res.getString("username"));
				user.setBirthday(res.getDate("birthday"));
				user.setEmail(res.getString("email"));
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
		User user = new User();
		user.setEmail(email);
		String sql = "select * from user where email=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			res = pstmt.executeQuery();
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("failed to find User by email!");
		}
			
		DataBase.free(res, con, pstmt);
		return null;
	}

	@Override
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
	}

	@Override
	/**
	 * 需要完整的信息，username，birthday，email，registertime，tel，password
	 * 当插入的email或tel和已有的用户重复时，抛出MySQLIntegrityConstraintViolationException异常
	 */
	public long addUser(User user) throws MySQLIntegrityConstraintViolationException {
		// TODO Auto-generated method stub
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into user(username,birthday,email,tel,password) "
				+ "values(?,?,?,?,?)";
		long id = -1;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setDate(2, (Date) user.getBirthday());
			pstmt.setString(3, user.getEmail());
			pstmt.setString(4, user.getTel());
			pstmt.setString(5, user.getPassword());
			pstmt.executeUpdate();
			id = DBtools.GetLastID(con);
			DataBase.freeStatement(con, pstmt);
			System.out.println("add user success.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("failed to update user!");
			System.out.println("UNIQUE属性的列插入了相同值");
		}
		return id;
	}
	

	@Override
	public boolean isUser(String email) {  //是否存在该用户			
		// TODO Auto-generated method stub
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "select * from user where email=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			res = pstmt.executeQuery();
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
		// TODO Auto-generated method stub
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "select * from user where userid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, userid);
			res = pstmt.executeQuery();
		
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
	/*
	 * 第二个参数为"email"或"tel",第一个为具体值
	 * @see com.zzu.dao.UserDao#getId(java.lang.String, java.lang.String)
	 */
	public long getId(String str, String type) {  //获得用户ID
		// TODO Auto-generated method stub
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = null;
		Long userid;
		if(type.equals("email")){
			try {
				sql = "select userid from user where email=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, str);
				res = pstmt.executeQuery();
				if(res.next()){
					userid = res.getLong("userid");
					return userid;
				}
				else {
					System.out.println("无该用户！");
				}
				
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
					userid = res.getLong("userid");
					return userid;
				}
				else {
					System.out.println("无该用户！");
				}
				
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
	public boolean confUser(long userid, String password) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
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
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, userid);
			res = pstmt.executeQuery();
			if(res.next()){
				if(!password.equals(res.getString("password")) ){
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
		return true;
		
	}

	@Override
	public boolean confUser(String email, String password) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "select * from user where email=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
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
				if(!password.equals(res.getString("password")) ){
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
		return true;
		
	}

	@Override
	public ArrayList<Message> getAllSendMeg(long userid) {
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
		GroupDaoImp GD = new GroupDaoImp();
		RelationDaoImp RD = new RelationDaoImp();
		ArrayList<Group> glist = GD.findAllGroup(userid);
		HashSet<Long> set = new HashSet<Long>(); 
		
		for(int i = 0;i < glist.size();i++){
			Group g = glist.get(i);
			ArrayList<ArrayList<Long>> up = RD.findUp(g.getGroupid());
			if(up.size() == 0){
				System.out.println("B");
			}
			ArrayList<ArrayList<Long>> down = RD.findDown(g.getGroupid());
			if(down.size() == 0){
				System.out.println("B");
			}
			for(int j = 0;j < up.size();j++){
				for(int k = 0;k < up.get(j).size();k ++){
					set.add(GD.getUserid(up.get(j).get(k)));
				}
			}
			for(int j = 0;j < down.size();j++){
				for(int k = 0;k < down.get(j).size();k ++){
					set.add(GD.getUserid(down.get(j).get(k)));
				}
			}
		}
		
		Iterator<Long> ite = set.iterator();
		ArrayList<User> ans = new ArrayList<User>();
		UserDaoImp UD = new UserDaoImp();
		while(ite.hasNext()){
			Long a = ite.next();
			if(a.longValue()!=userid)
				ans.add(UD.getUser(a.longValue()));
		}
		
		return ans;
	}
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
}



	