package com.gclasscn.resource.service

import com.gclasscn.resource.dao.CloudDiskDAO
import com.gclasscn.resource.domain.CloudDisk
import com.gclasscn.resource.domain.CloudDiskDTO
import com.gclasscn.resource.domain.RequestParam
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
}