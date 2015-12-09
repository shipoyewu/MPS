package com.zzu.dao;

import com.zzu.modle.Message;
/**
 * 
 * @author xufuguo
 *
 */
public interface MessageDao {
	public String getTitle(long messageid);
	public long addMessage(Message msg);
	public boolean isValid(long messageid);
	public void deleteMsg(long messageid);
	public long getContent(long messageid);
	public Message getMessage(long messageid);
}
