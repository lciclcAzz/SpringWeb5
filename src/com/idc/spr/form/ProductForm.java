package com.idc.spr.form;

import java.util.ArrayList;

import com.idc.spr.dto.GoodsInfo;

public class ProductForm {
	private static final long serialVersionUID =1L;
	private String flag=null;
	private String msgCode=null;	
	private ArrayList <GoodsInfo> al =null;

	public ArrayList<GoodsInfo> getAl() {
		return al;
	}
	public void setAl(ArrayList<GoodsInfo> al) {
		this.al = al;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getMsgCode() {
		return msgCode;
	}
	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}	
}
