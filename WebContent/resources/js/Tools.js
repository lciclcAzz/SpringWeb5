
function changeFormatDate( data,  from,  to, convYear) {
		var tmp = to;
		var i1 = 0;
		var y = 0;

		if ((data != null) && (data.length >= from.length)) {

			i1 = from.indexOf("dd");
			if (i1 > -1) {
				tmp = tmp.replace("dd", data.substring(i1, i1 + 2));
			}
			i1 = from.indexOf("mm");
			if (i1 > -1) {
				tmp = tmp.replace("mm", data.substring(i1, i1 + 2));
			}
			if (from.indexOf("yyyy") > -1) {
				i1 = from.indexOf("yyyy");
				if (convYear=="TE") {
					y = 543;
				} else if (convYear=="ET") {
					y = -543;
				}
				if (to.indexOf("yyyy") > -1) {
					if (i1 > -1) {
						tmp = tmp.replace("yyyy", (parseInt(data.substring(i1, i1 + 4),10) - y)+ "");
					}
				} else if (to.indexOf("yy") > -1) {
					if (i1 > -1) {
						tmp = tmp.replace("yy", ((parseInt(data.substring(i1, i1 + 4),10) - y) + "").substring(2));
					}
				}
			} else if (from.indexOf("yy") > -1) {
				i1 = from.indexOf("yy");
				if (convYear=="TE") {
					y = -1957;
				} else if (convYear=="ET") {
					y = -2543;
				} else if (convYear=="T" || convYear=="TT") {
					y = -2500;
				} else if (convYear=="E" || convYear=="EE") {
					y = -2000;
				} else {
					if (to.indexOf("yyyy") > -1) {
						if ((parseInt(data.substring(i1, i1 + 2),10) - y) < 40) {
							y = -2000;
						} else {
							y = -2500;
						}
					}
				}

				if (to.indexOf("yyyy") > -1) {
					if (i1 > -1) {
						tmp = tmp.replace("yyyy", (parseInt(data.substring(i1, i1 + 2),10) - y)+ "");
					}
				} else if (to.indexOf("yy") > -1) {
					if (i1 > -1) {
						if (convYear=="") {
							tmp = tmp.replace("yy", data.substring(i1,i1 + 2));
						} else {
							tmp = tmp.replace("yy",
									((parseInt(data.substring(i1,i1 + 2),10) - y) + "").substring(2));
						}
					}
				}
			}

			i1 = from.indexOf("hh");
			if (i1 > -1) {
				tmp = tmp.replace("hh", data.substring(i1, i1 + 2));
			}
			i1 = from.indexOf("mi");
			if (i1 > -1) {
				tmp = tmp.replace("mi", data.substring(i1, i1 + 2));
			}
			i1 = from.indexOf("ss");
			if (i1 > -1) {
				tmp = tmp.replace("ss", data.substring(i1, i1 + 2));
			}

			if (from.indexOf("fff") > -1) {
				i1 = from.indexOf("fff");
				if (to.indexOf("fff") > -1) {
					tmp = tmp.replace("fff", data.substring(i1, i1 + 3));
				} else if (to.indexOf("ff") > -1) {
					tmp = tmp.replace("ff", data.substring(i1, i1 + 2));
				}
			} else if (from.indexOf("ff") > -1) {
				i1 = from.indexOf("ff");
				if (to.indexOf("fff") > -1) {
					tmp = tmp.replace("fff", data.substring(i1, i1 + 2)
							+ "0");
				} else if (to.indexOf("ff") > -1) {
					tmp = tmp.replace("ff", data.substring(i1, i1 + 2));
				}
			}
		} else {
			tmp = "";
		}

		return tmp;
	}

	$.extend( $.fn.dataTable.defaults.oLanguage, {		 
		"sProcessing": "กำลังประมวลผล...",
		"sLengthMenu": "แสดง _MENU_ รายการ",
		"sZeroRecords": "ไม่พบข้อมูล",
		"sEmptyTable": "ไม่มีข้อมูลในตาราง",
		"sLoadingRecords": "Loading...",
		"sInfo": "แสดง _START_ ถึง _END_ จาก _TOTAL_ รายการ",
		"sInfoEmpty": "แสดง 0 ถึง 0 จาก 0 รายการ",
		"sInfoFiltered": "(กรองข้อมูลจากทั้งหมด _MAX_ รายการ)",
		"sInfoPostFix": "",
		"sSearch": "ค้นหา:",
		"sUrl": "",
		"oPaginate": {
			"sFirst":    "หน้าแรก",
			"sPrevious": "ก่อน",
			"sNext":     "ถัดไป",
			"sLast":     "สุดท้าย"
		},
		"fnInfoCallback": null
	});