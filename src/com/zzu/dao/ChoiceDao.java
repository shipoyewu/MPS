package com.zzu.dao;

import com.zzu.modle.Choice;
/**
 * 
 * @author liushuo
 *
 */
public interface ChoiceDao {
	public long addChoice(Choice choice);//增加一个选项
	public boolean ifchoice(long groupid,long choiceid);
}
