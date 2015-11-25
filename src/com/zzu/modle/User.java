package com.zzu.modle;

import java.awt.Image;
import java.util.Date;

public class User {
	private long userid;
	private String username;
	private String password;
	private Date birthday;
	private String tel;
	private String email;
	private Date registertime;
	private View picture;
	public long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegistertime() {
		return registertime;
	}
	public void setRegistertime(Date registertime) {
		this.registertime = registertime;
	}
	public View getPicture() {
		return picture;
	}
	public void setPicture(View picture) {
		this.picture = picture;
	}
	public static void main(String args[]){
		
	}
}