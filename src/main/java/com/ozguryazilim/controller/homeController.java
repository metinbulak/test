package com.ozguryazilim.controller;


import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.ozguryazilim.model.Actor;
import com.ozguryazilim.model.Film;
import com.ozguryazilim.repository.ActorRepository;
import com.ozguryazilim.repository.FilmRepository;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER') ")

public class homeController {

	@Autowired
	FilmRepository filmRepo;
	
	@Autowired
	ActorRepository actorRepository;
	
	
	@GetMapping("/")
	public ModelAndView getAll(ModelAndView model)
	{
		
		List<Film> filmData= filmRepo.findAll();
		
		
	
		model.setViewName("home");
		model.addObject("data",filmData);
		
		
		return model;
	}
	

	@PostMapping("/search")
	public ModelAndView search(String searchValue,String searchBy)
	{
		
		List<Film> filmdb = null;
		
		if(searchBy.contains("filmName"))
		{
			filmdb = filmRepo.findByNameOrderByPublicationDate(searchValue);
			
		}else if(searchBy.contains("genre")) 
		{
			filmdb = filmRepo.findByGenreOrderByPublicationDate(searchValue);
			
		}if(searchBy.contains("actor")) 
		{
			filmdb = filmRepo.findByActor(searchValue);
			
		}
	    
	      
		
		ModelAndView model = new ModelAndView();
	
		model.addObject("data",filmdb);
		model.setViewName("home");
		
		
		
		return model;
	}
	

	@GetMapping("/all")
	public List<Film> getAll()
	{
		
		return filmRepo.findAll();
	}
	

	@GetMapping("/film/{id}")
	public ModelAndView getOne(@PathVariable("id") Long id)
	{
		
		ModelAndView model = new ModelAndView();
		
		
		
		if(filmRepo.findById(id).isPresent()) {
			
			
			Film filmData = filmRepo.findById(id).get();
			
			model.addObject("data",filmData);
			model.setViewName("filmDetail");
			return model;
		}
		
		model.setStatus(HttpStatus.NOT_FOUND);
		model.addObject("data",null);
		model.setViewName("redirect:/");
		
		return model;
	}
	
	
	// add new film 
	@GetMapping("/add")
	public ModelAndView add()
	{
	
		
		List<Actor> actors = actorRepository.findAll();
		
		 ModelAndView model = new ModelAndView();
	
		 model.setViewName("filmInsert");
		 model.addObject("actors",actors);
		 
		 return model;
	}
	
	
	@PostMapping("/add")
	public ModelAndView add(
			String name,
			@DateTimeFormat(pattern = "yyyy-MM-dd") Date publicationDate, 
			String genre,
			String description,
			String media,
			String language,
			@RequestParam(required=false)List<Long> actorid)
	{
	    Film film = new Film();
	    film.setDescription(description);
	    film.setGenre(genre);
	    film.setLanguage(language);
	    film.setMedia(media);
	    film.setName(name);
	    film.setPublicationDate(publicationDate);
		 if(actorid != null)
		 {
			 
			 List<Actor> actors = actorid.stream().map(m->{
				  Actor act = new Actor();
				  act.setId(m);
				  
				  return act;
			  }).collect(Collectors.toList());
			 
		
			 film.setActor(actors);
		 }
		  
		 
	      
		Film filmdb = filmRepo.save(film);
		
		
		ModelAndView model = new ModelAndView("redirect:/");
		
		
		
		return model;
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER') ")
	@PostMapping("/update")
	public ModelAndView update(@RequestBody()Film film)
	{
		if(film== null || film.getId()==null)
		{
			throw new IllegalArgumentException("film not found!");
			
		}
		
	
		Optional<Film> actors = filmRepo.findById(film.getId());
		
		if(actors.isPresent()) 
		{
			film.setActor(actors.get().getActor());
		}
		
		
		// update film
	     filmRepo.save(film);
	     
	     ModelAndView model = new ModelAndView("redirect:/");
		return model;
	}
	
	

	
	
	@PreAuthorize("hasRole('ROLE_ADMIN') ")
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") long id)
	{
		Film film = new Film();
		film.setId(id);
		
		 filmRepo.delete(film);
		 
		 ModelAndView model = new ModelAndView();
		 model.setStatus(HttpStatus.OK);
		 model.setViewName("redirect:/");
		 
		 return model;
	}
}
