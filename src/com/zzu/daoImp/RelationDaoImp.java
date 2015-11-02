package com.zzu.daoImp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.mysql.fabric.xmlrpc.base.Array;
import com.zzu.dao.RelationDao;
import com.zzu.modle.Group;
import com.zzu.modle.Relation;
import com.zzu.modle.User;

import databaseconnection.DataBase;

public class RelationDaoImp implements RelationDao {

	@Override
	public ArrayList<ArrayList<Long>> findDown(long groupid) {
		// TODO Auto-generated method stub
		Connection con = new DataBase().getConnection();
		
		ArrayList<ArrayList<Long>> tree = new ArrayList<ArrayList<Long>>();
		Queue<Long> que =(Queue<Long>) new ArrayList<Long>();
		que.offer(groupid);
		HashMap<Long,Integer> ma = new HashMap<Long, Integer>();
		ma.put(groupid,0);
		
		String sql = "select groupid from relation where up=?";
		
		while(!que.isEmpty()){
			Long u = que.poll();
			PreparedStatement pre = null;
			ResultSet res = null;
			try{
				pre = con.prepareStatement(sql);
				pre.setLong(1, u);
				res = pre.executeQuery();
				while(res.next()){
					Long v = res.getLong(1);
					int cen = ma.get(u)+1;
					ma.put(v, cen);
					
					if(tree.size() < cen){
						tree.add(new ArrayList<Long>());
					}
					tree.get(cen).add(v);
					que.add(v);
				}
			}catch(Exception e){
				System.out.println("\nshihu:findUp失败\n");
	            e.printStackTrace();
			}
		}
		try{
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return tree;

	}

	@Override
	public ArrayList<Long> findSameRank(long groupid) {
		// TODO Auto-generated method stub
		Connection con = new DataBase().getConnection();
		PreparedStatement pre = null;
		ResultSet res = null;
		ArrayList<Long> sameRank = new ArrayList<Long>();
		String sql1 = "select groupid from relation where down=?";
		String sql2 = "select groupid from relation where up=?";
		ResultSet same = null;
		PreparedStatement pre2 = null;
		
		try{
			pre = con.prepareStatement(sql1);
			pre.setLong(1, groupid);
			res = pre.executeQuery();
			while(res.next()){
				Long u = res.getLong(1);
				pre2 = con.prepareStatement(sql2);
				pre2.setLong(1, u);
				same=pre2.executeQuery();
				while(res.next()){
					Long k = res.getLong(1);
					sameRank.add(k);
				}
			}
		}catch(Exception e){
			System.out.println("\nshihu:findSameRank\n");
			e.printStackTrace();
		}finally{
			try {
				pre2.close();
				pre.close();
				same.close();
				res.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//去重
		ArrayList<Long> uniqueRank = new ArrayList<Long>();
		Set<Long> se = new HashSet<Long>();
		for(Iterator ter = sameRank.iterator();ter.hasNext();){
			Long ele = (Long)ter.next();
			if(se.add((Long) ele)){
				uniqueRank.add(ele);
			}
		}
		return uniqueRank;
	}

	@Override
	public ArrayList<Long> findAllGroup(long userid) {
		// TODO Auto-generated method stub
		Connection con = new DataBase().getConnection();
		ResultSet res = null;
		PreparedStatement pre = null;
		ArrayList<Long> group = new ArrayList<Long>();
		
		String sql = "select groupid from group where userid=?";
		try{
			pre = con.prepareStatement(sql);
			pre.setLong(1, userid);
			res = pre.executeQuery();
			while(res.next()){
				Long u = res.getLong(1);
				group.add(u);
			}
		}catch(Exception e){
			System.out.println("\nshihu:findAllGroup\n");
			e.printStackTrace();
		}
		finally{
			try{
				res.close();
				pre.close();
				con.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return group;
	}

	@Override
	public boolean addRelation(Relation relation) {
		// TODO Auto-generated method stub
		Connection con = new DataBase().getConnection();
		String sql = "insert into relation(up,down,jointime,isvalid) values(?,?,?,?)";
		PreparedStatement pre = null;
		try{
			con.prepareStatement(sql);
			pre.setLong(1, relation.getUp());
			pre.setLong(2, relation.getDown());
			Date time = new Date(System.currentTimeMillis());
			pre.setDate(3,time);
			pre.setBoolean(4, true);
			pre.execute();
		}catch(Exception e){
			System.out.println("\nshihu:addRelation\n");
			e.printStackTrace();
		}
		try{
			pre.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return false;
	}

	@Override
	public ArrayList<ArrayList<Long>> findUp(long groupid) {
		// TODO Auto-generated method stub
		Connection con = new DataBase().getConnection();
		
		ArrayList<ArrayList<Long>> tree = new ArrayList<ArrayList<Long>>();
		Queue<Long> que =(Queue<Long>) new ArrayList<Long>();
		que.offer(groupid);
		HashMap<Long,Integer> ma = new HashMap<Long, Integer>();
		ma.put(groupid,0);
		
		String sql = "select groupid from relation where down=?";
		
		while(!que.isEmpty()){
			Long u = que.poll();
			PreparedStatement pre = null;
			ResultSet res = null;
			try{
				pre = con.prepareStatement(sql);
				pre.setLong(1, u);
				res = pre.executeQuery();
				while(res.next()){
					Long v = res.getLong(1);
					int cen = ma.get(u)+1;
					ma.put(v,cen);
					if(tree.size() < cen){
						tree.add(new ArrayList<Long>());
					}
					tree.get(cen).add(v);
					que.add(v);
				}
			}catch(Exception e){
				System.out.println("\nshihu:finddown失败\n");
	            e.printStackTrace();
			}
		}
		try{
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return tree;
	}

	public static void main(String args[]){
		
	}
}
//shihu 1342
