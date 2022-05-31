package com.example.ProjectFinalMaktab_part3.project.service.Impl;

import com.example.ProjectFinalMaktab_part3.project.model.Offer;
import com.example.ProjectFinalMaktab_part3.project.model.SortOrdersBy;
import com.example.ProjectFinalMaktab_part3.project.model.Users;
import com.example.ProjectFinalMaktab_part3.project.repository.GenericRepository;
import com.example.ProjectFinalMaktab_part3.project.repository.OfferRepository;
import com.example.ProjectFinalMaktab_part3.project.service.OfferService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OfferServiceImpl extends GenericServiceImpl<Offer, Integer> implements OfferService {
    private Offer offer;
    private OfferRepository offerRepository;

    public OfferServiceImpl(GenericRepository<Offer, Integer> genericRepository, Offer offer
            , OfferRepository offerRepository) {
        super(genericRepository);
        this.offer = offer;
        this.offerRepository = offerRepository;
    }

    @Override
    public void findByOrdersId(Integer orderId) {

    }

    @Override
    public Offer saveOffer(String durationWork, Double proposedPrice, LocalDateTime startTime, Integer orderId, Integer specialistId) {
        return null;
    }

    @Override
    public List<Offer> findAllByOrders(Integer orders, SortOrdersBy sortOrdersBy) {
        return null;
    }

    @Override
    public void selectOfferByCustomer(Integer orderId, Integer offerId) {

    }
}
