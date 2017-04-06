<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>根据adminid相关联数据</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js" type="text/javascript"></script>
</head>
<body>

<script>
function deleteBook(bookid){
    $.ajax({
        url:'deletebook',
        data:{'bookid':bookid},
        dataType:'JSON',
        type:'post',
        success:function(data){
        var obj = JSON.parse(data);
            if(obj.msg == 'ok'){
                 $("tr[id="+bookid+"]").remove();
            }else{
                alert('失败');
            }
        }
    });

}
</script>
<a href="/book/addbook" >增加书本</a>
<table class="table table-bordered">

    <thead>
    <tr>

        <th>bookid</th>
        <th>bookname</th>
        <th>classification</th>
        <th>statement</th>
        <th>number</th>
        <th>delete</th>
        <th>update</th>

    </tr>
    </thead>
    <tbody>
    <#list books as book>
        <tr id="<#if (book.bookid)??>${(book.bookid)}<#else>""</#if>">
            <td><#if (book.bookid)??>${(book.bookid)}<#else>""</#if></td>
            <td><#if (book.bookname)??>${(book.bookname)}<#else>""</#if></td>
            <td><#if (book.classification)??>${(book.classification)}<#else>""</#if></td>
            <td><#if (book.statement)??>${(book.statement)}<#else>""</#if></td>
            <td><#if (book.number)??>${(book.number)}<#else>""</#if></td>
            <td><a onclick="deleteBook(<#if (book.bookid)??>${(book.bookid)}<#else>0</#if>)">删除书本信息</a></td>
            <td><a href="updatebook/<#if (book.bookid)??>${(book.bookid)}<#else>0</#if>">更新书本信息</a></td>
    </#list>
    </tbody>
</table>

</body>
</html>