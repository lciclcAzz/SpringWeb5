var yearOld;
var deptIdOld;
$.ajaxSetup({scriptCharset: "UTF-8" , contentType: "application/json; charset=UTF-8", cache: false});
// jQuery Ready State
$(document).ready(function() {
	
	 $("#bSubmit").button();
	 $("#bPrint").button(); 
	 $("#bClear").button();

//	 $("#year").setKeyPressFormat({number: true});

//	$("#bClear").click(function(){
//		 $("#year").val(year);
//		 $("#deptId").val(deptIdOld);
//	});

//	$("#bBack").click(function(){
//		// window.parent.closeIframe();
//		 window.parent.document.getElementById("detail").src = "erpss/erpssi001";
//	});
//	$("#deptId").change( function() {
//	    	var deptId 		= $('#deptId').val();
//	    	$.getJSON("erpssr002/getDeptSubByDeptId", {deptId: deptId}, 
//			function(json) {		
//	    			if ( jQuery.trim(json.msgCode=="FOUND")) {
//	    				$('#deptSubId option').remove();
//	    				 $('#deptSubId').append($("<option></option>")
//				                    .attr("value","")
//				                    .text(""));
//	    				$.each(json.deptSubList,function(columname,deptSub){
//							 $('#deptSubId').append($("<option></option>")
//					                    .attr("value",deptSub.deptSubId)
//					                    .text(deptSub.deptSubNameId));
//						 });
//	    			}
//
//	    	});
//	    }); 
	$("#btnGetData").click(function(){
		var id = "xxx";
		$.getJSON("form1/getData", {id: id}, 
				function(json) {		
					if ( jQuery.trim(json.msgCode=="FOUND")) {
						$('#prdCode').text(json.form1List[0].productCode);
						$.each(json.form1List,function(idx,val,val2){
							if(idx >= 0){
								$('#prdCode'+idx).text(val.productCode);
							}
						});
					}
		    	});		
	});

} );