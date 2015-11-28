package com.zzu.daoImp;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
<<<<<<< HEAD
=======
import java.util.Vector;
>>>>>>> shihu

import com.zzu.dao.VoteDao;
import com.zzu.modle.Content;
import com.zzu.modle.Group;
import com.zzu.modle.Message;
import com.zzu.modle.User;
import com.zzu.modle.Vote;

import databaseconnection.DataBase;
<<<<<<< HEAD

=======
/**
 * 
 * @author xingjiali
 *
 */
>>>>>>> shihu
public class VoteDaoImp implements VoteDao {

	@Override
	public long addVote(Vote vote) {		
		// TODO Auto-generated method stub
		Connection con=  new DataBase().getConnection();
<<<<<<< HEAD
		String sql= "insert into vote(voteid,votecontent,starttime,endtime,messageid,ismul,isvalue) value(?,?,?,?,?,?,?)";
        PreparedStatement pre=null;
        ResultSet  res=null;
        long id = 0;
=======
		String sql= "insert into vote(voteid,votecontent,starttime,endtime,messageid,ismul,isvalue,isanonymous) value(?,?,?,?,?,?,?,?)";
        long id = 0;
        PreparedStatement pre=null; 
>>>>>>> shihu
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
<<<<<<< HEAD
        	pre.execute();
        	id=vote.getVoteid();
=======
        	pre.setBoolean(8,vote.isIsnonymous());
        	pre.execute();
            id=vote.getVoteid();
>>>>>>> shihu
        	
        }catch(Exception e){
        	System.out.println("\nxingjiali:votedaoimp:addvote\n");
			e.printStackTrace();
        }
        try
        {
          pre.close();
          con.close();
        }catch(Exception e){
<<<<<<< HEAD
=======
        	System.out.println("\nxingjiali:getcontent:isvalid\n");
>>>>>>> shihu
        	e.printStackTrace();
        }
      return id;
	}

	@Override
	public boolean isvalid(long voteid) {
		// TODO Auto-generated method stub
		Connection con = new DataBase().getConnection();
<<<<<<< HEAD
		String sql = "select value from vote where voteid=?";
		PreparedStatement pre = null;
		ResultSet res = null;
		boolean b = false;
=======
		String sql = "select isvalue from vote where voteid=?";
		PreparedStatement pre = null;
		ResultSet res = null;
		boolean b=false;
>>>>>>> shihu
		try{
			pre = con.prepareStatement(sql);
			pre.setLong(1, voteid);
			res=pre.executeQuery();
<<<<<<< HEAD
			b=res.getBoolean("value");
=======
			if(res.next())
			{
			b=res.getBoolean("isvalue");
			System.out.print("isvalid");
			}
			if(b==true)
				System.out.print("投票有效");
			else
				System.out .print("投票无效");
			return b;
>>>>>>> shihu
		}catch(Exception e){
			System.out.println("\nxingjiali:getcontent:isvalid\n");
			e.printStackTrace();
		}
		try{
			pre.close();
			con.close();
		}catch(Exception e){
<<<<<<< HEAD
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



=======
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
				Date s=res.getDate("starttime");
				Date e=res.getDate("endtime");
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
				System.out.print("getvote");
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
		Date d =new Date(2013-10-12);
		Date dd=new Date(2013-10-23);
		
		v.setVotecontent("abcdc");
		v.setMessageid(1);
		v.setStarttime(d);
		v.setIsmul(true);
		v.setIsvalue(true);
		v.setEndtime(dd);
		v.setIsanonymous(true);
			
		
		VoteDaoImp vdi=new VoteDaoImp();
		//long vid=123;
			long vvid;
			boolean bool;
			Vote vote= new Vote();
			vvid = vdi.addVote(v);
			System.out.println("A");
			bool = vdi.isvalid(3);
			System.out.println("B");
			vote=vdi.getVote(3);
		    System.out.println("C");
	
	}

	
>>>>>>> shihu
}
