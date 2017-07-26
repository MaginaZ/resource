package com.gclasscn.resource.controller

import com.gclasscn.resource.domain.CloudDisk
import com.gclasscn.resource.domain.CloudDiskDTO
import com.gclasscn.resource.domain.PageList
import com.gclasscn.resource.domain.RequestParam
import com.gclasscn.resource.service.CloudDiskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class CloudDiskController {
	
	@Autowired
	private val cloudDiskService: CloudDiskService = CloudDiskService()
	
	@RequestMapping(value = "/", method = arrayOf(RequestMethod.GET))
	fun linkLiskPage() = "link/link-list"
	
	@RequestMapping(value = "/links", method = arrayOf(RequestMethod.GET))
	@ResponseBody
	fun listLinks(pageNum: Int, size: Int, query: String?) : PageList<CloudDisk> {
		
		val start = (pageNum - 1) * size
		val param = RequestParam(start, size, query)
		
		val record = cloudDiskService.countLinks(query)
		val links = cloudDiskService.listLinks(param)
		
		return PageList<CloudDisk>(record, size, links)
	}
	
	@RequestMapping(value = "/link-add", method = arrayOf(RequestMethod.GET))
	fun linkAddPage() = "link/link-add"
	
	@RequestMapping(value = "/links",  method = arrayOf(RequestMethod.POST))
	@ResponseBody
	fun saveLink(name: String, link: String, code: String?, resInfo: String?) : Int {
		cloudDiskService.saveLink(CloudDiskDTO(name, link, code, resInfo))
		return HttpStatus.CREATED.value()
	}
	
	@RequestMapping(value = "/links", method = arrayOf(RequestMethod.DELETE))
	@ResponseBody
	fun removeLink(linkId: Long) : Int {
		cloudDiskService.removeLinkById(linkId)
		return HttpStatus.NO_CONTENT.value()
	}
}