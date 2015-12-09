package com.zzu.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zzu.dao.ChoiceDao;
import com.zzu.modle.Choice;
import com.zzu.util.DBtools;

import databaseconnection.DataBase;

public class ChoiceDaoImp implements ChoiceDao {

	@Override
	public long addChoice(Choice choice) {
		new DataBase();
		// TODO Auto-generated method stub
		Connection con = DataBase.getConnection();
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

	@Override
	public boolean ifchoice(long groupid, long choiceid) {
		// TODO Auto-generated method stub
		Connection con = (Connection) DataBase.getConnection();
		PreparedStatement pstmt = null;
		ResultSet res = null;
		String sql = "select groupid from support where choiceid=?";
		boolean flag = true;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, choiceid);
			res = pstmt.executeQuery();
			if(!res.next()){
				flag = false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DataBase.free(res, con, pstmt);
		}
		return flag;
	}
	

}
