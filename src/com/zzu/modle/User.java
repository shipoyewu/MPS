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
<<<<<<< HEAD
	private Date registertime;
=======
	private String registertime;
>>>>>>> ea1b245b4e07eee18c10579f12e521f716630af5
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
	public String getRegistertime() {
		return registertime;
	}
	public void setRegistertime(String registertime) {
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
