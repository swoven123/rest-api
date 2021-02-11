package org.swoven.webservice.restapi.util;

import org.swoven.webservice.restapi.dto.PersonDTO;

import java.util.List;

/**
 * @author swoven
 * Custom API response to client
 */
public class ApiResponse {

  private String status;
  private List<PersonDTO> personDTOList;
  private String errorMessage;

  public ApiResponse(String status, List<PersonDTO> personDTOList, String errorMessage) {
    this.status = status;
    this.personDTOList = personDTOList;
    this.errorMessage = errorMessage;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public List<PersonDTO> getPersonDTOList() {
    return personDTOList;
  }

  public void setPersonDTOList(List<PersonDTO> personDTOList) {
    this.personDTOList = personDTOList;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }
}
