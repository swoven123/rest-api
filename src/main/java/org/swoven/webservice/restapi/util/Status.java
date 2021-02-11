package org.swoven.webservice.restapi.util;

/**
 * @author swoven
 * This enum contains the rest service status message
 */
public enum Status {
  OK("SUCCESS"), ERROR("ERROR");

  private String value;

  Status(String value) {
    this.value = value;
  }

  public String value() {
    return this.value;
  }
}
