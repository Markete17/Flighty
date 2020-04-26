
/******************************

[Table of Contents]

1. Vars and Inits
2. Autocomplete functionality
3. Modify inputs flight dates
4. Submit form

******************************/

$( document ).ready(function() {
	
	/* 

	1. Vars and Inits

	*/
	
    const url_airports = 'http://localhost:8080/airports';
    	
   	var list = [];
    
	/* 

	2. Autocomplete funcionality

	*/

    $.get(url_airports, function(response, status){
        if (status == 'success'){
            list=response;
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
    	
    	if (!$('#date2').val()) {
    		url = url + encodeURI('1927-03-14'); //Avoid error in backend
    	}
    	
    	$.getJSON(url, function (response) {
    		$('#not_found').text('');
    		$('#list_flights').append(
				["<h2 class='w-100 text-center pb-3'>",
					"<span class='text-dark'>7 July Tuesday - 14 July Tuesday</span>",
				"</h2>",
				"<div class='row d-sm-flex justify-content-between mt-4 mb-3 ml-4 mr-4 border-bottom'>",
					"<!-- Outgoing flight -->",
					"<div class='d-block text-center text-sm-left'>",
						"<div class='row'>",
							"<img class='img_icon mr-sm-2' src='/images/plane_orig.png'",
								"alt='plane taking off'>",
							"<table class='d-flex table-sm text-center'>",
								"<tbody>",
									"<tr>",
										"<th><small>Madrid (Barajas-Adolfo Suarez)</small></th>",
										"<th></th>",
										"<th><small>Barcelona</small></th>",
									"</tr>",
									"<tr>",
										"<th><h3 class='text-dark'>07:30</h3></th>",
										"<th><h3 class='text-dark mx-3'>-</h3></th>",
										"<th><h3 class='text-dark'>08:55</h3></th>",
									"</tr>",
								"</tbody>",
							"</table>",
							"<img class='img_icon ml-sm-1' src='/images/plane_dest.png'",
								"alt='plane landing'>",
						"</div>",
						"<div class='row d-block text-center'>",
							"<h4 class='text-primary'>235€</h4>",
							"<h6>",
								"<span class='font-weight-bold'>Duration:</span> 173 min",
							"</h6>",
							"<h6>",
								"<span class='font-weight-bold'>Company:</span> Cathay Pacific",
									"Airways",
							"</h6>",
						"</div>",
					"</div>",
					"<!-- Image between outcoming & incoming flight -->",
					"<div class='d-block mx-auto mt-2'>",
						"<div class='row'>",
							"<img class='img_icon' src='/images/arrows.png'",
								"alt='two arrows'>",
						"</div>",
						"<div class='row d-block text-center mt-3'>",
							"<h3 class='text-primary'>235€</h3>",
						"</div>",
					"</div>",
					"<!-- Incoming flight -->",
					"<div class='d-block text-center text-sm-left'>",
						"<div class='row'>",
							"<img class='img_icon mr-sm-2' src='/images/plane_orig.png'",
								"alt='plane taking off'>",
							"<table class='d-flex table-sm text-center'>",
								"<tbody>",
									"<tr>",
										"<th><small>Madrid (Barajas-Adolfo Suarez)</small></th>",
										"<th></th>",
										"<th><small>Barcelona</small></th>",
									"</tr>",
									"<tr>",
										"<th><h3 class='text-dark'>07:30</h3></th>",
										"<th><h3 class='text-dark mx-3'>-</h3></th>",
										"<th><h3 class='text-dark'>08:55</h3></th>",
									"</tr>",
								"</tbody>",
							"</table>",
							"<img class='img_icon ml-sm-1' src='/images/plane_dest.png'",
								"alt='plane landing'>",
						"</div>",
						"<div class='row d-block text-center'>",
							"<h4 class='text-primary'>235€</h4>",
							"<h6>",
								"<span class='font-weight-bold'>Duration:</span> 173 min",
							"</h6>",
							"<h6>",
								"<span class='font-weight-bold'>Company:</span> Cathay Pacific",
								"Airways",
							"</h6>",
						"</div>",
					"</div>",
				"</div>"].join(""));
    	}).fail(function() {
    		$('#not_found').text('No flights found');
    	});
    	
    	// Scroll to results
		$([document.documentElement, document.body]).animate({
	        scrollTop: $(".intro").offset().top
	    }, 2000);
    });
        
});