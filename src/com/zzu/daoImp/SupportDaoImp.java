package com.zzu.daoImp;

<<<<<<< HEAD
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.ejb.ConcurrencyManagement;

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
		Connection con = new DataBase().getConnection();
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
		Connection con = new DataBase().getConnection();
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
		
=======
import java.util.ArrayList;

import com.zzu.dao.SupportDao;

public class SupportDaoImp implements SupportDao {

	public SupportDaoImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Object> getUserChoice(long groupid, long userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Object> getChoiceOfUser(long choiceid) {
		// TODO Auto-generated method stub
		return null;
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
	}

}
