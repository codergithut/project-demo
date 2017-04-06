<html>
<head>
    <meta charset="utf-8">
    <title>Admin登录系统</title>
    <link rel="stylesheet" href="https://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        body{
            margin-left:auto;
            margin-right:auto;
            margin-TOP:100PX;
            background-color:#e0ffff;
            width:60em;

        }
    </style>
</head>
<body>
<h1 class="text-center">徐州工程学院图书馆</h1>

<form class="form-horizontal" role="form" method="post" action="/admin/check">
    <div class="form-group">
        <label for="name" class="col-sm-2 control-label">账号</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="name"
                   placeholder="请输入账号">
        </div>
    </div>
    <div class="form-group">
        <label for="password" class="col-sm-2 control-label">密码</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" name="password"
                   placeholder="请输入密码">
        </div>
    </div>

    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-primary btn-lg">登录</button>
        </div>
    </div>
</form>

</body>
</html>