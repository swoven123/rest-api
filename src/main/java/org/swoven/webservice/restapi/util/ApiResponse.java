package org.swoven.webservice.restapi.util;

import org.swoven.webservice.restapi.dto.PersonDTO;

import java.util.List;

/**
 * @author swoven
 * Custom API response to client
 */
public class ApiResponse {

  private String status;
  private List<PersonDTO> persons;
  private String errorMessage;

  public ApiResponse(String status, List<PersonDTO> persons, String errorMessage) {
    this.status = status;
    this.persons = persons;
    this.errorMessage = errorMessage;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<PersonDTO> getPersons() {
    return persons;
  }

  public void setPersons(List<PersonDTO> persons) {
    this.persons = persons;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
