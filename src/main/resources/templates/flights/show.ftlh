<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Flight | BlaBlaFly</title>
</head>
<body>
<form action="/flights/${flight.id}/edit" method="get">
    <button type="submit">Edit</button>
</form>
<form action="/flights/${flight.id}" method="post">
    <button type="submit">Delete</button>
</form>
<dl>
    <dt>Departure Time: </dt>
    <dd>${flight.departureTime}</dd>

    <dt>Departure City: </dt>
    <dd>${flight.departureCity}</dd>

    <dt>Arrival Time: </dt>
    <dd>${flight.arrivalTime}</dd>

    <dt>Arrival City: </dt>
    <dd>${flight.arrivalCity}</dd>

    <dt>Available Places: </dt>
    <dd>${flight.availablePlacesCount}</dd>

    <dt>Aircraft Name: </dt>
    <dd>${flight.aircraft}</dd>

    <dt>Pilot Username: </dt>
    <dd>${flight.pilot}</dd>
</dl>
</body>
</html>