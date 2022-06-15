package com.example.ProjectFinalMaktab_part3.project.gridSearch;

import com.example.ProjectFinalMaktab_part3.project.model.Users;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;

@Component
public class UserGridSearch {
    public Specification<Users> gridSearch(Integer id, String email, String firstName, String lastName) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();


            if (id != null) {
                predicates.add( criteriaBuilder.equal(root.get("id"), id));

            }

            if (email != null && !email.isEmpty()) {
                predicates.add( criteriaBuilder.equal(root.get("email"), email));
            }

            if (firstName != null && !firstName.isEmpty()) {
                predicates.add( criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%"));
            }

            if (lastName != null && !lastName.isEmpty()) {
                predicates.add( criteriaBuilder.like(root.get("lastName"), "%" + lastName + "%"));
            }

            query.orderBy(criteriaBuilder.asc(root.get("lastName")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
