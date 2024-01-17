package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.DTO.AdminRequest;
import com.example.Entity.AdminInfo;
import com.example.Entity.UserInfo;

public interface AdminRepository extends JpaRepository<AdminInfo, Integer> {
	AdminInfo findByEmail(String email);
	boolean existsByEmail(String email);
	void save(AdminRequest admin);
	

}
