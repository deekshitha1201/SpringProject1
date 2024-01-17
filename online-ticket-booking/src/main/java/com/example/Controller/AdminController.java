package com.example.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.AdminRequest;
import com.example.DTO.NewPasswordRequest;
import com.example.DTO.OtpVerificationRequest;
import com.example.DTO.UserRequest;
import com.example.Entity.AdminInfo;
import com.example.Entity.BusDetails;
import com.example.Entity.UserInfo;
import com.example.Repository.AdminRepository;
import com.example.Repository.BusDetailRepository;
import com.example.Service.AdminRegistration;
import com.example.Service.AdminService;
import com.example.Service.AdminServiceImpl;
import com.example.Service.EmailService;
import com.example.Service.LoginService;
import com.example.Service.OtpService;
@RestController
public class AdminController {
	@Autowired
	private EmailService emailService;
	@Autowired
	private OtpService otpService;
	@Autowired
	private PasswordEncoder passwordencoder;
	@Autowired
	private AdminRegistration adminRegister;
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private LoginService loginService;
	@Autowired
	private AdminServiceImpl adminServiceimp;
	
	
	
	@PostMapping("/admin/sendotp")
	public ResponseEntity<String> sendOtp(@RequestBody AdminRequest admin) {
		String adminEmail=admin.getEmail();
		AdminInfo ui=adminRegister.findByEmail(adminEmail);
		if(ui==null) {
			String otp=otpService.generateOtp(adminEmail);
			emailService.sendOtpEmail(adminEmail, otp);
			return ResponseEntity.ok("OTP sent to your email");
		} else {
			return ResponseEntity.badRequest().body("Email is already registered.");
			
		}
		
		}
 
	@PostMapping("/admin/verify-otp")
		    public ResponseEntity<String> verifyOtp( @RequestBody  OtpVerificationRequest verificationRequest) {
		        String otp=verificationRequest.getOtp();
		        String email=verificationRequest.getEmail();
		        System.out.println(otp+email);
	 
		        if (otpService.validateOtp(email, otp)) {
		            return ResponseEntity.ok("OTP verified successfully");
		        } else {
		            return ResponseEntity.badRequest().body("Incorrect OTP.");
		        }
	 
		    }
	@PostMapping("/adminregistration")
    public ResponseEntity<String> register(@RequestBody AdminInfo admin) {
       return adminRegister.saveadmin(admin);
    }
	
	@PostMapping("/adminForgotpassword/sendotp")
	public ResponseEntity<String> sendotp(@RequestBody AdminInfo request) {
		String userEmail=request.getEmail();
		AdminInfo cu=adminRegister.findByEmail(userEmail);
		if(cu!=null) {
			String otp=otpService.generateOtp(userEmail);
			emailService.sendOtpEmail(userEmail, otp);
			return ResponseEntity.ok("OTP sent to your email");
		} else {
			return ResponseEntity.badRequest().body("Email is not registered.");
			
		}
	}
	
	@PostMapping("/adminresetpassword/{email}")
	public ResponseEntity<String> resetpassword(@RequestBody NewPasswordRequest request,@PathVariable String email) {
		String newpassword =request.getNewpassword();
		String confirmedpassword=request.getConfirmedpassword();
		AdminInfo cust=adminRegister.findByEmail(email);
		if(cust==null) {
			return ResponseEntity.badRequest().body("User not found");
		}  
		if(!(newpassword.equals(confirmedpassword))) {
			return ResponseEntity.badRequest().body("Passwords do not matched");
		}
		String encoddedpassword=passwordencoder.encode(newpassword);
		cust.setAdminpassword(encoddedpassword);
		adminRepository.save(cust);
		return ResponseEntity.ok("Password resetted successfully");
	}
	
	@PostMapping("/adminLogin/{id}")
	public ResponseEntity<String> loginAccount(@RequestBody AdminInfo request,@PathVariable int id)
	{
		return loginService.login(request, id);	
	}
	@PostMapping("/{id}/bus")
	public ResponseEntity<String> addBusByAdmin(@PathVariable int id, @RequestBody BusDetails bus) {
	    AdminInfo admin = adminRepository.findById(id).orElse(null);

	    if (admin != null) {
	       
	        bus.setAdmininfo(admin);

	        adminServiceimp.addBusByAdmin(bus);

	        return ResponseEntity.status(HttpStatus.OK).body("Bus added successfully");
	    } else { 
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Admin not found with the id " + id);
	    }
	}
	@DeleteMapping("/{id}")
	public String removeBus(@PathVariable int id){
		return adminServiceimp.removeBus(id);
		
	}


}
