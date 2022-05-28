package com.example.ProjectFinalMaktab_part3.project.repository;

import com.example.ProjectFinalMaktab_part3.project.model.Orders;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdersRepository extends GenericRepository<Orders, Integer> {
    Orders findBySubTaskName(String subTaskName);

    @Query("select o from SubTask as st INNER JOIN Specialist as s on s.id=st.specialist.id" +
            " JOIN Orders as o on st.id = o.subTask.id where st.specialist.id = :specialist_id")
    List<Orders> showOrdersToSpecialist(@Param("specialist_id") Integer specialist_id);

    List<Orders> findByCustomerId(Integer customerId);




}

