package com.zzu.daoImp;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.zzu.dao.VoteDao;
import com.zzu.modle.Content;
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
        	con.prepareStatement(sql);
        	pre.setLong(1,vote.getVoteid() );
        	pre.setString(2,vote.getVotecontent());
        	pre.setDate(3,(Date) vote.getStarttime());
        	pre.setDate(4, (Date) vote.getEndtime());
        	pre.setLong(5,vote.getMessageid());
        	pre.setBoolean(6, true);
        	pre.setBoolean(7, true);
        	res = pre.executeQuery();
        	id=res.getLong("voteid");
        	
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
			res=pre.executeQuery(sql);
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

	@Override
	public Vote getVote(Vote vote) {
		// TODO Auto-generated method stub
		Connection con = new DataBase().getConnection();
		String sql = "select *from vote where voteid=?";
		Vote  v=new Vote();
		PreparedStatement pre = null;
		ResultSet res = null;
	
		try{
			pre=con.prepareStatement(sql);
			pre.setLong(1, vote.getVoteid());
			res=pre.executeQuery();
			while(res.next())
			{
				long vi=res.getLong(1);
				String vc=res.getString(2);
				Date s=res.getDate(3);
				Date e=res.getDate(4);
				boolean im=res.getBoolean(5);
				boolean iv=res.getBoolean(6);
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
