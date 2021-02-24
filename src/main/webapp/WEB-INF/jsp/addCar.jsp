<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Car registration</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<body>
<h3>User ${name} is added!</h3>
<h3>${name}, please add your car.</h3>
</body>
<form action="/carSuccess" method="POST">
    <h4>Enter the brand name</h4>
    <div class="form-group">
        <div class="col-md-4">
            <textarea class="form-control" name="brandName">
            </textarea>
        </div>
    </div>
    <h4>Enter the model name</h4>
    <div class="form-group">
        <div class="col-md-4">
            <textarea class="form-control" name="brandModelName">
            </textarea>
        </div>
    </div>
    <h4>Enter the color</h4>
    <div class="form-group">
        <div class="col-md-4">
            <textarea class="form-control" name="color">
            </textarea>
        </div>
    </div>
    <input type="submit" value="Register car">
</form>
<br/>
<a href="/main">Back to main page</a>
<br/>
</body>
</html>