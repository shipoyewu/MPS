package com.zzu.modle;

import java.util.Date;

public class Comment {
	private long commentid;
	private String comcomtent;
	private long groupid;
	private Date commenttime;
	private Date deletetime;
	private long messageid;
	
	public long getCommentid() {
		return commentid;
	}

	public void setCommentid(long commentid) {
		this.commentid = commentid;
	}

	public String getComcomtent() {
		return comcomtent;
	}

	public void setComcomtent(String comcomtent) {
		this.comcomtent = comcomtent;
	}

	public long getGroupid() {
		return groupid;
	}

	public void setGroupid(long groupid) {
		this.groupid = groupid;
	}

	public Date getCommenttime() {
		return commenttime;
	}

	public void setCommenttime(Date commenttime) {
		this.commenttime = commenttime;
	}

	public Date getDeletetime() {
		return deletetime;
	}

	public void setDeletetime(Date deletetime) {
		this.deletetime = deletetime;
	}

	public long getMessageid() {
		return messageid;
	}

	public void setMessageid(long messageid) {
		this.messageid = messageid;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
