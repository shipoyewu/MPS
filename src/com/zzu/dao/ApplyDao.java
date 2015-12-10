package com.zzu.dao;

import com.zzu.modle.Apply;
/**
 * 
 * @author liushuo
 *
 */
public interface ApplyDao {
	public void addApply(Apply apply);//添加一个申请
	public void deleteApply(Apply apply);//删除一个申请
	public void delete(long groupup, long groupdown);
	
}
