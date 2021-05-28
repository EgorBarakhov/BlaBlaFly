<#import '_form.ftlh' as form>
<#import '../template.ftlh' as template/>

<@template.template 'Edit Flight | BlaBlaFly'>
<h3>Edit Flight</h3>
<@form.flightForm action="/flights/${flight.id}/edit" citiesList=citiesList aircraftsList=aircraftsList errors=errors!/>
</@template.template>
