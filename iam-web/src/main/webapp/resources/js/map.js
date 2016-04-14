var ContactPage = function () {

    var contentInfo = '<strong>Addr:</strong> Hokyoung 2F, 122-30, Samsung-dong, Gangnam-gu, Seoul, Korea<br>'
            + '<strong>E-mail:</strong> support@cloudine.co.kr<br>'
            + '<strong>Tel:</strong> <a href="tel:+827088063735">(+82) 070-8806-3735</a><br>'
            + '<strong>Fax:</strong> <a href="fax:+8225563402">(+82) 02-556-3402</a><br>'
            + '<strong>HomePage:</strong> http://www.cloudine.io<br>';

    return {

        //Basic Map
        initMap: function () {
            var map;
            $(document).ready(function () {
                map = new GMaps({
                    div: '#map',
                    scrollwheel: false,
                    lat: 37.510229,
                    lng: 127.052749,
                    enableNewStyle: true
                });

                var marker = map.addMarker({
                    lat: 37.510229,
                    lng: 127.052749,
                    title: 'Company, Inc.',
                    infoWindow: {
                        content: contentInfo
                    }
                });

                // 이벤트 먹이기 예제
                //google.maps.event.addListener(marker, 'click', function() {
                //    console.log(123123);
                //});

                setTimeout(function () {
                    google.maps.event.trigger(marker, 'click');
                }, 1000);
            });
        },

        //Panorama Map
        initPanorama: function () {
            var panorama;
            $(document).ready(function () {
                panorama = GMaps.createPanorama({
                    el: '#panorama',
                    lat: 37.510229,
                    lng: 127.052749
                });
            });
        }

    };
}();