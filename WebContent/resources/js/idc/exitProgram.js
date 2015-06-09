// jQuery Ready State
$(document).ready(function() {
	$.ajaxSetup({scriptCharset: "UTF-8" , contentType: "application/json; charset=UTF-8", cache: false});
	$("#svse001Form").confirm(showMsg(eval("I0025")), 
			{buttons : 
				{
					"ตกลง" : function() 
					{
						
						$(this).dialog("close");
						//$.get("./exitProgram/removeSession");
						//window.parent.location = "/rtnerpss";	
						window.parent.closeWindow();
					
					},
					"ยกเลิก" : function()
					{
						$(this).dialog("close");
						return false;
					}
				}
			});
	
});