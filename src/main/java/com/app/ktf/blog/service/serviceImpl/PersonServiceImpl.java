package com.app.ktf.blog.service.serviceImpl;

import com.app.ktf.blog.entity.PersonEntity;
import com.app.ktf.blog.repository.PersonRepository;
import com.app.ktf.blog.service.PersonService;

public class PersonServiceImpl extends GenericServiceImpl<PersonEntity, Long> implements PersonService {
    private final PersonRepository personRepository;
    public PersonServiceImpl(PersonRepository personRepository){
        super(personRepository);
        this.personRepository = personRepository;
    }
}
