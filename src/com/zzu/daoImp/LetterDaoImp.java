package com.zzu.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.zzu.dao.LetterDao;
import com.zzu.modle.Letter;



import databaseconnection.DataBase;

public class LetterDaoImp implements LetterDao {
	public static void main(String args[]){
		LetterDaoImp LD = new LetterDaoImp();
		Letter letter= new Letter();
		letter.setCreatetime(new Date());
		letter.setSenderuserid(5l);
		letter.setReceiveuserid(1l);
		letter.setStatus(true);
		letter.setLettercontent("fuguo is so cool!");
		LD.addLetter(letter);
		ArrayList<Letter> arrayList;
		arrayList = LD.getUnRead(5,1);
		
		for (Letter le : arrayList) {
			System.out.println(le.getLettercontent());
		} 
		
	}
	//添加私信
	public void addLetter(Letter letter) {
		Connection conn = DataBase.getConnection();
		PreparedStatement ptmt = null;
		String sql = "insert into letter(lettercontent,senderuserid,receiveuserid,status,createtime) values(?,?,?,?,?)";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setString(1, letter.getLettercontent());
			ptmt.setLong(2, letter.getSenderuserid());
			ptmt.setLong(3, letter.getReceiveuserid());	
			ptmt.setBoolean(4,letter.isStatus());
			ptmt.setDate(5, new java.sql.Date(letter.getCreatetime().getTime()));
			System.out.println(ptmt);
			int result = ptmt.executeUpdate();
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
	}

	//返回sender和receiver之间的最新30条消息
	public ArrayList<Letter> getLetters(long sender, long receiver) {
		Connection conn = DataBase.getConnection();
		PreparedStatement ptmt = null;
		ArrayList<Letter> list=new ArrayList<Letter>();
		long letterid, senduserid, receiveuserid;
		String lettercontent;
		Date createtime;
		boolean status=false;
		String sql = "select * from letter where senderuserid=? and receiveuserid=?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setLong(1, sender);
			ptmt.setLong(2, receiver);
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()){
				letterid=rs.getLong("letterid");
				lettercontent=rs.getString("lettercontent");
				senduserid=rs.getLong("senderuserid");
				receiveuserid=rs.getLong("receiveruserid");
				status=rs.getBoolean("status");
				createtime=rs.getDate("createtime");
				
				Letter pl=new Letter();
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
		ArrayList<Letter> lists=new ArrayList<Letter>();
		for(int i=(list.size()-1)%30;i>=0;i--){//倒序输出最多30个
			lists.add(list.get(i));
		}
		return lists;
	}

	//返回用户sender和receiver之间的未读消息
	public ArrayList<Letter> getUnRead(long sender, long receiver) {
		Connection conn = DataBase.getConnection();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		ArrayList<Letter> list=new ArrayList<Letter>();
		long letterid, senduserid, receiveuserid;
		String lettercontent;
		Date createtime;
		boolean status=true;
		String sql = "select * from letter where senderuserid=? and receiveuserid=? and status=?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setLong(1, sender);
			ptmt.setLong(2, receiver);
			ptmt.setBoolean(3, true);
			rs = ptmt.executeQuery();
			while(rs.next()) {
				letterid=rs.getLong("letterid");
				lettercontent=rs.getString("lettercontent");
				senduserid=rs.getLong("senderuserid");
				receiveuserid=rs.getLong("receiveuserid");
				status=rs.getBoolean("status");
				createtime=rs.getDate("createtime");
				Letter pl=new Letter();
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

	//返回用户sender和receiver之间是否有未读的消息,返回true即为有未读消息
	public boolean ifUnRead(long sender, long receiver) {
		Connection conn = DataBase.getConnection();
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		boolean status=false;
		String sql = "select * from letter where senderuserid=? and receiveuserid=? and status=?";
		try {
			ptmt = conn.prepareStatement(sql);
			ptmt.setLong(1, sender);
			ptmt.setLong(2, receiver);
			ptmt.setBoolean(3, false);
			rs = ptmt.executeQuery();
			if(rs.next()) {
				status=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.free(rs, conn, ptmt);
		}
		return status;
	}
	

}
