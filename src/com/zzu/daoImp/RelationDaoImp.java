package com.zzu.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import com.zzu.dao.RelationDao;
import com.zzu.modle.Group;
import com.zzu.modle.Relation;
import com.zzu.util.DBtools;
import com.zzu.util.JsonRelation;

import databaseconnection.DataBase;

public class RelationDaoImp implements RelationDao {
	
	/*
	 * (non-Javadoc)
	 * @see com.zzu.dao.RelationDao#findDown(long)
	 * 根据groupid得到全部的下级的groupid
	 */
	@Override
	public ArrayList<ArrayList<Long>> findDown(long groupid) {
		new DataBase();
		// TODO Auto-generated method stub
		Connection con = DataBase.getConnection();
		
		ArrayList<ArrayList<Long>> tree = new ArrayList<ArrayList<Long>>();
		Queue<Long> que = null;
		try {
			que =new LinkedList<Long>();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		que.offer(groupid);
		HashMap<Long,Integer> ma = new HashMap<Long, Integer>();
		ma.put(groupid,0);
		ArrayList<Long> root = new ArrayList<Long>();
		root.add(groupid);
		tree.add(root);
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
					System.out.println(tree.size()+" "+cen);
					if(tree.size() <= cen){
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
		new DataBase();
		// TODO Auto-generated method stub
		Connection con = DataBase.getConnection();
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
			if(se.add(ele)){
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
		new DataBase();
		// TODO Auto-generated method stub
		Connection con = DataBase.getConnection();
		ResultSet res = null;
		PreparedStatement pre = null;
		ArrayList<Long> group = new ArrayList<Long>();
		
		String sql = "select groupid from fork where userid=? and isvalue=true";
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
		new DataBase();
		// TODO Auto-generated method stub
		Connection con = DataBase.getConnection();
		
		if(relation.getDown() == relation.getUp()) return false;
		Queue<Long> que = null;
		try {
			que =new LinkedList<Long>();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
		que.offer(relation.getDown());
		LinkedList<Long> up = new LinkedList<Long>(); 
		
		String sql = "select up from relation where down=?";
		while(!que.isEmpty()){
			long u = que.poll();
			PreparedStatement pre = null;
			ResultSet res = null;
			try{
				pre = con.prepareStatement(sql);
				pre.setLong(1, u);
				res = pre.executeQuery();
				int flag  = 0;
				while(res.next()){
					Long v = res.getLong(1);
					que.add(v);
					flag ++;
				}
				if(flag == 0){
					up.add(u);
				}
			}catch(Exception e){
				System.out.println("\nshihu:addRelation\n");
	            e.printStackTrace();
	            return false;
			}
		}
		
		for(int i = 0;i < up.size();i++){
			ArrayList<ArrayList<Long>> down =  findDown(up.get(i));
			
			for(int j = 0;j < down.size();j++){
				ArrayList<Long> a = down.get(j);
				for(int k = 0;k < a.size();k++){
					if(a.get(k) == relation.getDown()){
						return false;
					}
				}
			}
			
		}
		
		String sql1 = "insert into relation(up,down,isvalid) values(?,?,?)";
		PreparedStatement pre = null;
		try{
			pre = con.prepareStatement(sql1);
			pre.setLong(1, relation.getUp());
			pre.setLong(2, relation.getDown());
			pre.setBoolean(3, true);
			pre.execute();
		}catch(Exception e){
			System.out.println("\nshihu:addRelation\n");
			e.printStackTrace();
			return false;
		}
		try{
			pre.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
	}
	
	/*
	 * 根据groupid得到全部的上级
	 * (non-Javadoc)
	 * @see com.zzu.dao.RelationDao#findUp(long)
	 */
	@Override
	public ArrayList<ArrayList<Long>> findUp(long groupid) {
		// TODO Auto-generated method stub
		Connection con = DataBase.getConnection();

		ArrayList<ArrayList<Long>> tree = new ArrayList<ArrayList<Long>>();
		Queue<Long> que = null;
		try {
			que =new LinkedList<Long>();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		que.offer(groupid);
		HashMap<Long,Integer> ma = new HashMap<Long, Integer>();
		ma.put(groupid,0);
		ArrayList<Long> root = new ArrayList<Long>();
		root.add(groupid);
		tree.add(root);
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
					if(tree.size() <= cen){
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
	
	@Override
	public void delRelation(long up, long down) {
		// TODO Auto-generated method stub
		String del="delete from relation where up="+"\'"+up+"\'"+" and down="+"\'"+down+"\';";
		DBtools.RowDel(del);
	}
	@Override
	public void delRelation(long forkid) {
		// TODO Auto-generated method stub
		String delup ="delete from relation where down="+"\'"+forkid+"\';";
		String deldown = "delete from relation where up="+"\'"+forkid+"\';";
		DBtools.RowDel(delup);
		DBtools.RowDel(deldown);
	}
		
	/**
	 * 2015/11/2,23:05 添加了findUp findDwon findSameRank findAllGroup的无数据测试
	 */
	private void test() {
		
		ArrayList<JsonRelation> g = getJsonRela(1);
		Iterator<JsonRelation> item = g.iterator();
		/*while(item.hasNext()){
			JsonRelation j = item.next();
			System.out.println("id:"+j.getId());
			System.out.println("pid:"+j.getPId());
			System.out.println("name:"+j.getName());
		}*/
		Relation a = new Relation();
		a.setUp(1);
		a.setDown(2);
		System.out.println(addRelation(a));
		/*Long groupid = Long.parseLong("100");
		ArrayList<Long> b = this.findAllGroup(groupid);
		for(int i = 0;i < b.size();i++){
			System.out.println(b.get(i));
		}
		
		ArrayList<ArrayList<Long>> c = this.findUp(5);
		ArrayList<ArrayList<Long>> d = this.findDown(groupid);
		System.out.println(c.get(0).get(0));
		System.out.println(c.get(1).get(0));*/
		/*System.out.println("asdad");
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
		}*/
	
	}
	@Override
	public ArrayList<Group> getDownOne(long groupid){
		String sql = "select groupid,groupname from relation,fork where up=? and fork.groupid=down and isvalue=true";
		Connection con = DataBase.getConnection();
		PreparedStatement pre = null;
		ResultSet res= null;
		ArrayList<Group> ans = new ArrayList<Group>();
		
		try{
			pre = con.prepareStatement(sql);
			pre.setLong(1,groupid);
			res = pre.executeQuery();
			while(res.next()){
				Group group = new Group();
				group.setGroupid(res.getLong("groupid"));
				group.setGroupname(res.getString("groupname"));
				ans.add(group);
			}
		}catch(Exception e){
			System.out.println("\nshihu:GetDownOne()");
			e.printStackTrace();
		}finally{
			DataBase.free(res, con, pre);
		}
		return ans;
	}
	@Override
	public ArrayList<JsonRelation> getJsonRela(long groupid){//给一个groupid，返回适合json格式的上下级关系list
		ArrayList<JsonRelation> jrList = new ArrayList<JsonRelation>();
		String name = getUserName(groupid);
		Group g = new GroupDaoImp().getGroup(groupid);
		jrList.add(new JsonRelation(groupid, 0, g.getGroupname()));//该用户的group作为根节点
		Queue<Long> que = null;
		try {
			que =new LinkedList<Long>();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		que.add(groupid);
		Set<Long> vis = new HashSet<Long>();
		vis.add(groupid);
		while(!que.isEmpty()){
			long u = que.poll();
			ArrayList<Group> next = new ArrayList<Group>();
			next = getDownOne(u);
			for(int i = 0;i < next.size();i++){
				long v = next.get(i).getGroupid();
				if(!vis.contains(new Long(v))){
					if(u == 0){
						jrList.add(new JsonRelation(v,u,getUserName(next.get(i).getGroupid()),"true","root"));
					}
					else{
						jrList.add(new JsonRelation(v,u,getUserName(next.get(i).getGroupid()),"false","up_user"));
					}
					que.add(v);
					vis.add(v);
				}
			}
		}
		return jrList;
	} 	
	public static void main(String args[]){
		new RelationDaoImp().test();
	}
	

	@Override
	public String getUserName(long groupid) {
		// TODO Auto-generated method stub
		String sql = "select username from user inner join fork using( userid) where groupid=? and isvalue=true";
		Connection con = DataBase.getConnection();
		PreparedStatement pre = null;
		ResultSet res = null;
		String ans = null;
		try{
			pre = con.prepareStatement(sql);
			pre.setLong(1, groupid);
			res = pre.executeQuery();
			if(res.next()){
				ans = res.getString("username");
			}
		}catch(Exception e){
			System.out.println("\nshihu:getUserName()");
			e.printStackTrace();
		}finally{
			DataBase.free(res, con, pre);
		}
		
		return ans;
	}

	@Override
	public boolean ifCanRecv(long up, long down) {
		// TODO Auto-generated method stub
		
		ArrayList<ArrayList<Long> >  d = findDown(up);
		
		for(int i = 0;i < d.size();i++){
			ArrayList<Long> a = d.get(i);
			for(int j = 0;j < a.size();j++){
				if(a.get(j).longValue() == down){
					return true;
				}
			}
		}
		return false;
	}


	
	

}
///shihu
