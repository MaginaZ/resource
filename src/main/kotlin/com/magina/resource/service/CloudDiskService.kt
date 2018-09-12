package com.magina.resource.service

import com.magina.resource.dao.CloudDiskDAO
import com.magina.resource.domain.CloudDisk
import com.magina.resource.domain.CloudDiskDTO
import com.magina.resource.domain.RequestParam
import com.google.common.collect.Lists
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
open class CloudDiskService {
	
	@Autowired
	private val cloudDiskDao: CloudDiskDAO? = null
	
	open fun listLinks(param: RequestParam) : List<CloudDisk> {
		if (cloudDiskDao != null) {
			return cloudDiskDao.listLinks(param)
		}
		return Lists.newArrayList()
	}
	
	open fun countLinks(query: String?) : Int {
		if (cloudDiskDao != null) {
			return cloudDiskDao.countLinks(query)
		}
		return 0
	}
	
	open fun getLinkById(linkId: Long) : CloudDisk {
		if (cloudDiskDao != null) {
			return cloudDiskDao.getLinkById(linkId)
		}
		return CloudDisk()
	}
	
	@Transactional
	open fun removeLinkById(linkId: Long) {
		if (cloudDiskDao != null) {
			cloudDiskDao.removeLinkById(linkId)
		}
	}
	
	@Transactional
	open fun saveLink(cloudDisk: CloudDiskDTO) {
		if (cloudDiskDao != null) {
			cloudDiskDao.saveLink(cloudDisk)
		}
	}
	
	@Transactional
	open fun updateLink(cloudDisk: CloudDiskDTO) {
		if (cloudDiskDao != null) {
			cloudDiskDao.updateLink(cloudDisk)
		}
	}
}