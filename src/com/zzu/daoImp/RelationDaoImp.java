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
import java.util.LinkedList;
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
	
	/*
	 * (non-Javadoc)
	 * @see com.zzu.dao.RelationDao#findDown(long)
	 * 根据groupid得到全部的下级的groupid
	 */
	@Override
	public ArrayList<ArrayList<Long>> findDown(long groupid) {
		// TODO Auto-generated method stub
		Connection con = new DataBase().getConnection();
		ArrayList<ArrayList<Long>> tree = new ArrayList<ArrayList<Long>>();
		Queue<Long> que = null;
		try {
			que =(Queue<Long>) new LinkedList<Long>();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		que.offer(groupid);
		HashMap<Long,Integer> ma = new HashMap<Long, Integer>();
		ma.put(groupid,0);
		
		String sql = "select down from relation where up=?";
		
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

	/*
	 * 得到所有的同级的fork的group不会有重复
	 * (non-Javadoc)
	 * @see com.zzu.dao.RelationDao#findSameRank(long)
	 * 
	 */
	@Override
	public ArrayList<Long> findSameRank(long groupid) {
		// TODO Auto-generated method stub
		Connection con = new DataBase().getConnection();
		PreparedStatement pre = null;
		ResultSet res = null;
		ArrayList<Long> sameRank = new ArrayList<Long>();
		String sql1 = "select up from relation where down=?";
		String sql2 = "select down from relation where up=?";
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
				if(pre2 != null)
				pre2.close();
				System.out.println("dasda");
				pre.close();
				System.out.println("B");
				if(same!=null)
				same.close();
				System.out.println("C");
				res.close();
				System.out.println("D");
				con.close();
				System.out.println("E");
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

	/*
	 * 根据userid得到全部的group
	 * (non-Javadoc)
	 * @see com.zzu.dao.RelationDao#findAllGroup(long)
	 */
	@Override
	public ArrayList<Long> findAllGroup(long userid) {
		// TODO Auto-generated method stub
		Connection con = new DataBase().getConnection();
		ResultSet res = null;
		PreparedStatement pre = null;
		ArrayList<Long> group = new ArrayList<Long>();
		
		String sql = "select groupid from fork where userid=?";
		try{
			pre = con.prepareStatement(sql);
			pre.setLong(1, userid);
			res = pre.executeQuery();
			while(res.next()){
				Long u = res.getLong(1);
				group.add(u);
			}
		}catch(Exception e){
			System.out.println(pre);
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
	
	/*
	 *	添加一个关系，如果失败返回false
	 * (non-Javadoc)
	 * @see com.zzu.dao.RelationDao#addRelation(com.zzu.modle.Relation)
	 */
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
	
	/*
	 * 根据groupid得到全部的上级
	 * (non-Javadoc)
	 * @see com.zzu.dao.RelationDao#findUp(long)
	 */
	@Override
	public ArrayList<ArrayList<Long>> findUp(long groupid) {
		// TODO Auto-generated method stub
		Connection con = new DataBase().getConnection();
		
		ArrayList<ArrayList<Long>> tree = new ArrayList<ArrayList<Long>>();
		Queue<Long> que = null;
		try {
			que =(Queue<Long>) new LinkedList<Long>();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		que.offer(groupid);
		HashMap<Long,Integer> ma = new HashMap<Long, Integer>();
		ma.put(groupid,0);
		
		String sql = "select up from relation where down=?";
		
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
				System.out.println("\nshihu:findup失败\n");
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

	/**
	 * 2015/11/2,23:05 添加了findUp findDwon findSameRank findAllGroup的无数据测试
	 */
	private void test() {
		Long groupid = Long.parseLong("100");
		ArrayList<Long> b = this.findAllGroup(groupid);
		for(int i = 0;i < b.size();i++){
			System.out.println(b.get(i));
		}
		
		ArrayList<ArrayList<Long>> c = this.findUp(groupid);
		ArrayList<ArrayList<Long>> d = this.findDown(groupid);
		System.out.println("asdad");
		if(d == null){
			System.out.println("sdad");
		}
		ArrayList<Long> e = this.findSameRank(groupid);

		
		for(int i = 0;i < c.size();i++){
			ArrayList<Long> tmp = c.get(i);
			for(int j = 0;j < tmp.size();j++){
				System.out.println(tmp.get(j));
			}
		}
		
		for(int i = 0;i < d.size();i++){
			ArrayList<Long> tmp = d.get(i);
			for(int j = 0;j < tmp.size();j++){
				System.out.println(tmp.get(j));
			}
		}
		
		for(int j = 0;j < e.size();j++){
			System.out.println(e.get(j));
		}
	
	}
	public static void main(String args[]){
		new RelationDaoImp().test();
	}
}
///shihu
