package com.ozguryazilim;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ozguryazilim.model.Actor;
import com.ozguryazilim.model.Film;
import com.ozguryazilim.repository.FilmRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class FilmRepositoryTest {

	
	
	@Autowired
	FilmRepository repository;
	
	
	@Test
	public void findById()
	{
		
		Optional<Film> result =repository.findById(1L);
		
		System.out.println(result.isPresent());
	}
	
	@Test
	
	public void insertFilm() {
		
		Film film = new Film();
		Actor actor = new Actor();
		actor.setName("actoor");
		List<Actor> actorList = new ArrayList();
		actorList.add(actor);
		
		film.setName("film1");
		film.setActor(actorList);
		film.setLanguage("Türkçe");
		
		repository.save(film);
	}
	
	@Test
	public void getActor() throws JsonProcessingException {
		
		List<Film> film = repository.findByActor("actor");
		
		ObjectMapper mapper = new ObjectMapper();
		
		Assert.assertNotNull(film);
		
		
	
	}
	
	@Test
	public void deleteFilm()
	{
		Film film = new Film();
		film.setId(55L);
		repository.delete(film);
		
		
	}
}
