package com.magina.resource.domain

import java.util.ArrayList

data class PageList<T>(val record: Int, val size: Int, val data: List<T>) {

	val totalPages: Int
	init {
		totalPages = if(record % size == 0) record / size else record / size + 1
	}

}
