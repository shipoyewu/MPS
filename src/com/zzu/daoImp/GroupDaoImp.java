package com.zzu.daoImp;

import java.awt.Container;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.zzu.dao.GroupDao;
import com.zzu.modle.Group;
import com.zzu.util.DBtools;

import databaseconnection.DataBase;

public class GroupDaoImp implements GroupDao {
	@Override
	public long addGroup(Group group) {
		// TODO Auto-generated method stub
		String sql = "insert into fork(userid,isvalue,isneedagree,groupname) values(?,?,?,?)";
		Connection con = new DataBase().getConnection();
		PreparedStatement pre = null;
		ResultSet res = null;
		long id = -1;
		try{
			pre = con.prepareStatement(sql);
			pre.setLong(1, group.getUserid());
			pre.setBoolean(2, group.isIsvalue());
			pre.setBoolean(3,group.isIsneedagree());
			pre.setString(4, group.getGroupname());
			pre.execute();
			id = DBtools.GetLastID(con);
		}catch(Exception e){
			System.out.println("\nshihu:addGroup\n");
			e.printStackTrace();
		}finally{
			try{
				if(pre!=null){
					pre.close();
				}
			}
			catch (Exception e2) {
				e2.printStackTrace();
			}
			try{
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return id;
	}

	private void test() {
		System.out.println(getUserName(1l));
//		Group g = new Group();
//		g.setCreatetime(new java.util.Date());
//		g.setGroupname("asdasd");
//		g.setUserid(1l);
//		g.setIsneedagree(true);
//		g.setIsvalue(true);
//		long id = addGroup(g);
//		System.out.println("sadasds"+getGroup(id).getCreatetime());
//		System.out.println(findAllGroup(1).size());
//		System.out.println("A");
//		findUser(100);
//		System.out.println("B");
//		deleteGroup(100);
//		System.out.println("C");
//		System.out.println(isBelong(100, 100));
//		System.out.println("D");
//		isvalid(100);
//		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GroupDaoImp().test();
	}
	@Override
	public ArrayList<ArrayList<Long>> findUser(long groupid) {
		// TODO Auto-generated method stub
		return new RelationDaoImp().findDown(groupid);
	}

	
	@Override
	public void updateGroup(Group group) {
		// TODO Auto-generated method stub
		String sql = "select * from fork where groupid=?";
		Connection con = new DataBase().getConnection();
		PreparedStatement pre = null;
		ResultSet res = null;
		Group oldgroup = new Group();
		
		try{
			pre = con.prepareStatement(sql);
			pre.setLong(1,group.getGroupid());
			res = pre.executeQuery();
			if(res.next()){
				oldgroup.setGroupid(res.getLong("groupid"));
				oldgroup.setCreatetime(res.getTimestamp("createtime"));
				oldgroup.setGroupname(res.getString("groupname"));
				oldgroup.setIsneedagree(res.getBoolean("isneedagree"));
				oldgroup.setIsvalue(res.getBoolean("isvalue"));
				oldgroup.setUserid(res.getLong("userid"));
			}
		}catch(Exception e){
			System.out.println("\nshihu:updateGroup");
			e.printStackTrace();
		}
		String update = "";
		
		if(oldgroup.isIsneedagree() != group.isIsneedagree()){
			update ="update fork set isneedagree=? where groupid=?";
			try{
				pre = con.prepareStatement(update);
				pre.setBoolean(1, group.isIsneedagree());
				pre.setLong(2, group.getGroupid());
				pre.execute();
			}catch(Exception e){
				System.out.println("\nshihu:updateGroup");
				e.printStackTrace();
			}
		}
		if(oldgroup.isIsvalue() != group.isIsvalue()){
			update ="update group set isvalue=? where groupid=?";
			try{
				pre = con.prepareStatement(update);
				pre.setBoolean(1, group.isIsneedagree());
				pre.setLong(2, group.getGroupid());
				pre.execute();
			}catch(Exception e){
				System.out.println("\nshihu:updateGroup");
				e.printStackTrace();
			}
		}
		
		new DataBase().free(res, con, pre);
		
	}

	@Override
	public boolean isBelong(long userid, long groupid) {
		// TODO Auto-generated method stub
		String sql = "select * from fork where groupid="+ "\'" +groupid+"\' "+"and userid="+"\'"+userid+"\'"+" and isvalue=true"+";";
		System.out.println(sql);
		return new DBtools().RowConf(sql);
	}

	@Override
	public boolean isvalid(long groupid) {
		// TODO Auto-generated method stub
		String sql = "select isvalue from fork where groupid=?";
		Connection con = new DataBase().getConnection();
		PreparedStatement pre = null;
		ResultSet res = null;
		
		try{
			pre = con.prepareStatement(sql);
			pre.setLong(1, groupid);
			res =  pre.executeQuery();
			if(res.next()){
				return res.getBoolean("isvalue");
			}
			else{
				return false;
			}
		}catch(Exception e){
			System.out.println("\nshihu:isvalid");
			e.printStackTrace();
		}finally{
			new DataBase().free(res, con, pre);
		}
		return false;
	}

	@Override
	public void deleteGroup(long groupid) {
		// TODO Auto-generated method stub
		String sql = "update fork set isvalue=false where groupid=?";
		Connection con = new DataBase().getConnection();
		PreparedStatement pre = null;
		
		try{
			pre = con.prepareStatement(sql);
			pre.setLong(1, groupid);
			pre.execute();
		}catch(Exception e){
			System.out.println("\nshihu:deleteGroup1");
			e.printStackTrace();
		}finally{
			try{
				if(pre!=null){
					pre.close();
				}
				con.close();
			}catch(Exception e){
				System.out.println("\nshihu:delteGroup");
				e.printStackTrace();
			}
		}
		RelationDaoImp RD = new RelationDaoImp();
		RD.delRelation(groupid);
	}
	
	

	@Override
	public Group getGroup(long groupid) {
		// TODO Auto-generated method stub
		String sql = "select * from fork where groupid=?";
		Connection con = new DataBase().getConnection();
		PreparedStatement pre = null;
		ResultSet res = null;
		Group group = null;
		try{
			pre = con.prepareStatement(sql);
			pre.setLong(1, groupid);
			res = pre.executeQuery();
			
			if(res.next()){
				group = new Group();
				group.setGroupid(groupid);
				group.setGroupname(res.getString("groupname"));
				group.setIsneedagree(res.getBoolean("Isneedagree"));
				group.setCreatetime(res.getTimestamp("Createtime"));
				group.setIsvalue(res.getBoolean("isvalue"));
				group.setUserid(res.getLong("userid"));
			}
		}catch(Exception e){
			System.out.println("\nshihu:getGroup()");
			e.printStackTrace();
		}finally{
			new DataBase().free(res, con, pre);
		}
		return group;
	}

	@Override
	public ArrayList<Group> findAllGroup(long userid) {
		// TODO Auto-generated method stub
		String sql = "select * from fork where userid=?";
		DataBase db = new DataBase();
		ArrayList<Group> ans = new ArrayList<Group>();
		Connection con = db.getConnection();
		PreparedStatement pre = null;
		ResultSet res = null;
		try{
			pre = con.prepareStatement(sql);
			pre.setLong(1, userid);
			res = pre.executeQuery();
			while(res.next()){
				Group group = new Group();
				group.setGroupid(res.getLong("groupid"));
				group.setGroupname(res.getString("groupname"));
				group.setIsneedagree(res.getBoolean("Isneedagree"));
				group.setCreatetime(res.getTimestamp("Createtime"));
				group.setIsvalue(res.getBoolean("isvalue"));
				group.setUserid(res.getLong("userid"));
				ans.add(group);
			}
		}catch(Exception e){
			System.out.println("\nshihu:findAllGroup()");
			e.printStackTrace();;
		}finally{
			db.free(res, con, pre);
		}
		
		return ans;
	}
	@Override
	public long getUserid(long groupid) {
		// TODO Auto-generated method stub
		String sql = "select userid from fork where groupid=?";
		DataBase db = new DataBase();
		PreparedStatement pre = null;
		Connection con = db.getConnection();
		ResultSet res = null;
		
		try{
			pre = con.prepareStatement(sql);
			pre.setLong(1, groupid);
			res = pre.executeQuery();
			if(res.next()){
				return res.getLong("userid");
			}
		}catch(Exception e){
			System.out.println("\nshihu:getUserid()");
			e.printStackTrace();
		}finally{
			db.free(res, con, pre);
		}
		return 0;
	}

	@Override
	public String getUserName(long groupid) {
		// TODO Auto-generated method stub
		String sql = "select username from user inner join fork using( userid) where groupid=?";
		Connection con = DataBase.getConnection();
		PreparedStatement pre = null;
		ResultSet res = null;
		String ans = null;
		try{
			pre = con.prepareStatement(sql);
			pre.setLong(1, groupid);
			res = pre.executeQuery();
			if(res.next()){
				ans = res.getString("username");
			}
		}catch(Exception e){
			System.out.println("\nshihu:getUserName()");
			e.printStackTrace();
		}finally{
			DataBase.free(res, con, pre);
		}
		
		return ans;
	}

}
