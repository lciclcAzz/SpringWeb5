package com.idc.spr.services;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.idc.spr.dto.GoodsInfo;

public class Form1Service {
	private static final Logger logger = LoggerFactory.getLogger(Form1Service.class);

	@SuppressWarnings("finally")
	public  ArrayList<GoodsInfo> getForm1All() {
		Gson gson = new Gson();
		ArrayList<GoodsInfo>  form1al = new ArrayList<GoodsInfo>();
		try{
			for(int i=0;i<2;i++){
				GoodsInfo goodsInfo = new GoodsInfo();
				goodsInfo.setProductId("70010102"+i);
				goodsInfo.setProductName("aa"+1);
				form1al.add(goodsInfo);
			}
				//logger.info(gson.toJson(historyLoadList));
		} catch (Exception e) {		
			e.printStackTrace();
		} finally{			
	         try{

	         }catch(Exception e){	                 
	         }
//	         return form1List;
	         return form1al;
		}		
		
	}
}
