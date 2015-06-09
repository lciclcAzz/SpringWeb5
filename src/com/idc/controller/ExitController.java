package com.idc.controller;

import javax.servlet.http.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;


import com.idc.form.ExitForm;
import com.idc.utils.Tools;

@Controller
@RequestMapping(value="exitProgram")
public class ExitController {	
	
	@RequestMapping(method=RequestMethod.GET)
	public String showForm (HttpSession session, HttpServletRequest request, Model model) {
		System.out.println("SHOW ExitController GET");
		String returnCode =Tools.chkNull(request.getParameter("returnCode"));
		System.out.println("SHOW ExitController returnCode=="+returnCode);
		ExitForm exitForm = new ExitForm();
		exitForm.setMsgCode(returnCode);
		model.addAttribute("exitForm", exitForm);
		return "exitProgram";
	}
	
	@RequestMapping(value = "/removeSession", method = RequestMethod.GET) 
    public @ResponseBody void captcha(HttpSession session) {
		if(session!=null){
			session.removeAttribute("SsoDataDTO");
		}
    }
	
	@RequestMapping(method=RequestMethod.POST)
	public String processSubmit (
		@ModelAttribute("exitForm") ExitForm exitForm,
		BindingResult result,
		SessionStatus status,
		HttpSession session
	) {
		System.out.println("SHOW ExitController POST");
		
		//homeForm.setFlag("login");
		return "exitProgram";
	}
	
}
