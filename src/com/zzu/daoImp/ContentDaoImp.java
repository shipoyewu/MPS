<<<<<<< HEAD
package com.zzu.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.zzu.dao.ContentDao;
import com.zzu.modle.Content;

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
				long u=res.getLong("contentid");
				String t=res.getString("text");
				String i=res.getString("image");
				String f=res.getString("file");
				c.setContentid(u);
				c.setFile(t);
				c.setImage(i);
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
=======
package com.zzu.daoImp;

import com.zzu.dao.ContentDao;
import com.zzu.modle.Content;

public class ContentDaoImp implements ContentDao{

	public ContentDaoImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Content getContent(long contentid) {
		// TODO Auto-generated method stub
		return null;
	}

}
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
