$(document).ready(function() {
    drawPlot();

    $('#r').on('change', function() {
        refreshLabels(this.value);
        refreshPoints(this.value);
        drawPlot();
    });

    $('form').on('submit', function(event) {
        event.preventDefault();

        const template = $('template').contents().clone();

        const x = parseFloat($('#x').val());
        const y = parseFloat($('#y').val());
        const r = parseFloat($('#r').val());

        template.find('td').eq(0).text(x);
        template.find('td').eq(1).text(y);
        template.find('td').eq(2).text(r);
        template.find('td').eq(3).text('Вычисляем...');
        template.find('td').eq(4).text(new Date().toLocaleString());
        template.find('td').eq(5).text('Вычисляем...');
        template.hide();
        $('.requestData tbody').append(template);
        template.fadeIn(500);
        const startTime = new Date().getTime();

        $.ajax({
            url: '/fcgi-bin/server.jar',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({
                x: x,
                y: y,
                r: r
            }),
            success: function(data) {
                const endTime = new Date().getTime();
                const time = endTime - startTime;

                insertPoint(x, y, r, data.result);

                template.find('td').eq(3).replaceWith(data);
                template.find('td').eq(5).text(time + 'ms');
            },
            error: function() {
                template.find('td').eq(3).text('Ошибка').css('color', 'red');
                template.find('td').eq(5).text('Ошибка').css('color', 'red');
            }
        });
    });
});
