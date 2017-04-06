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
<a href="/rest/addreader" >增加客户</a>
<table class="table table-bordered">
    <caption>基本的表格布局</caption>
    <thead>
    <tr>

        <th>readerid</th>
        <th>username</th>
        <th>password</th>
        <th>delete</th>
        <th>update</th>

    </tr>
    </thead>
    <tbody>

    <#list readers as reader>
        <tr>
            <td><#if (reader.readerid)??>${(reader.readerid)}<#else>0</#if></td>
            <td><#if (reader.username)??>${(reader.username)}<#else>""</#if></td>
            <td><#if (reader.password)??>${(reader.password)}<#else>""</#if></td>
            <td><a href="deleteReader/<#if (reader.readerid)??>${(reader.readerid)}<#else>0</#if>">删除用户信息</a></td>
            <td><a href="updateReader/<#if (reader.readerid)??>${(reader.readerid)}<#else>0</#if>">更新用户信息</a></td>
        </tr>
    </#list>
    </tbody>
</table>

</body>
</html>