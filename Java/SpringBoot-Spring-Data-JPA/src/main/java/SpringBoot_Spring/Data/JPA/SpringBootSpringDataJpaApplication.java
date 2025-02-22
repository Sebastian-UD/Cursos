package SpringBoot_Spring.Data.JPA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Collections;

@SpringBootApplication
public class SpringBootSpringDataJpaApplication {

	public static void main(String[] args) {

		//SpringApplication app = new SpringApplication(SpringBootSpringDataJpaApplication.class);
		//app.setDefaultProperties(Collections.singletonMap("spring.profiles.active", "dev"));
		//ConfigurableApplicationContext ctx = app.run(args);

		SpringApplication.run(SpringBootSpringDataJpaApplication.class, args);
	}
}
