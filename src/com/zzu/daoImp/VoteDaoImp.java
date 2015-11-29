package com.zzu.daoImp;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Vector;

import com.zzu.dao.VoteDao;
import com.zzu.modle.Content;
import com.zzu.modle.Group;
import com.zzu.modle.Message;
import com.zzu.modle.User;
import com.zzu.modle.Vote;
import com.zzu.util.DBtools;
import com.zzu.util.baseTools;

import databaseconnection.DataBase;
/**
 * 
 * @author xingjiali
 *
 */
public class VoteDaoImp implements VoteDao {

	@Override
	public long addVote(Vote vote) {		
		// TODO Auto-generated method stub
		Connection con=  new DataBase().getConnection();
		String sql= "insert into vote(votecontent,endtime,messageid,ismul,isvalue,isanonymous) value(?,?,?,?,?,?)";
        long id = 0;
        PreparedStatement pre=null; 
        try
        {
        	pre=con.prepareStatement(sql);
        	pre.setString(1,vote.getVotecontent());
        	pre.setTimestamp(2, baseTools.getTimePrecise(vote.getEndtime()));
        	pre.setLong(3,vote.getMessageid());
        	pre.setBoolean(4, vote.isIsmul());
        	pre.setBoolean(5, vote.isIsvalue());
        	pre.setBoolean(6,vote.isIsanonymous());
        	pre.execute();
            id=DBtools.GetLastID(con);
        }catch(Exception e){
        	System.out.println("\nxingjiali:votedaoimp:addvote\n");
			e.printStackTrace();
        }
        try
        {
          pre.close();
          con.close();
        }catch(Exception e){
        	System.out.println("\nxingjiali:getcontent:isvalid\n");
        	e.printStackTrace();
        }
      return id;
	}

	@Override
	public boolean isvalid(long voteid) {
		// TODO Auto-generated method stub
		Connection con = new DataBase().getConnection();
		String sql = "select isvalue from vote where voteid=?";
		PreparedStatement pre = null;
		ResultSet res = null;
		boolean b=false;
		try{
			pre = con.prepareStatement(sql);
			pre.setLong(1, voteid);
			res=pre.executeQuery();
			if(res.next())
			{
			b=res.getBoolean("isvalue");
			//System.out.print("isvalid");
			}
			if(b==true)
				System.out.print("投票有效\n");
			else
				System.out .print("投票无效\n");
			return b;
		}catch(Exception e){
			System.out.println("\nxingjiali:getcontent:isvalid\n");
			e.printStackTrace();
		}
		try{
			pre.close();
			con.close();
		}catch(Exception e){
			System.out.println("\nxingjiali:isvalid");
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
				long mid=res.getLong("messageid");
				Timestamp s=res.getTimestamp("starttime");
				Timestamp e=res.getTimestamp("endtime");
				boolean im=res.getBoolean("ismul");
				boolean iv=res.getBoolean("isvalue");
				boolean ia=res.getBoolean("isanonymous");
				v.setVoteid(vi);
				v.setMessageid(mid);
				v.setVotecontent(vc);
				v.setStarttime(s);
				v.setEndtime(e);
				v.setIsmul(im);
				v.setIsvalue(iv);
				v.setIsanonymous(ia);
				//System.out.print("getvote");
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
	
		
				
		Vote v = new Vote();
		Date d = new Date(2013-10-12);
		
		v.setVotecontent("abcdc");
		v.setMessageid(1);
		v.setStarttime(new java.util.Date());
		v.setIsmul(true);
		v.setIsvalue(true);
		v.setEndtime(new java.util.Date());
		v.setIsanonymous(true);
			
		
		VoteDaoImp vdi=new VoteDaoImp();
			long vvid;
			boolean bool;
			Vote vote= new Vote();
			vvid = vdi.addVote(v);
			System.out.println(vvid);
			bool = vdi.isvalid(120);
			System.out.println(bool);
			vote=vdi.getVote(120);
		    System.out.println(vote.getMessageid());
	}
}
