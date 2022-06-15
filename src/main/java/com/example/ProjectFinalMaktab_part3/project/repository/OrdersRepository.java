package com.example.ProjectFinalMaktab_part3.project.repository;

import com.example.ProjectFinalMaktab_part3.project.model.Orders;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdersRepository extends GenericRepository<Orders, Integer>, JpaSpecificationExecutor<Orders> {
    Orders findBySubTaskName(String subTaskName);

    @Query("select o from SubTask as st INNER JOIN Specialist as s on s.id=st.specialist.id" +
            " JOIN Orders as o on st.id = o.subTask.id where st.specialist.id = :specialist_id")
    List<Orders> showOrdersToSpecialist(@Param("specialist_id") Integer specialist_id);

    @Query("select o from SubTask as st inner join Specialist as s on s.email=st.specialist.email" +
            " join Orders as o on st.id=o.subTask.id where st.specialist.email= :specialist_email")
    List<Orders> showOrderSpecialist(@Param("specialist_email")String specialist_email);

    List<Orders> findByCustomerId(Integer customerId);
    Orders findByOrderStatus(String orderStatus);
    List<Orders> findAll(Specification<Orders> specification);




}

