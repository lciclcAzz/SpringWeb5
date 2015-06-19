<!DOCTYPE HTML>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html; charset=TIS-620" pageEncoding="TIS-620"%>
<html>
<head>
<meta content="text/html; charset=TIS-620" http-equiv="Content-Type">
<meta content="no-cache" http-equiv="Cache-Control">
<meta content="0" http-equiv="Expires">
<%@ include file="include.jsp" %>
<script type='text/javascript' src='<c:url value="/resources/js/idc/form1.js"/>' ></script>
<script>
	function begin(){		
		var viewportwidth;
 		var viewportheight;
 		// the more standards compliant browsers (mozilla/netscape/opera/IE7) use window.innerWidth and window.innerHeight
 
		 if (typeof window.innerWidth != 'undefined')
		 {
		      viewportwidth = window.innerWidth,
		      viewportheight = window.innerHeight
		 }
 
		// IE6 in standards compliant mode (i.e. with a valid doctype as the first line in the document)
		
		 else if (typeof document.documentElement != 'undefined'
		     && typeof document.documentElement.clientWidth !=
		     'undefined' && document.documentElement.clientWidth != 0)
		 {
		       viewportwidth = document.documentElement.clientWidth,
		       viewportheight = document.documentElement.clientHeight
		 }
 
		 // older versions of IE
		 
		 
		 else
		 {
		       viewportwidth = document.getElementsByTagName('body')[0].clientWidth,
		       viewportheight = document.getElementsByTagName('body')[0].clientHeight
		 }

		var h = viewportheight;
		//h = h - 135;	
		h = h - 134;	
		document.getElementById("detail").height = h;
		
//		showMenuName("ERPSSI001","����� ERPSSI001:�ͺ����������ç��� EGP");
//		document.getElementById("detail").src = "erpss/erpssi001";
	}

function closeWindow() {
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
<style type="text/css">
#preloader  {
     position: absolute;
     top: 0;
     left: 0;
     right: 0;
     bottom: 0;
     background-color:#000;
     opacity:0.4;
     filter: alpha(opacity=40); /* For IE8 and earlier */
     z-index: 99;
    height: 100%;
 }

#status  {
     width: 200px;
     height: 200px;
     position: absolute;
     left: 50%;
     top: 50%;
     background-image: url(./resources/images/loading2.gif);
     background-repeat: no-repeat;
     background-position: center;
     margin: -100px 0 0 -100px;
 }
</style>
<script type="text/javascript">
// makes sure the whole site is loaded
jQuery(window).load(function() {
        // will first fade out the loading animation
	jQuery("#status").fadeOut();
        // will fade out the whole DIV that covers the website.
	jQuery("#preloader").delay(500).fadeOut("slow");
})
</script>
</head>
<body>
	<form:form id="form1" method="GET"	action="${contextPath}/form1"	commandName="form1">
        <div class="container">
           
            <div class ="row">
                <table class="table table-hover table-bordered table-responsive table-striped">
                    <thead>
                            <th>#</th>
                            <th>Name</th>
                            <th>Age</th>
                            <th>Tel.</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr class="success">
                            <td id="prdCode0">..</td>
                            <td>.....</td>
                            <td>.....</td>
                            <td>.....</td>
                        </tr>
                        <tr class="info">
                            <td id="prdCode1">.....</td>
                            <td>.....</td>
                            <td>.....</td>
                            <td>.....</td>
                        </tr>
                        <tr class="active">
                            <td>active</td>
                            <td id="prdCode">.....</td>
                            <td>.....</td>
                            <td>.....</td>
                        </tr>
                        <tr class="warning">
                            <td id="brandMain">.....</td>
                            <td>.....</td>
                            <td>.....</td>
                            <td>.....</td>
                        </tr>                        
                    </tbody>
                </table>            	
            </div>
       </div>
${myModel}
<input type="submit" value="ok" />
<input type="button" value="GetData" name="btnGetData" id="btnGetData" />
									<div class="demo_jui">
<table cellpadding="0" cellspacing="0" border="0" class="display" id="example">
	<thead>
		<tr>
			<th width="8%">�����������</th>
			<th width="8%">��������ش</th>
			<th width="14%">������</th>
		</tr>
	</thead>
	<tbody>
	    <c:if test="${fn:length(form1.form1al) > 0}" >
	    	<c:forEach items="${form1.form1al}" var="form1Load" varStatus="loop">
	    	<tr >
				<td><c:out value="${form1Load.productCode}"/></td>
				<td class="center"><c:out value="${form1Load.brandMainCode}"/></td>
				<td class="left"></td>
			</tr>
	    	</c:forEach>
	    </c:if>

	</tbody>
</table>
	    ${form1.form1List}
			</div>
	<div id="dialog" title="Message" style="display: none">
		<div id="message"></div>
	</div>
	<div class="log" style="display: none"></div>
	<div id='msgbox' title='' style='display:none'></div>
	<div id="preloader">
	  <div id="status">&nbsp;</div>
	</div>
</form:form>
</body>
</html>