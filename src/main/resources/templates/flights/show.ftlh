<#import '/spring.ftl' as spring>
<#import '../template.ftlh' as template/>

<@template.template 'Flight | BlaBlaFly'>
<div class="row justify-content-end">

    <div class="col-1">
        <form action="<@spring.url "/flights/${flight.id}/edit"/>" method="get">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <button type="submit" class="btn btn-primary">Edit</button>
        </form>
    </div>
    <div class="col-1">
        <form action="<@spring.url "/flights/${flight.id}"/>" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <button type="submit" class="btn btn-danger">Delete</button>
        </form>
    </div>
</div>
<div class="row justify-content-start">
    <div class="col-6">
        <dl>
            <dt>Departure Time: </dt>
            <dd>${flight.displayTime(flight.departureTime)}</dd>

            <dt>Departure City: </dt>
            <dd>${flight.departureCity}</dd>

            <dt>Arrival Time: </dt>
            <dd>${flight.displayTime(flight.arrivalTime)}</dd>

            <dt>Arrival City: </dt>
            <dd id="city-name">${flight.arrivalCity}</dd>

            <dt>Available Places: </dt>
            <dd>${flight.availablePlacesCount}</dd>

            <dt>Ticket Price: </dt>
            <dd>${flight.ticketPrice} ${flight.ticketCurrency}</dd>

            <dt>Aircraft Name: </dt>
            <dd>${flight.aircraft}</dd>

            <dt>Pilot Username: </dt>
            <dd>${flight.pilot}</dd>

        </dl>
    </div>
    <div class="col-6">
        <input type="hidden" id="api-key" value="${apiKey!}">
        <h4>Weather for ${flight.arrivalCity}</h4>

        <div id="city-forecast">
            <p class="weather-descr col-xs-12"></p>
            <p class="temp col-xs-12"></p>
            <p class="pressure col-xs-12"></p>
            <p class="wind-spd col-xs-12"></p>
        </div>
    </div>
</div>
<form action="<@spring.url "/flights/${flight.id}/ticket"/>" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <button type="submit" class="btn btn-success">Buy a Ticket</button>
</form>

</@template.template>
