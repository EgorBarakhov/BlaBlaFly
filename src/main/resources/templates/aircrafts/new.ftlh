<#import '_form.ftlh' as form>
<#import '../template.ftlh' as template/>

<@template.template 'New Aircraft | BlaBlaFly'>
<h3>Create New Aircraft</h3>
<@form.aircraftForm action="/aircrafts/new" errors=errors! />
</@template.template>
