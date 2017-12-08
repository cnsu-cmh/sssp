<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>菜单按钮页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%@ include file="/WEB-INF/common.jsp"%>

<!-- jqgrid-->
<link href="${path }/resources/css/plugins/jqgrid/ui.jqgrid.css?0820" rel="stylesheet">

<link href="${path }/resources/css/animate.css" rel="stylesheet">
<link href="${path }/resources/css/style.css?v=4.1.0" rel="stylesheet">

</head>
<body class="gray-bg">
	<input type="hidden" id="hide_menuId" name = "menuId" value="${menuId }"/>
	<input type="hidden" id="hide_menuName" name = "menuName" value="${menuName }"/>
	<div class="wrapper wrapper-content  animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox ">
					<div class="ibox-content">
						<div class="jqGrid_wrapper">
							<table id="table_menu_operation"></table>
							<div id="pager_menu_operation"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Peity -->
	<script src="${path }/resources/js/plugins/peity/jquery.peity.min.js"></script>

	<!-- jqGrid -->
	<script src="${path }/resources/js/plugins/jqgrid/i18n/grid.locale-cn.js?082"></script>
	<script src="${path }/resources/js/plugins/jqgrid/jquery.jqGrid.min.js?0820"></script>
	
	<!-- layer javascript -->
    <script src="${path }/resources/js/plugins/layer/layer.min.js"></script>
    
	<!-- 自定义js -->
	<script src="${path }/resources/js/content.js?v=1.0.0"></script>
	
	<script type="text/javascript">
	
	$(function() {
		mo_init();
	});		
		
	var mo_init = function(){
		var mo_table = new mo_TableInit();
	    mo_table.Init();
	    var mo_btn = new mo_ButtonInit();
	    mo_btn.Init();
	};
	
	var mo_TableInit = function () {
		var mo_oTable = new Object();
		mo_oTable.Init = function () {
			$.jgrid.defaults.styleUI = 'Bootstrap';
			$("#table_menu_operation").jqGrid({
				url : 'operationList.htm',
				postData : {
					'menuId' : $("#hide_menuId").val()
				}, //发送数据  
				datatype : "json",
				height : "300",
				autowidth : true,
				colNames : [ '按钮编号', '按钮名称', '按钮ID', '按钮图标', '按钮样式',  '所属菜单ID', '所属菜单' ],
				colModel : [ {
					name : "operationId",
					index : "operationId",
					hidden : true,
					editable : true,
					sortable: false,
					key : true
				}, {
					name : "operationName",
					index : "operationName",
					editable : true,
					editrules : {
						required : true
					},
					sortable: false
				},
				{
					name : "operationCode",
					index : "operationCode",
					editable : true,
					editrules : {
						required : true
					},
					sortable: false
				},
				{
					name : "",
					editable : false,
					formatter : function(cellvalue, options, rowObject){
						return '<span class="'+rowObject.iconcls+'" aria-hidden="true"></span>';
					}
				},
				{
					name : "iconCls",
					index : "iconCls",
					editable : true,
					sortable: false
				},
				{
					name : "menuid",
					index : "menuid",
					hidden : true,
					editable : true,
					formatter : function(cellvalue, options, rowObject){
						return rowObject.menuId.menuId ;
					},
					sortable: false
				},
				{
					name : "menuName",
					index : "menuName",
					hidden : false,
					editable : true,
					formatter : function(cellvalue, options, rowObject){
						return rowObject.menuId.menuName ;
					},
					sortable: false
				}
				],
				pager : "#pager_menu_operation",
				viewrecords : true,
				rowNum : "5",
				rowList : ["5", "10", "15" ],
				sortname : "operationId",
				sortorder : "asc",
				editurl : 'reserveOperation.htm'
			});
			
		};
			
		return mo_oTable;
	};

					
	var mo_ButtonInit = function () {
    	var mo_btnInit = new Object();
    	mo_btnInit.Init = function(){
	    	// Setup buttons
			var mo_jqnav = $("#table_menu_operation").jqGrid( 'navGrid', '#pager_menu_operation', {
				add : true,
				edit : true,
				del : true,
				search : false,
				refresh : false,
				addtext : "添加",
				edittext : "修改",
				deltext : "删除"
			},
			{//edit
				height : 300,
				reloadAfterSubmit : true,
			    beforeShowForm : function(frm) {
			    	var id = $("#table_menu_operation").jqGrid('getGridParam', 'selrow');
			    	frm.find('#operationId').val(id);
			    	frm.find('#menuid').val(menuid.value).attr('readonly',true);
			    	frm.find('#operationName').val(operationName.value);
			    	frm.find('#menuName').val(menuName.value).attr('readonly',true);
				},
				afterSubmit: function(xhr, postdata) {
	             		var obj = eval('(' + xhr.responseText + ')');
	             		if(obj.errorMsg === undefined){
	             			$("#table_menu_operation").trigger("reloadGrid");
	               			return [true];
	             		}else{
	             			layer.alert(obj.errorMsg, {icon: 2});
	             			return [false];
	             		}
				},
				closeAfterEdit : true
			},
			{//add
				height : 300,
				reloadAfterSubmit : true,
			    beforeShowForm : function(frm) {
			    	frm.find('#menuid').val($("#hide_menuId").val());
			    	frm.find('#menuName').val($("#hide_menuName").val()).attr('readonly',true);
				},
				afterSubmit: function(xhr, postdata) {
					console.info(xhr);
					console.info(postdata);
					var obj = eval('(' + xhr.responseText + ')');
             		if(obj.errorMsg === undefined){
             			console.info(obj.errorMsg);
             			$("#table_menu_operation").trigger("reloadGrid");
               			return [true];
             		}else{
	             		layer.alert(obj.errorMsg, {icon: 2});
             			return [false];
             		}
				},
				closeAfterAdd : true
			},
			{//del
             	url : 'deleteOperation.htm',
             	afterSubmit: function(xhr, postdata) {
             		var obj = eval('(' + xhr.responseText + ')');
             		if(obj.errorMsg){
             			layer.alert(obj.errorMsg, {icon: 2});
             			$("#delmodtable_menu_operation").hide();
             			return [false];
             		}else{
             			/* $("#table_menu_operation").trigger("reloadGrid"); */
             			$("#tokeInOutList").jqGrid('resetSelection');
						$("#delmodtable_menu_operation").hide();
               			return [true];
             		}
				}
             },
             {}
	        );
     	};
		return mo_btnInit;
	};
	</script>
</body>