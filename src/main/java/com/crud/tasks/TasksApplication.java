package com.crud.tasks;

import com.crud.tasks.domain.TaskDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//
public class TasksApplication {
//main
	public static void main(String[] args) {

		SpringApplication.run(TasksApplication.class, args);
	}
}
