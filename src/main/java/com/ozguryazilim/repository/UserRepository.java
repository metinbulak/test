package com.ozguryazilim.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ozguryazilim.model.User;

public interface UserRepository extends JpaRepository<User,Long> {

	
	User findByEmail(String userName);
}
