package com.idc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/MainUI")
public class MainMenu {
	@RequestMapping
	public ModelAndView mainUI(){
		return new ModelAndView("index","myModel","Knock Java SpringFrameWork");
	}
}
