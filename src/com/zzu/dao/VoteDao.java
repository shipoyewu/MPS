package com.zzu.dao;

import com.zzu.modle.Vote;
/**
 * 
 * @author xingjiali
 *
 */
public interface VoteDao {
	public long addVote(Vote vote);
	public boolean isvalid(long voteid);
	public Vote getVote(Vote vote);
}
