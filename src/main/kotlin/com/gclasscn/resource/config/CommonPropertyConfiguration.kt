package com.gclasscn.resource.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties
data class CommonPropertyConfiguration(var layout: String = "layout", var fullLayout: String = "fullLayout")