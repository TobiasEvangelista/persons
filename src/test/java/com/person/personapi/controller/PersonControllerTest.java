package com.person.personapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.person.personapi.exception.PersonNotFoundException;
import com.person.personapi.model.Person;
import com.person.personapi.service.PersonService;
import com.person.personapi.utils.PersonUtils;

@ExtendWith(SpringExtension.class)
public class PersonControllerTest {

	@InjectMocks
	private PersonController personController;
	
	@Mock
	private PersonService personServiceMock;	
	
	@BeforeEach
	void setup() throws PersonNotFoundException {
		List<Person> personPage = new ArrayList<>(List.of(PersonUtils.createFakePerson()));
		BDDMockito.when(personServiceMock.listAll(ArgumentMatchers.any()))
			.thenReturn(personPage);
		
		BDDMockito.when(personServiceMock.findById(ArgumentMatchers.anyLong()))
			.thenReturn(PersonUtils.createFakePerson());
		
		BDDMockito.when(personServiceMock.findByName(ArgumentMatchers.anyString()))
			.thenReturn(PersonUtils.createFakePerson());
		
		BDDMockito.when(personServiceMock.createPerson(ArgumentMatchers.any()))
			.thenReturn(PersonUtils.createFakePerson());
	}
	
	//@Test
	public void listAll_deveriaRetornarListaDePessoaQuandoSucesso() {
		Person pessoa = PersonUtils.createFakePerson();
		
		List<Person> pessoas = personController.listAll(null);
		
		Assertions.assertThat(pessoas).isNotEmpty();
		Assertions.assertThat(pessoas).isNotEmpty().hasSize(1);
		Assertions.assertThat(pessoas.get(0).getName()).isEqualTo(pessoa.getName());
	}
	
	@Test
	public void findById_deveriaBuscarPessoaPorId() throws PersonNotFoundException{
		Person pessoaEsperado = PersonUtils.createFakePerson();
		
		Person pessoa = personController.findById(1L);

		Assertions.assertThat(pessoa).isNotNull();		
		Assertions.assertThat(pessoa.getId()).isEqualTo(pessoaEsperado.getId());
		
	}
	
	@Test
	public void findByName_deveriaBuscarPessoaPorNome() throws PersonNotFoundException{
		Person pessoaEsperado = PersonUtils.createFakePerson();
		
		Person pessoa = personController.findByName("Tobias");

		Assertions.assertThat(pessoa).isNotNull();		
		Assertions.assertThat(pessoa.getName()).isEqualTo(pessoaEsperado.getName());
		
	}
	
	@Test
	public void createPerson_deveriaSalvarPessoa() throws PersonNotFoundException{
		Person pessoaEsperado = PersonUtils.createFakePerson();
		
		Person pessoa = personController.createPerson(PersonUtils.createFakePerson());

		Assertions.assertThat(pessoa).isNotNull().isEqualTo(pessoaEsperado);		
		
	}
	
}
