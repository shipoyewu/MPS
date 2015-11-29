package com.zzu.daoImp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import com.zzu.dao.ChoiceDao;
import com.zzu.modle.Choice;
import com.zzu.util.DBtools;

import databaseconnection.DataBase;

public class ChoiceDaoImp implements ChoiceDao {

	@Override
	public long addChoice(Choice choice) {
		// TODO Auto-generated method stub
		Connection con = new DataBase().getConnection();
		String sql =" insert into choice (chocontent, voteid ) values (?,?)";
		PreparedStatement pre = null;
		long id  =  -1;
		try{
			pre=con.prepareStatement(sql);
			pre.setString(1, choice.getChocontent());
			pre.setLong(2, choice.getVoteid());
			pre.execute();
			id = DBtools.GetLastID(con);
		}catch(Exception e){
			System.out.println("添加失败!");
			e.printStackTrace();
		}
		try{
			pre.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return id;
		
	}

}
