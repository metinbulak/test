package com.ozguryazilim.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ozguryazilim.repository.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		
		 com.ozguryazilim.model.User userFromDb = repository.findByEmail(userName);
		 
		 if(userFromDb == null)
		 {
			 throw new UsernameNotFoundException(userName+" username not found!");
		 }
		 
		 
		 
		List<SimpleGrantedAuthority> roles= userFromDb.getRoles()
				.stream()
				.map(role->new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
		 
		User user = new User(userFromDb.getEmail(), userFromDb.getPassword(), roles);
		return user;
	}

}
