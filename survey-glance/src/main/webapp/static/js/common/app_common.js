/**
 * File Name : pcps_Common.js
 *
 * Modification Log
 * --------------------------------------------------------------------------------------------------------------------
 * Sl No	Date			Author			Description Of Change
 * --------------------------------------------------------------------------------------------------------------------
 * 1		11 May 2012		Infosys			Initial Version created
 * --------------------------------------------------------------------------------------------------------------------
 */
//Common Declarations
var validDealerCodes = {'1': '0123', '2': '9876', '3': '4353', '4': '8585'};
var validSeries = {'1': 'Camry', '2': 'Corolla', '3': 'Tundra'};
var validInlineTls = {'I': 'I', 'T': 'T'};

var edit_b;
var del_b;
var save_b;
var cancel_b;
var todayDate = new Date();
var day = todayDate.getDate();
var month = todayDate.getMonth() + 1;
var year = todayDate.getFullYear();
var gridHeight = 500;
var phaseGridHeight = 382;
var rowNum = 3000;
var dateFormat =  /^(0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])[\/\-][1-9]\d\d\d$/;
var sysEndTsDefault = '12/31/9999';
var BASEAPPURL = '/survey-glance/';

function appendUrl(url){
	return BASEAPPURL + url;
}

/** Function to convert cell value into italic style
 * @param cellval
 * @param opts.
 * @param rwdat
 * @return
 */
function italicFormatter(cellval, opts, rwdat){
	if(null != cellval)
		return "<i>"+cellval+"</i>";
	else
		return "&nbsp;";
}

function currencyFormatter(cellval, opts, rwdat) {
	var num = cellval;
	if(!num){
		return '-';
	}
	num = num.toString().replace(/\$|\,/g,'');
	if(isNaN(num)){
		num = "0";
	}
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num*100+0.50000000001);
	cents = num%100;
	num = Math.floor(num/100).toString();
	if(cents<10){
		cents = "0" + cents;
	}
	for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++) {
		num = num.substring(0,num.length-(4*i+3))+','+ num.substring(num.length-(4*i+3));
	}
	val = (num + '.' + cents);
	if(!sign){
		val = '<font style="color:red">('+val+')</font>';
	}
	return val;
}

/**
 * Function to convert cell value into mm/yy format
 *
 * @param cellval
 *
 * @param opts
 *
 * @param rwdat
 *
 * @return
 *
 */
function dateformatter(cellval,opts,rwdata){
	if(cellval==null || cellval=='1999-12-31'){
		return '&nbsp';
	}
	var date = new Date(cellval);
	opts = $.extend({}, $.jgrid.formatter.date, opts);
 	return $.fmatter.util.DateFormat("", date, 'm/d/Y', opts);
}

/** Dummy Function which gets called when 'Enter' Key is pressed
* @param id
* @return
*/
function dummyCall(id){

}

/*
 * Converting list of json objects to StringArray.
 * */
function convertJsonToQueryString(jsonObj,toListName){
	var queryString ="";
	$.each(jsonObj, function(index,item) {
		  // here you can extract the data
		  for(key in item){
			  queryString  +=  toListName+'%5B'+index+'%5D.'+key+'='+item[key]+'&';
		  }
	});
	//queryString = queryString.slice(0,queryString.length - 1);
	return queryString;
}

/*
 * Converting a json object to String.
 * */
function convertJsonObjToQueryString(jsonObj,toObjName){
	var queryString ="";
		  for(key in jsonObj){
			  queryString  +=  toObjName+'.'+key+'='+jsonObj[key]+'&';
		  }
	//queryString = queryString.slice(0,queryString.length - 1);
	return queryString;
}

/*
 * Converting a array object to String.
 * */
function convertArrayToQueryString(arrObj,toListName){
	var queryString ="";
	$.each(arrObj, function(index,item) {
		  queryString  +=  toListName+'%5B'+index+'%5D'+'='+item+'&';
	});
	//queryString = queryString.slice(0,queryString.length - 1);
	return queryString;
}

/** Common Fn to add buttons at row level
 * @param id
 * @return
 */
