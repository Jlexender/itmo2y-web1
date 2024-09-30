$(document).ready(function() {
    const $plotData = $('.plotData'); // Получаем элемент .plotData
    let isDragging = false;
    let offsetX, offsetY;

    // Начало перетаскивания
    $plotData.on('mousedown', function(e) {
        isDragging = true;
        offsetX = e.clientX - $plotData.offset().left;
        offsetY = e.clientY - $plotData.offset().top;
        $plotData.css('cursor', 'grabbing'); // Изменение курсора на перетаскивание
    });

    // Движение мыши
    $(document).on('mousemove', function(e) {
        if (isDragging) {
            const x = e.clientX - offsetX;
            const y = e.clientY - offsetY;

            $plotData.css({
                position: 'absolute',
                left: `${x}px`,
                top: `${y}px`
            });
        }
    });

    // Остановка перетаскивания
    $(document).on('mouseup', function() {
        if (isDragging) {
            isDragging = false;
            $plotData.css('cursor', 'default'); // Возвращаем курсор
        }
    });
});
