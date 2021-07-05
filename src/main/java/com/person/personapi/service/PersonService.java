package com.person.personapi.service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.person.personapi.exception.PersonNotFoundException;
import com.person.personapi.model.Person;
import com.person.personapi.repository.PersonRepository;

@Service
public class PersonService {
	
	private PersonRepository personRepository;
	
	@Autowired
	public PersonService(PersonRepository personRepository) {
		super();
		this.personRepository = personRepository;
	}
	
	public List<Person> listAll(Pageable page) {
		Page<Person> allPersons = personRepository.findAll(page);
		return allPersons.stream().collect(Collectors.toList());
	}
	
	public Person findById(Long id)throws PersonNotFoundException {
		Person person = verifyIfExists(id);
		return person;
	}
	
	public Person findByName(String  name)throws PersonNotFoundException {
		 Person person = personRepository.findByName(name);
		 return person;
	}
	
	public Person UpdateById(Long id, Person person) throws PersonNotFoundException{
		verifyIfExists(id);
		Person personToUpdate = person;
		personToUpdate.setId(id);
		Person updatePerson = personRepository.save(personToUpdate);
		return updatePerson;
	}
	
	public Person createPerson(Person person) {
		Person personToSave = person;
		Person savedPerson = personRepository.save(personToSave);
		return savedPerson;
	}
	
	public void delete(Long id) throws PersonNotFoundException {
		verifyIfExists(id);
		personRepository.deleteById(id);
	}
	
	public Person verifyIfExists(Long id) throws PersonNotFoundException {
        return personRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

}
