package com.zzu.dao;

import java.util.ArrayList;

import com.zzu.modle.Message;
import com.zzu.modle.Receive;
/**
 * 
 * @author xingjiali
 *
 */
public interface ReceiveDao {
	public ArrayList<Message> getAllReceiveMeg(long userid);//返回该用户所有接收的信息
	public ArrayList<Message> getAllUnReadMeg(long userid);//返回所有
	public void addReceive(Receive receive);
	public void Read(long messageid,long groupid);
	public boolean ifReaded(long messageid,long groupid);
	
}
