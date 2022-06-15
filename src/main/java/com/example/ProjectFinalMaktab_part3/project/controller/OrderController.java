package com.example.ProjectFinalMaktab_part3.project.controller;


import com.example.ProjectFinalMaktab_part3.project.model.*;
import com.example.ProjectFinalMaktab_part3.project.service.Impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private Orders orders;
    private OrdersServiceImpl ordersService;
    private CustomerServiceImpl customerService;
    private SpecialistServiceImpl specialistService;
    private UserServiceImpl userService;
    private SubTaskServiceImpl subTaskService;


    public OrderController(Orders orders, OrdersServiceImpl ordersService,
                           CustomerServiceImpl customerService,
                           SpecialistServiceImpl specialistService,
                           UserServiceImpl userService, SubTaskServiceImpl subTaskService
    ) {
        this.orders = orders;
        this.ordersService = ordersService;
        this.customerService = customerService;
        this.specialistService = specialistService;
        this.userService = userService;
        this.subTaskService = subTaskService;

    }

    private final Logger LOG = LoggerFactory.getLogger(SpecialistController.class);


    @GetMapping("/findAll")
    public ResponseEntity<List<Orders>> getAllOrders() {

        return ResponseEntity.ok(ordersService.findAll());
    }


    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<Orders> get(@PathVariable("id") Integer id) {
        LOG.info("getting Orders with id: {}", id);
        Orders orders = ordersService.findById(id).orElseThrow(() ->
                new IllegalStateException("user with id {} not found" + id));

        return ResponseEntity.ok().body(orders);
    }

    @PostMapping(value = "/saveOrders/{customerEmail}/{subTaskId}/{dateWork}")
    public ResponseEntity<Orders> create(@RequestBody  Orders orders,
                                         @PathVariable("customerEmail") String customerEmail,
                                         @PathVariable("subTaskId") Integer subTaskId,
                                         @PathVariable("dateWork")Integer dateWork) {
        System.out.println(customerEmail);
        System.out.println(subTaskId);
        System.out.println(dateWork);
        Customer customer1 = customerService.findByEmail(customerEmail);
        SubTask subTask = subTaskService.findById(subTaskId).orElseThrow(()
                -> new IllegalStateException("subTask id not found !"));
        orders.setCustomer(customer1);
        orders.setSubTask(subTask);
        orders.setOrderStatus(OrderStatus.Waiting_For_Expert_Suggestions);
        orders.setDateOrderRegistration(LocalDateTime.now());
       orders.setDateTimeWork(LocalDateTime.now().plus(dateWork, ChronoUnit.DAYS));
        Orders orders1 = ordersService.save(orders);
        return ResponseEntity.ok(orders1);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Orders> update(@PathVariable("id") Integer id, @RequestBody Orders orders) {

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
    ResponseEntity<Orders> paymentByCredit(@PathVariable("id") Integer id, @RequestBody Orders orders) throws Exception {
        LOG.info("updating orders: {}", orders);
        Orders order = ordersService.findById(id).orElseThrow(() ->
                new IllegalStateException("order not found"));
        ordersService.PaymentCredit(order);
        return new ResponseEntity<Orders>(order, HttpStatus.OK);
    }


    @GetMapping(value = "/showOrdersToSpecialist/{specialistEmail}")
    public ResponseEntity<List<Orders>> showOrdersToSpecialist(@RequestBody Orders orders ,
                                                               @PathVariable("specialistEmail")String specialistEmail){
        System.out.println(specialistEmail);
        Specialist specialist=specialistService.findByEmail(specialistEmail);
        List<Orders> ordersList=ordersService.showOrderSpecialist(specialistEmail);

        return new  ResponseEntity<List<Orders>>(ordersList,HttpStatus.OK);
    }
    @GetMapping(value = "/gridSreach/")
    public ResponseEntity<List<Orders>> gridSreach(@RequestParam(required = false, name = "proposedPrice") Double proposedPrice,
                                                  @RequestParam(required = false, name = "subTask") String subTask,
                                                  @RequestParam(required = false, name = "dateTimeWork") LocalDateTime dateTimeWork,
                                                  @RequestParam(required = false, name = "orderStatus") OrderStatus orderStatus) {

        List<Orders> ordersList = ordersService.gridSearchOrders(proposedPrice, subTask, dateTimeWork, orderStatus);

        return new ResponseEntity<List<Orders>>(ordersList,HttpStatus.OK);
    }


}