function addButtons(id){
	//Dummy button is used to catch 'Enter' Key press
    dummyBtn = "<input onclick='dummyCall(this.id)' style='height:0px;width:0px;' id='{0}' type='image' src='static/images/jqgrid/copy.png' title=''/>";
	edit_b = "<input style='height:13px;width:13px;' id='edit_b_"+id+"' type='image' src='static/images/jqgrid/edit.png' title='Edit' onclick=\"editRow('"+id+"');\"  />";
	del_b = "<input style='height:13px;width:13px;' id='del_b_"+id+"' type='image' src='static/images/jqgrid/delete.png' title='Delete' onclick=\"deleteRow('"+id+"');\"  />";
	save_b = "<input style='height:13px;width:13px;display:none;' id='save_b_"+id+"' type='image' src='static/images/jqgrid/save.png' title='Save' onclick=\"saveRow('"+id+"');\"  />";
	cancel_b = "<input style='height:13px;width:13px;display:none;' id='cancel_b_"+id+"' type='image' src='static/images/jqgrid/cancel.png' title='Cancel' onclick=\"cancelRow('"+id+"');\" />";
    deac_b = "<input style='height:13px;width:13px;' id='deac_b_"+id+"' type='image' src='static/images/jqgrid/delete.png' title='Deactivate' onclick=\"deactivateRow('"+id+"');\"  />";
}

/** Function to toggle buttons based on condition in row
 * @param id
 * @param b_name
 * @return
 */
function changeButtonState(id, b_name) {
	if(b_name && (b_name === 'edit' || b_name === 'copy')){
		$('#edit_b_'+id).hide();
		$('#copy_b_'+id).hide();
		$('#del_b_'+id).hide();
		$('#save_b_'+id).show();
		$('#cancel_b_'+id).show();
	} else if(b_name && (b_name === 'save' || b_name === 'cancel')){
		$('#edit_b_'+id).show();
		$('#copy_b_'+id).show();
		$('#del_b_'+id).show();
		$('#save_b_'+id).hide();
		$('#cancel_b_'+id).hide();
	}
};

/**
 * @param indRowData
 * @param dateStr
 * @return
 */
function getCurrentDateTime(){

	var todayDate=new Date();
	var date=todayDate.getDate();
	var month=todayDate.getMonth()+1;
	var year=todayDate.getFullYear();
	var hours=todayDate.getHours();
	var minutes=todayDate.getMinutes();
	var seconds=todayDate.getSeconds();
	var amOrPm;

	if(minutes<10){
		minutes ="0"+minutes;
	}
	if(month<10){
		month ="0"+month;
	}

	if(hours > 11){
        amOrPm = "PM";
	} else {
        amOrPm = "AM";
	}
	hours = hours % 12;
	hours = hours ? hours : 12;

	var savedDate=month+"/"+date+"/"+year+" "+hours+":"+minutes+" "+amOrPm;

	return savedDate;
}

/**
 * @param indRowData
 * @param dateStr
 * @return
 */
function getCurrentDate(){
	var todayDate=new Date();
	var date=todayDate.getDate();
	var month=todayDate.getMonth()+1;
	var year=todayDate.getFullYear();
	if(date<10){
		date ="0"+date;
	}
	if(month<10){
		month ="0"+month;
	}
	var savedDate=month+"/"+date+"/"+year;
	return savedDate;
}

/**
 * @param indRowData
 * @param dateStr
 * @return
 */
