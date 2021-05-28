<#import '_form.ftlh' as form>
<#import '../template.ftlh' as template/>

<@template.template 'New Flight | BlaBlaFly'>
<h3>Create New Flight</h3>
<@form.flightForm action="/flights/new" citiesList=citiesList aircraftsList=aircraftsList errors=errors!/>
</@template.template>
