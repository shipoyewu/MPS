package com.zzu.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.zzu.daoImp.RelationDaoImp;
import com.zzu.daoImp.UserDaoImp;

public class JsonRelation {

	private long id;
	private long pId;
	private String name;
	private String open;
	public JsonRelation(){
		
	}
	public JsonRelation(long id, long pId, String name) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.pId = pId;
		this.name = name;
	}
	public JsonRelation(long id, long pId, String name, String open) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.pId = pId;
		this.name = name;
		this.open = open;
	}
	public long getId(){
		return this.id;
	}
	public long getPId(){
		return this.pId;
	}
	public String getName(){
		return this.name;
	}
	public void setOpen(String s){
		this.open = s;
	}
	public String getOpen(){
		return this.open;
	}
	public static void main(String[] args){
//		ArrayList<JsonRelation> jr = new JsonRelation().getJsonRela(5);
//		System.out.println("id:"+jr.get(0).id);
//		System.out.println("pid:"+jr.get(0).pId);
//		System.out.println("name:"+jr.get(0).name);
	}
	
}
