package com.zzu.daoImp;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.zzu.dao.VoteDao;
import com.zzu.modle.Vote;

import databaseconnection.DataBase;

public class VoteDaoImp implements VoteDao {

	@Override
	public long addVote(Vote vote) {		
		// TODO Auto-generated method stub
		Connection con=  new DataBase().getConnection();
		String sql= "insert into vote(voteid,votecontent,starttime,endtime,messageid,ismul,isvalue) value(?,?,?,?,?,?,?)";
        PreparedStatement pre=null;
        ResultSet  res=null;
        long id = 0;
        try
        {
        	pre=con.prepareStatement(sql);
        	pre.setLong(1,vote.getVoteid() );
        	pre.setString(2,vote.getVotecontent());
        	pre.setDate(3,(Date) vote.getStarttime());
        	pre.setDate(4, (Date) vote.getEndtime());
        	pre.setLong(5,vote.getMessageid());
        	pre.setBoolean(6, vote.isIsmul());
        	pre.setBoolean(7, vote.isIsvalue());
        	pre.execute();
        	id=vote.getVoteid();
        	
        }catch(Exception e){
        	System.out.println("\nxingjiali:votedaoimp:addvote\n");
			e.printStackTrace();
        }
        try
        {
          pre.close();
          con.close();
        }catch(Exception e){
        	e.printStackTrace();
        }
      return id;
	}

	@Override
	public boolean isvalid(long voteid) {
		// TODO Auto-generated method stub
		Connection con = new DataBase().getConnection();
		String sql = "select value from vote where voteid=?";
		PreparedStatement pre = null;
		ResultSet res = null;
		boolean b = false;
		try{
			pre = con.prepareStatement(sql);
			pre.setLong(1, voteid);
			res=pre.executeQuery();
			b=res.getBoolean("value");
		}catch(Exception e){
			System.out.println("\nxingjiali:getcontent:isvalid\n");
			e.printStackTrace();
		}
		try{
			pre.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return b;
	}

	public Vote getVote(long voteid) {
		// TODO Auto-generated method stub
		Connection con = new DataBase().getConnection();
		Vote v=new Vote();
		PreparedStatement pre = null;
		ResultSet res = null;
		String sql = "select * from vote where voteid=?";
		try{
			pre=con.prepareStatement(sql);
			pre.setLong(1, voteid);
			res=pre.executeQuery();
			while(res.next())
			{
				long vi=res.getLong("voteid");
				String vc=res.getString("votecontent");
				Date s=res.getDate("starttime");
				Date e=res.getDate("endtime");
				boolean im=res.getBoolean("ismul");
				boolean iv=res.getBoolean("isvalue");
				v.setVoteid(vi);
				v.setVotecontent(vc);
				v.setStarttime(s);
				v.setEndtime(e);
				v.setIsmul(im);
				v.setIsvalue(iv);				
			}
			
		}catch(Exception e){
			System.out.println("\nxingjiali:getcontent:getvote\n");
			e.printStackTrace();
		}
		try{
			pre.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return v;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}



}
