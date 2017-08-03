package com.haojie.pojo;

import java.io.Serializable;


public class Customer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1795048790656248628L;
	private int id;
	private String name;
	private String address;
	private String tel;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
