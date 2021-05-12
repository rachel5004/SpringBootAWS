package com.springboot.springboot.domain.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface postRepository extends JpaRepository<post,Long> {

    @Query("SELECT p FROM post p ORDER BY p.id DESC")
    List<post> findAllDesc();
}
