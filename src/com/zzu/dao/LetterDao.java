package com.zzu.dao;

import java.util.ArrayList;

import com.zzu.modle.Letter;

/**
 * 
 * @author xufuguo
 *
 */

public interface LetterDao {
	public long addLetter(Letter letter);//添加私信
	public ArrayList<Letter> getLetters(long sender,long receiver);//返回sender和receiver之间的最后30条消息
	public ArrayList<Letter> getUnRead(long sender,long receiver); //返回用户sender和用户receiver之间receiver没有读过的消息
	public boolean ifUnRead(long sender,long receiver);//返回用户sender和receiver之间是否有未读的消息
}
