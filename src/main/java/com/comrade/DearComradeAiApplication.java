package com.comrade;

import com.comrade.service.ChatService;
import com.comrade.service.PgVectorStoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;

@SpringBootApplication
@Slf4j
public class DearComradeAiApplication {
    @Autowired
	private ChatService pdfOperationService;

	public static void main(String[] args) {
		SpringApplication.run(DearComradeAiApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner(PgVectorStoreService pgVectorStoreService){
		return args -> {
			String status = pgVectorStoreService.loadData();
			log.info(status);
		};
	}
}
