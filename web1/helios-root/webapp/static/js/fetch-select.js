$(document).ready(function() {
    const $r = $('#r');
    const $x = $('#x');

    function populateSelectWithFloats($element, optionsHTML) {
        $element.empty();
        $element.append('<option value="" disabled selected>Select</option>');

        const temp = $('<div>').html(optionsHTML);


        temp.find('option').each(function() {
            let value = parseFloat($(this).val()).toFixed(2);
            let text = parseFloat($(this).text()).toFixed(2);

            $element.append('<option value="' + value + '">' + text + '</option>');
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
