<#import 'spring.ftl' as spring>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>SignUp | BlaBlaFly</title>
</head>
<body>
<h3>Signup page</h3>
<form action="<@spring.url "/signup"/>" method="post" modelattribute="userForm">
    <h4>Create an account on BlaBlaFly</h4>
    <input type="text" name="username" placeholder="Username" autofocus="autofocus">
    <input type="password" name="password" placeholder="Password">
    <input type="password" name="confirmPassword" placeholder="Confirm password">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <button type="submit" class="btn btn-primary">Ready to Fly!</button>
    <div>
        <#if errors??>
            <#list errors as error>
                <li>${error.getDefaultMessage()}</li>
            </#list>
        </#if>
    </div>
</form>
<a href="<@spring.url "/login"/>">I have an account</a>
</body>
</html>
