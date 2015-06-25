<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%//@ page import="com.pccth.rtn.erpss.dto.SsoDataDTO"%>

	<head>

	<c:set var="contextPath" value="${pageContext.servletContext.contextPath}" scope="page"/>


	
	<link rel="stylesheet" type="text/css"  href='<c:url value="./resources/css/jquery-ui/start/jquery-ui-1.10.1.custom.min.css"/>'/>
	<link rel="stylesheet" type="text/css" href='<c:url value="./resources/css/bootstrap.css"/>'>
	<link rel="stylesheet" type="text/css" href='<c:url value="./resources/css/font-awesome.css"/>'>	
	
	<link rel="stylesheet" type="text/css" href='<c:url value="./resources/xdb/css/AdminLTE.css"/>'>
	<link rel="stylesheet" type="text/css" href='<c:url value="./resources/xdb/css/AdminLTE.min.css"/>'>
	<link rel="stylesheet" type="text/css" href='<c:url value="./resources/xdb/css/skins/skin-blue.css"/>'>	
	<script type='text/javascript' src='<c:url value="./resources/xdb/js/app.js"/>'></script>
	<script type='text/javascript' src='<c:url value="./resources/xdb/js/app.min.js"/>'></script>
	<script type='text/javascript' src='<c:url value="./resources/xdb/js/demo.js"/>'></script>		

	
	<script type='text/javascript' src='<c:url value="./resources/js/bootstrap.js"/>'></script>	
	<script type='text/javascript' src='<c:url value="./resources/js/jquery-1.9.1.js"/>'></script>	 
	<script type='text/javascript' src='<c:url value="./resources/js/jquery.dataTables.js"/>'></script>	
	<script type='text/javascript' src='<c:url value="./resources/js/jquery-ui.js"/>'></script>
	<script type='text/javascript' src='<c:url value="./resources/js/script_menu.js"/>'></script>
	<script type='text/javascript' src='<c:url value="./resources/js/jquery.hotkeys-0.7.9.min.js"/>'></script>
	<script type='text/javascript' src='<c:url value="./resources/js/Tools.js"/>'></script>	
	<script type='text/javascript' src='<c:url value="./resources/js/ErrorWarning.js"/>'></script>
	<script type='text/javascript' src='<c:url value="./resources/js/jquery.ui.datepicker.th.js"/>'></script>
	<script type='text/javascript' src='<c:url value="./resources/js/jquery.dataTables.min.js"/>'></script>
	<script type='text/javascript' src='<c:url value="./resources/js/jquery.dataTables.grouping.js"/>'></script>
	<script type='text/javascript' src='<c:url value="./resources/js/jquery.validate.js"/>'></script>
	<script type='text/javascript' src='<c:url value="./resources/js/jquery.pccth.common.js"/>'></script>

	 </head>
	

<script type="text/javascript">

	$.contextPath = "${contextPath}";

	
	function showMenuNameParent(menuName){
		parent.document.getElementById("programName").innerHTML = menuName;
		parent.document.getElementById('frameDetail').style.display = '';
	}
	
	var container = parent.document.getElementById("container");
 	var progressBar = parent.document.getElementById("progress-bar");

 	$(document).ready(function() {   
 	 
 		if( container == null || typeof container == 'undefined'){
      		progressBar = document.getElementById("progressBarHelp"); 
      				 
      		window.onbeforeunload = function(){
				$('select').css('display', 'none');
			  	$(progressBar).css('display', 'block');
			  	//$(progressBar).bgIframe();  
			     //$(progressBar).spin("large","black"); 
			}	   			 

		}else{
		  	window.onbeforeunload = function(){
		  		$('select').css('display', 'none');
		  		$(container).css('display', 'block');
		  		$(progressBar).css('display', 'block'); 
		  		$(container).bgIframe();
			  	//$(progressBar).bgIframe();  
			}			  
		}
	 
	
		$(window).load(function() {
			$('select').css('display', 'inline');	
			$(progressBar).css('display', 'none');	
		  	$(container).css('display', 'none');							 
		});
   }); 
</script>