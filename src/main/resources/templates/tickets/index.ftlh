<#import '/spring.ftl' as spring>
<#import '../template.ftlh' as template/>

<@template.template 'My Tickets | BlaBlaFly'>
<table class="table table-stripped">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Flight Info</th>
        <th scope="col">Price</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <#list ticketsList as ticket>
        <tr>
            <th scope="row">${ticket_index + 1}</th>
            <td><a href="<@spring.url "/flights/${ticket.flightId}"/>">Preview</a></td>
            <td>${ticket.price} ${ticket.currency}</td>
            <td><a href="">Cancel</a></td>
        </tr>
    </#list>
    </tbody>
</table>
</@template.template>
