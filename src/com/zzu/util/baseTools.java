package com.zzu.util;

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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new baseTools().getNowTosql().toString());
	}

}
