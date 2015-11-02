package com.zzu.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
		PreparedStatement sta = null;
		boolean a = false;
		try{
			sta = con.prepareStatement(sql);
			a =  sta.execute(sql);
		}catch(Exception e){
			System.out.println("\nshihu: RowConf\n");
			e.printStackTrace();
		}
		return a;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
