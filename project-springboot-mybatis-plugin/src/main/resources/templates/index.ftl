<!DOCTYPE html>
<html>
<title>My Table</title>
<meta charset="UTF-8">
<!--css-->
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css"></link>
<link rel="stylesheet" href="css/ui.jqgrid-bootstrap.css"
	type="text/css" />
<style type="text/css">
#mytable {
	margin: 0 auto;
	width: 1000px;
}
</style>
<!--js-->
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery.jqGrid.min.js"></script>
<!--和字体fonts,用于显示分页栏的提示信息-->
<script type="text/javascript" src="js/grid.locale-cn.js"></script>
<!--弹出层样式-->
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/my.js"></script>
</head>

<body>
	<!--显示用户数据的表格  -->
	<div id="mytable">
		<table id="jqGridList"></table>
		<div id="jqGridPager"></div>
	</div>

</body>

</html>