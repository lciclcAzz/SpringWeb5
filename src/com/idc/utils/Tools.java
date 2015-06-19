package com.idc.utils;

/**
 * Store tools for develop application
 * 
 * @version 1.00 25 Mar 2002
 * @author lciclcAzz
 */
public class Tools {
	// private static final String appVersion = getResourceString("appVersion");

	public Tools() {
	}
	public static String chkNull(String value) {
		return (value == null ? "" : value.trim());
	}
    public static String dateTimeToDb(String dateTime) {
        if (dateTime != null && dateTime.compareTo("") != 0) {
            if (dateTime.startsWith("'") || dateTime.startsWith("\"")) {
                dateTime = dateTime.substring(1);
                if (dateTime.endsWith("'") || dateTime.endsWith("\"")) {
                    dateTime = dateTime.substring(0, dateTime.length() - 1);
                }
            }
            if (dateTime.length() == 14) {
                dateTime =
                    dateTime.substring(0, 4) + "-" + dateTime.substring(4, 6) +
                    "-" + dateTime.substring(6, 8) + " " +
                    dateTime.substring(8, 10) + ":" +
                    dateTime.substring(10, 12) + ":" + dateTime.substring(12);
            }
            if (dateTime.indexOf("/") != -1) {
                dateTime =
                    (Integer.parseInt(dateTime.substring(6, 10)) - 543) + "-" +
                    dateTime.substring(3, 5) + "-" + dateTime.substring(0, 2) +
                    dateTime.substring(10);
            }
            return "TO_DATE('" + dateTime.substring(0, 19) +
            "','yyyy-mm-dd hh24:mi:ss')";
        } else {
            return "Null";
        }
    }
    public static String chkNullToZero(String value) {
        return ((value == null || value.trim().compareTo("") == 0 ||
                 value.trim().compareToIgnoreCase("null") == 0) ? "0" :
                (value.trim()));
    }
    public static String dateToDb(String date) {
        if (date != null && date.compareTo("") != 0) {
            if (date.startsWith("'") || date.startsWith("\"")) {
                date = date.substring(1);
                if (date.endsWith("'") || date.endsWith("\"")) {
                    date = date.substring(0, date.length() - 1);
                }
            }
            if (date.length() == 8)
                date =
                    date.substring(0, 4) + "-" + date.substring(4, 6) + "-" +
                    date.substring(6);
            else if (date.length() > 10)
                return dateTimeToDb(date);
            else if (date.indexOf("/") != -1) {
                date =
                    (Integer.parseInt(date.substring(6)) - 543) + "-" + date.substring(3,
                                                                                          5) +
                    "-" + date.substring(0, 2);
            }
            return "TO_DATE('" + date + "','yyyy-mm-dd')";
        } else {
            return "Null";
        }
    }    
}
