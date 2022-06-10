package com.example.ProjectFinalMaktab_part3.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class OnlinePayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Customer customer;
    @OneToOne
    private Orders orders;
    @Size(min = 12,max = 16,message = "invalid your number card!")
    private  String cardNumber;
    @Transient
    private String captcha;

    @Transient
    private String hiddenCaptcha;

    @Transient
    private String realCaptcha;
}
