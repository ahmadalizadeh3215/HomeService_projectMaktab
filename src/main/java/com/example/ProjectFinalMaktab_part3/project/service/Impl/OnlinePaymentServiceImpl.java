package com.example.ProjectFinalMaktab_part3.project.service.Impl;

import com.example.ProjectFinalMaktab_part3.project.model.OnlinePayment;
import com.example.ProjectFinalMaktab_part3.project.repository.GenericRepository;
import com.example.ProjectFinalMaktab_part3.project.service.OnlinePaymentService;
import org.springframework.stereotype.Service;

@Service
public class OnlinePaymentServiceImpl extends GenericServiceImpl<OnlinePayment, Long>implements OnlinePaymentService {
    public OnlinePaymentServiceImpl(GenericRepository<OnlinePayment, Long> genericRepository) {
        super(genericRepository);
    }
}
