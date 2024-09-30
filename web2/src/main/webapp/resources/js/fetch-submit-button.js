$(document).ready(function() {
    const canvas = $('#canvas')[0];
    const context = canvas.getContext('2d');

    $('form').on('submit', function(event) {
        event.preventDefault();
        const radius = parseFloat($('#r').val());
        const y = parseFloat($('#y').val());
        const x = parseFloat($('input[name="x"]:checked').val());

        const startTime = performance.now();
        const template = $('template').contents().clone();
        template.find('td').eq(0).text(x);
        template.find('td').eq(1).text(y);
        template.find('td').eq(2).text(radius);
        template.find('td').eq(3).text('Вычисляем...');
        template.find('td').eq(4).text(new Date().toLocaleString());
        template.find('td').eq(5).text('Вычисляем...');
        template.find('td').eq(6).text('Вычисляем...');
        template.hide();
        template.fadeIn(500);
        $('.requestData tbody').prepend(template);

        $.ajax({
            url: '/query',
            type: 'GET',
            data: {
                x: x,
                y: y,
                r: radius
            },
            success: function(data) {
                const result = data.result;
                template.find('td').eq(3).text(result ? '+1 HMSTR' : 'Слил казик');
                template.find('td').eq(5).text((performance.now() - startTime).toFixed(2) + ' мс');
                template.find('td').eq(6).text(data.executionTime + ' нс');
                if (result) {
                    const audio = new Audio('/resources/mp3/probitie1.mp3');
                    audio.play();
                } else {
                    const audio = new Audio('/resources/mp3/ne-probil.mp3');
                    audio.play();
                }
                insertPoint(x, y, radius);
            },
            error: function() {
                template.find('td').eq(3).text('Ошибка').css('color', 'red');
                template.find('td').eq(5).text('Ошибка').css('color', 'red');
                template.find('td').eq(6).text('Ошибка').css('color', 'red');
            }
        });
    });
});
