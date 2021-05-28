<#import 'spring.ftl' as spring>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Welcome! | BlaBlaFly</title>
</head>
<body>
<h3>Index page</h3>
<a href="<@spring.url "/login"/>">Log In</a>
<a href="<@spring.url "/signup"/>">Sign Up</a>
</body>
</html>
