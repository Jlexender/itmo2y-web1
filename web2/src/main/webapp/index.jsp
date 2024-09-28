<!-- Updated `index.jsp` -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Index of Web1</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/static/css/normalize.css">
    <script src="<%= request.getContextPath() %>/static/js/jquery-3.7.1.min.js"></script>
</head>
<body>
<div class="container">
    <header>
        <a href="#" class="header-image-link">
            <img src="<%= request.getContextPath() %>/static/img/kombat.webp" alt="Kombat" class="header-image">
        </a>
        <div class="header-text">
            <h1>Веб-программирование 2</h1>
            <div class="credentials">
                <ul>
                    <li>Калиев Александр Дмитриевич</li>
                    <li>P3211</li>
                    <li>21054</li>
                </ul>
            </div>
        </div>
    </header>
    <main>
        <div class="plotData" id="draggableTable">
            <h1 style="color: lightblue">Майнинг машина</h1>
            <form method="GET" autocomplete="off">
                <label for="x">X:</label>
                <div id="x">
                    <label><input type="radio" name="x" value="-4" required>-4</label>
                    <label><input type="radio" name="x" value="-3" required>-3</label>
                    <label><input type="radio" name="x" value="-2" required>-2</label>
                    <label><input type="radio" name="x" value="-1" required>-1</label>
                    <label><input type="radio" name="x" value="0" required>0</label>
                    <label><input type="radio" name="x" value="1" required>1</label>
                    <label><input type="radio" name="x" value="2" required>2</label>
                    <label><input type="radio" name="x" value="3" required>3</label>
                    <label><input type="radio" name="x" value="4" required>4</label>
                </div>
                <label for="y">Y:
                    <input type="number" id="y" name="y" placeholder="-3..5" min="-3" max="5" step="0.1" required>
                </label>
                <label for="r">R:
                    <input type="number" id="r" name="r" placeholder="1..4" min="1" max="4" step="0.1" required>
                </label>
                <button id="submit">Отправить!</button>
            </form>

            <canvas id="canvas" width="500" height="500"></canvas>
        </div>

        <table class="requestData">
            <thead>
            <tr>
                <th>X</th>
                <th>Y</th>
                <th>R</th>
                <th>Результат</th>
                <th>Время отправки</th>
                <th>Время работы скрипта</th>
                <th>Время работы сервера</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
            <template>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </template>
        </table>

        <div class="toncoin">
            <h1 class="ton-price">TON Price прогноз</h1>
            <img src="<%= request.getContextPath() %>/static/img/price.jpeg" alt="TON Coin" class="ton-coin-img">
        </div>
    </main>
</div>

<video id="background-video" loop style="display:none; position: fixed; top: 0; left: 0; width: 100%; height: 100%; z-index: -1;">
    <source src="<%= request.getContextPath() %>/static/vid/nazar.mp4" type="video/mp4">
    Your browser does not support the video tag.
</video>

<div class="tg-logo" id="tg-logo">
    <img src="<%= request.getContextPath() %>/static/img/tg-logo.webp" alt="TG Logo">
</div>

<footer>
    <img src="<%= request.getContextPath() %>/static/img/vt.png" alt="IFMO LOGO" style="width: 100px; height: 100px" id="ifmo">
    <p>© 2024 Empire of Unstoppable Innovations.
        All rights reserved, including but not limited to the rights of unparalleled creativity,
        extraordinary concepts, and infinite brilliance. Any unauthorized reproduction
        shall be met with epic failure!</p>
    <div class="nyan-cat">
        <img src="<%= request.getContextPath() %>/static/img/nyan.gif" alt="Nyan Cat">
    </div>
</footer>

<script type="text/javascript" src="<%= request.getContextPath() %>/static/js/draw-plot.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/static/js/fetch-data.js"></script>
<script type="text/javascript" src="<%= request.getContextPath() %>/static/js/table-drag.js"></script>
<script>
    $(document).ready(function() {
        const $backgroundVideo = $('#background-video');
        const $tgLogo = $('#tg-logo');

        $backgroundVideo.hide();

        $(document).mousemove(function(e) {
            $tgLogo.css({
                left: e.pageX - 10,
                top: e.pageY - 10
            }).show();
        });

        $(document).mouseleave(function() {
            $tgLogo.hide();
        });

        $('.header-image-link').click(function(event) {
            event.preventDefault();

            if ($backgroundVideo.is(':hidden')) {
                $backgroundVideo.show();
                $backgroundVideo[0].play();
            }
        });
    });
</script>
<script>
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
</script>
</body>
</html>
