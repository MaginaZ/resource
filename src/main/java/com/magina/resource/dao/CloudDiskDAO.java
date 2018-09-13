package com.magina.resource.dao;

import com.magina.resource.domain.CloudDisk;
import com.magina.resource.domain.RequestParam;

import java.util.List;

import com.magina.resource.domain.CloudDiskDTO;

public interface CloudDiskDAO {
    
    List<CloudDisk> listLinks(RequestParam param);
    
    int countLinks(String query);
    
    CloudDisk getLinkById(Long linkId);
    
    void removeLinkById(Long linkId);
    
    void saveLink(CloudDiskDTO cloudDisk);
    
    void updateLink(CloudDiskDTO cloudDisk);
}