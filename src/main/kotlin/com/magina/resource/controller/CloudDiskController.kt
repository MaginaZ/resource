package com.magina.resource.controller

import com.magina.resource.domain.CloudDisk
import com.magina.resource.domain.CloudDiskDTO
import com.magina.resource.domain.PageList
import com.magina.resource.domain.RequestParam
import com.magina.resource.service.CloudDiskService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class CloudDiskController {
	
	@Autowired
	private val cloudDiskService: CloudDiskService = CloudDiskService()
	
	/**
	 * 资源链接列表
	 */
	@GetMapping("/")
	fun linkLiskPage() = "link/link-list"
	
	@GetMapping("/links")
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
	@GetMapping("/link-add")
	fun linkAddPage() = "link/link-add"
	
	@PostMapping("/links")
	@ResponseBody
	fun saveLink(name: String, link: String, code: String?, resInfo: String?) : Int {
		cloudDiskService.saveLink(CloudDiskDTO(name, link, code, resInfo))
		return HttpStatus.CREATED.value()
	}
	
	/**
	 * 修改资源链接
	 */
	@GetMapping("/link-update")
	fun linkUpdatePage(model: Model, linkId: Long) : String {
		model.addAttribute("link", cloudDiskService.getLinkById(linkId))
		return "link/link-update"
	}
	@PutMapping("/links")
	@ResponseBody
	fun updateLink(id: Long, name: String, link: String, code: String?, resInfo: String?) : Int {
		cloudDiskService.updateLink(CloudDiskDTO(id, name, link, code, resInfo))
		return HttpStatus.CREATED.value()
	}
	
	/**
	 * 资源链接详情
	 */
	@GetMapping("/link-info")
	fun linkInfo(model: Model, linkId: Long) : String {
		model.addAttribute("link", cloudDiskService.getLinkById(linkId))
		return "link/link-info"
	}
	
	/**
	 * 删除资源链接
	 */
	@DeleteMapping("/links")
	@ResponseBody
	fun removeLink(linkId: Long) : Int {
		cloudDiskService.removeLinkById(linkId)
		return HttpStatus.NO_CONTENT.value()
	}
}