package com.magina.resource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.magina.resource.domain.CloudDisk;
import com.magina.resource.service.CloudDiskService;

@Controller
public class CloudDiskController {
    
    @Autowired
    private CloudDiskService cloudDiskService;
    
    /**
     * 资源链接列表
     */
    @GetMapping("/")
    public String linkLiskPage() {
        return "link/link-list";
    }
    
    @GetMapping("/links")
    @ResponseBody
    public Page<CloudDisk> listLinks(int pageNum, int size, String query) {
        
        Pageable pageable = new PageRequest(pageNum - 1, size, new Sort(Sort.Direction.DESC, "id"));
        
        return cloudDiskService.listLinks(pageable, query);
    }
    
    /**
     * 添加资源链接
     */
    @GetMapping("/link-add")
    public String linkAddPage() {
        return "link/link-add";
    }
    
    @PostMapping("/links")
    @ResponseBody
    public int saveLink(String name, String link, String code, String resInfo) {
        cloudDiskService.save(new CloudDisk(name, link, code, resInfo));
        return HttpStatus.CREATED.value();
    }
    
    /**
     * 修改资源链接
     */
    @GetMapping("/link-update")
    public String linkUpdatePage(Model model, Long linkId) {
        model.addAttribute("link", cloudDiskService.getLink(linkId));
        return "link/link-update";
    }
    
    @PutMapping("/links")
    @ResponseBody
    public int updateLink(Long id, String name, String link, String code, String resInfo) {
        cloudDiskService.save(new CloudDisk(id, name, link, code, resInfo));
        return HttpStatus.CREATED.value();
    }
    
    /**
     * 资源链接详情
     */
    @GetMapping("/link-info")
    public String linkInfo(Model model, Long linkId) {
        model.addAttribute("link", cloudDiskService.getLink(linkId));
        return "link/link-info";
    }
    
    /**
     * 删除资源链接
     */
    @DeleteMapping("/links")
    @ResponseBody
    public int removeLink(Long linkId) {
        cloudDiskService.delete(linkId);
        return HttpStatus.NO_CONTENT.value();
    }
}