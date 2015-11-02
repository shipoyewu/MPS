package com.zzu.dao;

import java.util.ArrayList;

import com.zzu.modle.User;
//登陆使用ID或邮箱
/**
 * 
 * @author zongzan
 *
 */
public interface UserDao 
{
	public User findUser(long userid);//通过userid查找用户，返回User
	public User findUser(String email);//通过email查找用户，返回User
	public void updateUser(User user);//更新用户信息
	public void addUser(User user);//增加用户
	
	public boolean isUser(String email);//通过email判断该用户是否存在
	public boolean isUser(long userid);//通过userid判断该用户是否存在
	
	public long getId(String str,String type); //通过设置type=="tel" || "email" || "userid"，都可获得用户ID;
	public boolean confUser(long userid,String password);//校验用户信息
	public boolean confUser(String email,String password);//校验用户信息
	
	public ArrayList<Long> getAllSendMeg(long userid);//返回该用户所有发送的信息
	public ArrayList<Long> getAllVote(long voteid);//返回发起的所有投票
}
