package com.example.ProjectFinalMaktab_part3.project.service.Impl;

import com.example.ProjectFinalMaktab_part3.project.model.Orders;
import com.example.ProjectFinalMaktab_part3.project.repository.GenericRepository;
import com.example.ProjectFinalMaktab_part3.project.repository.OrdersRepository;
import com.example.ProjectFinalMaktab_part3.project.service.OrdersService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrdersServiceImpl extends GenericServiceImpl<Orders, Integer> implements OrdersService {
    private Orders orders;
    private OrdersRepository ordersRepository;

    public OrdersServiceImpl(GenericRepository<Orders, Integer> genericRepository
            , Orders orders, OrdersRepository ordersRepository) {
        super(genericRepository);
        this.orders = orders;
        this.ordersRepository = ordersRepository;
    }

    @Override
    public Orders findBySubTaskName(String subTaskName) {
        return null;
    }

    @Override
    public List<Orders> showOrdersToSpecialist(Integer specialist_id) {
        return null;
    }

    @Override
    public List<Orders> findByCustomerId(Integer customerId) {
        return null;
    }

    @Override
    public Orders chooseOrderCustomer(Integer orderId, List<Orders> ordersList) {
        return null;
    }

    @Override
    public Orders registrationOrders(String address, LocalDateTime timeWorek, String jobDescription, Double proposedPrice, String customer_email, String subTask_name) {
        return null;
    }
}
