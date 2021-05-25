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
    <table>
        <thead>
            <tr>
                <th>Id</th>
                <th>Username</th>
                <th>Roles</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <#list allUsers as account>
                <tr>
                    <td>${account.id}</td>
                    <td>${account.username}</td>
                    <td>
                        <#list account.roles as role>
                            <span>${role.name}; </span>
                        </#list>
                    </td>
                    <td>
                        <form action="/admin" method="post" modelattribute="deleteUser">
                            <input type="hidden" name="userId" value="${account.id}">
                            <input type="hidden" name="action" value="delete">
                            <input type="submit" value="Delete">
                        </form>
                    </td>
                </tr>
            </#list>
        </tbody>
    </table>
</body>
</html>