document.getElementById('r').addEventListener('input', function(event) {
    const newR = parseFloat(event.target.value);
    refreshLabels(newR);
    refreshPoints(newR); // Update points based on new R value
    drawPlot();
});

function refreshLabels(R) {
    labels[1].text = R / 2;
    labels[2].text = -R;
    labels[3].text = -R / 2;
    labels[4].text = -R;
    labels[6].text = R / 2;
}

function refreshPoints(newR) {
    for (let i = 0; i < points.length; i++) {
        points[i].x = canvas.width / 2 + points[i].realX * radius / newR;
        points[i].y = canvas.height / 2 - points[i].realY * radius / newR;
    }
}
