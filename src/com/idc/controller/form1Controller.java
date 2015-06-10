package com.idc.controller;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.idc.form.form1;
import com.idc.services.Form1Service;
import com.idc.utils.Tools;

@Controller
@RequestMapping("/form1")
public class form1Controller {
	private static final Logger logger = LoggerFactory.getLogger(form1Controller.class);
	
	@Autowired
	private Form1Service form1Service;
	
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("form1") form1 form1 ,Model model){
		form1 RtnForm1 = new form1();
	
		String dateFrom =Tools.chkNull(form1.getDateFrom());
		String dateTo 	=Tools.chkNull(form1.getDateTo()); 
//		RtnForm1.setForm1List(form1Service.getForm1All("01-01-2558", "31-12-2558"));
		RtnForm1.setForm1al(form1Service.getForm1All("01-01-2558", "31-12-2558"));
		
		RtnForm1.setDateFrom(dateFrom);
		RtnForm1.setDateTo(dateTo);
		
		model.addAttribute("form1", RtnForm1);
		
		return "/index";
	}

}
