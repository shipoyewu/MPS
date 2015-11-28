package com.zzu.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class baseTools {
	//将一个“yyyy-mm-dd”格式的时间函数返回成能直接插入到数据库中的问
	public java.sql.Date str2Date(String str){
		Date a = null;
		try{
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			a =  s.parse(str);
		}catch(Exception e){
			System.out.println("\nshihu: str2date");
			e.printStackTrace();
		}
		return new java.sql.Date(a.getTime());
	}
	//得到当前的时间，返回值能直接插入到数据库
	public java.sql.Date getNowTosql(){
		return new java.sql.Date(new Date().getTime());
	}
	//得到Timestamp类型的方法
	public java.sql.Timestamp getTimePrecise(){
		Date today=new Date();
		java.sql.Timestamp dateTime = null;
		try{
			SimpleDateFormat formatDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//hh:mm:ss为12小时制
			String time=formatDate.format(today);
			java.util.Date timeDate = formatDate.parse(time);
			dateTime = new java.sql.Timestamp(timeDate.getTime());
	
		 }catch(Exception e){
			e.printStackTrace();
		}
		return dateTime;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.out.println(new baseTools().getNowTosql().toString());
		baseTools bt = new baseTools();
		System.out.println(bt.getTimePrecise().toString());
	}

}
