package com.comrade;

import com.comrade.service.PdfOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DearComradeAiApplication {
    @Autowired
	private PdfOperationService pdfOperationService;
	public static void main(String[] args) {
		SpringApplication.run(DearComradeAiApplication.class, args);
	}
	@Bean
	public ApplicationRunner applicationRunner(){
		return args -> {
			//pdfOperationService.pdfOperationInit();
			//pdfOperationService.resumeOperationInit();
        };
	}
}
