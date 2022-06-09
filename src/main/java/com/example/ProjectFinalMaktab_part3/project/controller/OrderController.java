package com.example.ProjectFinalMaktab_part3.project.controller;

import com.example.ProjectFinalMaktab_part3.project.dto.SpecialistDto;
import com.example.ProjectFinalMaktab_part3.project.model.Customer;
import com.example.ProjectFinalMaktab_part3.project.model.Orders;
import com.example.ProjectFinalMaktab_part3.project.model.Role;
import com.example.ProjectFinalMaktab_part3.project.model.Specialist;
import com.example.ProjectFinalMaktab_part3.project.registration.PasswordEncoder;
import com.example.ProjectFinalMaktab_part3.project.service.Impl.CustomerServiceImpl;
import com.example.ProjectFinalMaktab_part3.project.service.Impl.OrdersServiceImpl;
import com.example.ProjectFinalMaktab_part3.project.service.Impl.SpecialistServiceImpl;
import com.example.ProjectFinalMaktab_part3.project.service.Impl.UserServiceImpl;
import org.hibernate.criterion.Order;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private Orders orders;
    private OrdersServiceImpl ordersService;
    private Customer customer;
    private CustomerServiceImpl customerService;
    private SpecialistServiceImpl specialistService;
    private UserServiceImpl userService;


    public OrderController(Orders orders, OrdersServiceImpl ordersService,
                           Customer customer, CustomerServiceImpl customerService,
                           SpecialistServiceImpl specialistService,
                           UserServiceImpl userService
                           ) {
        this.orders = orders;
        this.ordersService = ordersService;
        this.customer = customer;
        this.customerService = customerService;
        this.specialistService = specialistService;
        this.userService = userService;

    }

    private final Logger LOG = LoggerFactory.getLogger(SpecialistController.class);



    @GetMapping("/findAll")
    public ResponseEntity<List<Orders>> getAllOrders() {

        return ResponseEntity.ok(ordersService.findAll());
    }

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<Orders> get(@PathVariable("id") Integer id) {
        LOG.info("getting Orders with id: {}", id);
        Orders orders= ordersService.findById(id).orElseThrow(()->
                 new IllegalStateException("user with id {} not found"+id));

        return ResponseEntity.ok().body(orders);
    }

    @PostMapping(value = "/saveOrders")
    public ResponseEntity<Orders> create(@RequestBody @Valid Orders orders) {
Orders orders1=ordersService.save(orders);
return ResponseEntity.ok(orders1);
    }

    @PutMapping(value = "/update/{id}")
    public  ResponseEntity<Orders> update(@PathVariable("id")Integer id, @RequestBody Orders orders) {

        LOG.info("updating orders: {}", orders);
       Orders orders1 = ordersService.findById(id).orElseThrow();
        if (orders1 == null) {
            LOG.info("orders with id {} not found", id);
            return new ResponseEntity<Orders>(HttpStatus.NOT_FOUND);
        }
        orders1.setAddress(orders.getAddress());
        orders1.setCustomer(orders.getCustomer());
        orders1.setOrderStatus(orders.getOrderStatus());
        orders1.setJobDescription(orders.getJobDescription());
        orders1.setDateOrderRegistration(orders.getDateOrderRegistration());
        orders1.setDateTimeWork(orders.getDateTimeWork());
        orders1.setSubTask(orders.getSubTask());
        orders1.setProposedPrice(orders.getProposedPrice());
        ordersService.update(orders1);
        return new ResponseEntity<Orders>(orders1, HttpStatus.OK);

    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        LOG.info("deleting orders with id: {}", id);
        Orders orders = ordersService.findById(id).orElseThrow();

        if (orders == null) {
            LOG.info("Unable to delete. orders with id {} not found", id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }

      ordersService.delete(orders);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @PutMapping(value = "/paymentByCredit/{id}")
    ResponseEntity<Orders> paymentByCredit(@PathVariable("id")Integer id, @RequestBody Orders orders) throws Exception {
        LOG.info("updating orders: {}", orders);
      Orders order=ordersService.findById(id).orElseThrow(()->
                new IllegalStateException("order not found"));
      ordersService.PaymentCredit(order);
        return new ResponseEntity<Orders>(order, HttpStatus.OK);
    }


}
