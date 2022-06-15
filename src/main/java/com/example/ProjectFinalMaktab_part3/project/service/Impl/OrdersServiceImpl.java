package com.example.ProjectFinalMaktab_part3.project.service.Impl;

import com.example.ProjectFinalMaktab_part3.project.gridSearch.OrdersGridSearch;
import com.example.ProjectFinalMaktab_part3.project.model.Customer;
import com.example.ProjectFinalMaktab_part3.project.model.OrderStatus;
import com.example.ProjectFinalMaktab_part3.project.model.Orders;
import com.example.ProjectFinalMaktab_part3.project.model.Specialist;
import com.example.ProjectFinalMaktab_part3.project.repository.GenericRepository;
import com.example.ProjectFinalMaktab_part3.project.repository.OrdersRepository;
import com.example.ProjectFinalMaktab_part3.project.service.OrdersService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrdersServiceImpl extends GenericServiceImpl<Orders, Integer> implements OrdersService {
    private Orders orders;
    private OrdersRepository ordersRepository;
    private CustomerServiceImpl customerService;
    private SpecialistServiceImpl specialistService;
    private OrdersGridSearch ordersGridSearch;

    public OrdersServiceImpl(GenericRepository<Orders, Integer> genericRepository
            , Orders orders, OrdersRepository ordersRepository,
           CustomerServiceImpl customerService,
          SpecialistServiceImpl specialistService,
          OrdersGridSearch ordersGridSearch) {
        super(genericRepository);
        this.orders = orders;
        this.ordersRepository = ordersRepository;
        this.customerService=customerService;
        this.specialistService=specialistService;
        this.ordersGridSearch=ordersGridSearch;
    }

    @Override
    public Orders findBySubTaskName(String subTaskName) {
        return ordersRepository.findBySubTaskName(subTaskName);
    }

    @Override
    public List<Orders> showOrdersToSpecialist(Integer specialist_id) {
        Specialist specialist=specialistService.findById(specialist_id).orElseThrow(
                ()-> new IllegalStateException("specialist_id not found !"));

        return ordersRepository.showOrdersToSpecialist(specialist_id);
    }

    @Override
    public List<Orders> showOrderSpecialist(String email) {
        Specialist specialist=specialistService.findByEmail(email);
        if (specialist ==null){
            return (List<Orders>) new IllegalStateException("specialist not found");
        }

        return ordersRepository.showOrderSpecialist(email);
    }

    @Override
    public List<Orders> findByCustomerId(Integer customerId) {
        return ordersRepository.findByCustomerId(customerId);
    }

    @Override
    public Orders chooseOrderCustomer(Integer orderId, List<Orders> ordersList) {
        return null;
    }

    @Override
    public Orders registrationOrders(String address, LocalDateTime timeWorek, String jobDescription,
                                     Double proposedPrice, String customer_email, String subTask_name) {
        return null;
    }

    @Override
    public Orders findByOrderStatus(String orderStatus) {
        return ordersRepository.findByOrderStatus(orderStatus);
    }
    public void PaymentCredit(Orders order) throws Exception {
        Orders orders= ordersRepository.getById(order.getId());
        if (!orders.getOrderStatus().equals(OrderStatus.Done)){
            throw new Exception("The work is not finished");
        }
        if (orders.getProposedPrice() >orders.getCustomer().getValidity()) {
            throw new Exception("Inventory is not enough");
        }
        Customer customer=orders.getCustomer();
        Double orderFee=customer.getValidity()-orders.getProposedPrice();
        customer.setValidity(orderFee);
        customerService.update(customer);
        orders.setOrderStatus(OrderStatus.Paid);
        update(orders);
        Specialist specialist=orders.getSubTask().getSpecialist();
        Double speFee= ((orders.getProposedPrice() * 70) / 100);
        specialist.setValidity(specialist.getValidity()+speFee);
        specialistService.update(specialist);



    }

    @Override
    public List<Orders> gridSearchOrders(Double proposedPrice, String subTask, LocalDateTime dateTimeWork, OrderStatus orderStatus) {
        Specification<Orders> specification=ordersGridSearch.gridSearchOrders(proposedPrice,subTask,dateTimeWork,orderStatus);
        return ordersRepository.findAll(specification);
    }
}
