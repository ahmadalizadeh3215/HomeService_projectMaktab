package com.example.ProjectFinalMaktab_part3.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;
@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String jobDescription;
    private Double proposedPrice;
    private LocalDateTime dateOrderRegistration;
    private LocalDateTime dateTimeWork;
    private String address;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private SubTask subTask;

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", jobDescription='" + jobDescription + '\'' +
                ", proposedPrice=" + proposedPrice +
                ", dateOrderRegistration=" + dateOrderRegistration +
                ", dateTimeWork=" + dateTimeWork +
                ", address='" + address + '\'' +
                ", orderStatus=" + orderStatus +
                ", customer=" + customer +
                ", underTask=" + subTask +
                '}'+"\n";
    }
}
