package com.zzu.dao;

import java.util.ArrayList;

import com.zzu.modle.Message;
/**
 * 
 * @author xingjiali
 *
 */
public interface ReceiveDao {
	public ArrayList<Message> getAllReceiveMeg(long userid);//返回该用户所有接收的信息
	public ArrayList<Message> getAllUnReadMeg(long userid);//返回所有
}
