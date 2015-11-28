package com.zzu.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import com.zzu.dao.ReceiveDao;
import com.zzu.modle.Message;

import databaseconnection.DataBase;
/**
 * 
 * @author xingjiali
 *
 */
public class ReceiveDaoImp implements ReceiveDao {

	@Override
	public ArrayList<Message> getAllReceiveMeg(long groupid) {
		// TODO Auto-generated method stub
		Connection con=new DataBase().getConnection();
		String sql1="select messageid from receive where groupid=?";
		PreparedStatement pre=null;
		ResultSet res=null;		
		long mid=0;	
		Message message=new Message();
		ArrayList<Message> receive=new ArrayList<Message>();
		try
		{
			pre = con.prepareStatement(sql1);
			pre.setLong(1, groupid);
			res = pre.executeQuery();
			while(res.next()){
<<<<<<< HEAD
				Long u = res.getLong(1);
				Long m = res.getLong(2);
				boolean b=res.getBoolean(3);
				receive.add(u);
				receive.add(m);
				receive.add(b);
			}
			
=======
				mid=res.getLong("messageid");
				String sql="select * from message where messageid=?";
				PreparedStatement pre2=null;
				ResultSet res2=null;
				try
				{
					pre = con.prepareStatement(sql);
					pre.setLong(1, groupid);
					res = pre.executeQuery();
					while(res.next()){
					long m=res.getLong("messageid");
					String mt=res.getString("messagetitle");
					long cid=res.getLong("contentid");
					long gid=res.getLong("groupid");
					boolean isv=res.getBoolean("isvalue");
					Date ct=res.getDate("createtime");
					boolean isr=res.getBoolean("isremind");
					boolean isc=res.getBoolean("iscomment");
					Date rt=res.getDate("remindtime");
					Date dt=res.getDate("deletetime");
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
						System.out.println("\nxingjiali:receivedaoimp:getallreceivemegin\n");
						e.printStackTrace();
				        }
			  }
>>>>>>> shihu
		}catch(Exception e){
			System.out.println("\nxingjiali:receivedaoimp:getallreceivemeg\n");
			e.printStackTrace();
		}
		try
		{
			con.close();
			pre.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return receive;
	}

	@Override
	public ArrayList<Message> getAllUnReadMeg(long groupid) {
		// TODO Auto-generated method stub
		Connection con=new DataBase().getConnection();
		String sql1="select messageid from receive where groupid=? and status=false";
		PreparedStatement pre=null;
		ResultSet res=null;		
		long mid=0;	
		Message message=new Message();
		ArrayList<Message> receive=new ArrayList<Message>();
		try
		{
			pre = con.prepareStatement(sql1);
			pre.setLong(1, groupid);
			res = pre.executeQuery();
			while(res.next()){
				mid=res.getLong("messageid");
				String sql="select * from message where messageid=?";
				PreparedStatement pre2=null;
				ResultSet res2=null;
				try
				{
					pre = con.prepareStatement(sql);
					pre.setLong(1, groupid);
					res = pre.executeQuery();
					while(res.next()){
					long m=res.getLong("messageid");
					String mt=res.getString("messagetitle");
					long cid=res.getLong("contentid");
					long gid=res.getLong("groupid");
					boolean isv=res.getBoolean("isvalue");
					Date ct=res.getDate("createtime");
					boolean isr=res.getBoolean("isremind");
					boolean isc=res.getBoolean("iscomment");
					Date rt=res.getDate("remindtime");
					Date dt=res.getDate("deletetime");
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
						System.out.println("\nxingjiali:receivedaoimp:getallreceivemegin\n");
						e.printStackTrace();
				        }
			  }
		}catch(Exception e){
			System.out.println("\nxingjiali:receivedaoimp:getallreceivemeg\n");
			e.printStackTrace();
		}
		try
		{
			con.close();
			pre.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return receive;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
     /*   ReceiveDaoImp rdi=new ReceiveDaoImp();
        long gid=0;
        if(rdi.getAllReceiveMeg(gid)!=null)
        	System.out.printf("查看全部信息");
        if(rdi.getAllUnReadMeg(gid)!=null)
        	System.out.printf("查看未读信息");
        	*/
	}

}