function getNextDate(dt,mnth,yr) {
	var todayDate;
	var date;
	var month;
	var year;
	var nextDay;
	if(dt == 0 || mnth == 0 || yr == 0){
		todayDate = new Date();
		 date = todayDate.getDate();
		 month = todayDate.getMonth() + 1;
		 year = todayDate.getFullYear();
	}else{
		date = parseInt(dt,10);
		 month = parseInt(mnth,10);
		 year = parseInt(yr,10);
	}
	 nextDay=date + 1;
	if (date == 31) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
				|| month == 10 || month == 12) {
			nextDay = 1;
			month = month + 1;
		}
	} else if (date == 30) {
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			nextDay = 1;
			month = month + 1;
		}
	} else if(date == 29){
		if (month == 2){
			nextDay = 1;
			month = month + 1;
		}
	} else if(date == 28){
		if (month == 2){
			if((((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) == false){
			nextDay = 1;
			month = month + 1;
			}
		}
	}
	month = (parseInt(month)<10)?"0"+month:month;
	nextDay = (parseInt(nextDay)<10)?"0"+nextDay:nextDay;
	var savedDate = month + "/" + nextDay + "/" + year;
	return savedDate;
}

function getNextDateForEmpty(arrExistDates){
	var dateValue = getNextDate(0, 0, 0);
	if((jQuery.inArray(dateValue, arrExistDates)) > -1){
		var latestDate = arrExistDates.sort().pop();
		var month = latestDate.substring(0, 2);
		var day = latestDate.substring(3, 5);
		var year = latestDate.substring(6, 10);
		dateValue = getNextDate(day, month, year);
	}
	return dateValue;
}

function getNextDateForDateArray(arrExistDates){
	var latestDate = arrExistDates.sort().pop();
	var month = latestDate.substring(0, 2);
	var day = latestDate.substring(3, 5);
	var year = latestDate.substring(6, 10);
	var dateValue = getNextDate(day, month, year);
	return dateValue;
}

function showFormWeekPicker(id){
	$('#'+id).datepicker({
		showOn: "button",
		buttonImage: "static/images/calendar.png",
		buttonImageOnly: true,
		buttonText: "Select Week",
        showOtherMonths: true,
        selectOtherMonths: true,
        showWeek: true,
        firstDay: 1,
        // Onselect function.
        onSelect: function(dateText, inst) {
        	var selecteddate = $(this).datepicker('getDate');
            var selectedYear = selecteddate.getFullYear();
            //If date is Dec 29, 2013, we need to increment year
            if((selecteddate.getMonth() == "11") && $.datepicker.iso8601Week(new Date(selecteddate)) == "1"){
           	 selectedYear++;
            }
            var week = $.datepicker.iso8601Week(new Date(selecteddate))+'/'+selectedYear;
            if($.datepicker.iso8601Week(new Date(selecteddate)) < 10){
           	 week = "0" + week;
            }
         	$('#prodweek').val(week);
     		$('#ordermonth').val('');
    		$('#prodmonth').val('');
    		$('#ordermonthDiv').removeClass('tms-border-red-1');
    		$('#prodmonthDiv').removeClass('tms-border-red-1');
    		$('#prodweekhDiv').removeClass('tms-border-red-1');
 	        }
    });
	$("#ui-datepicker-div").css('font-size','11px');
    $('#ui-datepicker-div .ui-datepicker-calendar tr').live('mousemove', function() { $(this).find('td a').addClass('ui-state-hover'); });
    $('#ui-datepicker-div .ui-datepicker-calendar tr').live('mouseleave', function() { $(this).find('td a').removeClass('ui-state-hover'); });
 }

function showFormMonthPicker(id){
$('#'+id).monthpicker({
	showOn:     "button",
	buttonImage: "static/images/calendar.png",
	buttonImageOnly: true,
	buttonText: "Select Month",
	changeYear: true,
	yearRange: 'c-1:c+50',
	dateFormat: 'mm/yy',
	onSelect: function(){
				if(id === "ordermonth_dup"){
					$("#ordermonth").val($("#ordermonth_dup").val().substring(0,3)+$("#ordermonth_dup").val().substring(5,7));
		     		$('#prodmonth').val('');
		     		$('#weekno').val('SELECT');
		     		$('#weekno').multiselect('refresh');
		     		$('#selectedYear').val('SELECT');
		    		$('#selectedYear').multiselect('refresh');
		    		$('#ordermonthDiv').removeClass('tms-border-red-1');
		    		$('#prodmonthDiv').removeClass('tms-border-red-1');
		    		$('#prodweekhDiv').removeClass('tms-border-red-1');
		     	}
				if(id === "prodmonth_dup"){
					$("#prodmonth").val($("#prodmonth_dup").val().substring(0,3)+$("#prodmonth_dup").val().substring(5,7));
		     		$('#ordermonth').val('');
		     		$('#weekno').val('SELECT');
		     		$('#weekno').multiselect('refresh');
		     		$('#selectedYear').val('SELECT');
		    		$('#selectedYear').multiselect('refresh');
		    		$('#ordermonthDiv').removeClass('tms-border-red-1');
		    		$('#prodmonthDiv').removeClass('tms-border-red-1');
		    		$('#prodweekhDiv').removeClass('tms-border-red-1');
		     	}
			}
		});

if(id === "ordermonth_dup"){
	$("#ui-monthpicker-div").position({
	    of: $('#ordermonth'),
	    offset:'50 60'
	});
}
if(id === "prodmonth_dup"){
	$("#ui-monthpicker-div").position({
	    of: $('#prodmonth'),
	    offset:'50 60'
	});
}

}

/**
 * @param id
 * @return
 */
function showWeekPicker(id){
	var startDate,endDate;
	$('#'+id+'_prodweek').datepicker({
        showOtherMonths: true,
        selectOtherMonths: true,
        showWeek: true,
//        dateFormat: 'dd/mm/yy',
        firstDay: 1,
        // Onselect function.
        onSelect: function(dateText, inst) {
        	var date = $(this).datepicker('getDate');
        	var week = $.datepicker.iso8601Week(new Date(date))+'/'+date.getFullYear();
        	startDate = new Date(date.getFullYear(), date.getMonth(), (date.getDate() - date.getDay())+1);
            endDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() + 7);
        	$("#"+id+"_prodweek").val(week);
        	if(grid.jqGrid('getCell',id,'prodweek').length != 0){
	          	$("#"+id+"_prodmonth").val("");
	        	$("#"+id+"_ordermonth").val("");
	        	$("#"+id+"_datetype").val("3");
          	 }
	        },beforeShowDay:function(date) {
 	            var cssClass = '';
 	            if(date >= startDate && date <= endDate)
 	                cssClass = 'ui-state-error';
 	            return [true, cssClass];
 	        }

    });

	$('#'+id+'_prodweek').datepicker('show');
	$("#ui-datepicker-div").css('font-size','10px');
    $('.ui-datepicker-div .ui-datepicker-calendar tr').live('mousemove', function() { $(this).find('td a').addClass('ui-state-hover'); });
    $('.ui-datepicker-div .ui-datepicker-calendar tr').live('mouseleave', function() { $(this).find('td a').removeClass('ui-state-hover'); });
 }

