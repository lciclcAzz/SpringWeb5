package com.idc.spr.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.google.gson.Gson;
import com.idc.spr.dto.GoodsInfo;
import com.idc.spr.form.ProductForm;
import com.idc.spr.form.form1;
import com.idc.spr.services.Form1Service;
import com.idc.spr.services.ProductService;
import com.idc.utils.Tools;

public class ProductController {
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
    @RequestMapping(method = RequestMethod.GET)
   public String doGet(HttpSession session, Model model) {
    	logger.info("form1Controller : doGet");
		ProductForm RtnForm1 = new ProductForm();
		ArrayList<ProductForm> al = new ArrayList<ProductForm>();
		String dateFrom =Tools.chkNull("");
		String dateTo 	=Tools.chkNull(""); 
		al =(ArrayList) productService.getForm1All();
		RtnForm1.setAl(al);

		model.addAttribute("productForm", RtnForm1);
	   return "spr/product";
   }
    
	@RequestMapping(method = RequestMethod.POST)
	public String processSubmit(@ModelAttribute("productForm") ProductForm productForm ,
			BindingResult result,SessionStatus status,HttpSession session, Model model)throws Exception{
    	logger.info("productForm : doPOST");
		ProductForm RtnForm1 = new ProductForm();
		ArrayList<ProductForm> al = new ArrayList<ProductForm>();
		al =(ArrayList) productService.getForm1All();
		RtnForm1.setAl(al);

		model.addAttribute("productForm", RtnForm1);
	   return "spr/product";
	}
//	@RequestMapping(value="getData",  method=RequestMethod.GET, produces={"application/json; charset=TIS-620"})
//	public @ResponseBody String getData(@RequestParam  String id) {
//		form1 rtnForm1 = new form1();
//		ArrayList<GoodsInfo> al = form1Service.getForm1All();
//		if(al.size()>0 && al != null){
//			rtnForm1.setMsgCode("FOUND");
//			rtnForm1.setForm1List(al);
//		}else{
//			rtnForm1.setMsgCode("NOT FOUND");
//		}
//		Gson gson = new Gson();			    	
//		return gson.toJson(rtnForm1);
//	}
}
