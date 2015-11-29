package com.zzu.daoImp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.zzu.dao.ContentDao;
import com.zzu.modle.Content;
import com.zzu.util.DBtools;

import databaseconnection.DataBase;

/**
 * 
 * @author xingjiali
 *
 */
public class ContentDaoImp implements ContentDao {


	@Override
	public Content getContent(long contentid) {
		// TODO Auto-generated method stub
		
		Connection con = new DataBase().getConnection();
		String sql = "select *from content where contentid=?";
		Content  c=new Content();
		PreparedStatement pre = null;
		ResultSet res = null;	
		try{
			pre=con.prepareStatement(sql);
			pre.setLong(1, contentid);
			res=pre.executeQuery();
			while(res.next())
			{
				long u=res.getLong("contentid");
				String t=res.getString("text");
				String i=res.getString("image");
				String f=res.getString("file");
				c.setContentid(u);
				c.setFile(f);
				c.setImage(i);
				c.setText(t);
				//System.out.println(u);
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

	public long addContent(Content content)
	{
		Connection con=new DataBase().getConnection();
		String sql="insert into content(text,image,file)value(?,?,?)";
		PreparedStatement pre=null;
		long id=0;
		  try
	        {
	        	pre=con.prepareStatement(sql);	        	
	           	pre.setString(1,content.getText());
	        	pre.setString(2, content.getImage());
	        	pre.setString(3, content.getFile());
	        	pre.executeUpdate();
	            id=new DBtools().GetLastID(con);
	    		
	        }catch(Exception e){
	        	System.out.println("\nxingjiali:Contentdaoimp:addcontent\n");
				e.printStackTrace();
	        }
	        try
	        {
	          pre.close();
	          con.close();
	        }catch(Exception e){
	        	e.printStackTrace();
	        }
	      return id;
	
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Content  c=new Content();		
		c.setFile("abc");
		c.setImage("def");
		c.setText("woshixingjiali");		
   		ContentDaoImp cdi=new ContentDaoImp();
   		cdi.addContent(c);
   		System.out.println(cdi.getContent(134).getText());
		System.out.println(cdi.addContent(c));
   
	}

}
