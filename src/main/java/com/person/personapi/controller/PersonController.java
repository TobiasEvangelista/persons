package com.person.personapi.controller;

import org.springframework.data.domain.Pageable;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.person.personapi.exception.PersonNotFoundException;
import com.person.personapi.model.Person;
import com.person.personapi.service.PersonService;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
	
	private PersonService personService;
	
	@Autowired
	public PersonController(PersonService personService) {
		this.personService = personService;
	}
	
	@GetMapping
	public List<Person> listAll(@PageableDefault(sort="id", direction = Sort.Direction.ASC, page = 0, size = 2)Pageable page){
		
		return personService.listAll(page);
	}
	
	@GetMapping("/{id}")
	public Person findById(@PathVariable Long id) throws PersonNotFoundException {
		
		return personService.findById(id);
	}
	
	@GetMapping("/{name}")
	public Person findByName(@PathVariable String name) throws PersonNotFoundException {
		
		return personService.findByName(name);
	}
	
	@PutMapping("/{id}")
	public Person UpdateById(@PathVariable Long id, @RequestBody @Valid Person person)throws PersonNotFoundException {
		return personService.UpdateById(id,person);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Person createPerson(@RequestBody @Valid Person person) {
		return personService.createPerson(person);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void DeleteById(@PathVariable Long id)  throws PersonNotFoundException {
		personService.delete(id);
		
	}

}


