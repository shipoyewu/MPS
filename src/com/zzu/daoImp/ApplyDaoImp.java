package com.zzu.daoImp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.zzu.dao.ApplyDao;
import com.zzu.modle.Apply;

import databaseconnection.DataBase;

public class ApplyDaoImp implements ApplyDao {
	Connection con = new DataBase().getConnection();

	@Override
	public void addApply(Apply apply) {
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
		}
		try{
			pre.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	
		
		
	}

	@Override
	public void deleteApply(Apply apply) {
		// TODO Auto-generated method stub
		String sql="update Apply set isValid = '0' where groupup= ?  and groupdown = ?  ";
		PreparedStatement pre = null;
		try{
			con.prepareStatement(sql);
			
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
	

}
