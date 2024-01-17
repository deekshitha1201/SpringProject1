package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Entity.BusDetails;
import com.example.Entity.UserInfo;

public interface BusDetailRepository extends JpaRepository<BusDetails, Integer> {

	BusDetails deleteById(BusDetails bus);

	BusDetails findById(int busid);

	
}
