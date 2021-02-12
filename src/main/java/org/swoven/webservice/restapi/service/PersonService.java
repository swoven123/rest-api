package org.swoven.webservice.restapi.service;

import org.swoven.webservice.restapi.entity.Person;

import java.util.List;

public interface PersonService {

  Person getPersonById(String id);
  List<Person> getAllPerson();
  void addOrUpdatePerson(Person person);
  void bulkInsert(List<Person> personList);
  void removePersonById(String id);

}
