
/******************************

[Table of Contents]

1. Vars and Inits
2. Autocomplete functionality
3. Modify return flight date


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
        $(this).autocomplete({
            //source take a list of data
            source: list
        });
    });
    
	/* 

	3. Modify return flight date

	*/
    
    $('input[name=method]').change(function(){
    	if ($('#round_trip').is(':checked')) {
    		$('#date2').show();
    	} else {
    		$('#date2').hide();
    	}
    });
    
});