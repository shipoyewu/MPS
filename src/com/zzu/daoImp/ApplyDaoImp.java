package com.zzu.daoImp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.zzu.dao.ApplyDao;
import com.zzu.modle.Apply;
import com.zzu.modle.Choice;

import databaseconnection.DataBase;

public class ApplyDaoImp implements ApplyDao {
	Connection con = new DataBase().getConnection();

	@Override
	public boolean addApply(Apply apply) {
		// TODO Auto-generated method stub
		String sql="insert into Apply(groupup,groupdown,applycontent,type) values(?,?,?,?) ";
		PreparedStatement pre = null;
		try{
			pre=con.prepareStatement(sql);
			pre.setLong(1, apply.getGroupup());
			pre.setLong(2, apply.getGroupdown());
			pre.setString(3, apply.getApplycontent());
			pre.setBoolean(4, apply.isType());
			pre.execute();
		}catch(Exception e){
			System.out.println("添加失败！");
			e.printStackTrace();
			return false;
		}
		try{
			pre.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public void deleteApply(Apply apply) {
		// TODO Auto-generated method stub
		String sql="update Apply set isValid = '0' where groupup= ?  and groupdown = ?  ";
		PreparedStatement pre = null;
		try{
			pre=con.prepareStatement(sql);
			pre.setLong(1, apply.getGroupup());
			pre.setLong(2, apply.getGroupdown());
			
		}catch(Exception e){
			System.out.println("删除失败！");
			e.printStackTrace();
		}
		try{
			pre.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(long groupup, long groupdown) {
		// TODO Auto-generated method stub
		String sql="delete from apply where groupup= ? and groupdown= ? ";
		PreparedStatement pre =null;
		try{
			pre=con.prepareStatement(sql);
			pre.setLong(1, groupup);
			pre.setLong(2, groupdown);
			pre.executeUpdate();
		}catch(Exception e){
			System.out.print("删除失败");
			e.printStackTrace();
		}try{
			pre.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Apply> getApply(long groupid) {
		String sql1="select * from apply where groupup= ? order by applytime desc ";
		String sql2="select * from apply where groupdown= ? order by applytime desc";
		PreparedStatement pre1=null;
		PreparedStatement pre2=null;
		ArrayList<Apply> ans=new ArrayList<Apply>();
		ResultSet res=null;
		try{
			pre1=con.prepareStatement(sql1);
			pre1.setLong(1, groupid);
			res=pre1.executeQuery();
			while(res.next()){
				Apply ap1=new Apply();
				ap1.setApplycontent(res.getString("applycontent"));
				ap1.setApplytime(res.getDate("applytime"));
				ap1.setGroupdown(res.getLong("groupdown"));
				ap1.setGroupup(res.getLong("groupup"));
				ap1.setType(res.getBoolean("type"));
				ans.add(ap1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			pre2=con.prepareStatement(sql2);
			pre2.setLong(1, groupid);
			res=pre2.executeQuery();
			while(res.next()){
				Apply ap2=new Apply();
				ap2.setApplycontent(res.getString("applycontent"));
				ap2.setApplytime(res.getDate("applytime"));
				ap2.setGroupdown(res.getLong("groupdown"));
				ap2.setGroupup(res.getLong("groupup"));
				ap2.setType(res.getBoolean("type"));
				ans.add(ap2);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			try {
				pre2.close();
				con.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		// TODO Auto-generated method stub
		return ans;
	}
	
	public static void main(String[] args){
		ApplyDao ad = new ApplyDaoImp();
		//ArrayList<Apply> al = ad.getApply(1);
		ad.delete(1, 2);
		/*ArrayList<Apply> al = ad.getApply(1);
		for(int i = 0; i< al.size();i++){
			System.out.println(al.get(i).getApplycontent());
		}*/
		
	}

}
