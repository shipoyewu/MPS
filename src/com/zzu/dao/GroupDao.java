package com.zzu.dao;

import java.util.ArrayList;

import com.zzu.modle.Group;
/**
 * 
 * @author shihu
 *
 */
public interface GroupDao {
	
	public ArrayList<ArrayList<Long>> findUser(long groupid);//输入Groupid,返回所有user 
	public void addGroup(Group group);//新增一个group
	public void updateGroup(Group group);//更新群组信息
	public boolean isBelong(long userid,long groupid);//判断用户是否属于该群租
	public boolean isvalid(long groupid);//该群组是否有效（是否已被删除）
	public void deleteGroup(long groupid);//删除群组,isvalid置为false
	
}
