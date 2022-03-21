package edu.spring.data.repo;

import edu.spring.data.domain.Country;
import org.springframework.data.jpa.domain.Specification;

/**
 * @author elyntsevkv
 * @version $Id$
 */

public class CountrySpecifications {

    public static Specification<Country> all() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isTrue(criteriaBuilder.literal(Boolean.TRUE));
    }

    public static Specification<Country> getCountryByName(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), name);
    }
}
