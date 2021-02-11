package org.swoven.webservice.restapi.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author swoven
 * This is the person entity data transfer class
 */
public class PersonDTO implements Serializable {

  private String id;
  @NotNull
  @Size(min = 1)
  private String firstName;
  @NotNull
  @Size(min = 1)
  private String lastName;
  @Min(1)
  @Max(200)
  private int age;
  @NotNull
  @Size(min=3)
  private String favouriteColour;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public String getFavouriteColour() {
    return favouriteColour;
  }

  public void setFavouriteColour(String favouriteColour) {
    this.favouriteColour = favouriteColour;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PersonDTO personDto = (PersonDTO) o;
    return age == personDto.age &&
            Objects.equals(id, personDto.id) &&
            Objects.equals(firstName, personDto.firstName) &&
            Objects.equals(lastName, personDto.lastName) &&
            Objects.equals(favouriteColour, personDto.favouriteColour);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, age, favouriteColour);
  }

}
