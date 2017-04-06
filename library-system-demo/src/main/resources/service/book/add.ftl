<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form  action="/book/addbook" method="post">
    <label> 书名: <input type="text" name="bookname"  /> </label>
    <label> 书分类 : <input type="text" name="classification"  /> </label>
    <label> 借出状态: <input type="text" name="statement"  /> </label>
    <label> 借出次数: <input type="text" name="number"  /> </label>
    <button type="submit" >增加书本</button>
</form>
</body>
</html>