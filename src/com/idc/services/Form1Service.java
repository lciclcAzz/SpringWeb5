package com.idc.services;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.idc.dto.GoodsInfo;

public class Form1Service {
	private static final Logger logger = LoggerFactory.getLogger(Form1Service.class);

	@SuppressWarnings("finally")
	public  ArrayList<GoodsInfo> getForm1All(String dateFrom ,String dateTo) {
		Gson gson = new Gson();
		List<GoodsInfo>  form1List = new ArrayList<GoodsInfo>();
		ArrayList<GoodsInfo>  form1al = new ArrayList<GoodsInfo>();
		try{
			for(int i=0;i<2;i++){
				GoodsInfo goodsInfo = new GoodsInfo();
				goodsInfo.setProductCode("70010102"+i);
				goodsInfo.setBrandMainCode("aa"+i);
				form1List.add(goodsInfo);
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
