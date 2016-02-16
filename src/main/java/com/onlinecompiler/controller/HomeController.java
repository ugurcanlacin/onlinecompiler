package com.onlinecompiler.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping("/")
	public String getHomePage(Model model){
		model.addAttribute("name","Ugurcan");
		return "index";
	}
}
