package com.application.upskilling.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.upskilling.model.User;
import com.application.upskilling.repository.AdminRepository;
import com.application.upskilling.repository.UserRepository;

@Service
public class AdminService 
{
	@Autowired
	AdminRepository adminRepository;
	@Autowired
	UserRepository userRepository;
	
	
	

}
