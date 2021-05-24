<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Flights | BlaBlaFly</title>
</head>
<body>
<form action="/flights" method="get" modelattribute="flightForm">
    <label> Departure City:
        <select name="departureCity">
            <option value=""></option>
            <#list citiesList as city>
                <option value=${city.name}>${city.name}</option>
            </#list>
        </select>
    </label>
    <label> Arrival City:
        <select name="arrivalCity">
            <option value=""></option>
            <#list citiesList as city>
                <option value=${city.name}>${city.name}</option>
            </#list>
        </select>
    </label>
    <input type="submit" value="Search">
</form>
</body>
</html>