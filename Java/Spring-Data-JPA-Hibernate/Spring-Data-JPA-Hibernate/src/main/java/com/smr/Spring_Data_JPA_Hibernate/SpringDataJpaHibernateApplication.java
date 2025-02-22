package com.smr.Spring_Data_JPA_Hibernate;

import com.github.javafaker.Faker;
import com.smr.Spring_Data_JPA_Hibernate.domain.Author;
import com.smr.Spring_Data_JPA_Hibernate.domain.Video;
import com.smr.Spring_Data_JPA_Hibernate.repositories.AuthorRepository;
import com.smr.Spring_Data_JPA_Hibernate.repositories.VideoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringDataJpaHibernateApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringDataJpaHibernateApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			AuthorRepository authorRepository,
			VideoRepository videoRepository
	){
		return args -> {

			for(int i=0; i< 50; i++){
				Faker faker = new Faker();
				Author author = Author.builder()
					.firstName(faker.name().firstName())
					.lastName(faker.name().lastName())
					.age(faker.number().numberBetween(19, 50))
					.email("contact@example.com")
					.build();
			authorRepository.save(author);
			}

			// update author with id = 1
			Author author = Author.builder()
					.id(1)
					.firstName("Pedro")
					.lastName("Pascal")
					.age(30)
					.email("pedro@gmail.com")
					.build();
			// authorRepository.save(author);

			//authorRepository.updateAuthor(100, 1);

			// update all authors
			//authorRepository.updateAllAuthorsAges(100);

			// find by named query
//			authorRepository.findByNamedQuery(30)
//					.forEach(System.out::println);

			/*Video video = Video.builder()
					.name("abd")
					.length(5)
					.build();
			videoRepository.save(video);*/
		};
	}

}
