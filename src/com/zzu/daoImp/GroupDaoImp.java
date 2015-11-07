package com.zzu.daoImp;

import java.util.ArrayList;

import com.zzu.dao.GroupDao;
import com.zzu.modle.Group;

public class GroupDaoImp implements GroupDao {

	@Override
	public ArrayList<Object> findUser(String groupid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addGroup(Group group) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateGroup(Group group) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isBelong(long userid, long groupid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isvalid(long groupid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void deleteGroup(long groupid) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
