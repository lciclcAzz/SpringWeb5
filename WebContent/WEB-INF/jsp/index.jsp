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
		
//		showMenuName("ERPSSI001","โปรแกรม ERPSSI001:สอบถามข้อมูลโครงการ EGP");
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

</head>
<body>
	<form:form id="form1" method="POST"	action="${contextPath}/form1"	commandName="form1">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <h3>Row 1 :: Col-md-6</h3>
                        </div>
                        <div class="panel-body">
                            <p>...</p>
                        </div>
                    </div>
                </div>
            </div>
            
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
                            <td>..</td>
                            <td>.....</td>
                            <td>.....</td>
                            <td>.....</td>
                        </tr>
                        <tr class="info">
                            <td>.....</td>
                            <td>.....</td>
                            <td>.....</td>
                            <td>.....</td>
                        </tr>
                        <tr class="active">
                            <td>active</td>
                            <td>.....</td>
                            <td>.....</td>
                            <td>.....</td>
                        </tr>
                        <tr class="warning">
                            <td>.....</td>
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
									<div class="demo_jui">
<table cellpadding="0" cellspacing="0" border="0" class="display" id="example">
	<thead>
		<tr>
			<th width="8%">เวลาเริ่มต้น</th>
			<th width="8%">เวลาสิ้นสุด</th>
			<th width="14%">ประเภท</th>

		</tr>
	</thead>
	<tbody>
	    <c:if test="${fn:length(form1.form1al) > 0}" >
	    	<c:forEach items="${form1.form1al}" var="form1Load" varStatus="loop">
	    	<tr >
				<td><c:out value="${form1Load.ProductCode}"/></td>
				<td class="center"></td>
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
</form:form>
</body>
</html>