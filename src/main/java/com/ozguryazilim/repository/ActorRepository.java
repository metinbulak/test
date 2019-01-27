package com.ozguryazilim.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ozguryazilim.model.Actor;


public interface ActorRepository extends JpaRepository<Actor,Long> {

}
