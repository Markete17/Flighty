
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
    
    //Show/hide input of return flight 
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
    
    //On blur set to text type if empty or update min value of return flight if round trip
    $('#date1').blur(function() {
    	var outboundDate = $('#date1');
    	if (!outboundDate.val()) {
    		outboundDate.prop('type', 'text');
    		$('#date2').removeAttr('min');
    	} else {
    		$('#date2').attr('min', outboundDate.val());
    	}
    });
    
    //On focus set type to date
    $('#date1').focus(function() {
    	$('#date1').prop('type', 'date');
    });
    
    //On blur set to text type if empty
    $('#date2').blur(function() {
    	var returnDate = $('#date2');
    	if (!returnDate.val()) {
    		returnDate.prop('type', 'text');
    	} 
    });
    
    //On focus set type to date
    $('#date2').focus(function() {
    	$('#date2').prop('type', 'date');
    });
    
	/* 

	4. Submit form

	*/
    
    $('#home_search_form').on('submit', function(e) {
    	e.preventDefault();
    	var url = $(this).attr('action') + '?' + $(this).serialize();
    	$.getJSON(url, function (response) {
    			alert('Perfect');
    	}).fail(function() {
    		
    	});
    });
        
});