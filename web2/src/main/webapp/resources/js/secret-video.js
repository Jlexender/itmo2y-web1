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
