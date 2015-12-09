	package com.zzu.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.zzu.dao.ReceiveDao;
import com.zzu.modle.Message;
import com.zzu.modle.Receive;

import databaseconnection.DataBase;
/**
 * 
 * @author xingjiali
 *
 */
public class ReceiveDaoImp implements ReceiveDao {
	public void addReceive(Receive receive)
	{
		Connection con=new DataBase().getConnection();
		String sql="insert into receive(messageid,groupid,status) value(?,?,?)";
		PreparedStatement pre=null;
		try
		{
			pre=con.prepareStatement(sql);
			pre.setLong(1, receive.getMessageid());
			pre.setLong(2, receive.getGroupid());
			pre.setBoolean(3, receive.isStatus());
			pre.executeUpdate();
			
		}catch(Exception e){
			System.out.println("\nxingjiali:receivedaoimp:addreceive\n");
			e.printStackTrace();
		}
		try
		{
			con.close();
			pre.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void Read(long messageid,long groupid)
	{
		Connection con=new DataBase().getConnection();
		String sql="update receive set status=false where messageid=? and groupid=?";
		PreparedStatement pre=null;
		try
		{
			pre=con.prepareStatement(sql);
			pre.setLong(1, messageid);
			pre.setLong(2, groupid);			
			pre.executeUpdate();
			
		}catch(Exception e){
			System.out.println("\nxingjiali:receivedaoimp:read\n");
			e.printStackTrace();
		}
		try
		{
			con.close();
			pre.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean ifReaded(long messageid, long groupid) {
		// TODO Auto-generated method stub
		
		Connection con=new DataBase().getConnection();
		String sql="select status from receive where messageid=? and groupid=?";
		PreparedStatement pre=null;
		ResultSet res=null;	
		boolean b=true;
		try
		{
			pre=con.prepareStatement(sql);
			pre.setLong(1, messageid);
			pre.setLong(2, groupid);			
			res=pre.executeQuery();
			if(res.next())
			{
				b=res.getBoolean("status");
			}
			
		}catch(Exception e){
			System.out.println("\nxingjiali:receivedaoimp:read\n");
			e.printStackTrace();
		}
		try
		{
			con.close();
			pre.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return b;
	}
	
	@Override
	public ArrayList<Message> getAllReceiveMeg(long groupid) {
		// TODO Auto-generated method stub
		Connection con=new DataBase().getConnection();
		String sql="select messageid,messagetitle,contentid,message.groupid as forkid,isvalue,createtime,isremind,iscomment,remindtime,deletetime from receive inner join message using(messageid) where receive.groupid=? order by status desc,createtime desc ";
		PreparedStatement pre=null;
		ResultSet res=null;	
		Message message=null;
		ArrayList<Message> receive=new ArrayList<Message>();
		try
		{
			pre = con.prepareStatement(sql);
			pre.setLong(1, groupid);
			System.out.println(pre);
			res = pre.executeQuery();
			while(res.next()){
				    message=new Message();
					long m=res.getLong("messageid");
					String mt=res.getString("messagetitle");
					long cid=res.getLong("contentid");
					long gid=res.getLong("forkid");
					boolean isv=res.getBoolean("isvalue");
					Timestamp ct=res.getTimestamp("createtime");
					boolean isr=res.getBoolean("isremind");
					boolean isc=res.getBoolean("iscomment");
					Timestamp rt= null;
					if(isr)
						rt=res.getTimestamp("remindtime");
					Timestamp dt = null;
					if(!isv)
						 dt =res.getTimestamp("deletetime");
					message.setMessageid(m);
					message.setMessagetitle(mt);
					message.setContentid(cid);
					message.setGroupid(gid);
					message.setIsvalue(isv);
					message.setCreatetime(ct);
					message.setIsremind(isr);
					message.setIscomment(isc);
					if(isr)
						message.setRemindtime(rt);
					if(!isv)
						message.setDeletetime(dt);
					//System.out.println(message.getMessageid());
					receive.add(message);
					}
		}catch(Exception e){
			System.out.println("\nxingjiali:receivedaoimp:getallreceivemeg\n");
			e.printStackTrace();
		}
		try
		{
			con.close();
			pre.close();
			res.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		for(int i = 0;i < receive.size();i++){
			Timestamp q = (Timestamp) receive.get(i).getCreatetime();
			System.out.println(q);
			System.out.println(""+q.getYear()+"-"+q.getMonth() + "-" + q.getDay() + " " + q.getHours()+":"+q.getMinutes());
		}
		return receive;
	}

	@Override
	public ArrayList<Message> getAllUnReadMeg(long groupid) {
		// TODO Auto-generated method stub
		Connection con=new DataBase().getConnection();
		String sql="select messageid,messagetitle,contentid,message.groupid as forkid,isvalue,createtime,isremind,iscomment,remindtime,deletetime from receive inner join message using(messageid)  where receive.groupid=? and status=true order by status desc,createtime desc ";
		PreparedStatement pre=null;
		ResultSet res=null;	
		Message message=null;
		ArrayList<Message> receive=new ArrayList<Message>();
		try
		{
			pre = con.prepareStatement(sql);
			pre.setLong(1, groupid);
			res = pre.executeQuery();
			while(res.next()){
				    message=new Message();
					long m=res.getLong("messageid");
					String mt=res.getString("messagetitle");
					long cid=res.getLong("contentid");
					long gid=res.getLong("forkid");
					boolean isv=res.getBoolean("isvalue");
					Timestamp ct=res.getTimestamp("createtime");
					boolean isr=res.getBoolean("isremind");
					boolean isc=res.getBoolean("iscomment");
					Timestamp rt=res.getTimestamp("remindtime");
					Timestamp  dt=res.getTimestamp("deletetime");
					message.setMessageid(m);
					message.setMessagetitle(mt);
					message.setContentid(cid);
					message.setGroupid(gid);
					message.setIsvalue(isv);
					message.setCreatetime(ct);
					message.setIsremind(isr);
					message.setIscomment(isc);
					message.setRemindtime(rt);
					message.setDeletetime(dt);
					receive.add(message);
					  }
				
		}catch(Exception e){
			System.out.println("\nxingjiali:receivedaoimp:getallunreceivemeg\n");
			e.printStackTrace();
		}
		try
		{
			con.close();
			pre.close();
			res.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return receive;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    long gid=35;
	    long mid=7;
        ReceiveDaoImp rdi=new ReceiveDaoImp();
        System.out.print(rdi.ifReaded(mid, gid));
        /* Receive r=new Receive();
        r.setGroupid(1);
        r.setMessageid(7);
        r.setStatus(false);
        rdi.addReceive(r);
        ArrayList<Message> array=rdi.getAllReceiveMeg(gid);
        ArrayList<Message> array2=rdi.getAllUnReadMeg(gid);
        System.out.println("asdasdad");
        System.out.println(array.size());  
    	System.out.println(array2.size()); */  
        //rdi.Read(mid, gid);
    	     
	}
	

}
