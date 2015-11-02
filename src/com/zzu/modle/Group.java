package com.zzu.modle;

import java.util.Date;

public class Group {
	private long groupid;
	private String groupname;
	private Date createtime;
	private long userid;
	private boolean isvalue;
	private boolean isneedagree;
	
	public long getGroupid() {
		return groupid;
	}

	public void setGroupid(long groupid) {
		this.groupid = groupid;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public boolean isIsvalue() {
		return isvalue;
	}

	public void setIsvalue(boolean isvalue) {
		this.isvalue = isvalue;
	}

	public boolean isIsneedagree() {
		return isneedagree;
	}

	public void setIsneedagree(boolean isneedagree) {
		this.isneedagree = isneedagree;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
