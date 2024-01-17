package com.example.Service;


import com.example.Entity.BusDetails;

public interface AdminService {
	public BusDetails addBusByAdmin(BusDetails bus);
	public String removeBus(int id);

}
