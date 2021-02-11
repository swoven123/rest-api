package org.swoven.webservice.restapi.utils;

import org.swoven.webservice.restapi.entity.Person;
import org.swoven.webservice.restapi.repository.PersonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TestUtils {

  public static Person createDummyPerson(String firstName, String lastName, int age, String favouriteColour) {
    Person person = new Person();
    person.setFirstName(firstName);
    person.setLastName(lastName);
    person.setFavouriteColour(favouriteColour);
    person.setAge(age);
    return person;
  }

  public static PersonRepository createMockedRepository() {
    return new PersonRepository() {
      List<Person> personList = new ArrayList<>();

      @Override
      public <S extends Person> S save(S s) {
        if (s.getId() != null) {
          for (Person person : personList) {
            if (person.getId().equals(s.getId())) {
              person.setAge(s.getAge());
              person.setFavouriteColour(s.getFavouriteColour());
              person.setLastName(s.getLastName());
              person.setFirstName(s.getFirstName());
              break;
            }
          }
        } else {
          s.setId(UUID.randomUUID().toString());
          personList.add(s);
        }

        return s;
      }

      @Override
      public <S extends Person> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
      }

      @Override
      public Optional<Person> findById(String s) {
        for (Person p : personList) {
          if (p.getId().equals(s))
            return Optional.of(p);
        }
        return Optional.empty();
      }

      @Override
      public boolean existsById(String s) {
        return false;
      }

      @Override
      public Iterable<Person> findAll() {
        return personList;
      }

      @Override
      public Iterable<Person> findAllById(Iterable<String> iterable) {
        return null;
      }

      @Override
      public long count() {
        return 0;
      }

      @Override
      public void deleteById(String s) {
        Person personToBeDeleted = null;
        for (Person person : personList) {
          if (person.getId().equals(s)) {
            personToBeDeleted = person;
            break;
          }
        }
        personList.remove(personToBeDeleted);
      }

      @Override
      public void delete(Person person) {

      }

      @Override
      public void deleteAll(Iterable<? extends Person> iterable) {

      }

      @Override
      public void deleteAll() {

      }
    };
  }
}
