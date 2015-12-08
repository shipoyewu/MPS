package com.zzu.dao;

import java.util.ArrayList;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.zzu.modle.Message;
import com.zzu.modle.User;
import com.zzu.modle.Vote;
//登陆使用ID或邮箱
/**
 * 
 * @author zongzan
 *
 */
public interface UserDao 
{
		public User getUser(long userid);//通过userid查找用户，返回User
		public User getUser(String email);//通过email查找用户，返回User
		public boolean updateUser(User user);//更新用户信息,不包含头像
		public long addUser(User user) throws MySQLIntegrityConstraintViolationException;//增加用户
		public void updateIcon(User user);//该方法不用，图片不存数据库//设置该用户的头像 
		
		public boolean isUser(String email);//通过email判断该用户是否存在
		public boolean isUser(long userid);//通过userid判断该用户是否存在
		public ArrayList<Long> findGroup(long userid);//返回所在群组号
		public long getId(String str,String type); //通过设置type=="tel" || "email"，都可获得用户ID;
		public boolean confUser(long userid,String password);//校验用户信息
		public boolean confUser(String email,String password);//校验用户信息
		public ArrayList<User> getHaveRelation(long userid);
		public ArrayList<Message> getAllSendMeg(long userid);//返回该用户所有发送的信息
		public ArrayList<Vote> getAllVote(long voteid);//返回发起的所有投票
}