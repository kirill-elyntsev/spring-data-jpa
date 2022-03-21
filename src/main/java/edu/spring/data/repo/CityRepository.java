package edu.spring.data.repo;

import edu.spring.data.domain.City;
import org.springframework.data.repository.CrudRepository;

/**
 * @author elyntsevkv
 * @version $Id$
 */
public interface CityRepository extends CrudRepository<City, Integer> {

    City findByName(String name);
}
