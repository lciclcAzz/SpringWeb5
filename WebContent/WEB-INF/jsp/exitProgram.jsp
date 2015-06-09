<%@ page language="java" contentType="text/html; charset=TIS-620"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<% System.out.println("===exitProgram===");%>
<meta http-equiv="Content-Type" content="text/html; charset=TIS-620">
<%@ include file="include.jsp" %>


<script type="text/javascript">
$.ajaxSetup({scriptCharset: "UTF-8" , contentType: "application/json; charset=UTF-8", cache: false});
$(document).ready(function(){

});
	function closeWindow2() {
var nAgt = navigator.userAgent;
	var browserName  = navigator.appName;
	
	// In Opera, the true version is after "Opera" or after "Version"
	if ((verOffset=nAgt.indexOf("Opera"))!=-1) {
	 browserName = "Opera";
	// In MSIE, the true version is after "MSIE" in userAgent
	}else if ((verOffset=nAgt.indexOf("MSIE"))!=-1) {
	 browserName = "Microsoft Internet Explorer";
	}
	// In Chrome, the true version is after "Chrome" 
	else if ((verOffset=nAgt.indexOf("Chrome"))!=-1) {
	 browserName = "Chrome";
	
	// In Safari, the true version is after "Safari" or after "Version" 
	}else if ((verOffset=nAgt.indexOf("Safari"))!=-1) {
	 browserName = "Safari";
	// In Firefox, the true version is after "Firefox" 
	}else if ((verOffset=nAgt.indexOf("Firefox"))!=-1) {
	 browserName = "Firefox";
	
	// In most other browsers, "name/version" is at the end of userAgent 
	}else if ( (nameOffset=nAgt.lastIndexOf(' ')+1) < (verOffset=nAgt.lastIndexOf('/')) ) 
	{
	 browserName = nAgt.substring(nameOffset,verOffset);
	 if (browserName.toLowerCase()==browserName.toUpperCase()) {
	  browserName = navigator.appName;
	 }
	}
	//==================================================
	if (browserName == "Microsoft Internet Explorer"){
		window.opener='X';
		window.open('','_parent','');
		window.close();
    } else if (browserName == "Firefox"){
        netscape.security.PrivilegeManager.enablePrivilege("UniversalBrowserWrite");
	    window.close();
    } else if (browserName == "Chrome"){
        window.open('', '_self', '');
        window.close();

    }
}


</script>

<title>ERROR PAGE</title>
 <script type='text/javascript' src='<c:url value="/resources/js/erpss/exitProgram.js"/>'></script>	 
</head>
<body>


<form:form  action="${contextPath}/exitProgram"  commandName="exitForm" method="Post" id="exitForm">
<form:hidden path="flag" id="flag"/>
<form:hidden path="msgCode" id="msgCode"/>

</form:form>
</body>
</html>