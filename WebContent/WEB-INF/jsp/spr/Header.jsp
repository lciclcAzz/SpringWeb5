<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<% 
	
%>

<%@page language="java" contentType="text/html; charset=TIS-620" pageEncoding="TIS-620"%>
<html>
<head>
<title>Menu</title>
<meta http-equiv="Content-Type" content="text/html; charset=TIS-620">
<script type="text/javascript">
    //setInterval( "refreshFde();", 10000 );
var intval;
$(function() {
refreshFde = function(){

	    var programIdTmp=$("#programIdTmp").val();
	    var arrMosFde;
		arrMosFde="<%//=FDE_MOS%>";
		if (programIdTmp != ""){
	    var mosFde =  new Array(); 
		mosFde = arrMosFde.split("|");
		 for(var i = 0;i < mosFde.length;i++){
		    if (programIdTmp==mosFde[i]){
		    aJaxGetFde();
		    break;
		    }
		}
		}
};
aJaxGetFde = function(){
//alert("aJaxGetFde");
	   // $.getJSON("home/getMosProgramName", {tmp: "==WHTSVS Stand By=="},
		//		function(json) {	
					//alert(json.programId);	
					//showMenuName(json.programId,json.programName);								
		//		}
		//	);
};
 });

</script>
<style type="text/css">
.auto-style1 {
	margin-bottom: 0px;
}
</style>
</head>
<body>

</body>
<script>
function showMenuName(programId,menuName){
	document.getElementById("programName").innerHTML = menuName;
	document.getElementById('frameDetail').style.display = '';
	$("#programIdTmp").val(programId);
	arrMosFde="<%//=FDE_MOS%>";
		if (programId != ""){
	    var mosFde =  new Array(); 
	    
	    var mos="";
		mosFde = arrMosFde.split("|");
		 for(var i = 0;i < mosFde.length;i++){
		    if (programId==mosFde[i]){
		    intval=setInterval( "aJaxGetFde();", 30000 );
		    mos="T";
		    break;
		    }
		}
			if (mos == "") {
			  clearInterval(intval);
			}
		}
}

// แก้ เมนู dorpdown menu
var index = 1000000;
var i=0;
$("li").each(function(){
	$("li").eq(i).css("z-index",index);
 index=index-50;
 i++;
});

</script>
</html>