package com.zzu.dao;

import java.util.ArrayList;

import com.zzu.modle.Group;
import com.zzu.modle.Relation;
import com.zzu.util.JsonRelation;
/**
 * 
 * @author shihu
 *
 */
public interface RelationDao {
	public ArrayList<ArrayList<Long>> findUp(long groupid);//查找用户上级，返回含所有上层用户的ArrayList
	public ArrayList<ArrayList<Long>> findDown(long groupid);//查找用户下级，返回含所有下面各层用户的ArrayList，其中每层为一个ArrayList
	public ArrayList<Long> findSameRank(long groupid);// 返回所有同级用户
	public ArrayList<Long> findAllGroup(long userid);//查找用户所在的所有群组
	public boolean addRelation(Relation relation);//增加relation
	public void delRelation(long up,long down);
	public void delRelation(long forkid);
	ArrayList<Group> getDownOne(long groupid);
	ArrayList<JsonRelation> getJsonRela(long groupid);
	String getUserName(long groupid);
	
}
	