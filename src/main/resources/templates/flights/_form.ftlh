<#import '/spring.ftl' as spring>

<#macro flightForm action citiesList aircraftsList errors>
    <form action="${action}" method="post" modelattribute="flightForm">
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
            <input type="datetime-local" name="departureTimeUtc">
        </label>
        <label> Arrival Time:
            <input type="datetime-local" name="arrivalTimeUtc">
        </label>
        <label> Aircraft:
            <select class="form-select" name="aircraft">
                <option value=""></option>
                <#list aircraftsList as aircraft>
                    <option value="${aircraft.name}">${aircraft.name}</option>
                </#list>
            </select>
        </label>
        <label>Price:
            <input type="number" name="ticketPrice">
        </label>
        <label> Currency:
            <select class="form-select" name="ticketCurrency">
                <option value=""></option>
                <option value="USD">USD</option>
                <option value="RUB">RUB</option>
            </select>
        </label>
        <input type="hidden" name="availablePlacesCount" value="0">
        <button type="submit" class="btn btn-primary">Save</button>
        <div>
            <#if errors??>
                <#list errors as error>
                    <li>${error.getDefaultMessage()}</li>
                </#list>
            </#if>
        </div>
    </form>
</#macro>
