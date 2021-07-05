package com.person.personapi.utils;

import java.time.LocalDate;
import java.util.Collections;
import com.person.personapi.model.Contact;
import com.person.personapi.model.Person;

public class PersonUtils {

    public static Contact createFakeContact() {
        return Contact.builder()
        		.id(1L)
        		.name("Maria")
                .phone("9980189885574")
                .email("email@email.com")
                .build();
    }
	
	public static Person createFakePerson() {
        return Person.builder()
        		.id(1L)
                .name("Tobias")
                .cpf("47509208866")
                .birthDate(LocalDate.of(2010, 10, 1))
                .contacts(Collections.singletonList(createFakeContact()))
                .build();
    }
	
}
