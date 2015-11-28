package com.zzu.daoImp;

<<<<<<< HEAD
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.zzu.dao.ApplyDao;
import com.zzu.modle.Apply;

import databaseconnection.DataBase;

public class ApplyDaoImp implements ApplyDao {
	Connection con = new DataBase().getConnection();
=======
import com.zzu.dao.ApplyDao;
import com.zzu.modle.Apply;

public class ApplyDaoImp implements ApplyDao{
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5

	@Override
	public void addApply(Apply apply) {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		String sql="insert into Apply(groupup,groupdown,applytime,applycontent,type) values(?,?,?,?,?) ";
		PreparedStatement pre = null;
		try{
			pre=con.prepareStatement(sql);
			pre.setLong(1, apply.getGroupup());
			pre.setLong(2, apply.getGroupdown());
			Date time = new Date(System.currentTimeMillis());
			pre.setDate(3,time);
			pre.setString(4, apply.getApplycontent());
			pre.setBoolean(5, apply.isType());
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
	
		
=======
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
		
	}

	@Override
	public void deleteApply(Apply apply) {
		// TODO Auto-generated method stub
<<<<<<< HEAD
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
	

=======
		
	}

	
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
}
