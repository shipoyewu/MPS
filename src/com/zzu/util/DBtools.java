package com.zzu.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import databaseconnection.DataBase;

/**
 * 
 * @author shihu
 *这个DBtools中有很多的关于数据库的简单操作
 *
 */
public class DBtools {
	
	/*
	 * 这个工具是对数据库中的某一个元组进行查询，看是否存在这个元组
	 */
	public static boolean RowConf(String sql){
		Connection con = new DataBase().getConnection();
		if(sql.charAt(sql.length()-1)!=';'){
			sql+=";";
		}
		PreparedStatement sta = null;
		boolean a = false;
		try{
			sta = con.prepareStatement(sql);
			ResultSet res = sta.executeQuery();
			if(res.next()){
				a = true;
			}
		}catch(Exception e){
			System.out.println("\nshihu: RowConf\n");
			e.printStackTrace();
		}finally{
			try{
				if(sta!=null){
					sta.close();
				}
				con.close();
			}catch(Exception e){
				System.out.println("\nshihu:RowConf");
				e.printStackTrace();
			}
		}
		return a;
	}
	
	public static boolean RowDel(String del){
		
		Connection con = new DataBase().getConnection();
		PreparedStatement pre = null;
		boolean flag = false; 
		if(del.charAt(del.length()-1)!=';'){
			del+=";";
		}
		
		try{
			pre = con.prepareStatement(del);
			flag = pre.execute();
		}catch(Exception e){
			System.out.println("\nshihu:RowDel");
			e.printStackTrace();
		}
		return flag;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
