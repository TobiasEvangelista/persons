package com.person.personapi.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.person.personapi.model.Person;
import com.person.personapi.utils.PersonUtils;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {

	@Autowired
	private PersonRepository personRepository;
	
	@Test
	public void deveriaCriarPessoa() {
		Person pessoa = PersonUtils.createFakePerson();
		Person pessoaSalvo = personRepository.save(pessoa);
		Person pessoaCriada = personRepository.findByName(pessoa.getName());
	
		assertNotNull(pessoaCriada);
		assertEquals(pessoaCriada.getId(), pessoaSalvo.getId());
	}
	
	@Test
	public void deveriaRetornarListaPessoas() {
		Person pessoa = PersonUtils.createFakePerson();
		personRepository.save(pessoa);
		
		List<Person> listaPessoas = personRepository.findAll();
		
		assertNotNull(listaPessoas);
		assertEquals(listaPessoas.size(), 1);
	}

	@Test
	public void deveriaDeletarPessoa() {
		Person pessoa = PersonUtils.createFakePerson();
		personRepository.save(pessoa);
		personRepository.delete(pessoa);
		Assertions.assertThat(personRepository.findById(pessoa.getId())).isEmpty();
		
	}
	
	@Test
	public void deveriaAtualizarPessoa() {
		Person pessoa = PersonUtils.createFakePerson();
		personRepository.save(pessoa);
		pessoa.setName("Carlos");
		Person pessoaAtualizada = personRepository.save(pessoa);
		Assertions.assertThat(pessoaAtualizada.getName()).isEqualTo("Carlos");
		
	}
}
