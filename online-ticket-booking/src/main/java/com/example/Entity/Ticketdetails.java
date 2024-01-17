package com.example.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Ticketdetails {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name="userid")
	private UserInfo user;
	@ManyToOne
	@JoinColumn(name="busid")
	private BusDetails bus;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
	public BusDetails getBus() {
		return bus;
	}
	public void setBus(BusDetails bus) {
		this.bus = bus;
	}
	public Ticketdetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ticketdetails(int id, UserInfo user, BusDetails bus) {
		super();
		this.id = id;
		this.user = user;
		this.bus = bus;
	}
	
	

}
