const canvas = document.getElementById('canvas');
const ctx = canvas.getContext('2d');

const center = {x: 0, y: 0};
ctx.clearRect(0, 0, canvas.width, canvas.height);
ctx.translate(canvas.width / 2, canvas.height / 2);
ctx.scale(1, -1);

const radius = 175;

const drawAxes = () => {
    ctx.beginPath();
    ctx.moveTo(-canvas.width / 2, 0);
    ctx.lineTo(canvas.width / 2, 0);
    ctx.moveTo(0, -canvas.height / 2);
    ctx.lineTo(0, canvas.height / 2);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(canvas.width / 2, 0);
    ctx.lineTo(canvas.width / 2 - 10, 5);
    ctx.moveTo(canvas.width / 2, 0);
    ctx.lineTo(canvas.width / 2 - 10, -5);
    ctx.moveTo(0, canvas.height / 2);
    ctx.lineTo(5, canvas.height / 2 - 10);
    ctx.moveTo(0, canvas.height / 2);
    ctx.lineTo(-5, canvas.height / 2 - 10);
    ctx.stroke();

    function drawTicks(x, y, x1, y1, label) {
        ctx.beginPath();
        ctx.moveTo(x, y);
        ctx.lineTo(x1, y1);
        ctx.stroke();

        ctx.save();
        ctx.scale(1, -1);
        ctx.font = '16px sans-serif';
        ctx.fillStyle = 'gray';
        ctx.fillText(label, x1, -y1 + 20);
        ctx.restore();
    }

    drawTicks(center.x + radius, center.y, center.x + radius, center.y - 10, 'R');
    drawTicks(center.x - radius, center.y, center.x - radius, center.y - 10, '-R');
    drawTicks(center.x, center.y + radius, center.x + 10, center.y + radius, 'R');
    drawTicks(center.x, center.y - radius, center.x + 10, center.y - radius, '-R');

    drawTicks(center.x + radius / 2, center.y, center.x + radius / 2, center.y - 10, 'R/2');
    drawTicks(center.x - radius / 2, center.y, center.x - radius / 2, center.y - 10, '-R/2');
    drawTicks(center.x, center.y + radius / 2, center.x + 10, center.y + radius / 2, 'R/2');
    drawTicks(center.x, center.y - radius / 2, center.x + 10, center.y - radius / 2, '-R/2');
}

const drawBroken = (data) => {
    ctx.beginPath();
    ctx.moveTo(data[0].x, data[0].y);
    for (let i = 1; i < data.length; i++) {
        ctx.lineTo(data[i].x, data[i].y);
    }
    ctx.fill();
}

ctx.strokeStyle = 'white';
ctx.fillStyle = 'rgba(158,169,255,0.23)';
drawAxes();
drawBroken([
    {x: -radius, y: 0},
    {x: radius/2, y: 0},
    {x: radius/2, y: -radius},
    {x: 0, y: -radius},
    {x: 0, y: -radius/2},
    {x: -radius, y: 0}
]);

ctx.beginPath();
ctx.arc(0, 0, radius/2, 0, Math.PI/2);
ctx.lineTo(0, 0);
ctx.fill();
