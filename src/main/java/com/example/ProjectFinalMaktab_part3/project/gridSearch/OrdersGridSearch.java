package com.example.ProjectFinalMaktab_part3.project.gridSearch;

import com.example.ProjectFinalMaktab_part3.project.model.OrderStatus;
import com.example.ProjectFinalMaktab_part3.project.model.Orders;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrdersGridSearch {
    public Specification<Orders> gridSearchOrders(Double proposedPrice, String subTask, LocalDateTime dateTimeWork, OrderStatus orderStatus) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (proposedPrice != null) {
                predicates.add(criteriaBuilder.equal(root.get("proposedPrice"), proposedPrice));

            }

            if (subTask != null && !subTask.isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("subTask"), subTask));
            }

            if (dateTimeWork != null) {
                predicates.add(criteriaBuilder.like(root.get("dateTimeWork"), "%" + dateTimeWork + "%"));
            }

            if (orderStatus != null) {
                predicates.add(criteriaBuilder.equal(root.get("orderStatus"),  orderStatus ));
            }


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
