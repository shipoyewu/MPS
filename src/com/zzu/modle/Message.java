package com.zzu.modle;

import java.util.Date;

public class Message {
	private long messageid;
	private String messagetitle;
	private long contentid;
	private long groupid;
	private boolean isvalue;
	private Date createtime;
	private boolean isremind;
	private boolean iscomment;
	private Date remindtime;
	private Date deletetime;
	
	public long getMessageid() {
		return messageid;
	}

	public void setMessageid(long messageid) {
		this.messageid = messageid;
	}

	public String getMessagetitle() {
		return messagetitle;
	}

	public void setMessagetitle(String messagetitle) {
		this.messagetitle = messagetitle;
	}

	public long getContentid() {
		return contentid;
	}

	public void setContentid(long contentid) {
		this.contentid = contentid;
	}

	public long getGroupid() {
		return groupid;
	}

	public void setGroupid(long groupid) {
		this.groupid = groupid;
	}

	public boolean isIsvalue() {
		return isvalue;
	}

	public void setIsvalue(boolean isvalue) {
		this.isvalue = isvalue;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public boolean isIsremind() {
		return isremind;
	}

	public void setIsremind(boolean isremind) {
		this.isremind = isremind;
	}

	public boolean isIscomment() {
		return iscomment;
	}

	public void setIscomment(boolean iscomment) {
		this.iscomment = iscomment;
	}

	public Date getRemindtime() {
		return remindtime;
	}

	public void setRemindtime(Date remindtime) {
		this.remindtime = remindtime;
	}

	public Date getDeletetime() {
		return deletetime;
	}

	public void setDeletetime(Date deletetime) {
		this.deletetime = deletetime;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
