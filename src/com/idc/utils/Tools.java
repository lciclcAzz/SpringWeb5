package com.idc.utils;

/**
 * Store tools for develop application
 * 
 * @version 1.00 25 Mar 2002
 * @author lciclcAzz
 */
public class Tools {
	// private static final String SSOVersion =
	// PccProperties.getResourceString("SSOVersion");

	public Tools() {
	}
	public static String chkNull(String value) {
		return (value == null ? "" : value.trim());
	}

	}