function showOrderMonthPicker(id){

	$('#ordermonth_dup').monthpicker({
		dateFormat: 'mm/yy',
		yearRange: '-0:+2',
		changeYear: true,
		onSelect: function(dateText, inst) {
       		$('#'+id+'_ordermonth').val($("#ordermonth_dup").val().substring(0,3)+$("#ordermonth_dup").val().substring(5,7));
	       	if($("#"+id+"_ordermonth").val().length != 0){
	          	$("#"+id+"_prodmonth").val("");
	        	$("#"+id+"_prodweek").val("");
	        	$("#"+id+"_datetype").val("1");
	          	 }
	        }
	   	});
	 $('#ordermonth_dup').monthpicker('show');
	 $("#ui-monthpicker-div").position({
         of: $('#'+id+'_ordermonth'),
         offset:'50 60'
     });
}


function showProdMonthPicker(id){

	$("#prodmonth_dup").monthpicker({
		dateFormat: 'mm/yy',
		yearRange: '-0:+2',
		changeYear: true,
		onSelect: function(dateText, inst) {
			$('#'+id+'_prodmonth').val($("#prodmonth_dup").val().substring(0,3)+$("#prodmonth_dup").val().substring(5,7));
	       	if($("#"+id+"_prodmonth").val().length != 0){
	          	$("#"+id+"_ordermonth").val("");
	        	$("#"+id+"_prodweek").val("");
	        	$("#"+id+"_datetype").val("2");
	          	 }
		        }
	   		});
	 $("#prodmonth_dup").monthpicker('show');
	 $("#ui-monthpicker-div").position({
         of: $('#'+id+'_prodmonth'),
         offset:'50 60'
     });
}

function showMsgDialog(title, message1, message2) {
	var msgDialog = $('<div id="msgDialogBox" class="tms-display-none" title='+title+'>\
	<p align="center" style="margin-top:15px" class="tms-sheader-h6 tms-font-bold tms-margin-a">'+message1+'</p>\
	<p align="center" class="tms-sheader-h6 tms-font-bold tms-margin-a tms-margin-top-d">'+message2+'</p>\
	</div>');
	msgDialog.dialog({
		modal: true,
		width:'450',
		height:'40',
		resizable:false,
		buttons: {
			OK: function() {
					$( this ).dialog( "close" );
				}
			}
		});
	}

/**Function to Collapse All Rows of tree grid
 * @param gridObj
* @return
*/
function collapseAllRows(gridId){
	var gridObj = $('#'+gridId);
	gridObj.setGridParam({
  		gridComplete: function() {
  			$(".treeclick",gridObj).each(function() {
  		        if($(this).hasClass("tree-minus"))
  		              $(this).trigger("click");
  		  });
  		}
  	});

}

/** Function to expand required rows in tree grid
 * @param inputValue
 * @param gridObj
 * @param inputCol
 * @return
 */
