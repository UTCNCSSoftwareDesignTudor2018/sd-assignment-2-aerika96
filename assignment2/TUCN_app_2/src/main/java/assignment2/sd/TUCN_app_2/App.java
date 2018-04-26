package assignment2.sd.TUCN_app_2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
/**
 * Hello world!
 *
 */

@SpringBootApplication
@EnableJpaRepositories
public class App {
	public static void main(String[] args) { 
	
	        SpringApplication.run(App.class, args);
	
	}
}
