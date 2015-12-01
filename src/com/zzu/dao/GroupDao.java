package com.zzu.dao;

import java.util.ArrayList;

import com.zzu.modle.Group;
/**
 * 
 * @author shihu
 *
 */
public interface GroupDao {
	
	public long getUserid(long groupid);
	public ArrayList<ArrayList<Long>> findUser(long groupid);//输入Groupid,返回下面的所fork
	
	public long addGroup(Group group);//新增一个group
	public void updateGroup(Group group);//更新群组信息
	public boolean isBelong(long userid,long groupid);//判断用户是否属于该群租
	public boolean isvalid(long groupid);//该群组是否有效（是否已被删除）
	public void deleteGroup(long groupid);//删除群组,isvalid置为false
	public Group getGroup(long groupid);
	public ArrayList<Group> findAllGroup(long userid); 
	public String getUserName(long groupid);
	
}
