// Updated `fetch-data.js`
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

        const x = parseFloat($('input[name="x"]:checked').val());
        const y = parseFloat($('#y').val());
        const r = parseFloat($('#r').val());

        template.find('td').eq(0).text(x);
        template.find('td').eq(1).text(y);
        template.find('td').eq(2).text(r);
        template.find('td').eq(3).text('Вычисляем...');
        template.find('td').eq(4).text(new Date().toLocaleString());
        template.find('td').eq(5).text('Вычисляем...');
        template.find('td').eq(6).text('Вычисляем...');
        template.hide();
        $('.requestData tbody').prepend(template);
        template.fadeIn(500);
        const startTime = new Date().getTime();

        $.ajax({
            url: '/query',
            type: 'GET',
            data: {
                x: x,
                y: y,
                r: r
            },
            success: function(data) {
                const time = new Date().getTime() - startTime;

                const result = data.result;
                const executionTime = data.executionTime;

                insertPoint(x, y, r, result);

                template.find('td').eq(3).text(result);
                template.find('td').eq(5).text(time + 'ms');
                template.find('td').eq(6).text(executionTime + 'ns');
            },
            error: function() {
                template.find('td').eq(3).text('Ошибка').css('color', 'red');
                template.find('td').eq(5).text('Ошибка').css('color', 'red');
                template.find('td').eq(6).text('Ошибка').css('color', 'red');
            }
        });
    });
});
