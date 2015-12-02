package com.zzu.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.zzu.dao.LetterDao;
import com.zzu.modle.Letter;
import com.zzu.util.DBtools;

import databaseconnection.DataBase;

public class LetterDaoImp implements LetterDao {
	public static void main(String args[]) {
		LetterDaoImp LD = new LetterDaoImp();
		Letter letter = new Letter();
		letter.setCreatetime(new Date());
		letter.setSenderuserid(5l);
		letter.setReceiveuserid(1l);
		letter.setStatus(true);
		letter.setLettercontent("fuguo is so cool!");
		LD.addLetter(letter);
		ArrayList<Letter> arrayList;
		arrayList = LD.getUnRead(5, 1);

		for (Letter le : arrayList) {
			System.out.println(le.getLettercontent());
		}

	}

	// 添加私信
	public long addLetter(Letter letter) {
		Connection conn = DataBase.getConnection();
		PreparedStatement ptmt = null;
		String sql = "insert into letter(lettercontent,senderuserid,receiveuserid,status) values(?,?,?,?)";
		long id = -1;
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, letter.getLettercontent());
			ptmt.setLong(2, letter.getSenderuserid());
			ptmt.setLong(3, letter.getReceiveuserid());
			ptmt.setBoolean(4, letter.isStatus());
			System.out.println(ptmt);
			int result = ptmt.executeUpdate();
			id = DBtools.GetLastID(conn);

			if (result != 0) {
				System.out.println("添加私信成功!");
			} else {
				System.out.println("添加私信失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBase.freeStatement(conn, ptmt);
		}
		return id;
	}

	// 返回sender和receiver之间的最新30条消息
	public ArrayList<Letter> getLetters(long sender, long receiver) {
		Connection conn = DataBase.getConnection();
		PreparedStatement ptmt = null;
		ArrayList<Letter> list = new ArrayList<Letter>();
		long letterid, senduserid, receiveuserid;
		String lettercontent;
		Date createtime;
		boolean status = false;
		//11.30改
		String sql = "select * from letter where senderuserid=? and receiveuserid=? order by createtime desc";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setLong(1, sender);
			ptmt.setLong(2, receiver);
			ResultSet rs = ptmt.executeQuery();
			while (rs.next()) {
				letterid = rs.getLong("letterid");
				lettercontent = rs.getString("lettercontent");
				senduserid = rs.getLong("senderuserid");
				receiveuserid = rs.getLong("receiveruserid");
				status = rs.getBoolean("status");
				createtime = rs.getTimestamp("createtime");

				Letter pl = new Letter();
				pl.setLetterid(letterid);
				pl.setLettercontent(lettercontent);
				pl.setSenderuserid(senduserid);
				pl.setReceiveuserid(receiveuserid);
				pl.setStatus(status);
				pl.setCreatetime(createtime);
				list.add(pl);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.freeStatement(conn, ptmt);
		}
		ArrayList<Letter> lists = new ArrayList<Letter>();
		int k=list.size();
		if(k>30){
			k=30;
		}
		for (int i=0; i<k; i++) {
			lists.add(list.get(i));
		}
		return lists;
	}

	// 返回用户sender和receiver之间的未读消息
	public ArrayList<Letter> getUnRead(long sender, long receiver) {
		Connection conn = DataBase.getConnection();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		ArrayList<Letter> list = new ArrayList<Letter>();
		long letterid, senduserid, receiveuserid;
		String lettercontent;
		Date createtime;
		boolean status = true;
		//按时间降序查询 //11.30改
		String sql = "select * from letter where senderuserid=? and receiveuserid=? and status=? order by createtime desc";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setLong(1, sender);
			ptmt.setLong(2, receiver);
			ptmt.setBoolean(3, true);
			rs = ptmt.executeQuery();
			while (rs.next()) {
				letterid = rs.getLong("letterid");
				lettercontent = rs.getString("lettercontent");
				senduserid = rs.getLong("senderuserid");
				receiveuserid = rs.getLong("receiveuserid");
				status = rs.getBoolean("status");
				createtime = rs.getTimestamp("createtime");
				Letter pl = new Letter();
				pl.setLetterid(letterid);
				pl.setLettercontent(lettercontent);
				pl.setSenderuserid(senduserid);
				pl.setReceiveuserid(receiveuserid);
				pl.setStatus(status);
				pl.setCreatetime(createtime);
				list.add(pl);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.free(rs, conn, ptmt);
		}
		return list;
	}

	// 返回用户sender和receiver之间是否有未读的消息,返回true即为有未读消息
	public boolean ifUnRead(long sender, long receiver) {
		Connection conn = DataBase.getConnection();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		boolean status = false;
		String sql = "select * from letter where senderuserid=? and receiveuserid=? and status=?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setLong(1, sender);
			ptmt.setLong(2, receiver);
			ptmt.setBoolean(3, false);
			rs = ptmt.executeQuery();
			if (rs.next()) {
				status = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.free(rs, conn, ptmt);
		}
		return status;
	}

	// 设置私信为已读,即设置status为0
	public void setAlreadyRead() {
		Connection conn = DataBase.getConnection();
		PreparedStatement ptmt = null;
		String sql = "update letter set status=false";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.freeStatement(conn, ptmt);
		}
	}

	@Override
	public ArrayList<Letter> getUnRead(long receiver) {
		// TODO Auto-generated method stub
		ArrayList<Letter> ans = new ArrayList<Letter>();
		String sql = "select * from letter where receiveuserid=? and status=true";
		Connection con = DataBase.getConnection();
		PreparedStatement pre = null;
		ResultSet res = null;
		try{
			pre = con.prepareStatement(sql);
			pre.setLong(1, receiver);
			res = pre.executeQuery();
			while(res.next()){
				Letter l = new Letter();
				l.setCreatetime(res.getTimestamp("createtime"));
				l.setLettercontent(res.getString("lettercontent"));
				l.setLetterid(res.getLong("letterid"));
				l.setSenderuserid(res.getLong("senderuserid"));
				l.setStatus(res.getBoolean("status"));
				ans.add(l);
			}
		}catch(Exception e){
			System.out.println("\nxufuguo:shihu:getUnRead()");
			e.printStackTrace();
		}
		return ans;
	}
}
