package org.swoven.webservice.restapi.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.swoven.webservice.restapi.dto.PersonDTO;
import org.swoven.webservice.restapi.entity.Person;
import org.swoven.webservice.restapi.service.PersonService;
import org.swoven.webservice.restapi.util.ApiResponse;
import org.swoven.webservice.restapi.util.Constants;
import org.swoven.webservice.restapi.util.Status;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author swoven
 * This is the main controller class and is the entry point for endpoints
 */
@RestController
@RequestMapping("/person")
public class PersonController {

  private PersonService personService;
  private ModelMapper modelMapper;

  @Autowired
  public PersonController(PersonService personService, ModelMapper modelMapper) {
    this.personService = personService;
    this.modelMapper = modelMapper;
  }

  @GetMapping
  public ApiResponse getAllPerson() {
    List<Person> personList = null;
    try {
      personList = personService.getAllPerson();
    } catch (Exception exception) {
      return new ApiResponse(Status.ERROR.value(), null, Constants.ERROR_RETRIEVING_PERSON);
    }
    return new ApiResponse(Status.OK.value(), personList.stream().map(this::convertToDto).collect(Collectors.toList()), null);
  }

  @GetMapping("/{id}")
  public ApiResponse getPersonById(@PathVariable("id") String id) {
    Person person = null;
    try {
      person = personService.getPersonById(id);
    } catch (Exception exception) {
      return new ApiResponse(Status.ERROR.value(), null, Constants.ERROR_RETRIEVING_PERSON);
    }
    return new ApiResponse(Status.OK.value(), Collections.singletonList(convertToDto(person)), null);
  }

  @PostMapping
  public ApiResponse addPerson(@RequestBody @Valid PersonDTO personDTO) {
    Person person = null;
    try {
      person = convertToEntity(personDTO);
      personService.addOrUpdatePerson(person);
    } catch (ParseException e) {
      return new ApiResponse(Status.ERROR.value(), null, Constants.ERROR_SAVING_PERSON);
    }
    return new ApiResponse(Status.OK.value(), Collections.singletonList(personDTO), null);
  }

  @PutMapping(value = "/{id}")
  public ApiResponse updatePerson(@PathVariable("id") String id, @RequestBody @Valid PersonDTO personDTO) {
    Person person = null;
    try {
      person = personService.getPersonById(id);
      if (person == null) {
        return new ApiResponse(Status.ERROR.value(), null, Constants.ERROR_FINDING_PERSON);
      }
      person.setFavouriteColour(personDTO.getFavouriteColour());
      person.setLastName(personDTO.getLastName());
      person.setAge(personDTO.getAge());
      person.setFirstName(personDTO.getFirstName());
      personService.addOrUpdatePerson(person);
    } catch (Exception e) {
      return new ApiResponse(Status.ERROR.value(), null, Constants.ERROR_UPDATING_PERSON);
    }
    return new ApiResponse(Status.OK.value(), Collections.singletonList(convertToDto(person)), null);
  }

  @DeleteMapping("/{id}")
  private ApiResponse deletePerson(@PathVariable("id") String id) {
    try {
      personService.removePersonById(id);
    } catch (Exception exception) {
      return new ApiResponse(Status.ERROR.value(), null, Constants.ERROR_DELETING_PERSON);
    }
    return new ApiResponse(Status.OK.value(), null, null);
  }

  private PersonDTO convertToDto(Person person) {
    PersonDTO personDTO = modelMapper.map(person, PersonDTO.class);
    return personDTO;
  }

  private Person convertToEntity(PersonDTO personDTO) throws ParseException {
    Person person = modelMapper.map(personDTO, Person.class);
    return person;
  }

}
