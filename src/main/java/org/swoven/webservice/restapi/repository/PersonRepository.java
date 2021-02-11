package org.swoven.webservice.restapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.swoven.webservice.restapi.entity.Person;

/**
 * @author swoven
 * This is person entity repository for persistence
 */
@Repository
public interface PersonRepository extends CrudRepository<Person, String> {
}
