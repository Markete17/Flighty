$( function() {
    var availableTags = [
      "Madrid (Barajas-Adolfo Suarez)",
      "Barcelona (El Prat)",
      "Paris (París-Charles de Gaulle)",
      "Berlin (Tegel)",
      "Lisboa (Portela)",
    ];
    $( "#tags" ).autocomplete({
      source: availableTags
    });
  } );