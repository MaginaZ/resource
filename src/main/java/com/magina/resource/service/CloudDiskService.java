package com.magina.resource.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.magina.resource.domain.CloudDisk;
import com.magina.resource.repository.CloudDiskRepository;

@Service
public class CloudDiskService {
    
    @Autowired
    private CloudDiskRepository cloudDiskRepo;
    
    public Page<CloudDisk> listLinks(Pageable pageable, String query) {
        return StringUtils.hasText(query) ? cloudDiskRepo.findByNameLike(query, pageable) : cloudDiskRepo.findAll(pageable);
    }
    
    public CloudDisk getLink(Long id) {
        return cloudDiskRepo.findOne(id);
    }
    
    @Transactional
    public void delete(Long id) {
        cloudDiskRepo.delete(id);
    }
    
    @Transactional
    public void save(CloudDisk cloudDisk) {
        cloudDiskRepo.save(cloudDisk);
    }

}