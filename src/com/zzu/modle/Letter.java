package com.zzu.modle;

import java.sql.Date;

public class Letter {
	private long letterid;
	private String lettercontent;
	private long senderuserid;// 发送者id
	private long receiveuserid;// 接受者id
	private Date createtime;
	public long getLetterid() {
		return letterid;
	}
	public void setLetterid(long letterid) {
		this.letterid = letterid;
	}
	public String getLettercontent() {
		return lettercontent;
	}
	public void setLettercontent(String lettercontent) {
		this.lettercontent = lettercontent;
	}
	public long getSenderuserid() {
		return senderuserid;
	}
	public void setSenderuserid(long senderuserid) {
		this.senderuserid = senderuserid;
	}
	public long getReceiveuserid() {
		return receiveuserid;
	}
	public void setReceiveuserid(long receiveuserid) {
		this.receiveuserid = receiveuserid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
}
