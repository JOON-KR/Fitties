package com.ssafy.fitty.model.dto.user;

public class User {
	private String id;
	private String password;
	private String email;
	private String name;
	private String nickname;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public User(String id, String password, String email, String name, String nickname) {
		super();
		this.id = id;
		this.password = password;
		this.email = email;
		this.name = name;
		this.nickname = nickname;
	}
	
	
	public User() {
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", password=" + password + ", email=" + email + ", name=" + name + ", nickname="
				+ nickname + "]";
	}
	
	
	
}
