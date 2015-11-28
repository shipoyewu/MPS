package com.zzu.modle;

import java.util.Date;

public class Vote {
	private long voteid;
	private String votecontent;
	private Date starttime;
	private Date endtime;
	private long messageid;
	private boolean ismul;
	private boolean isvalue;
	private boolean isanonymous;
	public long getVoteid() {
		return voteid;
	}

	public void setVoteid(long voteid) {
		this.voteid = voteid;
	}

	public String getVotecontent() {
		return votecontent;
	}

	public void setVotecontent(String votecontent) {
		this.votecontent = votecontent;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public long getMessageid() {
		return messageid;
	}

	public void setMessageid(long messageid) {
		this.messageid = messageid;
	}

	public boolean isIsmul() {
		return ismul;
	}

	public void setIsmul(boolean ismul) {
		this.ismul = ismul;
	}

	public boolean isIsvalue() {
		return isvalue;
	}

	public void setIsvalue(boolean isvalue) {
		this.isvalue = isvalue;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void setIsanonymous(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public boolean isIsnonymous() {
		// TODO Auto-generated method stub
		return false;
	}

}
