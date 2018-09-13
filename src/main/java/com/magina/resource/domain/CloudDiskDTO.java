package com.magina.resource.domain;

public class CloudDiskDTO {
    
    private Long id;
    private String name;
    private String link;
    private String code;
    private String resInfo;
    
    public CloudDiskDTO() {}
    
    public CloudDiskDTO(String name, String link, String code, String resInfo) {
        this.name = name;
        this.link = link;
        this.code = code;
        this.resInfo = resInfo;
    }
    
    public CloudDiskDTO(Long id, String name, String link, String code, String resInfo) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.code = code;
        this.resInfo = resInfo;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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