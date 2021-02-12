package org.swoven.webservice.restapi.dto;

import java.util.List;

/**
 * @author swoven
 * This class encapsulates PersonDTO as a list
 */
public class PersonDTOCollection {

  private List<PersonDTO> persons;

  public List<PersonDTO> getPersons() {
    return persons;
  }

  public void setPersons(List<PersonDTO> persons) {
    this.persons = persons;
  }
}
