<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Admin View | BlaBlaFly</title>
</head>
<body>
<#list allUsers as account>
    <div>Id: ${account.id}</div>
    <div>Username: ${account.username}</div>
    <#list account.roles as role>
        <div>Role ${role_index} : ${role.name}</div>
    </#list>
    <div>
        <form action="/admin" method="post" modelattribute="deleteUser">
            <input type="hidden" name="userId" value="${account.id}">
            <input type="hidden" name="action" value="delete">
            <input type="submit" value="Delete">
        </form>
    </div>
</#list>
</body>
</html>