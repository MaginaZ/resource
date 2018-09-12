package com.magina.resource.dao

import com.magina.resource.domain.CloudDisk
import com.magina.resource.domain.RequestParam
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import com.magina.resource.domain.CloudDiskDTO

@Mapper
interface CloudDiskDAO {
	fun listLinks(param: RequestParam) : List<CloudDisk>
	
	fun countLinks(@Param("query") query: String?) : Int
	
	fun getLinkById(@Param("linkId") linkId: Long) : CloudDisk
	
	fun removeLinkById(@Param("linkId") linkId: Long)
	
	fun saveLink(cloudDisk: CloudDiskDTO)
	
	fun updateLink(cloudDisk: CloudDiskDTO)
}