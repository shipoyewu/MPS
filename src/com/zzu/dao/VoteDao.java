package com.zzu.dao;

import java.util.ArrayList;

import com.zzu.modle.Choice;
import com.zzu.modle.Vote;
/**
 * 
 * @author xingjiali
 *
 */
public interface VoteDao {
	public long addVote(Vote vote);
	public boolean isvalid(long voteid);
	public Vote getVoteByMessage(long messageid);
	public Vote getVote(long voteid);
	public ArrayList<Choice> getChoices(long voteid);
	public boolean isparticipate(long voteid,long groupid);
	boolean messageHave(long messageid);
	
}
