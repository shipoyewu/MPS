package com.zzu.modle;

import java.util.Date;

public class Apply {
	private long groupup;
	private long groupdown;
	private Date applytime;
	private String applycontent;
	private boolean type;
	
	public long getGroupup() {
		return groupup;
	}

	public void setGroupup(long groupup) {
		this.groupup = groupup;
	}

	public long getGroupdown() {
		return groupdown;
	}

	public void setGroupdown(long groupdown) {
		this.groupdown = groupdown;
	}

	public Date getApplytime() {
		return applytime;
	}

	public void setApplytime(Date applytime) {
		this.applytime = applytime;
	}

	public String getApplycontent() {
		return applycontent;
	}

	public void setApplycontent(String applycontent) {
		this.applycontent = applycontent;
	}

	public boolean isType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
