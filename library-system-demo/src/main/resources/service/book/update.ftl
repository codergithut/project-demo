<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>根据adminid相关联数据</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<form  action="/book/updatebook" method="post">
    <label> 书编号 : <input type="text" name="bookid" value="<#if (book.bookid)??>${(book.bookid)}<#else>0</#if>" /> </label>
    <label> 书名: <input type="text" name="bookname"  value="<#if (book.bookname)??>${(book.bookname)}<#else>0</#if>"/> </label>
    <label> 书分类 : <input type="text" name="classification"  value="<#if (book.classification)??>${(book.classification)}<#else>0</#if>"/> </label>
    <label> 借出状态: <input type="text" name="statement"  value="<#if (book.statement)??>${(book.statement)}<#else>0</#if>"/> </label>
    <label> 借出次数: <input type="text" name="number"  value="<#if (book.number)??>${(book.number)}<#else>0</#if>"/> </label>
    <button type="submit" >更新书本信息</button>
</form>
</body>
</html>