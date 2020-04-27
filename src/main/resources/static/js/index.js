
/******************************

[Table of Contents]

1. Vars and Inits
2. Autocomplete functionality
3. Modify inputs flight dates
4. Submit form
5. Dialog with the info company
6. Rating of the company

******************************/

$( document ).ready(function() {
	
	/* 

	1. Vars and Inits

	*/
	
    const url_airports = 'http://localhost:8080/airports';
    	
   	var list = [];
   	
   	$.fn.bootstrapBtn = $.fn.button.noConflict();
    
	/* 

	2. Autocomplete funcionality

	*/

    $.get(url_airports, function(response, status){
        if (status == 'success'){
            list = response;
        }
    });
    
    $(document).on('focus', '#idLocalNames', function () {
    	$(this).val('');
        $(this).autocomplete({
            //source take a list of data
            source: list
        });
    });
    
	/* 

	3. Modify inputs flight dates

	*/
    
    // Show/hide input of return flight 
    $('input[name=method]').on('change', function(){
    	var returnDate = $('#date2');
    	if ($('#round_trip').is(':checked')) {
    		returnDate.prop('required', true);
    		if (!returnDate.val()) {
    			returnDate.prop('type', 'text');
    		}
    		returnDate.show();
    	} else {
    		returnDate.hide();
    		returnDate.prop('type', 'date');
    		returnDate.removeAttr('required');
    	}
    });
    
    // On blur set to text type if empty or update min value of return flight if round trip
    $('#date1').blur(function() {
    	var outboundDate = $('#date1');
    	if (!outboundDate.val()) {
    		outboundDate.prop('type', 'text');
    		$('#date2').removeAttr('min');
    	} else {
    		$('#date2').attr('min', outboundDate.val());
    	}
    });
    
    // On focus set type to date
    $('#date1').focus(function() {
    	$('#date1').prop('type', 'date');
    });
    
    // On blur set to text type if empty
    $('#date2').blur(function() {
    	var returnDate = $('#date2');
    	if (!returnDate.val()) {
    		returnDate.prop('type', 'text');
    	} 
    });
    
    // On focus set type to date
    $('#date2').focus(function() {
    	$('#date2').prop('type', 'date');
    });
    
	/* 

	4. Submit form

	*/
    
    $('#home_search_form').on('submit', function(e) {
    	// Not load page
    	e.preventDefault();
    	
    	// Get flights with the search criteria
    	var url = $(this).attr('action') + '?' + $(this).serialize();
    	var div_parent = $('#list_flights');
    	
    	if (!$('#date2').val()) {
    		url = url + encodeURI('1927-03-14'); //Avoid error in backend
    	}
    	
    	// If already flights we remove it
    	div_parent.empty();
    	
    	$.getJSON(url, function (response) {
    		// Remove text of not_found
    		$('#not_found').text('');
			
    		if ($('input[name=method]:checked').val() === 'One Way') {
    			div_parent.append(
    				["<h2 class='w-100 text-center pb-3'>",
    					"<span class='text-dark'>", transformFormatDate($('#date1').val()),
    					" (outbound flight)</h2>"].join(""));
    			
    			for (flight of response) {
    				
    				// Minutes outbound flight
    				var minutes = flight.minutes;
    				if (minutes < 10) minutes = "0" + minutes;
    				
    				//Arrival time for the flight
    				var hours_return = flight.hour + Math.floor(flight.flightTime / 60);
    				var minutes_return = flight.minutes + (flight.flightTime % 60);
    				
    				// If minutes are greater than 60 reduce the minutes and add an hour
    				while (minutes_return >= 60) {
    					minutes_return -= 60;
    					hours_return++;
    				}
    				
    				// If minutes are greater than 60 reduce the minutes and add an hour
    				if (minutes_return < 10) minutes_return = "0" + minutes_return;
    				
    				// Add html code dinamically
    				div_parent.append(
    					["<hr>",
            			 "<div class='row d-sm-flex justify-content-between m-4 border-bottom'>",
        					"<!-- Outgoing flight -->",
        					"<div class='d-block text-center mx-auto text-sm-left'>",
        						"<div class='row'>",
        							"<img class='img_icon mr-sm-2' src='/images/plane_orig.png'",
        								"alt='plane taking off'>",
        							"<table class='d-flex table-sm text-center'>",
        								"<tbody>",
        									"<tr>",
        										"<th><small>", $('input[name=origin]').val(), "</small></th>",
        										"<th></th>",
        										"<th><small>", $('input[name=dest]').val(), "</small></th>",
        									"</tr>",
        									"<tr>",
        										"<th><h3 class='text-dark'>", flight.hour, ":", minutes, "</h3></th>",
        										"<th><h3 class='text-dark mx-3'>-</h3></th>",
        										"<th><h3 class='text-dark'>", hours_return, ":", minutes_return, "</h3></th>",
        									"</tr>",
        								"</tbody>",
        							"</table>",
        							"<img class='img_icon ml-sm-1' src='/images/plane_dest.png'",
        								"alt='plane landing'>",
        						"</div>",
        						"<div class='row d-block text-center'>",
        							"<h4 class='text-primary'>", flight.price, "€ </h4>",
        							"<h6>",
        								"<span class='font-weight-bold'>Duration: </span>", flight.flightTime, " min",
        							"</h6>",
        							"<a class='color_gray' id='open_dialog' data-value='", flight.company, "' href='#'><h6>",
        								"<span class='font-weight-bold'>Company: </span>", getCompanyName(flight.company), " (", flight.company, ")",
        							"</h6></a>",
        						"</div>",
        					"</div>",
        				 "</div>"].join(""));
    			}
    			
    		} else {
    			// Date of outbound & inbound flights
    			div_parent.append(
    				["<h2 class='w-100 text-center pb-3'>",
    					"<span class='text-dark'>", transformFormatDate($('#date1').val())," (outbound flight) - ", transformFormatDate($('#date2').val()),
    					" (inbound flight) </span>",
    				"</h2>"].join(""));
    			
    			for (var i = 0; i < response.length; i = i + 2) {
    				// Minutes outbound flight
    				var min_1 = response[i].minutes;
    				if (min_1 < 10) min_1 = "0" + min_1;
    				
    				// Minutes inbound flight
    				var min_2 = response[i + 1].minutes;
    				if (min_2 < 10) min_2 = "0" + min_2;
    				
    				//Arrival time for both flights
    				var hours_1 = response[i].hour + Math.floor(response[i].flightTime / 60);
    				var minutes_1 = response[i].minutes + (response[i].flightTime % 60);
    				
    				var hours_2 = response[i + 1].hour + Math.floor(response[i + 1].flightTime / 60);
    				var minutes_2 = response[i + 1].minutes + (response[i + 1].flightTime % 60);
    				
    				// If minutes are greater than 60 reduce the minutes and add an hour
    				while (minutes_1 >= 60) {
    					minutes_1 -= 60;
    					hours_1++;
    				} 
    				
    				while (minutes_2 >= 60) {
    					minutes_2 -= 60;
    					hours_2++;
    				}
    				
    				if (minutes_1 < 10) minutes_1 = "0" + minutes_1;
    				if (minutes_2 < 10) minutes_2 = "0" + minutes_2;
    				
    				// Discount if company is the same
    				var total = response[i].price + response[i + 1].price;
    				if (response[i].company === response[i + 1].company) 
    					total = "<div class='text-danger go_search'><del>" + total + "€ </del></div>" + (total * 0.8);
    				
    				// Add html code dinamically
    				div_parent.append(
            				["<hr>",
            				"<div class='row d-sm-flex justify-content-between m-4 border-bottom'>",
            					"<!-- Outgoing flight -->",
            					"<div class='d-block text-center mx-auto text-sm-left'>",
            						"<div class='row'>",
            							"<img class='img_icon mr-sm-2' src='/images/plane_orig.png'",
            								"alt='plane taking off'>",
            							"<table class='d-flex table-sm text-center'>",
            								"<tbody>",
            									"<tr>",
            										"<th><small>", $('input[name=origin]').val(), "</small></th>",
            										"<th></th>",
            										"<th><small>", $('input[name=dest]').val(), "</small></th>",
            									"</tr>",
            									"<tr>",
            										"<th><h3 class='text-dark'>", response[i].hour, ":", min_1, "</h3></th>",
            										"<th><h3 class='text-dark mx-3'>-</h3></th>",
            										"<th><h3 class='text-dark'>", hours_1, ":", minutes_1, "</h3></th>",
            									"</tr>",
            								"</tbody>",
            							"</table>",
            							"<img class='img_icon ml-sm-1' src='/images/plane_dest.png'",
            								"alt='plane landing'>",
            						"</div>",
            						"<div class='row d-block text-center'>",
            							"<h4 class='text-primary'>", response[i].price, "€ </h4>",
            							"<h6>",
            								"<span class='font-weight-bold'>Duration: </span>", response[i].flightTime, " min",
            							"</h6>",
            							"<a class='color_gray' id='open_dialog' data-value='", response[i].company, "' href='#'><h6>",
            								"<span class='font-weight-bold'>Company: </span>", getCompanyName(response[i].company), " (", response[i].company, ")",
            							"</h6></a>",
            						"</div>",
            					"</div>",
            					"<!-- Image between outcoming & incoming flight -->",
            					"<div class='d-block mx-auto mt-2'>",
            						"<div class='row'>",
            							"<img class='img_icon' src='/images/arrows.png'",
            								"alt='two arrows'>",
            						"</div>",
            						"<div class='row d-block text-center mt-3'>",
            							"<h3 class='text-primary'>", total,"€ </h3>",
            						"</div>",
            					"</div>",
            					"<!-- Incoming flight -->",
            					"<div class='d-block text-center mx-auto text-sm-left'>",
            						"<div class='row'>",
            							"<img class='img_icon mr-sm-2' src='/images/plane_orig.png'",
            								"alt='plane taking off'>",
            							"<table class='d-flex table-sm text-center'>",
            								"<tbody>",
            									"<tr>",
            										"<th><small>", $('input[name=dest]').val(), "</small></th>",
            										"<th></th>",
            										"<th><small>", $('input[name=origin]').val(), "</small></th>",
            									"</tr>",
            									"<tr>",
            										"<th><h3 class='text-dark'>", response[i + 1].hour, ":", min_2, "</h3></th>",
            										"<th><h3 class='text-dark mx-3'>-</h3></th>",
            										"<th><h3 class='text-dark'>", hours_2, ":", minutes_2, "</h3></th>",
            									"</tr>",
            								"</tbody>",
            							"</table>",
            							"<img class='img_icon ml-sm-1' src='/images/plane_dest.png'",
            								"alt='plane landing'>",
            						"</div>",
            						"<div class='row d-block text-center'>",
            							"<h4 class='text-primary'>", response[i + 1].price, "€", "</h4>",
            							"<h6>",
            								"<span class='font-weight-bold'>Duration: </span>", response[i + 1].flightTime, " min",
            							"</h6>",
            							"<a class='color_gray' id='open_dialog' data-value='", response[i + 1].company, "' href='#'><h6>",
            								"<span class='font-weight-bold'>Company: </span>", getCompanyName(response[i + 1].company), " (", response[i + 1].company, ")",
            							"</h6></a>",
            						"</div>",
            					"</div>",
            				"</div>"].join(""));	
    			}    			
    		}
    	}).fail(function() {
    		$('#not_found').text('No flights found');
    	});
    	
    	// Scroll to results
		$([document.documentElement, document.body]).animate({
	        scrollTop: $(".intro").offset().top
	    }, 2000);
    });
    
    // Change the format of a date
    function transformFormatDate(date) {
    	var arr = date.split("-");
    	var res = arr[2] + " ";
    	
    	switch (arr[1]) {
    	case "01":
    		res = res + "January";
    		break;
    	case "02":
    		res = res + "February";
    		break;
    	case "03":
    		res = res + "March";
    		break;
    	case "04":
    		res = res + "April";
    		break;
    	case "05":
    		res = res + "May";
    		break;
    	case "06":
    		res = res + "June";
    		break;
    	case "07":
    		res = res + "July";
    		break;
    	case "08":
    		res = res + "August";
    		break;
    	case "09":
    		res = res + "September";
    		break;
    	case "10":
    		res = res + "October";
    		break;
    	case "11":
    		res = res + "November";
    		break;
    	case "12":
    		res = res + "December";
    		break;
    	}
    	
    	res = res + " " + arr[0];
    	
    	return res;
    }
    
    // Get name of the company
	function getCompanyName(code) {
		switch (code) {
		case "SA":
			return "Singapore Airlines";
		case "QA":
			return "Qatar Airways";
		case "NA":
			return "ANA All Nippon Airways";
		case "EA":
			return "Emirates";
		case "IB":
			return "Iberia";
		case "LU":
			return "Lufthansa";
		case "CP":
			return "Cathay Pacific Airways";
		case "UX":
			return "Air Europa";
		case "AF":
			return "Air France";
		default:
			return "";
		}
	}
	
	/* 

	5. Dialog with the info company

	*/
	
	// Modify behaviour of dialog
	$('#dialog').dialog({
		autoOpen: false,
		modal: true,
		resizable: false,
		height: 425,
		width: 450,
	    buttons: {
	    	Ok: function() {
	    		$( this ).dialog( "close" );
	    	}
	    },
		show: {
			effect: "puff",
			duration: 1500
		},
		hide: {
			effect: "fade",
			duration: 1000
		}
	});
	
	// Get info of the company
	$(document).on('click', '#open_dialog', function () {
		var company = $(this).data('value');
		var url = '/get_company?code=' + company;
		$.getJSON(url, function(response) {
			$('#name_company').text(response.name);
			$('#code_iata').text(response.code);
			$('#page').text(response.linkPage);
			$('#phone').text(response.phone);
			$('#rateYo').rateYo("rating",response.rating);
		});
		
		$( "#dialog" ).dialog( "open" );
		return false;
    });
	
	/*
	 
	6. Dialog of the company
	 
	 */
	
	 // Modify behaviour of rating
	 $("#rateYo").rateYo({
	    starWidth: "45px",
	    readOnly: true
	  });
	        
});