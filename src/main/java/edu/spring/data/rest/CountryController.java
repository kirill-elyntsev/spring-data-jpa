package edu.spring.data.rest;

import edu.spring.data.domain.Country;
import edu.spring.data.repo.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author elyntsevkv
 * @version $Id$
 */

@RestController
public class CountryController {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryController(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @RequestMapping(
            value = "/country/{id}",
            method = RequestMethod.GET
    )
    public Country get(
            @PathVariable("id") int id
    ) {
        Country country = countryRepository.findById(id).orElseThrow(NullPointerException::new);
        return country;
    }

    @RequestMapping(
            value = "/country",
            method = RequestMethod.GET
    )
    public Iterable<Country> get() {
        return countryRepository.findAll();
    }

    @PutMapping("/country/{id}/holder")
    public void changeName(
            @PathVariable("id") int id,
            @RequestParam("name") String name
    ) {
        Country country = countryRepository.findById(id).orElseThrow(NullPointerException::new);
        country.setName(name);
        countryRepository.save(country);
    }

    @DeleteMapping("/country/{id}")
    public void delete(@PathVariable("id") int id) {
        countryRepository.deleteById(id);
    }

}
