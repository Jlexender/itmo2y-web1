$(document).ready(function() {
    const $r = $('#r');
    const $x = $('#x');

    function populateSelectWithHTML($element, optionsHTML) {
        $element.empty();
        $element.append('<option value="" disabled selected>Select</option>');
        $element.append(optionsHTML); // Append the HTML from the server directly
    }

    $.ajax({
        url: '/fcgi-bin/server.jar?rParams=1',
        type: 'GET',
        success: function(response) {
            console.log(response)
            populateSelectWithHTML($r, response);
        },
        error: function(xhr, status, error) {
            console.error('Failed to fetch R options: ' + error);
        }
    });

    $.ajax({
        url: '/fcgi-bin/server.jar?xParams=1',
        type: 'GET',
        success: function(response) {
            populateSelectWithHTML($x, response);
        },
        error: function(xhr, status, error) {
            console.error('Failed to fetch X options: ' + error);
        }
    });
});
