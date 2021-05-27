<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Tickets | BlaBlaFly</title>
</head>
<body>
<table>
    <thead>
    <tr>
        <th>Flight Info</th>
        <th>Price</th>
        <th></th>
    </tr>
    </thead>
    <tbody>
    <#list ticketsList as ticket>
        <tr>
            <td><a href="/flights/${ticket.flightId}">Preview</a></td>
            <td>${ticket.price} ${ticket.currency}</td>
            <td><a href="">Cancel</a></td>
        </tr>
    </#list>
    </tbody>
</table>
</body>
</html>