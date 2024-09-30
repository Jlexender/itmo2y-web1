$(document).ready(function() {
    const canvas = $('#canvas')[0];
    const context = canvas.getContext('2d');
    let radius = parseFloat($('#r').val());

    $('#canvas').on('click', function(event) {
        radius = parseFloat($('#r').val());
        if (isNaN(radius) || radius <= 0 || radius > 4) {
            alert('Радиус не установлен или некорректен.');
            return;
        }

        const rect = canvas.getBoundingClientRect();
        const x = event.clientX - rect.left;
        const y = event.clientY - rect.top;

        let realX = (x - canvas.width / 2) / (canvas.width / 2) * radius;
        let realY = (canvas.height / 2 - y) / (canvas.height / 2) * radius;
        realX = realX.toFixed(2);
        realY = realY.toFixed(2);

        startTime = performance.now();
        const template = $('template').contents().clone();
        template.find('td').eq(0).text(realX);
        template.find('td').eq(1).text(realY);
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
                x: realX,
                y: realY,
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
                insertPoint(realX, realY, radius);
            },
            error: function() {
                alert('Ошибка при отправке данных на сервер.');
            }
        });
    });
});
