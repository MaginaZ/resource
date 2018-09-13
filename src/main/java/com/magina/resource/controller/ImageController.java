package com.magina.resource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magina.resource.domain.Image;
import com.magina.resource.service.ImageService;

@Controller
public class ImageController {
    
    @Autowired
    private ImageService imageService;
    
    /**
     * 资源链接列表
     */
    @GetMapping("/images/page")
    public String linkLiskPage() {
        return "link/link-list";
    }
    
    @GetMapping("/images")
    @ResponseBody
    public Page<Image> listLinks(int pageNum, int size, String query) {
        
        Pageable pageable = new PageRequest(pageNum, size, new Sort(Sort.Direction.DESC, "id"));
        
        return imageService.listImages(pageable, query);
    }
    
}