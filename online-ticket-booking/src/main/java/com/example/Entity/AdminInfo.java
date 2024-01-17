package com.example.Entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class AdminInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String adminname;
	private String adminpassword;
	private String email;
	//@OneToMany(mappedBy = "admininfo", cascade = CascadeType.ALL, orphanRemoval = true)
	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
    private List<BusDetails> buses=new ArrayList<>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public String getAdminpassword() {
		return adminpassword;
	}

	public void setAdminpassword(String adminpassword) {
		this.adminpassword = adminpassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<BusDetails> getBuses() {
		return buses;
	}

	public void setBuses(List<BusDetails> buses) {
		this.buses = buses;
	}

	public AdminInfo(int id, String adminname, String adminpassword, String email, List<BusDetails> buses) {
		super();
		this.id = id;
		this.adminname = adminname;
		this.adminpassword = adminpassword;
		this.email = email;
		this.buses = buses;
	}
	

	public AdminInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	@Override
	public String toString() {
		return "AdminInfo [id=" + id + ", adminname=" + adminname + ", adminpassword=" + adminpassword + ", email="
				+ email + ", buses=" + buses + "]";
	}

	
	

}
