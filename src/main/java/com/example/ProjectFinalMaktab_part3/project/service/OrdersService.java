package com.example.ProjectFinalMaktab_part3.project.service;

import com.example.ProjectFinalMaktab_part3.project.model.OrderStatus;
import com.example.ProjectFinalMaktab_part3.project.model.Orders;

import java.time.LocalDateTime;
import java.util.List;

public interface OrdersService extends GenericService<Orders, Integer> {
    Orders findBySubTaskName(String subTaskName);

    List<Orders> showOrdersToSpecialist(Integer specialist_id);
    List<Orders>showOrderSpecialist(String email);

    List<Orders> findByCustomerId(Integer customerId);

    Orders chooseOrderCustomer(Integer orderId, List<Orders> ordersList);

    Orders registrationOrders(String address, LocalDateTime timeWorek, String jobDescription
            , Double proposedPrice, String customer_email, String subTask_name);
    Orders findByOrderStatus(String orderStatus);
    public void PaymentCredit(Orders orders) throws Exception;
    public List<Orders> gridSearchOrders(Double proposedPrice, String subTask, LocalDateTime dateTimeWork, OrderStatus orderStatus);


}
