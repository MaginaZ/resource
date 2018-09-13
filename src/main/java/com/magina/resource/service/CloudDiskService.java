package com.magina.resource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magina.resource.dao.CloudDiskDAO;
import com.magina.resource.domain.CloudDisk;
import com.magina.resource.domain.CloudDiskDTO;
import com.magina.resource.domain.RequestParam;

@Service
public class CloudDiskService {
    
    @Autowired
    private CloudDiskDAO cloudDiskDao;
    
    public List<CloudDisk> listLinks(RequestParam param) {
        return cloudDiskDao.listLinks(param);
    }
    
    public int countLinks(String query) {
        return cloudDiskDao.countLinks(query);
    }
    
    public CloudDisk getLinkById(Long linkId) {
        return cloudDiskDao.getLinkById(linkId);
    }
    
    @Transactional
    public void removeLinkById(Long linkId) {
        cloudDiskDao.removeLinkById(linkId);
    }
    
    @Transactional
    public void saveLink(CloudDiskDTO cloudDisk) {
        cloudDiskDao.saveLink(cloudDisk);
    }
    
    @Transactional
    public void updateLink(CloudDiskDTO cloudDisk) {
        cloudDiskDao.updateLink(cloudDisk);
    }
}