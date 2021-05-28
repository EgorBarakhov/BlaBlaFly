<#macro aircraftForm action errors>
    <form action="${action}" method="post" modelattribute="aircraftForm">
        <label>Name:
            <input type="text" name="name">
        </label>
        <label>Legacy Serial Number:
            <input type="text" name="legacySerialNumber">
        </label>
        <label>Capacity (persons):
            <input type="number" name="capacity">
        </label>
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
