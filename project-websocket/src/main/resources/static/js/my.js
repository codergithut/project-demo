$(function() {

	var jqGrid = $("#jqGridList");
	jqGrid.jqGrid({
		caption: "用户管理",
		url: "queryAll",
		mtype: "GET",
		styleUI: 'Bootstrap', //设置jqgrid的全局样式为bootstrap样式
		datatype: "json",
		colModel: [{
			label: '用户ID',
			name: 'id',
			width: 60,
			key: true
		}, {
			label: '用户名称',
			name: 'name',
			width: 100
		}],

		viewrecords: true,
		height: 385,
		rowNum: 10, //每页显示记录数
		rowList: [10, 30, 50], //用于改变显示行数的下拉列表框的元素数组
		rownumbers: true, //添加左侧行号
		rownumWidth: 25,
		autowidth: true,
		multiselect: true,
		pager: "#jqGridPager",
		/*设置分页显示的导航条信息*/
		jsonReader: {
			root: "page.list",
			page: "page.pageNum",
			total: "page.pages",
			records: "page.pageSize"
		},
		/*像后台请求的参数信息*/
		prmNames: {
			page: "page",
			rows: "limit",
			order: "order"
		},
		gridComplete: function() {
			//隐藏grid底部滚动条
			$("#jqGridList").closest(".ui-jqgrid-bdiv").css({
				"overflow-x": "hidden"
			});
		}
	});

});