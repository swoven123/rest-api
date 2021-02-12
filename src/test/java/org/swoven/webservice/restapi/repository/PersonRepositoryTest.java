package org.swoven.webservice.restapi.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.swoven.webservice.restapi.entity.Person;
import org.swoven.webservice.restapi.utils.TestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;
  @Autowired
  private PersonRepository personRepository;

  @Test
  public void testRepositoryFindAllMethod() {
    Person person = TestUtils.createDummyPerson("Test", "Test", 30, "blue");
    Person person2 = TestUtils.createDummyPerson("Test2", "Test2", 32, "blue2");
    entityManager.persist(person);
    entityManager.persist(person2);
    entityManager.flush();
    List<Person> personList = (List<Person>) personRepository.findAll();
    assertEquals(2, personList.size());
  }

  @Test
  public void testRepositoryFindById() {
    Person person = TestUtils.createDummyPerson("Test", "Test", 30, "blue");
    entityManager.persist(person);
    entityManager.flush();
    List<Person> personList = (List<Person>) personRepository.findAll();
    String idToBeTested = personList.get(0).getId();
    Person personExtractedFromDb = personRepository.findById(idToBeTested).get();
    assertEquals(person.getFirstName(), personExtractedFromDb.getFirstName());
  }

  @Test
  public void testRepositorySaveMethod() {
    Person person = TestUtils.createDummyPerson("Test", "Test", 30, "blue");
    personRepository.save(person);
    assertEquals(person.getFirstName(), personRepository.findAll().iterator().next().getFirstName());
    personRepository.deleteAll();
  }

  @Test
  public void testRepositoryDeleteMethod() {
    Person person = TestUtils.createDummyPerson("Test", "Test", 30, "blue");
    entityManager.persist(person);
    entityManager.flush();
    Person personFromDb = personRepository.findAll().iterator().next();
    personRepository.deleteById(personFromDb.getId());
    List<Person> personList = (List<Person>) personRepository.findAll();
    assertEquals(0, personList.size());
  }

  @Test
  public void testSaveAll() {
    List<Person> personList = new ArrayList<>();
    Person person = TestUtils.createDummyPerson("Test", "Test", 30, "blue");
    Person person1 =TestUtils.createDummyPerson("Test", "Test", 30, "blue");
    Person person2 =TestUtils.createDummyPerson("Test", "Test", 30, "blue");
    personList.add(person);
    personList.add(person1);
    personList.add(person2);
    personRepository.saveAll(personList);
    List<Person> listFromDb = (List<Person>) personRepository.findAll();
    assertEquals(3, listFromDb.size());
    personRepository.deleteAll();
  }

}
