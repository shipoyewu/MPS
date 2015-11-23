package com.zzu.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class baseTools {
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
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new baseTools().str2Date("2012-12-09"));
	}

}
