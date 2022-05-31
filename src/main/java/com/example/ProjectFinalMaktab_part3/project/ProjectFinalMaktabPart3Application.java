package com.example.ProjectFinalMaktab_part3.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ProjectFinalMaktabPart3Application {

	public static void main(String[] args) {
		System.out.println("1111");
		System.out.println(new BCryptPasswordEncoder().encode("1111"));
		SpringApplication.run(ProjectFinalMaktabPart3Application.class, args);

	}

}
