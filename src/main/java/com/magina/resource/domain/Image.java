package com.magina.resource.domain;

import javax.persistence.Entity;

@Entity
public class Image extends Base {
    
    private String name;
    private String link;
    private boolean used;
    
    public Image() {}
    
    public Image(String name, String link, boolean used) {
        this.name = name;
        this.link = link;
        this.used = used;
    }

    public Image(Long id, String name, String link, boolean used) {
        this(name, link, used);
        this.id = id;
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
    public boolean isUsed() {
        return used;
    }
    public void setUsed(boolean used) {
        this.used = used;
    }
    
}