function expandReqdItems(inputValue,gridId,inputCol){
	var gridObj = $('#'+gridId);
	setTimeout(function () {
		var ids = gridObj.jqGrid('getDataIDs');
		var rowId;
		var childArrData = [];
		//Looping through the grid rows
		for(var cntr = 0; cntr < ids.length; cntr++){
		      var individualRowData = gridObj.jqGrid('getRowData',ids[cntr]);
		      if(individualRowData[inputCol] === inputValue){
		            rowId = ids[cntr];
		            for(var index = 0; index < ids.length; index++){
		                  //Fetching child data
		                  var childRowData = gridObj.jqGrid('getRowData',ids[index]);
		                  //Hardcoding the 'parent'
		                  if(null != childRowData.parent && rowId === childRowData.parent){
		                        childArrData.push(ids[index]);
		                  }
		            }
		            break;
		      }
		}

		//expanding the row
		var gridDataObj = gridObj.getLocalRow(rowId);
		gridObj.expandRow(gridDataObj);
		gridObj.expandNode(gridDataObj);

		var gridChildDataObj;
		//Looping through the specific parent's child rows
		for(var count = 0; count < childArrData.length; count++){
			gridChildDataObj = gridObj.getLocalRow(childArrData[count]);
		    gridObj.expandRow(gridChildDataObj);
		    gridObj.expandNode(gridChildDataObj);
		}
	}, 10);
}

/** Function to collapse all rows in tree grid
* @param gridObj
* @return
*/

function collapseReqdItems(gridId) {
	var gridObj = $('#' + gridId);
	var ids = gridObj.jqGrid('getDataIDs');
	var gridDataObj;
	// Looping through the grid rows
	for ( var cntr = 0; cntr < ids.length; cntr++) {
		// Collapsing the row
		gridDataObj = gridObj.getLocalRow(ids[cntr]);
		gridObj.collapseRow(gridDataObj);
		gridObj.collapseNode(gridDataObj);
	}
}

function refresStartDtDiv(){
	$('#startDtDiv').removeClass('tms-border-red-1');
}

function refresEndDtDiv(){
	$('#endDtDiv').removeClass('tms-border-red-1');
}

function refreshOrdermonth(){
	$('#ordermonthDiv').removeClass('tms-border-red-1');
}

function refreshProdmonth(){
	$('#prodmonthDiv').removeClass('tms-border-red-1');
}

function refreshProdweek(){
	$('#prodweekDiv').removeClass('tms-border-red-1');
}

function formReset(){
	$('#searchForm').each (function(){
		  this.reset();
	});
}

function resetSearchThreeLevelPriceCheck()
{
	$('#division').reset();
	$('#brand').reset();
	$('#modelYear').reset();
	$('#series').reset();
	$('#orderMonth').reset();

}

function selectOrderMonthOnly(){
	$('#prodmonth').val('');
	$('#prodweek').val('');
}

function selectProdMonthOnly(){
	$('#ordermonth').val('');
	$('#prodweek').val('');
}

function selectWeekPickerOnly(){
	$('#ordermonth').val('');
	$('#prodmonth').val('');
}


function refreshPriceStartDate(){
	$('#pricestartdatediv').removeClass('tms-border-red-1');
}

function refreshPdTransDate(){
	$('#pdtransdateDiv').removeClass('tms-border-red-1');
}

function refreshPriceVisibilityDate(){
	$('#pricevisibiltydateDiv').removeClass('tms-border-red-1');
}

function refreshWholesaleBlockDate(){
	$('#wholesaleblockdatediv').removeClass('tms-border-red-1');
}

function refreshRetailBlockDate(){
	$('#retailblockdateDiv').removeClass('tms-border-red-1');
}

function refreshDailyVisibilityDate(){
	$('#dailyvisibilitydatediv').removeClass('tms-border-red-1');
}

function refreshFleetVisibilityDate(){
	$('#fleetvisibilitydateDiv').removeClass('tms-border-red-1');
}

/************Navigation List Code - Start*******************/

function LoadFilterdata(idval){
	idvalue=idval;
	var inputval= $('#'+idvalue+'').val();
	page=1;
	limit = 10;
	if(inputval){
		if (inputval.length >1) {
			var Loaddata=Loop(page,limit,null,idvalue);
			displayList(Loaddata,page,limit,null,idvalue);
			ddlArray=null;
		}else if(inputval=='All'){
			ddlArray= new Array();
			var ddl = document.getElementById(''+idvalue+'');
			for (var i = 0; i < ddl.options.length-1; i++) {
			   ddlArray[i] = ddl .options[i+1].value;
			}
			var Loaddata=Loop(page,limit,ddlArray,idvalue);
			displayList(Loaddata,page,limit,ddlArray,idvalue);
		}
	}

}

