
var list = [];

$( document ).ready(function() {
    const url = 'http://localhost:8080/airports'

    $.get(url, function(response, status){
        if (status == 'success'){
            list=response;
        }
    })
});


$(document).on('focus', '#idLocalNames', function () {
      $(this).autocomplete({
          //source take a list of data
          source: list,
          minLength: 1//min = 2 characters
      });
  });