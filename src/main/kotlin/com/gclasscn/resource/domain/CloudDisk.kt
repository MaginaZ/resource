package com.gclasscn.resource.domain

data class CloudDisk(val id: Long?, val name: String, val link: String, val code: String?, val resInfo: String?) {
	constructor(): this(null, "", "", null, null)
}