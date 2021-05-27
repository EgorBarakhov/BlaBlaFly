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
<form action="/flights" method="get" modelattribute="flightSearchForm">
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
    <label> Departure Time:
        <input type="datetime-local" name="departureTime">
    </label>
    <input type="submit" value="Search">
</form>
<form action="/flights/new" method="get">
    <button type="submit">Create new Flight</button>
</form>
<table>
    <thead>
    <tr>
        <th>Departure City</th>
        <th>Arrival City</th>
        <th>Departure Time</th>
        <th>Arrival Time</th>
        <th>Flight Aircraft</th>
        <th>Pilot</th>
        <th>Available Places</th>
        <th>Ticket Price</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
        <#list flightsList as flight>
            <tr>
                <td>${flight.departureCity}</td>
                <td>${flight.arrivalCity}</td>
                <td>${flight.departureTime}</td>
                <td>${flight.arrivalTime}</td>
                <td>${flight.aircraft}</td>
                <td>${flight.pilot}</td>
                <td>${flight.availablePlacesCount}</td>
                <td></td>
                <td><a href="/flights/${flight.id}">Buy a Ticket</a></td>
            </tr>
        </#list>
    </tbody>
</table>
</body>
</html>