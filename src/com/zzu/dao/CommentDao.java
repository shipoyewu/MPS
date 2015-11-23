package com.zzu.dao;

import java.util.ArrayList;

import com.zzu.modle.Comment;
/**
 * 
 * @author xufuguo
 *
 */
public interface CommentDao {
	public ArrayList<Comment> getComm(long messageid);
	public void addComment(Comment comment);
	public void deleteComment(long commentid);

}