function Next(){
	page +=1;
	var Loaddata=Loop(page,limit,seriesNmArray,idvalue)	;
	displayList(Loaddata,page,limit,seriesNmArray,idvalue);
}

function Back(){
	page--;
	var Loaddata=Loop(page,limit,seriesNmArray,idvalue)	;
	displayList(Loaddata,page,limit,seriesNmArray,idvalue);
}

function Loop(page,limit,AllValues,idvalue){
	var DisplayValue='';
	var ListOfData= null;
	if(AllValues!=null){
		ListOfData=AllValues;
	}else{
		ListOfData = $('#'+idvalue+'').val();
	}
	var spandisplay='';
	for(var i=(page-1)*limit;i<(page*limit) && (i>=0) && (i<ListOfData.length);i++){
		DisplayValue +=' <a id="class_'+i+'" href="#" style="margin-left: 5px" onclick="active(this,'+i+');">' +ListOfData[i]+'</a>';
		if((i+1)==ListOfData.length || (i+1)==(page*limit)){
			DisplayValue +='';
		}else{
			DisplayValue +='<span style="margin-left: 5px">|</span> ';
		}
	}
	return DisplayValue;
}

function displayList(Listdisp,page,limit,Allvalues,idvalue){
	 //limit=3;
	 var inputValue=null;
		if(Allvalues!=null){
			inputValue=Allvalues;
		}else{
			inputValue =$('#'+idvalue+'').val();
		}
	var lastpage=Math.ceil(inputValue.length/limit);
	if(inputValue.length<=limit){
		document.getElementById('grouping').style.display = 'block';
		document.getElementById('grouping').innerHTML =''+Listdisp+' ';
	}else if (page==1){
	document.getElementById('grouping').style.display = 'block';
	document.getElementById('grouping').innerHTML =''+Listdisp+' <img alt="Right Nav" src="static/images/paginationNext.png" style="height: 13px; width: 13px; vertical-align: top;margin-left:5px"  onclick="Next();"/>';
	}else if(page>=lastpage){
		document.getElementById('grouping').style.display = 'block';
		document.getElementById('grouping').innerHTML ='<img alt="Left Nav" src="static/images/paginationPrevious.png" style="height: 13px; width: 13px; vertical-align: top;" onclick="Back();"/> '+Listdisp+' ';
	}else{
		document.getElementById('grouping').style.display = 'block';
		document.getElementById('grouping').innerHTML ='<img alt="Left Nav" src="static/images/paginationPrevious.png" style="height: 13px; width: 13px; vertical-align: top;" onclick="Back();"/> '+Listdisp+' <img alt="Right Nav" src="static/images/paginationNext.png" style="height: 13px; width: 13px; vertical-align: top;margin-left:5px"  onclick="Next();"/>';
	}
}

function active(e,i) {
	$("#class_"+i).css("background","#C0C0C0");
	$("#grouping a").each(function(){
		var id = $(this).attr('id');
		if (id!="class_"+i){
			$("#"+id).css("background","white");
		}
	});
	var seriesIndex=i;
	filterBySeries(seriesIndex);
};

/************Navigation List Code - End*******************/

