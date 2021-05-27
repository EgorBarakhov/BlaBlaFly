<#macro flightForm action citiesList aircraftsList errors>
    <form action="${action}" method="post" modelattribute="flightForm">
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
            <input type="datetime-local" name="departureTimeUtc">
        </label>
        <label> Arrival Time:
            <input type="datetime-local" name="arrivalTimeUtc">
        </label>
        <label> Aircraft:
            <select name="aircraft">
                <option value=""></option>
                <#list aircraftsList as aircraft>
                    <option value="${aircraft.name}">${aircraft.name}</option>
                </#list>
            </select>
        </label>
        <input type="hidden" name="availablePlacesCount" value="12">
        <input type="submit" value="Create">
        <div>
            <#if errors??>
                <#list errors as error>
                    <li>${error.getDefaultMessage()}</li>
                </#list>
            </#if>
        </div>
    </form>
</#macro>
