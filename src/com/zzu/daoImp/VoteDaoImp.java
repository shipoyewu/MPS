package com.zzu.daoImp;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import com.zzu.dao.VoteDao;
import com.zzu.modle.Choice;
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
		new DataBase();
		// TODO Auto-generated method stub
		Connection con=DataBase.getConnection();
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
		new DataBase();
		// TODO Auto-generated method stub
		Connection con = DataBase.getConnection();
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
			res.close();
		}catch(Exception e){
			System.out.println("\nxingjiali:isvalid");
			e.printStackTrace();
		}
		return b;
	
	}

	@Override
	public Vote getVote(long voteid) {
		new DataBase();
		// TODO Auto-generated method stub
		Connection con = DataBase.getConnection();
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
			res.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return v;
	}

	
	@Override
	public boolean messageHave(long messageid) {
		new DataBase();
		// TODO Auto-generated method stub
		Connection con = DataBase.getConnection();
		String sql = "select * from vote where messageid=?";
		PreparedStatement pre = null;
		ResultSet res = null;
		boolean b=false;
		try{
			pre = con.prepareStatement(sql);
			pre.setLong(1, messageid);
			res=pre.executeQuery();
			if(res.next())
			{
			b=true;
			//System.out.print(true);
			}
			
		}catch(Exception e){
			System.out.println("\nxingjiali:getcontent:messageHava\n");
			e.printStackTrace();
		}
		try{
			pre.close();
			con.close();
			res.close();
		}catch(Exception e){
			System.out.println("\nxingjiali:getcontent:messageHava\n");
			e.printStackTrace();
		}
		return b;
	
	}

	@Override
	public Vote getVoteByMessage(long messageid) {
		new DataBase();
		// TODO Auto-generated method stub
		Connection con = DataBase.getConnection();
		String sql = "select * from vote where messageid=?";
		PreparedStatement pre = null;
		ResultSet res = null;
		Vote v=null;
		try{
			pre = con.prepareStatement(sql);
			pre.setLong(1, messageid);
			res=pre.executeQuery();
			while(res.next())
			{
			 v=new Vote();
			 v.setVoteid(res.getLong("voteid"));
			 v.setVotecontent(res.getString("votecontent"));
			 v.setMessageid(res.getLong("messageid"));
			 v.setStarttime(res.getTimestamp("starttime"));
			 v.setEndtime(res.getTimestamp("endtime"));
			 v.setIsanonymous(res.getBoolean("isanonymous"));
			 v.setIsmul(res.getBoolean("ismul"));
			 v.setIsvalue(res.getBoolean("isvalue"));
			
			}
			
		}catch(Exception e){
			System.out.println("\nxingjiali:getcontent:getVoteByMessage\n");
			e.printStackTrace();
		}
		try{
			pre.close();
			con.close();
			res.close();
		}catch(Exception e){
			System.out.println("\nxingjiali:getcontent:getVoteByMessage\n");
			e.printStackTrace();
		}
		return v;
	
	}
	
	
	@Override
	public ArrayList<Choice> getChoices(long voteid) {
		new DataBase();
		// TODO Auto-generated method stub
		Connection con = DataBase.getConnection();
		ArrayList<Choice> ac=new ArrayList<Choice>();
		Choice c=null;
		PreparedStatement pre = null;
		ResultSet res = null;
		String sql = "select * from choice where voteid=?";
		try{
			pre=con.prepareStatement(sql);
			pre.setLong(1, voteid);
			res=pre.executeQuery();
			while(res.next())
			{
				c=new Choice();
				c.setChoiceid(res.getLong("choiceid"));
				c.setChocontent(res.getString("chocontent"));
				c.setVoteid(res.getLong("voteid"));
				ac.add(c);
			}
			
		}catch(Exception e){
			System.out.println("\nxingjiali:getcontent:getChoices\n");
			e.printStackTrace();
		}
		try{
			pre.close();
			con.close();
			res.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return ac;
	}

	@Override
	public boolean isparticipate(long voteid, long groupid) {
		new DataBase();
		// TODO Auto-generated method stub
		Connection con = DataBase.getConnection();
		String sql = "select groupid from choice,support where voteid=? and choice.choiceid=support.choiceid";
		PreparedStatement pre = null;
		ResultSet res = null;
		boolean b=false;
		try{
			pre = con.prepareStatement(sql);
			pre.setLong(1, voteid);
			res=pre.executeQuery();
			while(res.next())
			{
			 //System.out.println( res.getLong("groupid"));
			 if(groupid==res.getLong("groupid"))
			 {
				b=true;				
			   return b;
			 }
			
			}
			
		}catch(Exception e){
			System.out.println("\nxingjiali:getcontent:isparticipate\n");
			e.printStackTrace();
		}
		try{
			pre.close();
			con.close();
			res.close();
		}catch(Exception e){
			System.out.println("\nxingjiali:getcontent:isparticipate\n");
			e.printStackTrace();
		}
		return b;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		
				
	//	Vote v = new Vote();
	//		Date d = new Date(2013-10-12);
		
	//	v.setVotecontent("xingjiali");
	//	v.setMessageid(9);
	//	v.setStarttime(new java.util.Date());
	//	v.setIsmul(true);
	//	v.setIsvalue(true);
	//	v.setEndtime(new java.util.Date());
	//	v.setIsanonymous(true);
	
		
		VoteDaoImp vdi=new VoteDaoImp();
		/* long vvid;
			boolean bool;
			Vote vote= new Vote();
			vvid = vdi.addVote(v);
			System.out.println(vvid);
			bool = vdi.isvalid(123);
			System.out.println(bool);
			vote=vdi.getVote(123);
		    System.out.println(vote.getMessageid());
		    	*/
		
	//	long mid=3;
	//	System.out.print(vdi.messageHave(mid));
		
	//	ArrayList<Choice> ac=new ArrayList<Choice>();
	//	long vid=123;
	//	ac=vdi.getChoices(vid);
	//	for(int i=0;i<ac.size();i++)
	//	{
	//	System.out.println(ac.get(i).getChoiceid());
	//	}

    //	long mmid=2;
	//	v=vdi.getVoteByMessage(mmid);
	//	System.out.println(v.getVoteid());
	//	System.out.println(v.getVotecontent());
	//	System.out.println(v.getMessageid());
		
	//	long vid=124;
	//	long gid=2;
	//	boolean bl=vdi.isparticipate(vid, gid);
	//	System.out.println(bl);
	}

	
}
