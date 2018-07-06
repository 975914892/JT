<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>权限管理系统</title>
<%@include file="script.html" %>


</head>
<body>

	<div id="master-layout">
		<!--顶部banner区开始-->
		<div
			data-options="region:'north',border:false,bodyCls:'theme-header-layout'">
			<div class="theme-navigate">
				<div class="left">
					<h3>京唐国际客户关系管理系统</h3>
				</div>
				<div class="right">
					<a href="#" class="easyui-menubutton"
						data-options="menu:'#mm1',hasDownArrow:false">消息<span
						class="badge color-orange">15</span></a>

					<div id="mm1" class="theme-navigate-menu-panel"
						style="width:180px;">
						<div>
							站内消息<span class="badge color-success">5</span>
						</div>
						<div>
							公司公告<span class="badge color-important">10</span>
						</div>
						<div>服务消息</div>
						<div class="menu-sep"></div>
						<div>查看历史消息</div>
						<div class="menu-sep"></div>
						<div>清除消息提示</div>
					</div>
					<a href="#" class="easyui-menubutton theme-navigate-user-button"
						data-options="menu:'.theme-navigate-user-panel'">${sessionScope.user.username }</a>

					<div class="theme-navigate-user-panel">
						<dl>
							<dd>
								<img src="static/easyui/themes/insdep/images/portrait86x86.png" width="86" height="86"> 
								<b class="badge-prompt">${sessionScope.user.username }</b> 
								<span>身份：${sessionScope.roleName}</span>
								

								<p>
									安全等级：<i class="text-success">高</i>
								</p>
							</dd>
							<dt>
								<a class="theme-navigate-user-modify">修改资料</a> 
								<a id="logout" class="theme-navigate-user-logout">注销</a>
							</dt>
						</dl>
					</div>
				</div>
			</div>

		</div>
		<!--顶部banner区结束-->
		<!--左侧系统菜单区开始-->
		<div data-options="region:'west',split:true,border:false" title="系统菜单" style="width:230px; padding:10px 20px;">
			<ul id="menu" class="ztree">
			
			</ul>
		</div>
		<!--左侧系统菜单区结束-->
		<!--右侧功能展示区开始-->
		<div id="tabs" data-options="region:'center',border:false,height:800" class="easyui-tabs">
			<div id="tab1" title="个人中心">
				<div class="theme-user-info-panel">
					<div class="left">
						<img src="static/easyui/themes/insdep/images/portrait86x86.png" width="86" height="86">
					</div>
					<div class="right">
						<ul>
							<li class="text-success">￥6,200.00<span>完成合同金额</span></li>
							<li class="text-info">33<span>月度客户数</span></li>
							<li class="text-warning">9820<span>月度任务额</span></li>
							<li>125<span>月度线索数</span></li>
						</ul>
					</div>
					<div class="center">
						<h1>
							${sessionScope.user.username }<span class="badge color-success">已认证</span>
						</h1>

						<h2>
							角色名:${sessionScope.roleName}
						</h2>
						<ul>
							<%-- <li><i class="iconfont">&#xe61c;</i> ${sessionScope.functions.funcName}</li>
							<li><i class="iconfont">&#xe65d;</i> ${sessionScope.user.phone}</li> --%>
						</ul>
					</div>
				</div>

				<div id="user-info-more"
					class="easyui-tabs theme-tab-blue-line theme-tab-body-unborder"
					data-options="tools:'#tab-tools',fit:true">
					<div title="待办事项" style="padding:10px"></div>
					<div title="任务列表" style="padding:10px"></div>
					<div title="站内信息" style="padding:10px"></div>
				</div>
			</div>
		</div>
		<!--右侧功能展示区结束-->
		<div id="topWindow" style="overflow: hidden;"></div>
	</div>
</body>
	<script type="text/javascript">
		//ztree的设置
		var setting = {
			data : {
				simpleData : {
					enable : true
				}
			},
			callback : {
				//当单击树状节点时的回调函数
				onClick : function(event, treeId, treeNode) {
					event.preventDefault();
					if (treeNode.url) {
						openTab("#tabs", treeNode.name, treeNode.url);
					}

				}
			}
		};
		//打开标签页的函数
		function openTab(tabsId, title, url) {
			//判断是不是已经存在这个标签页 如果存在选中该标签页
			var exists = $(tabsId).tabs("exists", title);
			if (exists) {
				$(tabsId).tabs("select", title);
			} else {
				//如果不存在打开新的标签页
				$(tabsId).tabs(
								"add",
								{
									"title" : title,
									"closable" : true,
									"content" : 
										"<iframe width='100%' height='100%' scrolling='yes' frameborder='0' src='"+ url + "'></iframe>"
								});
			}
		}
		$(function() {
			//当前页面加载完毕之后，发送一个ajax请求，获取当前用户的功能权限
			$.get("menu.do", function(data) {
				if (data && data.success) {
					//初始化权限菜单树
					$.fn.zTree.init($("#menu"), setting, data.data);
				} else {
					$.messager.alert("警告", data.message);
					window.location.href = "../login.jsp";
				}
			}, "json");

		});
		//注销
	/* 	$("#logout").on("click",function(data){
			
			$.messager.confirm("提示","确认要注销吗?",function(b){
				if(b){
					window.location.href = "login.jsp";
				}
			});
	}) */
	$("#logout").on("click",function(data){
		
		$.messager.confirm("提示","确认要注销吗?",function(b){
			if(b){
				location.href="Exitok.do"
			}
		});
})
	    $(function () {
	        /*布局部分*/
	        $('#master-layout').layout({
	            fit: true/*布局框架全屏*/
	        });
	
	        $(".navigate-user-modify").on("click", function () {
	            $('.navigate-user-panel').menu('hide');
	        });
	    });
		//打开id="topWindow"的div窗口
		function openTopWindow(options) {
			//为参数设置默认值
			options.width = options.width ? options.width : 600;
			options.height = options.height ? options.height : 400;
			options.title = options.title ? options.title : "  ";
			options.url = options.url ? options.url : "";
			options.close = options.close ? options.close : function() {};
			options.isScrolling=options.isScrolling?options.isScrolling:"no";
			//初始化窗体
			$("#topWindow").window({
								width : options.width,
								height : options.height,
								title : options.title,
								modal : true,
								content : "<iframe width='100%' height='100%' scrolling='"+options.isScrolling+"' frameborder='0' src='"
										+ options.url + "'></iframe>",
								onClose : options.close
							});
		}
		//关闭窗口
		function closeTopWindow() {
			$("#topWindow").window("close");
		}
		
	
	</script>
</html>
