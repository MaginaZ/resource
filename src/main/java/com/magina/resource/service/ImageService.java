package com.magina.resource.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.magina.resource.domain.Image;
import com.magina.resource.repository.ImageRepository;

@Service
public class ImageService {
    
    @Autowired
    private ImageRepository imageRepository;
    
    public Page<Image> listImages(Pageable pageable, String query) {
        return StringUtils.hasText(query) ? imageRepository.findByNameLike(query, pageable) : imageRepository.findAll(pageable);
    }
    
}