package com.magina.resource.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.magina.resource.domain.CloudDisk;

@Repository
public interface CloudDiskRepository extends JpaRepository<CloudDisk, Long> {
    
    Page<CloudDisk> findByNameLike(String name, Pageable pageable);
    
}