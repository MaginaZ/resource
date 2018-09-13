package com.magina.resource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.magina.resource.service.ImageService;

@Controller
public class ImageController {
    
    @Autowired
    private ImageService imageService;
    
    /**
     * 资源链接列表
     */
    @GetMapping("/")
    public String linkLiskPage() {
        return "link/link-list";
    }
    
}