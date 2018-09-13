package com.magina.resource.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.magina.resource.domain.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    
    Page<Image> findByNameLike(String name, Pageable pageable);
    
}