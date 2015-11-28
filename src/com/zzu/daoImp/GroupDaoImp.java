<<<<<<< HEAD
package com.zzu.daoImp;

import java.awt.Container;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.zzu.dao.GroupDao;
import com.zzu.modle.Group;
import com.zzu.util.DBtools;

import databaseconnection.DataBase;

public class GroupDaoImp implements GroupDao {

	@Override
	public ArrayList<ArrayList<Long>> findUser(long groupid) {
		// TODO Auto-generated method stub
		return new RelationDaoImp().findDown(groupid);
	}

	@Override
	public void addGroup(Group group) {
		// TODO Auto-generated method stub
		String sql = "insert into fork(groupid,createtime,userid,isvalue,isneedagree,groupname) values(?,?,?,?,?,?)";
		Connection con = new DataBase().getConnection();
		PreparedStatement pre = null;
		try{
			pre = con.prepareStatement(sql);
			pre.setLong(1, group.getGroupid());
			pre.setDate(2, (Date) group.getCreatetime());
			pre.setLong(3, group.getUserid());
			pre.setBoolean(4, group.isIsvalue());
			pre.setBoolean(5,group.isIsneedagree());
			pre.setString(6, group.getGroupname());
			pre.execute();
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
				oldgroup.setCreatetime(res.getDate("createtime"));
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
		String sql = "update group set isvalue=false where groupid=?";
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
	private void test() {
		findUser(100);
		deleteGroup(100);
		System.out.println(isBelong(100, 100));
		isvalid(100);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GroupDaoImp().test();
	}

	@Override
	public Group getGroup(long groupid) {
		// TODO Auto-generated method stub
		return null;
	}

}
=======
package com.zzu.daoImp;

import java.util.ArrayList;

import com.zzu.dao.GroupDao;
import com.zzu.modle.Group;

public class GroupDaoImp implements GroupDao {

	public GroupDaoImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Object> findUser(String groupid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addGroup(Group group) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateGroup(Group group) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isBelong(long userid, long groupid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isvalid(long groupid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteGroup(long groupid) {
		// TODO Auto-generated method stub

	}

}
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
