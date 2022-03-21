package edu.spring.data.repo;

import edu.spring.data.domain.City;
import edu.spring.data.domain.Country;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * @author elyntsevkv
 * @version $Id$
 */
public interface CountryRepository extends CrudRepository<Country, Integer>, JpaSpecificationExecutor<Country> {

    Country findByName(String name);

    Country findByCitiesIn(City city);

    Country findByPopulationGreaterThan(int value);

    @Query("SELECT c FROM Country c WHERE c.population = ?1")
    Country findCountryByPopulation(int value);
}
