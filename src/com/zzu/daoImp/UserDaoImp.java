package com.zzu.daoImp;

import java.util.ArrayList;

import com.zzu.dao.UserDao;
import com.zzu.modle.User;

public class UserDaoImp implements UserDao {

	@Override
	public User findUser(long userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findUser(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isUser(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isUser(long userid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long getId(String str, String type) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean confUser(long userid, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean confUser(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Long> getAllSendMeg(long userid) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ArrayList<Long> getAllVote(long voteid) {
		// TODO Auto-generated method stub
		return null;
	}

}
//你是不是傻吊

