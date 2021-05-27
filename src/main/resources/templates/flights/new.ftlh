<#import '_form.ftlh' as form>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>New Flight | BlaBlaFly</title>
</head>
<body>
<h3>Create New Flight</h3>
<@form.flightForm action="/flights/new" citiesList=citiesList aircraftsList=aircraftsList errors=errors!/>
</body>
</html>