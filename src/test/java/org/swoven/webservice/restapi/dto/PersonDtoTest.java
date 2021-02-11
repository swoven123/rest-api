package org.swoven.webservice.restapi.dto;


import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.swoven.webservice.restapi.entity.Person;

import static org.junit.Assert.assertEquals;


public class PersonDtoTest {

  private ModelMapper modelMapper = new ModelMapper();

  @Test
  public void testPersonDTOToEntityConversion() {
    PersonDTO personDTO = new PersonDTO();
    personDTO.setAge(30);
    personDTO.setFavouriteColour("Blue");
    personDTO.setFirstName("Tester");
    personDTO.setLastName("Adams");
    personDTO.setId("1");
    Person personEntity = modelMapper.map(personDTO, Person.class);
    assertEquals(personEntity.getAge(), personDTO.getAge());
    assertEquals(personEntity.getFavouriteColour(), personDTO.getFavouriteColour());
    assertEquals(personEntity.getFirstName(), personDTO.getFirstName());
    assertEquals(personEntity.getLastName(), personDTO.getLastName());
    assertEquals(personEntity.getId(), personDTO.getId());
  }

  @Test
  public void testPersonEntityToDTOConversion() {
    Person personEntity = new Person();
    personEntity.setAge(30);
    personEntity.setFavouriteColour("Blue");
    personEntity.setFirstName("Tester");
    personEntity.setLastName("Adams");
    personEntity.setId("1");
    PersonDTO personDTO = modelMapper.map(personEntity, PersonDTO.class);
    assertEquals(personDTO.getAge(), personEntity.getAge());
    assertEquals(personDTO.getFavouriteColour(), personEntity.getFavouriteColour());
    assertEquals(personDTO.getFirstName(), personEntity.getFirstName());
    assertEquals(personDTO.getLastName(), personEntity.getLastName());
    assertEquals(personDTO.getId(), personEntity.getId());
  }

}
