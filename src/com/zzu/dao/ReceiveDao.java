package com.zzu.dao;

import java.util.ArrayList;
/**
 * 
 * @author xingjiali
 *
 */
public interface ReceiveDao {
	public ArrayList<Object> getAllReceiveMeg(long userid);//返回该用户所有接收的信息
	public ArrayList<Object> getAllUnReadMeg(long userid);//返回所有
}
