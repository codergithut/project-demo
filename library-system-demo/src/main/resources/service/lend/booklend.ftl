<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <head>
        <meta charset="utf-8">
        <title>根据adminid相关联数据</title>
        <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.2.6/jquery.min.js" type="text/javascript"></script>
    </head>

<body>
<script>
    function lendBook(bookid){
        debugger;
        var adminid = $("#adminid").val();
        var readerid = $("#readerid").val();
        alert(adminid);
        alert(readerid);
        $.ajax({
            url:'lendBook',
            data:{'bookid':bookid,'adminid':adminid,'readerid':readerid},
            dataType:'JSON',
            type:'get',
            success:function(data){
                var obj = JSON.parse(data);
                if(obj.msg == 'ok'){
                    //todo 需要动态添加一个tr
                    //$("tr[id="+bookid+"]").remove();
                }else{
                    alert('失败');
                }
            }
        });

    }
</script>
</body>
<input value="3" type="hidden", id="adminid"/>

<form>
    <input  id="readerid" type="hidden" value="<#if (reader.readerid)??>${(reader.readerid)}<#else>""</#if>"/>
    <label> 用户名: <input type="text" name="username"  value = "<#if (reader.username)??>${(reader.username)}<#else>""</#if>"/> </label>
    <label> 用户状态 : <input type="text" name="password" value="<#if (reader.password)??>${(reader.password)}<#else>""</#if>"  /> </label>
</form>


<table class="table table-bordered">
    <thead>
    <tr>
        <th>bookid</th>
        <th>bookname</th>
        <th>classification</th>
        <th>statement</th>
        <th>number</th>
        <th>give</th>
    </tr>
    </thead>
    <tbody>
    <#if result != "false">
    <form>
    <input  id="readerid" type="hidden" value="<#if (reader.readerid)??>${(reader.readerid)}<#else>""</#if>"/>
        <label> 图书编号: <input type="text" name="username"  value = "<#if (reader.username)??>${(reader.username)}<#else>""</#if>"/> </label>
    </form>

    <button onclick="lendBook(33)">借阅图书</button>

    <#else>借阅图书已经达到上限了</#if>
    <#list lendBooks as book>
        <tr id="<#if (book.bookid)??>${(book.bookid)}<#else>""</#if>">
        <td><#if (book.bookid)??>${(book.bookid)}<#else>""</#if></td>
        <td><#if (book.bookname)??>${(book.bookname)}<#else>""</#if></td>
        <td><#if (book.classification)??>${(book.classification)}<#else>""</#if></td>
        <td><#if (book.statement)??>${(book.statement)}<#else>""</#if></td>
        <td><#if (book.number)??>${(book.number)}<#else>""</#if></td>
        <td><a onclick="giveBook/<#if (book.bookid)??>${(book.bookid)}<#else>0</#if>">归还图书</a></td>
        </tr>
    </#list>
    </tbody>
</table>


</html>