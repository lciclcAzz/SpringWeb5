$.ajaxSetup({scriptCharset: "UTF-8" , contentType: "application/json; charset=UTF-8", cache: false});
// jQuery Ready State
$(document).ready(function() {
	
	 $("#bSubmit").button();
	 $("#bPrint").button(); 
	 $("#bClear").button();
 
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