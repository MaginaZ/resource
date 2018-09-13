package com.magina.resource.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;

@Entity
public class CloudDisk extends Base {
    
    private String name;
    private String link;
    private String code;
    private String resInfo;
    
    public CloudDisk() {}
    
    public CloudDisk(String name, String link, String code, String resInfo) {
        this.name = name;
        this.link = link;
        this.code = code;
        this.resInfo = resInfo;
        this.createTime = LocalDateTime.now();
    }

    public CloudDisk(Long id, String name, String link, String code, String resInfo) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.code = code;
        this.resInfo = resInfo;
        this.updateTime = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getResInfo() {
        return resInfo;
    }
    public void setResInfo(String resInfo) {
        this.resInfo = resInfo;
    }
    
}