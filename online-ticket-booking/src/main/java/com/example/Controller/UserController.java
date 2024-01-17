package com.example.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.AuthRequestDTO;
import com.example.DTO.JwtResponseDTO;
import com.example.DTO.NewPasswordRequest;
import com.example.DTO.OtpVerificationRequest;
import com.example.DTO.UserRequest;
import com.example.Entity.AdminInfo;
import com.example.Entity.BusDetails;
import com.example.Entity.UserInfo;
import com.example.Repository.UserRepository;
import com.example.Service.EmailService;
import com.example.Service.JwtService;
import com.example.Service.OtpService;
import com.example.Service.RegisterService;
import com.example.Service.UserService;

@RestController
public class UserController {
	@Autowired
	private EmailService emailService;
	@Autowired
	private RegisterService registerService;
	@Autowired
	private OtpService otpService;
	@Autowired
	private PasswordEncoder passwordencoder;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserService userService;

	
	@PostMapping("/userregistration")
    public ResponseEntity<String> register(@RequestBody UserInfo user) {
       return registerService.saveuser(user);
    }
	
 @PostMapping("/user/sendotp")
	public ResponseEntity<String> sendOtp(@RequestBody UserInfo passenger) {
		String userEmail=passenger.getEmail();
		UserInfo ui=registerService.findByEmail(userEmail);
		if(ui==null) {
			String otp=otpService.generateOtp(userEmail);
			emailService.sendOtpEmail(userEmail, otp);
			return ResponseEntity.ok("OTP sent to your email");
		} else {
			return ResponseEntity.badRequest().body("Email is already registered.");
			
		}
		
		}
 
	@PostMapping("/user/verify-otp")
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
	@PostMapping("/userForgotpassword/sendotp")
	public ResponseEntity<String> sendotp(@RequestBody UserRequest request) {
		String userEmail=request.getEmail();
		UserInfo cu=registerService.findByEmail(userEmail);
		if(cu!=null) {
			String otp=otpService.generateOtp(userEmail);
			emailService.sendOtpEmail(userEmail, otp);
			return ResponseEntity.ok("OTP sent to your email");
		} else {
			return ResponseEntity.badRequest().body("Email is not registered.");
			
		}
	}
	
	@PostMapping("/userresetpassword/{email}")
	public ResponseEntity<String> resetpassword(@RequestBody NewPasswordRequest request,@PathVariable String email) {
		String newpassword =request.getNewpassword();
		String confirmedpassword=request.getConfirmedpassword();
		UserInfo cust=registerService.findByEmail(email);
		if(cust==null) {
			return ResponseEntity.badRequest().body("User not found");
		}  
		if(!(newpassword.equals(confirmedpassword))) {
			return ResponseEntity.badRequest().body("Passwords do not matched");
		}
		String encoddedpassword=passwordencoder.encode(newpassword);
		cust.setPassword(encoddedpassword);
		userRepository.save(cust);
		return ResponseEntity.ok("Password resetted successfully");
	}

	@PostMapping("/user/login")
	public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO){
	
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
	    if(authentication.isAuthenticated()){
	    	return JwtResponseDTO.builder().accessToken(jwtService.GenerateToken(authRequestDTO.getUsername())).build();
	    } else {
	        throw new UsernameNotFoundException("invalid user request..!!");
	    }
	    
	}
	@GetMapping("/user/getmsg")
    public String test() {
        try {
            return "Successfully Login Welcome to online ticket booking";
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    } 
	 @GetMapping("/{id}/search")
	    public ResponseEntity<String> searchBusByUser(@PathVariable int id,@RequestBody BusDetails bus) {
		 UserInfo user = userRepository.findById(id);

		    if (user != null) {
		       
		        bus.setUserInfo(user);

		        userService.searchBusByUser(bus);

		        return ResponseEntity.status(HttpStatus.OK).body("Bus found by user");
		    } else { 
		        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found with the id " + id);
		    }
	 }
	 @PostMapping("/bookbus/{id}/{busid}")
	 public ResponseEntity<String> reservation(@PathVariable int id,@PathVariable int busid)
	 {
		 String result=userService.bookBus(id, busid);
		return ResponseEntity.ok(result);
	 }
}


