package com.idc.controller;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.google.gson.Gson;
import com.idc.dto.GoodsInfo;
import com.idc.form.form1;
import com.idc.services.Form1Service;
import com.idc.utils.Tools;

@Controller
@RequestMapping("/form1")
public class form1Controller {
	private static final Logger logger = LoggerFactory.getLogger(form1Controller.class);
	
	@Autowired
	private Form1Service form1Service;
	
    @RequestMapping(method = RequestMethod.GET)
   public String doGet(HttpSession session, Model model) {
    	logger.info("form1Controller : doGet");
		form1 RtnForm1 = new form1();
		ArrayList<GoodsInfo> al = new ArrayList<GoodsInfo>();
		String dateFrom =Tools.chkNull("");
		String dateTo 	=Tools.chkNull(""); 
//		RtnForm1.setForm1List(form1Service.getForm1All("01-01-2558", "31-12-2558"));
		al =(ArrayList) form1Service.getForm1All();
		RtnForm1.setForm1al(al);
		
		RtnForm1.setDateFrom(dateFrom);
		RtnForm1.setDateTo(dateTo);
//		model.addAttribute("form1", RtnForm1);
		model.addAttribute("form1", RtnForm1);
	   return "/index";
   }
    
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("form1") form1 form1 ,
			BindingResult result,SessionStatus status,HttpSession session)throws Exception{
		form1 rtnForm1 = new form1();
		ArrayList<GoodsInfo> al = new ArrayList<GoodsInfo>();
		String dateFrom =Tools.chkNull(form1.getDateFrom());
		String dateTo 	=Tools.chkNull(form1.getDateTo()); 
//		RtnForm1.setForm1List(form1Service.getForm1All("01-01-2558", "31-12-2558"));
		al = form1Service.getForm1All();
		rtnForm1.setForm1al(al);
		rtnForm1.setDateFrom(dateFrom);
		rtnForm1.setDateTo(dateTo);
//		model.addAttribute("form1", RtnForm1);
		
		return "/index";
	}
	@RequestMapping(value="getData",  method=RequestMethod.GET, produces={"application/json; charset=TIS-620"})
	public @ResponseBody String getData(@RequestParam  String id) {
		form1 rtnForm1 = new form1();
		ArrayList<GoodsInfo> al = form1Service.getForm1All();
		if(al.size()>0 && al != null){
			rtnForm1.setMsgCode("FOUND");
			rtnForm1.setForm1List(al);
		}else{
			rtnForm1.setMsgCode("NOT FOUND");
		}
		Gson gson = new Gson();			    	
		return gson.toJson(rtnForm1);
	}

}
