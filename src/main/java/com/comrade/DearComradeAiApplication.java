package com.comrade;

import com.comrade.service.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class DearComradeAiApplication {
    @Autowired
	private ChatService pdfOperationService;

	public static void main(String[] args) {
		SpringApplication.run(DearComradeAiApplication.class, args);
	}
}
