package edu.spring.data;

import edu.spring.data.domain.City;
import edu.spring.data.domain.Country;
import edu.spring.data.repo.CityRepository;
import edu.spring.data.repo.CountryRepository;
import edu.spring.data.repo.CountrySpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@SpringBootApplication
public class Main {

    private static final String RUSSIA = "Russia";
    private static final String ENGLAND = "England";
    private static final String GERMAN = "German";

    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private CityRepository cityRepository;

    @PostConstruct
    public void init() {

        System.out.println("---------------------------------------------------------");
        createCountries();

        Country country = countryRepository.findByName("England");
        System.out.println(country);

        City berlin = cityRepository.findByName("Berlin");
        country = countryRepository.findByCitiesIn(berlin);
        System.out.println(country);

        country = countryRepository.findByPopulationGreaterThan(120);
        System.out.println(country);

        country = countryRepository.findCountryByPopulation(55);
        System.out.println(country);

        System.out.println("\nall countries:");
        countryRepository.findAll(CountrySpecifications.all()).forEach(System.out::println);
        System.out.println("\ncountry by name:");
        countryRepository.findAll(CountrySpecifications.getCountryByName("German")).forEach(System.out::println);
        System.out.println("---------------------------------------------------------");
    }


    protected void createCountries() {
        Country countryRussia = new Country(RUSSIA, 144);
        Country countryEngland = new Country(ENGLAND, 55);
        Country countryGerman = new Country(GERMAN, 83);

        countryRussia.setCities(getCitySet(countryRussia));
        countryEngland.setCities(getCitySet(countryEngland));
        countryGerman.setCities(getCitySet(countryGerman));

        countryRepository.save(countryRussia);
        countryRepository.save(countryEngland);
        countryRepository.save(countryGerman);
    }

    protected Set<City> getCitySet(Country country) {

        String cityName1 = null;
        String cityName2 = null;
        String cityName3 = null;

        if (RUSSIA.equals(country.getName())) {
            cityName1 = "Moscow";
            cityName2 = "Togliatti";
            cityName3 = "Samara";
        } else if (ENGLAND.equals(country.getName())) {
            cityName1 = "London";
            cityName2 = "Manchester";
            cityName3 = "Edinburgh";
        } else if (GERMAN.equals(country.getName())) {
            cityName1 = "Berlin";
            cityName2 = "Hamburg";
            cityName3 = "Munich";
        }

        Set<City> citySet = new HashSet<>();
        City city1 = new City(cityName1);
        City city2 = new City(cityName2);
        City city3 = new City(cityName3);

        citySet.add(city1);
        cityRepository.save(city1);
        citySet.add(city2);
        cityRepository.save(city2);
        citySet.add(city3);
        cityRepository.save(city3);
        return citySet;
    }
}
