$(function() {
    showCityName($('#city-name')[0].innerText);

    function showCityName(cityName) {
        var apiKey = $('#api-key')[0].value;

        $.getJSON("https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + apiKey, function(data) {

            var weatherDescription = data["weather"][0]["description"];
            var temp = data["main"]["temp"];
            var pressure = data["main"]["pressure"];
            var windSpeed = data["wind"]["speed"];

            $(".weather-descr").html(weatherDescription);
            $(".temp").html((temp - 273.15).toFixed(1) + " °C");
            $(".pressure").html(pressure + " mBar");
            $(".wind-spd").html(windSpeed + " m/s");

        })
    }
})
