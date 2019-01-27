package com.ozguryazilim.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ozguryazilim.model.Role;
import com.ozguryazilim.model.User;
import com.ozguryazilim.repository.UserRepository;

@Controller
public class UserController {

	
	@Autowired
	UserRepository repository;
	
	
	@GetMapping("/register")
	public ModelAndView user(ModelAndView model)
	{
		model.setStatus(HttpStatus.OK);
		model.setViewName("register");
		
		return model;
	}
	
	@GetMapping("/login")
	public ModelAndView login(ModelAndView model)
	{
		model.setStatus(HttpStatus.OK);
		model.setViewName("login");
		
		return model;
	}
	
	
	@PostMapping("/register")
	public ModelAndView register(User user,Long roleId) throws Exception
	{
		ModelAndView model = new ModelAndView();
		
		
		if(roleId==null) {
			throw new Exception("Role not defined");
		}
		Role role = new Role();
		role.setId(roleId);
		ArrayList<Role> roles= new ArrayList();
		roles.add(role);
		
		user.setRoles(roles);
		
		PasswordEncoder encoder =new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		
		 User userData = repository.save(user);
		 
		model.setStatus(HttpStatus.OK);
		model.setViewName("login");
		model.addObject("data",userData);
		model.addObject("message","Register succesfully.");
		
		
		
		return model;
	}
}
