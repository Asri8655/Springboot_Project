package many.tables;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import many.tables.model.SecurityQAId;
import many.tables.model.SecurityQA;
import many.tables.repository.AnswerInterface;

@SpringBootApplication
public class TablesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TablesApplication.class, args);
	}
	
//	 @Bean
//	    public CommandLineRunner mappingDemo(AnswerInterface Ans) {
//	        return args -> {
//	        	Ans.save(new SecurityQA(new LoginId(1,5),"Hello"));
//	        	Ans.save(new SecurityQA(new LoginId(1,3),"Hello"));
//	        	Ans.save(new SecurityQA(new LoginId(1,7),"Hello"));
//	        	Ans.save(new SecurityQA(new LoginId(1,9),"Hello"));
//	        };}
}
