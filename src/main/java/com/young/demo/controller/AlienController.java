package com.young.demo.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.young.demo.dao.AlienRepo;
import com.young.demo.model.Alien;


@RestController
public class AlienController {
	
	@Autowired
	AlienRepo repo;
	
	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}
	
	@PostMapping(path="/alien", consumes= {"application/json"})
	public Alien addAlien(@RequestBody Alien alien) {
		repo.save(alien);
		return alien;
	}
	
	//@RequestMapping(path="/aliens", produces= {"application/xml"})
	//@ResponseBody //if @RestController
	@GetMapping("/aliens")
	public List<Alien> getAliens() {
		return repo.findAll();
	}
	
	@RequestMapping("/alien/{aid}")
	@ResponseBody
	public Optional<Alien> getAlien(@PathVariable("aid") int aid) {
		return repo.findById(aid);
	}
	
	@RequestMapping("/getAlien")
	public ModelAndView getMyAliens(@RequestParam int aid) {
		ModelAndView mv = new ModelAndView("showAlien.jsp");
		Alien alien = repo.findById(aid).orElse(new Alien());
		
		/*
		System.out.println(repo.findByTech("Java"));
		System.out.println(repo.findByAidGreaterThan(102));
		System.out.println(repo.findByTechSorted("Java"));
		*/
		
		mv.addObject(alien);
		return mv;
	}
	
	//using JSTL
	//https://www.websparrow.org/spring/how-to-iterate-list-on-jsp-in-spring-mvc
	@RequestMapping("/viewAlien")
	public ModelAndView viewAlien() {
		System.out.println("getting here");
		Iterable<Alien> allAliens = repo.findAll();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("alienList", allAliens);
		mv.setViewName("viewAlien.jsp");
		return mv;
	}
}
