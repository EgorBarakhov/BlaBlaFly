<#import 'spring.ftl' as spring>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>LogIn | BlaBlaFly</title>
</head>
<body>
<h3>Login page</h3>
<form action="<@spring.url "/perform-login"/>" method="post" modelattribute="loginForm">
    <h4>Log In</h4>
    <input type="text" name="username" placeholder="Username" autofocus="autofocus">
    <input type="password" name="password" placeholder="Password">
    <#if error??>
        <div>${error}</div>
    </#if>
    <button type="submit" class="btn btn-primary">Fly!</button>
</form>
</body>
</html>
