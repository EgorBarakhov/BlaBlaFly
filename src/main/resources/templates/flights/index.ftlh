<#import '/spring.ftl' as spring>
<#import '../template.ftlh' as template/>

<@template.template 'Flights | BlaBlaFly'>
<div class="row justify-content-start">
<div class="col-10">
    <form action="<@spring.url "/flights"/>" method="get" modelattribute="flightSearchForm">
        <label> Departure City:
            <select class="form-select" name="departureCity">
                <option value=""></option>
                <#list citiesList as city>
                    <option value=${city.name}>${city.name}</option>
                </#list>
            </select>
        </label>
        <label> Arrival City:
            <select class="form-select" name="arrivalCity">
                <option value=""></option>
                <#list citiesList as city>
                    <option value=${city.name}>${city.name}</option>
                </#list>
            </select>
        </label>
        <label> Departure Time:
            <input type="datetime-local" name="departureTime">
        </label>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary">Search</button>
    </form>
</div>
<div class="col-2">
    <form action="<@spring.url "/flights/new"/>" method="get">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <button type="submit" class="btn btn-primary">Create new Flight</button>
    </form>
</div>
<table class="table table-stripped">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Departure City</th>
        <th scope="col">Arrival City</th>
        <th scope="col">Departure Time</th>
        <th scope="col">Arrival Time</th>
        <th scope="col">Flight Aircraft</th>
        <th scope="col">Pilot</th>
        <th scope="col">Available Places</th>
        <th scope="col">Ticket Price</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
        <#list flightsList as flight>
            <tr>
                <th scope="row">${flight_index + 1}</th>
                <td>${flight.departureCity}</td>
                <td>${flight.arrivalCity}</td>
                <td>${flight.displayTime(flight.departureTime)}</td>
                <td>${flight.displayTime(flight.arrivalTime)}</td>
                <td>${flight.aircraft}</td>
                <td>${flight.pilot}</td>
                <td>${flight.availablePlacesCount}</td>
                <td>${flight.ticketPrice} ${flight.ticketCurrency}</td>
                <td><a href="<@spring.url "/flights/${flight.id}"/>">Buy a Ticket</a></td>
            </tr>
        </#list>
    </tbody>
</table>
</@template.template>
