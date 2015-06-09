var yearOld;
var deptIdOld;
$.ajaxSetup({scriptCharset: "UTF-8" , contentType: "application/json; charset=UTF-8", cache: false});
// jQuery Ready State
$(document).ready(function() {
	
	 $("#bSubmit").button();
	 $("#bPrint").button(); 
	 $("#bClear").button();
	 $("#bBack").button();
	 $("#bExport").button();
	 
	 $("#bExcel").button();
	 $("#bText").button();
	 $("#bCsv").button();
	 $("#bXml").button();
	 $("#year").setKeyPressFormat({number: true});
	year  =  $("#year").val();
	$("#deptId").val("0205");
	deptIdOld  =  $("#deptId").val();
	function printPreview() {
		var type = 'pdf';
		if ($("#erpssr002Form").validate() != "" ){
	        return false;
	  	}
		$.get("erpssr002/showReport/token", function(response) {
			// Store token
			var token = response.message[0];
		
			// Start download	
			var deptSubId       = $("#deptSubId").val().replace(/^\s+|\s+$/g,"");
			var deptId 		= $("#deptId").val().replace(/^\s+|\s+$/g,"");
          	var year 			= $("#year").val().replace(/^\s+|\s+$/g,"");	
          
		 	var window_dimensions = "directories=0,titlebar=0,toolbar=0,location=0,status=0,menubar=0,scrollbars=no,resizable=yes"  ;
          	var url= 'erpssr002/showReport'+'?token='+token+'&type='+type+'&budgetYear='+year+'&deptId='+deptId+'&deptSubId='+deptSubId ;		
          	var windowReport  = window.open(url,'Report',  window_dimensions);  
          	windowReport.moveTo(0,0);  
            windowReport.resizeTo(screen.width,screen.height); 
			var frequency = 1000;
			var timer = setInterval(function() {
				$.getJSON("erpssr002/showReport/progress", {token: token}, 
						function(response) {
							if (response.message[0] != token) {
								clearInterval(timer);
								 if (window.focus) {windowReport.focus();}
						}
					});
			}, frequency);
			
		});
	}
	$("#bClear").click(function(){
		 $("#year").val(year);
		 $("#deptId").val(deptIdOld);
	});
	$("#bPrint").click(function(){
		printPreview();
	});
	$("#bBack").click(function(){
		// window.parent.closeIframe();
		window.parent.showMenuName("ERPSSI001","โปรแกรม ERPSSI001:สอบถามข้อมูลโครงการ EGP");
		 window.parent.document.getElementById("detail").src = "erpss/erpssi001";
	});
	$("#deptId").change( function() {
	    	var deptId 		= $('#deptId').val();
	    	$.getJSON("erpssr002/getDeptSubByDeptId", {deptId: deptId}, 
			function(json) {		
	    			if ( jQuery.trim(json.msgCode=="FOUND")) {
	    				$('#deptSubId option').remove();
	    				 $('#deptSubId').append($("<option></option>")
				                    .attr("value","")
				                    .text("ระบุสำนักงาน"));
	    				$.each(json.deptSubList,function(columname,deptSub){
							 $('#deptSubId').append($("<option></option>")
					                    .attr("value",deptSub.deptSubId)
					                    .text(deptSub.deptSubNameId));
						 });
	    			}

	    	});
	    }); 
	$("#bExport").click(function() {
		if ($("#erpssr002Form").validate() != "" ){
	        return false;
	  	}
		$("#erpssr002Form").toggleVisible('#exportBox','visible');
		$('#exportBox').dialog( {	title: 'Export to...',
							height: '240',
							width: '240',
							modal: true,
							buttons: {"Close": function()  {
								 $("#erpssr002Form").toggleVisible('#exportBox','hidden');
								$(this).dialog("close");
								} 
							}
	   });
	});
		 $("#bExcel").click(function(){
			 $('#exportBox').dialog("close");
			 $("#erpssr002Form").exportReport('xls');	
		 });
		 $("#bText").click(function(){
			 $('#exportBox').dialog("close");
			 $("#erpssr002Form").exportReport('txt');	
		 });
		 $("#bCsv").click(function(){
			 $('#exportBox').dialog("close");
			 $("#erpssr002Form").exportReport('csv');	
		 });
		 $("#bXml").click(function(){
			 $('#exportBox').dialog("close");
			 $("#erpssr002Form").exportReport('xml');	
		 });
} );
$.fn.toggleVisible = function(objectId,toggle){
	if(toggle=='visible'){
		 $(objectId).show();
	}else{
		  $(objectId).hide();
	}
};
$.fn.validate = function(){
	// Start download						
	var deptId 		= $("#deptId").val().replace(/^\s+|\s+$/g,"");
  	var year 		= $("#year").val().replace(/^\s+|\s+$/g,"");	
  	var message  ="";
  	if (deptId == ""  ){
  		message += "หน่วยงาน" + "<BR>";

  	}
  	if (year == ""  ){
  		message += "ปีงบประมาณ" + "<BR>";

  	}
  	if (message!=""){
  		$("#erpssr002Form").alert(showMsg(E0006)+ "<BR>" + message);
       
  	}
  	return message;
};
$.fn.exportReport = function(exportType){
	// Start download	
	var deptSubId 		= $("#deptSubId").val().replace(/^\s+|\s+$/g,"");
	var deptId 		= $("#deptId").val().replace(/^\s+|\s+$/g,"");
  	var year 		= $("#year").val().replace(/^\s+|\s+$/g,"");	
	$.getJSON("erpssr002/showReport/token", function(response) {
			var token = response.message[0];
			// Show progress dialog
			$('#msgbox').text('Processing download...');
			$('#msgbox').dialog( 
					{	title: 'Download',
						modal: true,
						buttons: {"Close": function()  {
								clearInterval(timer);
								$(this).dialog("close");
							} 
						}
					});
          	var url= 'erpssr002/showReport'+'?token='+token+'&type='+exportType+'&budgetYear='+year+'&deptId='+deptId+'&deptSubId='+deptSubId ;			
          	window.location.assign( url);
          
			var frequency = 1000;
			var timer = setInterval(function() {
				$.getJSON("erpssr002/showReport/progress", {token: token}, 
						function(response) {
							if (response.message[0] != token) {
								$('#msgbox').dialog('close');
								clearInterval(timer);
							}
					});
			}, frequency);
			$("#deptId").show();
		});

};