function changeButtonStates(id, b_name, gridId) {
	if(b_name === 'editableMode'){
		if($('#edit_b_' + gridId +id).length > 0){
			$('#edit_b_' + gridId +id).hide();
		}
		if($('#copy_b_' + gridId +id).length > 0){
			$('#copy_b_' + gridId +id).hide();
		}
		if($('#deac_b_' + gridId +id).length > 0){
			$('#deac_b_' + gridId +id).hide();
		}
		if($('#del_b_' + gridId +id).length > 0){
			$('#del_b_' + gridId +id).hide();
		}
		if($('#save_b_' + gridId +id).length > 0){
			$('#save_b_' + gridId +id).show();
		}
		if($('#cancel_b_' + gridId +id).length > 0){
			$('#cancel_b_' + gridId +id).show();
		}
		if($('#excep_b_' + gridId +id).length > 0){
			$('#excep_b_' + gridId +id).hide();
		}
		/*$('#edit_b_' + gridId +id).hide();
		$('#copy_b_' + gridId +id).hide();
		$('#deac_b_' + gridId +id).hide();
		$('#del_b_' + gridId +id).hide();
		$('#save_b_' + gridId +id).show();
		$('#cancel_b_' + gridId +id).show();*/
	} else if(b_name === 'uneditableMode'){
		if($('#edit_b_' + gridId +id).length > 0){
			$('#edit_b_' + gridId +id).show();
		}
		if($('#copy_b_' + gridId +id).length > 0){
			$('#copy_b_' + gridId +id).show();
		}
		if($('#deac_b_' + gridId +id).length > 0){
			$('#deac_b_' + gridId +id).show();
		}
		if($('#del_b_' + gridId +id).length > 0){
			$('#del_b_' + gridId +id).show();
		}
		if($('#excep_b_' + gridId +id).length > 0){
			$('#excep_b_' + gridId +id).show();
		}
		if($('#save_b_' + gridId +id).length > 0){
			$('#save_b_' + gridId +id).hide();
		}
		if($('#cancel_b_' + gridId +id).length > 0){
			$('#cancel_b_' + gridId +id).hide();
		}
		/*$('#edit_b_' + gridId +id).show();
		$('#copy_b_' + gridId +id).show();
		$('#deac_b_' + gridId +id).show();
		$('#del_b_' + gridId +id).show();
		$('#save_b_' + gridId +id).hide();
		$('#cancel_b_' + gridId +id).hide();*/
	}
};

function hideErrorBox(){
	if($('#errorBox').show()){
		$('#errorBox').hide();
	}
}

function hideSuccessBox(){
	if($('#successBox').show()){
		$('#successBox').hide();
	}
}

function clearBackground(id){
	$("#"+id).css('background','white');
}

function clearDivisionError(){
	$('#divError').remove();
	var len = 0;
	$('#errorList li').each(function(){
		len++;
	});
	if(len == 0){
		$('#errorBox').hide();
	}
}

function clearDivisionError(){
	$('#divError').remove();
	var len = 0;
	$('#errorList li').each(function(){
		len++;
	});
	if(len == 0){
		$('#errorBox').hide();
	}
}

function clearBrandError(){
	$('#brandError').remove();
	var len = 0;
	$('#errorList li').each(function(){
		len++;
	});
	if(len == 0){
		$('#errorBox').hide();
	}
}

function clearModelYearError(){
	$('#modelYearError').remove();
	var len = 0;
	$('#errorList li').each(function(){
		len++;
	});
	if(len == 0){
		$('#errorBox').hide();
	}
}

function hideMessageBox(){
	$('#successBox,#errorBox,#alertBox,#messageBox,#errorBoxCalculate,#alertBoxCalculate,#successBoxCalculate').empty().hide();
}

/** Call this function for any ajax calls that need to pass multiple TOs
 * url: the server side url to be called
 * data: any query parameters to be passed.
 * dataType: the data return from server. Eg. html, json
 * showResponse: callback method to handle the response text
 * loadingDivId: if some loading icon is to be shown during ajax call, pass the id of the loading icon.
 * loadToHistory: if history is enabled for ajax calls (to book go back, forward, bookmark even with ajax calls) pass the boolean
 * 					if passed true, jquery.history plugin has to be imported.
 *
 * In case any error happens during the ajax call, an error dialog box is shown.
 * */
