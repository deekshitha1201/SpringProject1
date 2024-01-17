package com.example.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.Entity.AdminInfo;
import com.example.Repository.AdminRepository;

@Service
public class LoginService {
	@Autowired
	private AdminRepository adminRepository;
	public ResponseEntity<String> login(AdminInfo admin,int id){
		AdminInfo ai=adminRepository.findById(id).get();
		if(ai.getEmail().equals(admin.getEmail()) && ai.getAdminpassword().equals(admin.getAdminpassword()))
		{
			return ResponseEntity.status(HttpStatus.OK).body("login successfully");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("invalid email and password");
		
	}

	

}
