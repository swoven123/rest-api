package org.swoven.webservice.restapi.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

/**
 * This is the entity class for DB persistence
 */
@Entity
@Table
public class Person {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  @Column(name = "first_name")
  @NotNull
  @Size(min = 1)
  private String firstName;

  @Column(name = "last_name")
  @NotNull
  @Size(min = 1)
  private String lastName;

  @Column
  @Min(1)
  @Max(200)
  private int age;

  @Column(name = "favourite_colour")
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
    Person person = (Person) o;
    return age == person.age &&
            Objects.equals(id, person.id) &&
            Objects.equals(firstName, person.firstName) &&
            Objects.equals(lastName, person.lastName) &&
            Objects.equals(favouriteColour, person.favouriteColour);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstName, lastName, age, favouriteColour);
  }

}
