package com.onlinecompiler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.onlinecompiler.service.CompileRequirements;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String getHomePage(Model model){
		model.addAttribute("compileRequirements",new CompileRequirements());
		return "index";
	}
	
}
