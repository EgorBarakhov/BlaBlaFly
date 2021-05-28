<#macro template title>
    <#import '_head.ftlh' as head />
    <#import '_navbar.ftlh' as navbar />

    <!doctype html>
    <html lang="en">
    <head>
        <@head.head />
        <title>${title}</title>
    </head>
    <body>
        <@navbar.navbar />
        <div class="container container-fluid">
            <#nested>
        </div>
    </body>
    </html>
</#macro>
