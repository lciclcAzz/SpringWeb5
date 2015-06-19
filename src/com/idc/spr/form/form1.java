package com.idc.spr.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.idc.spr.dto.GoodsInfo;

public class form1 implements Serializable{
	private static final long serialVersionUID =1L;
	private String flag=null;
	private String msgCode=null;	
	private String dateFrom=null;
	private String dateTo=null;
	private List<GoodsInfo> form1List=null;
	private ArrayList <GoodsInfo> form1al =null;
	
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
	public String getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}
	public String getDateTo() {
		return dateTo;
	}
	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}
	public List<GoodsInfo> getForm1List() {
		return form1List;
	}
	public void setForm1List(List<GoodsInfo> form1List) {
		this.form1List = form1List;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public ArrayList<GoodsInfo> getForm1al() {
		return form1al;
	}
	public void setForm1al(ArrayList<GoodsInfo> form1al) {
		this.form1al = form1al;
	}

}
