package com.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springboot.service.CovidService;

@Controller
public class CovidController {

	@Autowired
	CovidService service;

	@RequestMapping("/")
	public String show(Model model) {
		model.addAttribute("allData", service.allDataList);
		model.addAttribute("totalTodayCount", service.totalTodayCount);
		model.addAttribute("totalNewCount", service.totalNewCount);
		return "home";
	}

}
