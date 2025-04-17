package com.application.upskilling.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.upskilling.model.User;
import com.application.upskilling.repository.UserRepository;
import com.application.upskilling.service.EmailService;
import com.application.upskilling.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController 
{
	@Autowired
	UserService userService;
	@Autowired
	EmailService emailService;
	
	
	//private String roleString = userService
	
	@GetMapping("/users")
	public List<User> getAllUsers()
	{
		return userService.getAllUsers();
	}
	
	@PostMapping("/api/send-code")
	public void sendCode(@RequestBody Map<String, String> payload) {
	    String email = payload.get("email");
	    emailService.sendVerificationCode(email);
	}
	
	@PostMapping("/api/verify-code")
	public ResponseEntity<Map<String, String>> verifyCode(@RequestBody Map<String, String> payLoad)
	{
		if(emailService.verifyCode(payLoad.get("code")))
		{
			
			Map<String, String> response = new HashMap<>();
			response.put("message", "Verification Successful");
			return ResponseEntity.ok(response);

		}
		else {
			Map<String, String> errorResponse = new HashMap<>();
			errorResponse.put("message", "Verification Failed");
			return ResponseEntity.status(404).body(errorResponse);
		}
	}
	
	/*@GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }*/

    @PostMapping("/users")
    public ResponseEntity<User> addUser(@RequestBody User user) {
    	System.out.println("Received user: " + user);
        userService.addUser(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody User user) {
        if (userService.editUser(id, user)) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(404).body(user);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        if (userService.deleteUser(id)) {
            return ResponseEntity.ok("User Deleted");
        } else {
            return ResponseEntity.status(404).body("User Not Found");
        }
    }
    @SuppressWarnings("unlikely-arg-type")
	@GetMapping("/users/{mail}")
    public ResponseEntity<User> getUserByMail(@PathVariable String mail)
    {
    	int n = 0;
    	List<User> users = getAllUsers();
    	n = users.indexOf(mail);
    	User user =  users.get(n);
    	return ResponseEntity.ok(user);
    }
    
    @GetMapping("/users/id/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id)
    {
    	return ResponseEntity.ok(userService.getUserById(id));
    }

}
