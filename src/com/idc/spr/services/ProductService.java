package com.idc.spr.services;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.idc.spr.ProductBean;
import com.idc.spr.dto.GoodsInfo;
import com.idc.spr.form.ProductForm;
import com.idc.utils.conDB;

public class ProductService {
	private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

	@SuppressWarnings("finally")
	public  ArrayList<GoodsInfo> getShow() {
		ArrayList<GoodsInfo>  al = new ArrayList<GoodsInfo>();
		ProductBean productBean = new ProductBean();
		try{
				Connection con = null;
				con = conDB.getConnection();
				GoodsInfo goodsInfo = new GoodsInfo();
				productBean.selectWhere("");
				for(int at=0;at<productBean.getSize();at++){
					goodsInfo.setBrandProductId(productBean.getProductIdAt(at));
					goodsInfo.setDeleteFlag(productBean.getDeleteFlagAt(at));
					goodsInfo.setPriceCost(productBean.getPriceCostAt(at));
					goodsInfo.setPriceSale(productBean.getPriceSaleAt(at));
					goodsInfo.setProductDateExpire(productBean.getProductDateExpireAt(at));
					goodsInfo.setProductDateStart(productBean.getProductDateStartAt(at));
					goodsInfo.setProductDesc(productBean.getProductDescAt(at));
					goodsInfo.setProductId(productBean.getProductIdAt(at));
					goodsInfo.setProductItem(productBean.getProductItemAt(at));
					goodsInfo.setProductName(productBean.getProductNameAt(at));
					goodsInfo.setTypeProductId(productBean.getTypeProductIdAt(at)+"");
					al.add(goodsInfo);					
				}
				

		} catch (Exception e) {		
			e.printStackTrace();
		} finally{			
	         try{

	         }catch(Exception e){	                 
	         }
	         return al;
		}		
		
	}
}
