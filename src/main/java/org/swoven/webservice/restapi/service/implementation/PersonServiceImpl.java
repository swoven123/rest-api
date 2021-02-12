package org.swoven.webservice.restapi.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.swoven.webservice.restapi.entity.Person;
import org.swoven.webservice.restapi.repository.PersonRepository;
import org.swoven.webservice.restapi.service.PersonService;

import java.util.List;

/**
 * @author swoven
 * This class passes the data from controller to repository layer
 */
@Service
public class PersonServiceImpl implements PersonService {

  private PersonRepository personRepository;

  @Autowired
  public PersonServiceImpl(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @Override
  public Person getPersonById(String id) {
    return personRepository.findById(id).get();
  }

  @Override
  public List<Person> getAllPerson() {
    return (List<Person>) personRepository.findAll();
  }

  @Override
  public void addOrUpdatePerson(Person person) {
    personRepository.save(person);
  }

  @Override
  public void bulkInsert(List<Person> personList) {
    personRepository.saveAll(personList);
  }

  @Override
  public void removePersonById(String id) {
    personRepository.deleteById(id);
  }
}
