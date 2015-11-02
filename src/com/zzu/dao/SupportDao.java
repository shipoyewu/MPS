package com.zzu.dao;

import java.util.ArrayList;
/**
 * 
 * @author liushuo
 *
 */
public interface SupportDao {
	//在id为groupid的群组里，查找该用户，获取用户的选项，返回Choice对象
	public ArrayList<Object> getUserChoice(long groupid, long userid);
	public ArrayList<Object> getChoiceOfUser(long choiceid);
	
	
}
