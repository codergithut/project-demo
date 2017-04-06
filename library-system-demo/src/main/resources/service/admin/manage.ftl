<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>图书馆系统</title>
    <link rel="stylesheet" href="https://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        body{
            margin-left:auto;
            margin-right:auto;
            margin-TOP:100PX;
            width:100em;
            background-color:#e0ffff;
            font-size:18px;

        }
        p {font-size:40px;}

        a {font-size:20px;}

        div.address
        {
            border:2px solid #a1a1a1;
            padding:10px 40px;
            background:#dddddd;
            width:300px;
            border-radius:25px;
        }

        p.lead {font-size:40px;
            text-align:center;}

    </style>
</head>
<body>
<p class="text-center" ><b>欢迎来到徐州工程学院图书馆系统</b></p><br>
<nav class="navbar navbar-default" role="navigation">


    <div class="navbar-header">

        <a class="navbar-brand" href="#">快速入口</a>

    </div>
    <div>
        <ul class="nav navbar-nav">
            <li ><a href="http://baidu.com">借书系统</a></li>
            <li><a href="http://baidu.com">还书系统</a></li>
            <li class="dropdown">
                <a href="/book/allbook" >
                    图书信息管理
                </a>

            </li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    管理员信息管理
                    <b class="caret"></b>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="#">查询管理员信息</a></li>
                    <li><a href="#">修改管理员信息</a></li>
                    <li><a href="#">增加管理员信息</a></li>
                    <li><a href="#">删除管理员信息</a></li>
                </ul>
            </li>

            <li class="dropdown">
                <a href="/rest/getreaderall" >
                    读者信息管理
                </a>

            </li>
            <li><a href="/zhou/haha">关于我们</a></li>
        </ul>
    </div>
    <div>
        <form class="navbar-form navbar-right" role="search" action="/rest/search" method="post">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Search">
            </div>
            <button type="submit" class="btn btn-default">搜索</button>
        </form>
    </div>
</nav>
<div class="container">
    <div class="row">
        <div class="span12">
            <p class="lead">3月值班表</p>
        </div>
    </div>

    <div class="row">
        <div class="span12">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>编号 ID</th>
                    <th>姓名 DESC</th>
                    <th>联系方式 STATUS</th>
                    <th>值班时间 ACTION</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1</td>
                    <td>RPi.PCF8574.IO0</td>
                    <td>ON</td>
                    <td>ACTION</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>RPi.PCF8574.IO1</td>
                    <td>OFF</td>
                    <td>ACTION</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

    <div class="row">
        <div class="span12">
            <p class="text-right">如果有问题请电话联系</p>
            </p>
        </div>
    </div>

    <div class="row">
        <div class="span12">
            <div class="col-md-3">
                <a class="one" href="http://wwww.baidu.com" target="_blank">徐州工程学院学生部：点击进入</a><br>
                <a class="two" href="http://wwww.baidu.com">徐州工程学院文化部：点击进入</a>
            </div>
            <div class="col-md-9">
                <div class=address >
                    <strong>地址：</strong><br>
                    徐州新区************<br>
                    <abbr title="Phone">咨询电话:</abbr> (123) 456-7890
                    </address>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>