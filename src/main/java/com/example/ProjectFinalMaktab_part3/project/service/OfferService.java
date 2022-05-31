package com.example.ProjectFinalMaktab_part3.project.service;

import com.example.ProjectFinalMaktab_part3.project.model.Offer;
import com.example.ProjectFinalMaktab_part3.project.model.SortOrdersBy;

import java.time.LocalDateTime;
import java.util.List;

public interface OfferService extends GenericService<Offer, Integer> {
    void findByOrdersId(Integer orderId);

    Offer saveOffer(String durationWork, Double proposedPrice, LocalDateTime startTime, Integer orderId
            , Integer specialistId);

    List<Offer> findAllByOrders(Integer orders, SortOrdersBy sortOrdersBy);

    void selectOfferByCustomer(Integer orderId, Integer offerId);
}
