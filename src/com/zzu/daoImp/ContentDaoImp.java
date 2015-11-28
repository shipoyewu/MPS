package com.zzu.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.zzu.dao.ContentDao;
import com.zzu.modle.Content;

import databaseconnection.DataBase;


public class ContentDaoImp implements ContentDao {


	@Override
	public Content getContent(long contentid) {
		// TODO Auto-generated method stub
	
		Connection con = new DataBase().getConnection();
		String sql = "select * from content where contentid=?";
		Content  c=new Content();
		PreparedStatement pre = null;
		ResultSet res = null;
	
		try{
			pre=con.prepareStatement(sql);
			pre.setLong(1, contentid);
			res=pre.executeQuery();
			while(res.next())
			{
				String u=res.getString(1);
				String t=res.getString(2);
				String i=res.getString(3);
				String f=res.getString(4);
				c.setContentid(contentid);
				c.setFile(t);
				 c.setImage(t);
				c.setText(f);
			}
			
		}catch(Exception e){
			System.out.println("\nxingjiali:getcontent\n");
			e.printStackTrace();
		}
		try{
			pre.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	
	     return c;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
