<#import '/spring.ftl' as spring>
<#import '../template.ftlh' as template/>

<@template.template 'Flight | BlaBlaFly'>
<div class="row justify-content-end">

    <div class="col-1">
        <form action="<@spring.url "/flights/${flight.id}/edit"/>" method="get">
            <button type="submit" class="btn btn-primary">Edit</button>
        </form>
    </div>
    <div class="col-1">
        <form action="<@spring.url "/flights/${flight.id}"/>" method="post">
            <button type="submit" class="btn btn-danger">Delete</button>
        </form>
    </div>
</div>
<dl>
    <dt>Departure Time: </dt>
    <dd>${flight.displayTime(flight.departureTime)}</dd>

    <dt>Departure City: </dt>
    <dd>${flight.departureCity}</dd>

    <dt>Arrival Time: </dt>
    <dd>${flight.displayTime(flight.arrivalTime)}</dd>

    <dt>Arrival City: </dt>
    <dd>${flight.arrivalCity}</dd>

    <dt>Available Places: </dt>
    <dd>${flight.availablePlacesCount}</dd>

    <dt>Ticket Price: </dt>
    <dd>${flight.ticketPrice} ${flight.ticketCurrency}</dd>

    <dt>Aircraft Name: </dt>
    <dd>${flight.aircraft}</dd>

    <dt>Pilot Username: </dt>
    <dd>${flight.pilot}</dd>

</dl>
<form action="<@spring.url "/flights/${flight.id}/ticket"/>" method="post">
    <button type="submit" class="btn btn-success">Buy a Ticket</button>
</form>

</@template.template>
