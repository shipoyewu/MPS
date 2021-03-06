package com.zzu.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.zzu.dao.CommentDao;
import com.zzu.modle.Comment;

import databaseconnection.DataBase;

/**
 * 
 * @author 徐富国
 *
 */
public class CommentDaoImp implements CommentDao {

	public CommentDaoImp() {
		
	}

	// 得到评论
	public ArrayList<Comment> getComm(long messageid) {
		Connection conn = DataBase.getConnection();// 得到数据库的连接
		ResultSet rs = null;// 结果集
		PreparedStatement pstmt = null;
		ArrayList<Comment> comments = new ArrayList<Comment>();
		String sql = "select * from comment where messageid=? order by commenttime desc";
		long commentid, messid, groupid;
		String comcontent;
		Date commenttime, deletetime;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, messageid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				commentid = rs.getLong("commentid");
				comcontent = rs.getString("comcontent");
				groupid = rs.getLong("groupid");
				commenttime = rs.getDate("commenttime");
				deletetime = rs.getDate("deletetime");
				messid = rs.getLong("messageid");
				Comment com = new Comment(commentid, comcontent, groupid,
						commenttime, deletetime, messid);
				comments.add(com);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.free(rs, conn, pstmt);
		}
		return comments;
	}

	// 添加评论
	public void addComment(Comment comment) {
		Connection conn = DataBase.getConnection();// 得到数据库的连接
		PreparedStatement pstmt = null;
		String sql = "insert into comment(comcontent,groupid,messageid) values(?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);	
			//其他两个时间字段createtime,deletetime为系统默认时间
			pstmt.setString(1, comment.getComcontent());
			pstmt.setLong(2, comment.getGroupid());
			pstmt.setLong(3, comment.getMessageid());
			int result = pstmt.executeUpdate();
			if (result != 0) {
				System.out.println("添加评论成功!");
			} else {
				System.out.println("添加评论失败!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.freeStatement(conn, pstmt);
		}
	}

	// 删除评论
	public void deleteComment(long commentid) {
		Connection conn = DataBase.getConnection();// 得到数据库的连接
		PreparedStatement pstmt = null;
		String sql = "delete from comment where commentid=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, commentid);
			int result = pstmt.executeUpdate();
			if (result != 0) {
				System.out.println("删除评论成功!");
			} else {
				System.out.println("删除评论失败!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.freeStatement(conn, pstmt);
		}
	}

	public static void main(String args[]) {
		CommentDaoImp com = new CommentDaoImp();
		long messid=(long) 1;
		//ArrayList<Comment> coms=com.getComm(messid);//查询
		//System.out.println(coms);
		com.addComment(new Comment(1,"测试实例",1,new Date(),new Date(),1));//添加
		//com.deleteComment(10);//删除
	}
}
