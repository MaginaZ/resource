package com.magina.resource.config

import java.util.Locale

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.servlet.View
import org.thymeleaf.spring4.view.AbstractThymeleafView
import org.thymeleaf.spring4.view.ThymeleafViewResolver

open class CustomThymeleafViewResolver() : ThymeleafViewResolver() {

	@Autowired
	private val config: CommonPropertyConfiguration? = null
	
	override protected fun loadView(rawViewName: String, locale: Locale) : View {
		if(config == null){
			return super.loadView(rawViewName, locale)
		}
		var viewName = if (rawViewName.startsWith(config.layout)) rawViewName else config.fullLayout
		var view = super.loadView(viewName, locale)
		if(view is AbstractThymeleafView && !rawViewName.startsWith(config.layout)){
			view.addStaticVariable("templateName", rawViewName)
		}
		return view
	}

}
