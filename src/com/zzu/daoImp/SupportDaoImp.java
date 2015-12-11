package com.zzu.daoImp;

import java.net.ConnectException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.ejb.ConcurrencyManagement;
import javax.naming.spi.DirStateFactory.Result;

import java.sql.PreparedStatement;

import com.zzu.dao.SupportDao;
import com.zzu.modle.Choice;
import com.zzu.modle.Group;
import com.zzu.modle.Support;

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
		
	}
	
	public void addSupport(long groupid,long choiceid){
		String sql="select * from vote  nature join  choice using(voteid) where choiceid= ? and endtime > now() ";
		Connection con=new DataBase().getConnection();
		PreparedStatement pre=null;
		ResultSet res=null;
		ResultSet ress=null;
		try{
			pre=con.prepareStatement(sql);
			pre.setLong(1, choiceid);
			res=pre.executeQuery();
			if(res.next()){
				String sql1="insert into support where choiceid= ? and groupid = ?";
				PreparedStatement pres=null;
				try{
					pres=con.prepareStatement(sql1);
				    pres.setLong(1, choiceid);
				    pres.setLong(2, groupid);
				    pres.execute();
				    System.out.println("插入成功！");
				}catch(Exception e){
					System.out.println("插入失败！");
					e.printStackTrace();
				}
				con.close();
				pres.close(); 
			}
			System.out.print("超出投票时间");
			con.close();
			pre.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
		
	}

}
