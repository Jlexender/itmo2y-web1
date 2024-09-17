$(document).ready(function() {
    const $r = $('#r');
    const $x = $('#x');

    function populateSelectWithFloats($element, optionsHTML) {
        const temp = $('<div>').html(optionsHTML);

        temp.find('option').each(function() {
            let value = parseFloat($(this).val());
            let text = parseFloat($(this).text());

            // Display up to 2 decimal places only if necessary
            let formattedValue = (value % 1 === 0) ? value : value.toFixed(2);
            let formattedText = (text % 1 === 0) ? text : text.toFixed(2);

            $element.append('<option value="' + formattedValue + '">' + formattedText + '</option>');
        });
    }

    $.ajax({
        url: '/fcgi-bin/server.jar?rSelector=1',
        type: 'GET',
        success: function(response) {
            populateSelectWithFloats($r, response);
        },
        error: function(xhr, status, error) {
            console.error('Failed to fetch R options: ' + error);
        }
    });

    $.ajax({
        url: '/fcgi-bin/server.jar?xSelector=1',
        type: 'GET',
        success: function(response) {
            populateSelectWithFloats($x, response);
        },
        error: function(xhr, status, error) {
            console.error('Failed to fetch X options: ' + error);
        }
    });
});
