package com.zzu.daoImp;

import java.util.ArrayList;

import com.zzu.dao.LetterDao;
import com.zzu.modle.Letter;

public class LetterDaoImp implements LetterDao {

	@Override
	public void addLetter(Letter letter) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Letter> getLetters(long sender, long receiver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Letter> getUnRead(long sender, long receiver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean ifUnRead(long sender, long receiver) {
		// TODO Auto-generated method stub
		return false;
	}
	public static void main(String args[]){
		
	}
}
