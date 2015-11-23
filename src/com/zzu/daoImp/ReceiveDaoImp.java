package com.zzu.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.zzu.dao.ReceiveDao;

import databaseconnection.DataBase;

public class ReceiveDaoImp implements ReceiveDao {

	@Override
	public ArrayList<Object> getAllReceiveMeg(long forkid) {
		// TODO Auto-generated method stub
		Connection con=new DataBase().getConnection();
		String sql="select * from receive where forkid=?";
		PreparedStatement pre=null;
		ResultSet res=null;
		ArrayList<Object> receive=new ArrayList<Object>();
		try
		{
			pre = con.prepareStatement(sql);
			pre.setLong(1, forkid);
			res = pre.executeQuery();
			while(res.next()){
				Long u = res.getLong(1);
				Long m = res.getLong(2);
				boolean b=res.getBoolean(3);
				receive.add(u);
				receive.add(u);
				receive.add(m);
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

	@Override
	public ArrayList<Object> getAllUnReadMeg(long forkid) {
		// TODO Auto-generated method stub
		Connection con=new DataBase().getConnection();
		String sql="select * from receive where forkid=? and status='true'";
		PreparedStatement pre=null;
		ResultSet res=null;
		ArrayList<Object> receive=new ArrayList<Object>();
		try
		{
			pre = con.prepareStatement(sql);
			pre.setLong(1, forkid);
			res = pre.executeQuery();
			while(res.next()){
				Long u = res.getLong(1);
				Long m = res.getLong(2);
				boolean b=res.getBoolean(3);
				receive.add(u);
				receive.add(u);
				receive.add(m);
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

	}

}
