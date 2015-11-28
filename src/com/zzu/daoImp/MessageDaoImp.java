package com.zzu.daoImp;

<<<<<<< HEAD
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;
import com.zzu.dao.MessageDao;
import com.zzu.modle.Message;

import databaseconnection.DataBase;

/**
 * 
 * @author 徐富国
 *
 */
public class MessageDaoImp implements MessageDao {

	// 得到消息的标题
	public String getTitle(long messageid) {
		Connection conn = DataBase.getConnection();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String title = null;
		String sql = "select messagetitle from message where messageid=?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setLong(1, messageid);
			rs = ptmt.executeQuery();
			if (rs.next()) {
				title = rs.getString("messagetitle");
			}
			return title;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.free(rs, conn, ptmt);
		}
		return null;
	}

	// 添加消息
	public long addMessage(Message msg) {
		Connection conn = DataBase.getConnection();
		PreparedStatement ptmt = null;
		long id=0;//返回0则添加失败
		String sql = "insert into message values(?,?,?,?,?,?,?,?,?,?)";
		try {
			ptmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); 
			ptmt.setLong(1, msg.getMessageid());
			ptmt.setString(2, msg.getMessagetitle());
			ptmt.setLong(3, msg.getContentid());
			ptmt.setLong(4, msg.getGroupid());
			ptmt.setBoolean(5, msg.isIsvalue());
			ptmt.setDate(6, new java.sql.Date(msg.getCreatetime().getTime()));
			ptmt.setBoolean(7, msg.isIsremind());
			ptmt.setBoolean(8, msg.isIscomment());
			ptmt.setDate(9, new java.sql.Date(msg.getRemindtime().getTime()));
			ptmt.setDate(10, new java.sql.Date(msg.getDeletetime().getTime()));
			int result = ptmt.executeUpdate(sql);
            //检索由于执行此 Statement 对象而创建的所有自动生成的键
            ResultSet rs = ptmt.getGeneratedKeys();
            if (rs.next()) {
                    //知其仅有一列，故获取第一列
                   	id = rs.getLong(1);
                    System.out.println("-----预定义SQL模式-----id = " + id);
            } 
			if (result != 0) {
				System.out.println("添加消息成功!");
			} else {
				System.out.println("添加消息失败!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.freeStatement(conn, ptmt);
		}
		return id;
	}

	// 查看该消息是否有效，即是否被删除
	public boolean isValid(long messageid) {
		Connection conn = DataBase.getConnection();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		boolean isValue = false;
		String sql = "select isvalue from message where messageid=?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setLong(1, messageid);
			rs = ptmt.executeQuery();
			if (rs.next()) {
				isValue = rs.getBoolean("isvalue");
			}
			if (isValue = true) {
				System.out.println("消息有效!");
			} else {
				System.out.println("消息无效!");
			}
			return isValue;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.free(rs, conn, ptmt);
		}
		return isValue;
	}

	// 删除消息，isvalue置为false(假删除)
	public void deleteMsg(long messageid) {
		Connection conn = DataBase.getConnection();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		//boolean isValue = false;
		String sql = "update message set isvalue=false where messageid=?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setLong(1, messageid);
			int result = ptmt.executeUpdate(sql);
			if (result != 0) {
				System.out.println("删除消息成功!");
			} else {
				System.out.println("删除消息失败!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.free(rs, conn, ptmt);
		}
	}

	// 得到contentid
	public long getContent(long messageid) {
		Connection conn = DataBase.getConnection();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		Long contentid = null;
		String sql = "select contentid from message where messageid=?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setLong(1, messageid);
			rs = ptmt.executeQuery();
			if (rs.next()) {
				contentid = rs.getLong("contentid");
			}
			if (contentid != 0) {
				System.out.println("成功得到contentid" + contentid);
			} else {
				System.out.println("得到contentid失败!" + contentid);
			}
			return contentid;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.free(rs, conn, ptmt);
		}
		return 0;
	}

	public static void main(String args[]) {
		MessageDaoImp mes = new MessageDaoImp();
	}
}
=======
import com.zzu.dao.MessageDao;
import com.zzu.modle.Message;

public class MessageDaoImp implements MessageDao {

	public MessageDaoImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getTitle(long messageid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addMessage(Message msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(long messageid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteMsg(long messageid) {
		// TODO Auto-generated method stub

	}

	@Override
	public long getContent(long messageid) {
		// TODO Auto-generated method stub
		return 0;
	}

}
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
