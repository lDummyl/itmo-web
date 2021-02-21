<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Request</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<h2 class="hello-title">Please, enter your name.</h2>

<form action="/showData" method="POST">
    <div class="form-group">
        <div class="col-md-4">
            <textarea class="form-control" name="name">
            </textarea>
        </div>
    </div>
    <input type="submit" value="Search">
</form>

<script src="/js/main.js"></script>
</body>
</html>