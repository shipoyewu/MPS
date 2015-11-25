package com.zzu.daoImp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import com.zzu.dao.ChoiceDao;
import com.zzu.modle.Choice;

import databaseconnection.DataBase;

public class ChoiceDaoImp implements ChoiceDao {

	@Override
	public void addChoice(Choice choice) {
		// TODO Auto-generated method stub
		Connection con = new DataBase().getConnection();
		String sql =" insert into choice (choiceId ,chocontent, voteid ) values (?,?,?)";
		PreparedStatement pre = null;
		try{
			pre=con.prepareStatement(sql);
			pre.setLong(1, choice.getChoiceid());
			pre.setString(2, choice.getChocontent());
			pre.setLong(3, choice.getVoteid());
			pre.execute();
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
	
		
		
	}

}
