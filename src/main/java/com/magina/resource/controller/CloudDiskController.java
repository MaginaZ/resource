package com.magina.resource.controller;

import com.magina.resource.domain.CloudDisk;
import com.magina.resource.domain.CloudDiskDTO;
import com.magina.resource.domain.PageList;
import com.magina.resource.domain.RequestParam;
import com.magina.resource.service.CloudDiskService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public PageList<CloudDisk> listLinks(int pageNum, int size, String query) {
        
        int start = (pageNum - 1) * size;
        RequestParam param = new RequestParam(start, size, query);
        
        int record = cloudDiskService.countLinks(query);
        List<CloudDisk> links = cloudDiskService.listLinks(param);
        
        return new PageList<CloudDisk>(record, size, links);
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
        cloudDiskService.saveLink(new CloudDiskDTO(name, link, code, resInfo));
        return HttpStatus.CREATED.value();
    }
    
    /**
     * 修改资源链接
     */
    @GetMapping("/link-update")
    public String linkUpdatePage(Model model, Long linkId) {
        model.addAttribute("link", cloudDiskService.getLinkById(linkId));
        return "link/link-update";
    }
    
    @PutMapping("/links")
    @ResponseBody
    public int updateLink(Long id, String name, String link, String code, String resInfo) {
        cloudDiskService.updateLink(new CloudDiskDTO(id, name, link, code, resInfo));
        return HttpStatus.CREATED.value();
    }
    
    /**
     * 资源链接详情
     */
    @GetMapping("/link-info")
    public String linkInfo(Model model, Long linkId) {
        model.addAttribute("link", cloudDiskService.getLinkById(linkId));
        return "link/link-info";
    }
    
    /**
     * 删除资源链接
     */
    @DeleteMapping("/links")
    @ResponseBody
    public int removeLink(Long linkId) {
        cloudDiskService.removeLinkById(linkId);
        return HttpStatus.NO_CONTENT.value();
    }
}