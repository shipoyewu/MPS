package com.zzu.dao;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.zzu.modle.User;
import com.zzu.modle.View;
//登陆使用ID或邮箱
/**
 * 
 * @author zongzan
 *
 */
public interface UserDao 
{
	public User getUser(Long userid);//通过userid查找用户，返回User
	public User getUser(String email);//通过email查找用户，返回User
	public void updateUser(User user);//更新用户信息,不包含头像
	public void addUser(User user);//增加用户
	public void updateIcon(User user);//设置该用户的头像
	
	public boolean isUser(String email);//通过email判断该用户是否存在
	public boolean isUser(Long userid);//通过userid判断该用户是否存在
	
	public int getId(String str,String type); //通过设置type=="tel" || "email"，都可获得用户ID;
	public boolean confUser(Long userid,String password);//校验用户信息
	public boolean confUser(String email,String password);//校验用户信息
	
	public ArrayList<Object> getAllSendMeg(Long userid);//返回该用户所有发送的信息
	public ArrayList<Object> getAllVote(Long voteid);//返回发起的所有投票
}
