<#import '/spring.ftl' as spring>
<#import '../template.ftlh' as template/>

<@template.template 'My Aircrafts | BlaBlaFly'>
<div class="row justify-content-start">
    <div class="col-9">
        <table class="table table-stripped">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Name</th>
                <th scope="col">Legacy Serial Number</th>
                <th scope="col">Capacity (persons)</th>
            </tr>
            </thead>
            <tbody>
            <#list aircraftsList as aircraft>
                <tr>
                    <th scope="row">${aircraft_index + 1}</th>
                    <td>${aircraft.name}</td>
                    <td>${aircraft.legacySerialNumber}</td>
                    <td>${aircraft.capacity}</td>
                </tr>
            </#list>
            </tbody>
        </table>
    </div>
    <div class="col-3">
        <form action="<@spring.url "/aircrafts/new" />" method="get">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <button type="submit" class="btn btn-primary">Create new Aircraft</button>
        </form>
    </div>
</div>
</@template.template>
