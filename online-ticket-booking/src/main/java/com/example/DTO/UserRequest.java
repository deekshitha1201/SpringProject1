package com.example.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UserRequest {
	@NotNull(message="UserName Should Enter")
	private String username;
	@NotNull(message="Age Should Enter")
	private int age;
	@Email
	private String email;
	@Pattern(regexp = "[0-9]{6}")
	private String password;
	@Pattern(regexp = "[6-9]{1}[0-9]{9}")
	private String mobilenumber;
	@Pattern(regexp = "[0-9]{11}")
	private String aadharnumber;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(String mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public String getAadharnumber() {
		return aadharnumber;
	}
	public void setAadharnumber(String aadharnumber) {
		this.aadharnumber = aadharnumber;
	}
	public UserRequest(@NotNull(message = "UserName Should Enter") String username,
			@NotNull(message = "Age Should Enter") int age, @Email String email,
			@Pattern(regexp = "[0-9]{6}") String password, @Pattern(regexp = "[6-9]{1}[0-9]{9}") String mobilenumber,
			@Pattern(regexp = "[0-9]{11}") String aadharnumber) {
		super();
		this.username = username;
		this.age = age;
		this.email = email;
		this.password = password;
		this.mobilenumber = mobilenumber;
		this.aadharnumber = aadharnumber;
	}
	public UserRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserRequest [username=" + username + ", age=" + age + ", email=" + email + ", password=" + password
				+ ", mobilenumber=" + mobilenumber + ", aadharnumber=" + aadharnumber + "]";
	}
	

}
