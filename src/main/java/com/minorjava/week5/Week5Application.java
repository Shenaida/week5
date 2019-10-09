package com.minorjava.week5;

import com.minorjava.week5.model.BankAccount;
import com.minorjava.week5.service.BankAccountService;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.slf4j.Logger;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Week5Application {

	private static final Logger log= LoggerFactory.getLogger(Week5Application.class);
	public static void main(String[] args) {
		SpringApplication.run(Week5Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(BankAccountService service){
		return args -> {
			service.save(new BankAccount(1L,"INGB1", 12345678L, 100, BankAccount.AccountStatus.valueOf("ACTIVE") ));
			for(BankAccount account : service.findAll()){
				log.info(account.toString());
			}
		};
	}

}
