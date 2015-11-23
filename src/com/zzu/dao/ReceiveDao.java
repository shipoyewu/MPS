package com.zzu.dao;

import java.util.ArrayList;
/**
 * 
 * @author xingjiali
 *
 */
public interface ReceiveDao {
	public ArrayList<Object> getAllReceiveMeg(long forkid);//返回该用户所有接收的信息
	public ArrayList<Object> getAllUnReadMeg(long forkid);//返回所有
}
