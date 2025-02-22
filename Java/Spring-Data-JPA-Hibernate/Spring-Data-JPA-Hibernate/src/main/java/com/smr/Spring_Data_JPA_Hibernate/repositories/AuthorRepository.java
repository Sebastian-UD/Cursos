package com.smr.Spring_Data_JPA_Hibernate.repositories;

import com.smr.Spring_Data_JPA_Hibernate.domain.Author;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
    // SELECT *  FROM Author WHERE first_name = 'pedro'
    List<Author> findByFirstName(String firstName);

    // SELECT *  FROM Author WHERE first_name = 'pedro'
    List<Author> findByFirstNameIgnoreCase(String firstName);

    // SELECT *  FROM Author WHERE first_name LIKE '%dro%'
    List<Author> findByFirstNameContainingIgnoreCase(String firstName);

    // SELECT *  FROM Author WHERE first_name LIKE 'dro%'
    List<Author> findByFirstNameStartsWithIgnoreCase(String firstName);

    // SELECT *  FROM Author WHERE first_name LIKE '%dro'
    List<Author> findByFirstNameEndsWithIgnoreCase(String firstName);

    // SELECT *  FROM Author WHERE first_name in {'car', 'los', 'coding'}
    List<Author> findByFirstNameInIgnoreCase(List<String> firstNames);

    // update Author a set a.age = 22 where a.id = 1
    @Modifying
    @Transactional
    @Query("update Author a set a.age = :age where a.id = :id")
    int updateAuthor(int age, int id);

    @Modifying
    @Transactional
    @Query("update Author a set a.age = :age")
    void updateAllAuthorsAges(int age);

    @Transactional
    List<Author> findByNamedQuery(@Param("age") int age);
}
