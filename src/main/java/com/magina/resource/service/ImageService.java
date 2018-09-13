package com.magina.resource.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magina.resource.dao.ImageDAO;
import com.magina.resource.domain.RequestParam;

@Service
public class ImageService {
    
    @Autowired
    private ImageDAO imageDAO;
    
    public void listUrls(RequestParam param) {
        
    }
    
}