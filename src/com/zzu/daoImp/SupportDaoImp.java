package com.zzu.daoImp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import java.sql.PreparedStatement;

import com.zzu.dao.SupportDao;
import com.zzu.modle.Choice;
import com.zzu.modle.Group;

import databaseconnection.DataBase;

public class SupportDaoImp implements SupportDao{

	@Override
	public ArrayList<Choice> getUserChoice(long groupid, long voteid) {
		// TODO Auto-generated method stub
		String sql = "select A.choiceid,chocontent,voteid from (select * from choice where voteid=?) as A natural join (select * from support where groupid=?) as B";
		new DataBase();
		Connection con = DataBase.getConnection();
		PreparedStatement pre = null;
		ResultSet res = null;
		ArrayList<Choice> ans = new ArrayList<Choice>();
		
		try{
			pre = con.prepareStatement(sql);
			pre.setLong(1, voteid);
			pre.setLong(2, groupid);
			res = pre.executeQuery();
			while(res.next()){
				Choice ch = new Choice();
				ch.setChoiceid(res.getLong("choicid"));
				ch.setChocontent(res.getString("chocontent"));
				ch.setVoteid(res.getLong("voteid"));
				ans.add(ch);
			}
		}catch(Exception e){
			System.out.println("\nliushuo:SupportDaoImp()");
			e.printStackTrace();
		}
		return ans;
	}

	@Override
	public ArrayList<Group> getChoiceOfUser(long choiceid) {
		// TODO Auto-generated method stub
		String sql ="select groupid from support where choiceid=?";
		new DataBase();
		Connection con = DataBase.getConnection();
		PreparedStatement pre = null;
		ArrayList<Group> ans = new ArrayList<Group>();
		ResultSet res = null;
		try{
			pre = con.prepareStatement(sql);
			pre.setLong(1, choiceid);
			res = pre.executeQuery();
			GroupDaoImp g = new GroupDaoImp(); 
			while(res.next()){
				ans.add(g.getGroup(res.getLong(1)));
			}
		}catch(Exception e){
			System.out.println("\nliushuo:getChoiceOfUser");
			e.printStackTrace();
		}
		return ans;
		
	}

}
