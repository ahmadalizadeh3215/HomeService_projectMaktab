package com.example.ProjectFinalMaktab_part3.project.repository;


import com.example.ProjectFinalMaktab_part3.project.model.Orders;
import com.example.ProjectFinalMaktab_part3.project.model.Specialist;
import com.example.ProjectFinalMaktab_part3.project.model.Offer;
import org.springframework.data.domain.Sort;

import java.util.List;


public interface OfferRepository extends GenericRepository<Offer,Integer> {
List<Offer> findByOrdersId(Integer orderId);
List<Offer> findAllByOrders(Orders orders, Sort sort);
List<Offer> findAllBySpecialist(Specialist specialist,Sort sort);
List<Offer> findBySpecialistScore(Specialist specialist,Sort sort);





}
