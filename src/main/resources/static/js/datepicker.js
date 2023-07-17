function FunGetFormatDate(date) {
	    var year = date.getFullYear();                                 //yyyy
	    var month = (1 + date.getMonth());                     //M
	    month = month >= 10 ? month : '0' + month;     // month 두자리로 저장
	    var day = date.getDate();                                        //d
	    day = day >= 10 ? day : '0' + day;                            //day 두자리로 저장
	    return month + '/' + day + '/' + year;
	}
	var defTom = FunGetFormatDate('내일날짜');
	var defMon = FunGetFormatDate('한달뒤날짜');
	$("#dateRangePicker").val(defTom + " - " + defMon);
	$("#dateRangePicker").dateRangePicker({
		'applyClass': 'btn-sm btn-success',
		'cancelClass': 'btn-sm btn-default',
		startDate: from,
		locale: {
		format: 'yyyy-mm-dd',
		applyLabel: 'Apply',
		cancelLabel: 'Cancel'
		},
		endDate: to
		}).prev().on(ace.click_event, function () {
			$(this).next().focus();
	});
	    