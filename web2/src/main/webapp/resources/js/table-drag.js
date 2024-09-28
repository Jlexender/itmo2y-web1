document.addEventListener('DOMContentLoaded', function () {
    const table = document.getElementById('draggableTable');
    let isDragging = false;
    let offsetX, offsetY;

    table.addEventListener('mousedown', function (e) {
        // Начинаем перетаскивание
        isDragging = true;
        offsetX = e.clientX - table.getBoundingClientRect().left;
        offsetY = e.clientY - table.getBoundingClientRect().top;
        table.style.cursor = 'grabbing'; // Изменяем курсор при захвате
    });

    document.addEventListener('mousemove', function (e) {
        if (isDragging) {
            // Рассчитываем новое положение таблицы
            const x = e.clientX - offsetX;
            const y = e.clientY - offsetY;

            table.style.left = `${x}px`;
            table.style.top = `${y}px`;
        }
    });

    document.addEventListener('mouseup', function () {
        // Останавливаем перетаскивание
        isDragging = false;
        table.style.cursor = 'move'; // Возвращаем курсор
    });
});
