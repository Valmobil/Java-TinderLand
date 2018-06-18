<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User profile page</title>
</head>
<body>
<br>
<h2>${name}</h2>
<br>
<img src="${pictureLink}" alt="Photo" width="620" height="420">
<br>
<br>
<form action="/users" method="post">
    <input name="choice" type="submit"  value="Like">    <input name="choice" type="submit" value="NotToday">
    <input name="id" value="${id}" hidden>
</form>
</body>
</html>