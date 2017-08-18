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
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.ui.Model

@Controller
class CloudDiskController {
	
	@Autowired
	private val cloudDiskService: CloudDiskService = CloudDiskService()
	
	/**
	 * 资源链接列表
	 */
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
	
	/**
	 * 添加资源链接
	 */
	@RequestMapping(value = "/link-add", method = arrayOf(RequestMethod.GET))
	fun linkAddPage() = "link/link-add"
	@RequestMapping(value = "/links",  method = arrayOf(RequestMethod.POST))
	@ResponseBody
	fun saveLink(name: String, link: String, code: String?, resInfo: String?) : Int {
		cloudDiskService.saveLink(CloudDiskDTO(name, link, code, resInfo))
		return HttpStatus.CREATED.value()
	}
	
	/**
	 * 修改资源链接
	 */
	@RequestMapping(value = "/link-update", method = arrayOf(RequestMethod.GET))
	fun linkUpdatePage(model: Model, linkId: Long) : String {
		model.addAttribute("link", cloudDiskService.getLinkById(linkId))
		return "link/link-update"
	}
	@RequestMapping(value = "/links",  method = arrayOf(RequestMethod.PUT))
	@ResponseBody
	fun updateLink(id: Long, name: String, link: String, code: String?, resInfo: String?) : Int {
		cloudDiskService.updateLink(CloudDiskDTO(id, name, link, code, resInfo))
		return HttpStatus.CREATED.value()
	}
	
	/**
	 * 资源链接详情
	 */
	@RequestMapping(value = "/link-info", method = arrayOf(RequestMethod.GET))
	fun linkInfo(model: Model, linkId: Long) : String {
		model.addAttribute("link", cloudDiskService.getLinkById(linkId))
		return "link/link-info"
	}
	
	/**
	 * 删除资源链接
	 */
	@RequestMapping(value = "/links", method = arrayOf(RequestMethod.DELETE))
	@ResponseBody
	fun removeLink(linkId: Long) : Int {
		cloudDiskService.removeLinkById(linkId)
		return HttpStatus.NO_CONTENT.value()
	}
}