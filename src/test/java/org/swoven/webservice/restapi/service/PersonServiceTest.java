package org.swoven.webservice.restapi.service;

import org.junit.Before;
import org.junit.Test;
import org.swoven.webservice.restapi.entity.Person;
import org.swoven.webservice.restapi.service.implementation.PersonServiceImpl;
import org.swoven.webservice.restapi.utils.TestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PersonServiceTest {

  private PersonService personService;

  @Before
  public void setUp() {
    personService = new PersonServiceImpl(TestUtils.createMockedRepository());
  }

  @Test
  public void testGetPersonById() {
    Person person = TestUtils.createDummyPerson("Swoven", "Pokharel", 1, "Blue");
    personService.addOrUpdatePerson(person);
    List<Person> personList = personService.getAllPerson();
    String idToBeTested = personList.get(0).getId();
    assertEquals("Swoven", personService.getPersonById(idToBeTested).getFirstName());
  }

  @Test
  public void testGetAllPerson() {
    Person person = TestUtils.createDummyPerson("Swoven", "Pokharel", 1, "Blue");
    Person person2 = TestUtils.createDummyPerson("Swoven", "Pokharel", 1, "Blue");
    Person person3 = TestUtils.createDummyPerson("Swoven", "Pokharel", 1, "Blue");
    personService.addOrUpdatePerson(person);
    personService.addOrUpdatePerson(person2);
    personService.addOrUpdatePerson(person3);
    List<Person> personList = personService.getAllPerson();
    assertEquals(3, personList.size());
  }

  @Test
  public void testUpdateFromAddOrUpdateMethod() {
    Person person = TestUtils.createDummyPerson("Swoven", "Pokharel", 1, "Blue");
    personService.addOrUpdatePerson(person);
    List<Person> personList = personService.getAllPerson();
    Person personFromDb = personList.get(0);
    personFromDb.setFirstName("Name Changed");
    personService.addOrUpdatePerson(personFromDb);
    List<Person> personListAfterChange = personService.getAllPerson();
    assertEquals("Name Changed", personListAfterChange.get(0).getFirstName());
  }

  @Test
  public void testRemovePersonByIdMethod() {
    Person person = TestUtils.createDummyPerson("Swoven", "Pokharel", 1, "Blue");
    personService.addOrUpdatePerson(person);
    List<Person> personList = personService.getAllPerson();
    String idToBeTested = personList.get(0).getId();
    personService.removePersonById(idToBeTested);
    assertEquals(0, personService.getAllPerson().size());
  }

  @Test
  public void testBulkInsertMethod() {
    List<Person> personList = new ArrayList<>();
    Person person = TestUtils.createDummyPerson("Test", "Test", 30, "blue");
    Person person1 = TestUtils.createDummyPerson("Test", "Test", 30, "blue");
    Person person2 = TestUtils.createDummyPerson("Test", "Test", 30, "blue");
    personList.add(person);
    personList.add(person1);
    personList.add(person2);
    personService.bulkInsert(personList);
    assertEquals(3, personService.getAllPerson().size());
  }

}
