$(document).ready(function() {
    const $r = $('#r');
    const $x = $('#x');

    function populateSelectWithHTML($element, optionsHTML) {
        $element.empty();
        $element.append('<option value="" disabled selected>Select</option>');
        $element.append(optionsHTML); // Append the HTML from the server directly
    }

    $.ajax({
        url: 'http://localhost:8080/fcgi-bin/server.jar',
        type: 'GET',
        data: {
            rParams: true
        },
        success: function(response) {
            populateSelectWithHTML($r, response);
        },
        error: function(xhr, status, error) {
            console.error('Failed to fetch R options: ' + error);
        }
    });

    $.ajax({
        url: 'http://localhost:8080/fcgi-bin/server.jar',
        type: 'GET',
        data: {
            xParams: true
        },
        success: function(response) {
            populateSelectWithHTML($x, response);
        },
        error: function(xhr, status, error) {
            console.error('Failed to fetch X options: ' + error);
        }
    });
});
