package com.ozguryazilim.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ozguryazilim.model.Actor;
import com.ozguryazilim.repository.ActorRepository;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER') ")
@RequestMapping("actor/")
public class ActorController {

	
	@Autowired
	ActorRepository actorRepository;
	
	
	
	
	@GetMapping("insert")
	public ModelAndView insert()
	{
		
		
		ModelAndView model = new ModelAndView();
		
		model.setViewName("actorInsert");
		
		return model;
	}
	
	@PostMapping("insert")
	public ModelAndView insert(Actor actor)
	{
		actorRepository.save(actor);
		
		ModelAndView model = new ModelAndView();
		
		model.setViewName("redirect:/actor/all");
		
		return model;
	}
	
	@GetMapping("all")
	public ModelAndView getActor()
	{
		ModelAndView model = new ModelAndView();
		
		
		
		model.setStatus(HttpStatus.OK);
		model.setViewName("actor");
		model.addObject("data", actorRepository.findAll());
		
		return model;
	}
	
	
	@GetMapping("{id}")
	public ModelAndView actorDetail(@PathVariable("id") long id) 
	{
		
		ModelAndView model = new ModelAndView();
		
		Optional<Actor> actor = actorRepository.findById(id);
		
		if(actor.isPresent()) {
			
			
			
			model.setStatus(HttpStatus.OK);
			model.setViewName("actorDetail");
			model.addObject("data", actor.get());
			
			return model;
		}
		
		
		model.setStatus(HttpStatus.NOT_FOUND);
		model.setViewName("redirect:/");
		
		return model;
		
		
		
		
	}
	
	
	@PostMapping("update")
	public ModelAndView update(Actor actor)
	{
		
		if(actor == null || actor.getId()== null)
		{
			throw new IllegalArgumentException("actor not found!");
		}
		
		
		
		actorRepository.save(actor);
		
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/actor/all");
		
		return model;
		
	}
	
	
	
	@GetMapping("delete/{id}")
	public ModelAndView delete(@PathVariable("id") long id)
	{
		ModelAndView model = new ModelAndView();
		
		actorRepository.deleteById(id);
		
		model.setViewName("redirect:/actor/all");
		
		return model;
		
		
	}
	
	
	
}
