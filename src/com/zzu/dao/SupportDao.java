package com.zzu.dao;

import java.util.ArrayList;

import com.zzu.modle.Choice;
import com.zzu.modle.Group;
/**
 * 
 * @author liushuo
 *
 */
public interface SupportDao {
	//在id为groupid的群组里，查找该用户，获取用户的选项，返回Choice对象
	public ArrayList<Choice> getUserChoice(long groupid, long userid);
	public ArrayList<Group> getChoiceOfUser(long choiceid);
	public void addSupport(long groupid,long choiceid);
	
	
}
