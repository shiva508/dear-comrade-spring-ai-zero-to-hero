package com.comrade;

import com.comrade.service.Lang4jOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { DataSourceAutoConfiguration.class })
public class DearComradeAiApplication {
    @Autowired
	private Lang4jOperationService lang4jOperationService;
	public static void main(String[] args) {
		SpringApplication.run(DearComradeAiApplication.class, args);
	}
	@Bean
	public ApplicationRunner applicationRunner(){
		return args -> {
			List<String> questions = List.of("What is your favourite sport?","How is weather look like today?","What is functional interface?");
			lang4jOperationService.lang4jOperationInit();
			questions.forEach(question ->{
				System.out.println(lang4jOperationService.lang4jOperations(question));
			});
			lang4jOperationService.documentProcessing();
        };
	}
}