$.firePost = function(url, data, showResponse, loadingDivId, loadToHistory) {
	 if(loadingDivId)
 	{
	  $('#'+loadingDivId).show();
	 }
 var tag = $("<div title='Internal Error'></div>");

 $.post(url, data,
  function(data, textStatus, xhr) {
	if(loadingDivId)
	 {
		 $('#'+loadingDivId).hide();
	 }
	 var hash = url.replace(/^.*#/, '');
	 if (loadToHistory) {
		 $.history.load(hash);
	 }
  	showResponse(data, textStatus, xhr);
  }).fail(function(xhr){
	 if(loadingDivId)
	 {
		 $('#'+loadingDivId).hide();
	 }
	tag.html(xhr.responseText).dialog({modal: true,buttons: {Ok: function() {$( this ).dialog( "close" );}}}).dialog('open');
 	});
};

/** Call this function for posting JSON data
 * url: the server side url to be called
 * data: any query parameters to be passed.
 * callbackSuccess: Success callback function to be called.
 * callbackFailure: Failure callback function to be called.
 * loadingDivId: if some loading icon is to be shown during ajax call, pass the id of the loading icon.
 *
 * In case any error happens during the ajax call, an error dialog box is shown.
 * */
$.postJSONData = function(url, data, callbackSuccess, callbackFailure, loadingDivId) {
	if(loadingDivId)
	 {
		 $('#'+loadingDivId).show();
	 }
	if (callbackFailure) {
	    return jQuery.ajax({
	        'type': 'POST',
	        'url': url,
	        'contentType': 'application/json',
	        'data': JSON.stringify(data),
	        'success': callbackSuccess,
	        'error': callbackFailure
	    });
	} else {
		var tag = $("<div title='Internal Error'></div>");
		return jQuery.ajax({
	        'type': 'POST',
	        'url': url,
	        'contentType': 'application/json',
	        'data': JSON.stringify(data),
	        'success': callbackSuccess,
	        'error': function(xhr) {
	        	$('#preLoadingIcon').hide();
        		tag.html(xhr.responseText).dialog({modal: true,buttons: {Ok: function() {$( this ).dialog( "close" );}}}).dialog('open');
			}
	    });
	}
};



$.putJSONData = function(url, data, callbackSuccess, callbackFailure, loadingDivId) {
	if(loadingDivId)
	 {
		 $('#'+loadingDivId).show();
	 }
	if (callbackFailure) {
	    return jQuery.ajax({
	        'type': 'PUT',
	        'url': url,
	        'contentType': 'application/json',
	        'data': JSON.stringify(data),
	        'success': callbackSuccess,
	        'error': callbackFailure
	    });
	} else {
		var tag = $("<div title='Internal Error'></div>");
		return jQuery.ajax({
	        'type': 'PUT',
	        'url': url,
	        'contentType': 'application/json',
	        'data': JSON.stringify(data),
	        'success': callbackSuccess,
	        'error': function(xhr) {
	        	$('#preLoadingIcon').hide();
	        	tag.html(xhr.responseText).dialog({modal: true,buttons: {Ok: function() {$( this ).dialog( "close" );}}}).dialog('open');
			}
	    });
		
	}
};




$.deleteJSONData = function(url, data, callbackSuccess, callbackFailure, loadingDivId) {
	if(loadingDivId)
	 {
		 $('#'+loadingDivId).show();
	 }
	if (callbackFailure) {
	    return jQuery.ajax({
	        'type': 'DELETE',
	        'url': url,
	        'contentType': 'application/json',
	        'data': data,
	        'success': callbackSuccess,
	        'error': callbackFailure
	    });
	} else {
		var tag = $("<div title='Internal Error'></div>");
		return jQuery.ajax({
	        'type': 'DELETE',
	        'url': url,
	        'contentType': 'application/json',
	        'data': data,
	        'success': callbackSuccess,
	        'error': function(xhr) {
	        	$('#preLoadingIcon').hide();
	        	tag.html(xhr.responseText).dialog({modal: true,buttons: {Ok: function() {$( this ).dialog( "close" );}}}).dialog('open');
			}
	    });
	}
};


$.getJSONData = function(url, data, callbackSuccess, callbackFailure, loadingDivId) {
	if(loadingDivId)
	 {
		 $('#'+loadingDivId).show();
	 }
	if (callbackFailure) {
	    return jQuery.ajax({
	        'type': 'GET',
	        'url': url,
	        'contentType': 'application/json',
	        'data': JSON.stringify(data),
	        'success': callbackSuccess,
	        'error': callbackFailure
	    });
	} else {
		var tag = $("<div title='Internal Error'></div>");
		return jQuery.ajax({
	        'type': 'GET',
	        'url': url,
	        'contentType': 'application/json',
	        'data': JSON.stringify(data),
	        'success': callbackSuccess,
	        'error': function(xhr) {
	        	$('#preLoadingIcon').hide();
        		tag.html(xhr.responseText).dialog({modal: true,buttons: {Ok: function() {$( this ).dialog( "close" );}}}).dialog('open');
			}
	    });
	}
};


function validatePhaseCode(keyEvent, val) {
	var charCode = keyEvent.charCode || keyEvent.keyCode;
	// Allow arrow keys, backspace, lower case characters
	// upper case characters
	if (charCode == 37 || charCode == 39 || charCode == 8 ||
			(charCode > 64 && charCode < 91) || (charCode > 96 && charCode < 123)) {
		return true;
    } else {
		return false;
    }
}
