package org.swoven.webservice.restapi.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.swoven.webservice.restapi.entity.Person;
import org.swoven.webservice.restapi.service.PersonService;
import org.swoven.webservice.restapi.utils.TestUtils;

import java.util.Arrays;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@RunWith(SpringRunner.class)
public class PersonControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @MockBean
  private PersonService service;

  @Test
  public void testGetAllPersonApi() throws Exception {
    Person dummyPerson = getDummyPerson();
    Person dummyPerson2 = getDummyPerson();
    Person dummyPerson3 = getDummyPerson();
    when(service.getAllPerson()).thenReturn(Arrays.asList(dummyPerson, dummyPerson2, dummyPerson3));
    this.mockMvc.
            perform(get("/person").
                    with(user("user@user.com").
                            password("user").roles("ADMIN"))).
            andExpect(status().isOk()).
            andExpect(jsonPath("$.personDTOList[*]", hasSize(3)));
  }

  @Test
  public void testGetPersonByIdApi() throws Exception {
    Person dummyPerson = getDummyPerson();
    String randomId = UUID.randomUUID().toString();
    dummyPerson.setId(randomId);
    when(service.getPersonById(randomId)).thenReturn(dummyPerson);
    this.mockMvc.perform(get("/person/" + randomId).with(user("user@user.com").
            password("user").roles("ADMIN"))).andExpect(status().isOk()).
            andExpect(jsonPath("$.personDTOList[0].id", is(randomId)));
  }

  @Test
  public void testAddPersonApi() throws Exception {
    Person dummyPerson = getDummyPerson();
    doNothing().when(service).addOrUpdatePerson(dummyPerson);
    this.mockMvc.perform(post("/person/").contentType(MediaType.APPLICATION_JSON)
            .content("{\"firstName\":\"Dummy\", \"lastName\": \"Dummy\", \"age\":31, \"favouriteColour\": \"Dummy\"}").
                    with(user("user@user.com").
                            password("user").roles("ADMIN"))).
            andExpect(status().isOk()).
            andExpect(jsonPath("$.personDTOList[0].firstName", is("Dummy")));
  }

  @Test
  public void testUpdatePersonApi() throws Exception {
    Person dummyPerson = getDummyPerson();
    dummyPerson.setId("testId123");
    when(service.getPersonById("testId123")).thenReturn(dummyPerson);
    Person updatedPerson = getDummyPerson();
    updatedPerson.setFirstName("Modified");
    doNothing().when(service).addOrUpdatePerson(updatedPerson);

    this.mockMvc.perform(put("/person/testId123").contentType(MediaType.APPLICATION_JSON)
            .content("{\"id\":\"testId123\", \"firstName\":\"Modified\", \"lastName\": \"Dummy\", \"age\":31, \"favouriteColour\": \"Dummy\"}").
                    with(user("user@user.com").
                            password("user").roles("ADMIN"))).
            andExpect(status().isOk()).
            andExpect(jsonPath("$.personDTOList[0].firstName", is("Modified")));
  }

  @Test
  public void testDeletePersonApi() throws Exception {
    doNothing().when(service).removePersonById(anyString());
    this.mockMvc.perform(delete("/person/anyRandomId123").with(user("user@user.com").password("user").roles("ADMIN"))).
            andExpect(status().isOk()).
            andExpect(jsonPath("$.status", is("SUCCESS")));
  }

  private Person getDummyPerson() {
    return TestUtils.createDummyPerson("Dummy", "Dummy", 31, "Dummy");
  }

}
