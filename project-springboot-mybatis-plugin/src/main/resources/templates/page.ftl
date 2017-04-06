<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>Bootstrap 实例 - 默认的分页</title>
    <link rel="stylesheet" href="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>

<ul class="pagination">
    <table>

        <thead>
        <th>用户ID</th>
        <th>用户姓名</th>
        </thead>
        <tbody>
        <#list (pageinfo.list) as user>
        <tr>
            <td><#if (user.id)??>${(user.id)}<#else>""</#if></td>
            <td><#if (user.name)??>${(user.name)}<#else>""</#if></td>
        </tr>
        </#list>
        </tbody>
    </table>

    <li><a href="">&laquo;</a></li>
    <#list 1..pageinfo.pages as t>
        <li><a href = "${t}">${t}</a></li>
    </#list>
    <li><a href="">&raquo;</a></li>
</ul>

</body>
</html>