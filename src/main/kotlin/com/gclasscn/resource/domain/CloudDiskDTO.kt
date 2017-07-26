package com.gclasscn.resource.domain

data class CloudDiskDTO(val id: Long?, val name: String, val link: String, val code: String?, val resInfo: String?){
	constructor(name: String, link: String, code: String?, resInfo: String?) : this(null, name, link, code, resInfo)
}