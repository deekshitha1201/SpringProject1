package com.example.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.DTO.AdminRequest;
import com.example.Entity.AdminInfo;
import com.example.Repository.AdminRepository;

@Service
public class AdminRegistration {
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private PasswordEncoder passwordencoder;
	
	public AdminInfo findByEmail(String email) {
		return adminRepository.findByEmail(email);
		}
	public ResponseEntity<String> saveadmin(AdminInfo admin) {
		 
	     if (adminRepository.existsByEmail(admin.getEmail())) {
	         return ResponseEntity.badRequest().body("Email already registered");

	     }

	   	     admin.setAdminpassword(passwordencoder.encode(admin.getAdminpassword()));
	        	     adminRepository.save(admin);
	     return ResponseEntity.ok(" registered successfully");

	}

}
