package com.smr.Spring_Data_JPA_Hibernate.repositories;

import com.smr.Spring_Data_JPA_Hibernate.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Integer> {
}
