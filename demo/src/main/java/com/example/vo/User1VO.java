package com.example.vo;

public class User1VO {
	
	private Long id =0L;
	private String username=null;
	private String password=null;
	private String role=null;
	private String regdate=null;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "USER1VO [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", regdate=" + regdate + "]";
	}
	
}
