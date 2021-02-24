<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<h2 class="hello-title">Hello! Please, enter your name.</h2>

<form action="/addCar" method="POST">
    <div class="form-group">
        <div class="col-md-4">
            <textarea class="form-control" name="name">
            </textarea>
        </div>
    </div>
    <input type="submit" value="Register">
</form>
<br/>
<a href="/main">Back to main page</a>
<br/>
<script src="/js/main.js"></script>
</body>
</html>