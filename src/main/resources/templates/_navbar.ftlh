<#import 'spring.ftl' as spring>

<#macro navbar>
    <header>
        <nav>
            <ul class="nav">
                <li class="nav-item"><a class="nav-link" href="<@spring.url "/flights"/>">Flights</a></li>
                <li class="nav-item"><a class="nav-link" href="<@spring.url "/aircrafts"/>">Aircrafts</a></li>
                <li class="nav-item"><a class="nav-link" href="<@spring.url "/tickets"/>">Tickets</a></li>
                <li class="nav-item"><a class="nav-link" href="<@spring.url "/logout"/>">Logout</a></li>
            </ul>
        </nav>
        <#if error??>
            <div>Error: ${error}</div>
        </#if>
    </header>
</#macro>
