package com.nefu.shop.domain.po;

import java.io.Serializable;
import java.util.Date;

/**
 * User表的实体类
 *
 */
public class User implements Serializable {
	private int id;
	private String username;
	private String password;
	
	private String gender;
	private String email;
	private String telephone;
	
	private String introduce;
	private String activeCode;
	private int state;
	
	private String role;
	private Date registTime;

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getGender() {
		return gender;
	}

	public String getEmail() {
		return email;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getIntroduce() {
		return introduce;
	}

	public String getActiveCode() {
		return activeCode;
	}

	public int getState() {
		return state;
	}

	public String getRole() {
		return role;
	}

	public Date getRegistTime() {
		return registTime;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	public void setState(int state) {
		this.state = state;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}
     
	public String toString(){
		
		return "["+"id: "+id+" username: "+username+" password: "+password+"]";
	}
}
