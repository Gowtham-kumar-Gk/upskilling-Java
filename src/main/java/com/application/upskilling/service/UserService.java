package com.application.upskilling.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.upskilling.model.User;
import com.application.upskilling.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	
	/*public String userRole()
	{
		return userRepository.
	}*/
	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}
	
	public User addUser(User user)
	{
		userRepository.save(user);
		return user;
	}
	
	public Boolean editUser(Long id, User editedUser)
	{
		
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent())
		{
			User existingUser = user.get();
			existingUser.setName(editedUser.getName());
			existingUser.setEmail(editedUser.getEmail());
			existingUser.setStream(editedUser.getStream());
			existingUser.setProjName(editedUser.getProjName());
			existingUser.setStatus(editedUser.getStatus());
			
			userRepository.save(existingUser);
			return true;
			
		}
		else {
			return false;
		}
		
	}
	
	public Boolean deleteUser(Long id)
	{
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent())
		{
			User existingUser = user.get();
			userRepository.delete(existingUser);
			return true;
		}
		else {
			return false;
		}
	}
	
	public User getUserById(Long id) {
	    return userRepository.getReferenceById(id);
	}


}
