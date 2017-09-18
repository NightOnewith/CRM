<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.yuanma.net/taglibs/page" prefix="page" %>
<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="/common.jsp"></jsp:include>
</head>
<body class="navbar-top">
	<div class="modal-shiftfix">
		<jsp:include page="/menu.jsp"></jsp:include>
		<div class="container-fluid main-content">
			<div class="widget-container fluid-height clearfix">
				<div class="heading clearfix">
					<i class="icon-table"></i> 职位设置 <a
						class="btn btn-sm btn-primary pull-right weiBoxs-dialog"
						href="EmmPositionServlet?method=goToPositionAdd" dialog-title="添加职位"><i
						class="icon-plus"></i> 添加职位</a>
				</div>
				<div class="widget-content padded clearfix">
					<div class="dataTables_filter">
						<form id="positionForm" class="form-inline"
							action="EmmPositionServlet?method=getAllEmmPosition"
							method="post">
							<div class="btn-group">
								<span>职位名：</span> <input type="text" class="form-control"
									id="positionname" name="positionname" value="${positionName}">
							</div>
							<div class="btn-group">
								<button type="button" id="searchButton" class="btn btn-success"
								    	onclick="showEmmPosition()">
									<i class="glyphicon glyphicon-search"></i> 搜索
								</button>
							</div>
						</form>
					</div>
					<div id="w0" class="grid-view">
						<div class="table-responsive">
							<table class="table table-hover">
								<thead>
									<tr>
										<th nowrap>ID</th>
										<th nowrap>等级</th>
										<th nowrap>名称</th>
										<th nowrap>创建时间</th>
										<th nowrap>更新时间</th>
										<th class="action-column">操作</th>
									</tr>
								</thead>
								<tbody>
								<c:choose>
								<c:when test="${emmPositions.list.size() > 0}">
								<c:forEach items="${emmPositions.list}" var="emm">
									<tr>
										<td>${emm.positionId}</td>
										<td>${emm.positionLevel}</td>
										<td>${emm.positionName}</td>
										<td><fmt:formatDate value="${emm.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td><fmt:formatDate value="${emm.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td>
											<div class="col-lg-20">
												<button type="button" id="updatebtn" name="updatebtn"
													class="btn btn-warning btn-sm" onclick="updateposition(${emm.positionId})">修改
												</button>
												<button type="button" id="delbtn" name="delbtn"
													class="btn btn-danger btn-sm" onclick="deleteposition(${emm.positionId})">删除
												</button>
												<button type="button" id="linkbtn" name="linkbtn"
													class="btn btn-success btn-sm" onclick="showselectmodel(${emm.positionId})">关联菜单</button>
											</div>
										</td>
									</tr>
								</c:forEach>
								</c:when>
								<c:otherwise>
									<tr>
										<td>查无此职位信息</td>
									</tr>
								</c:otherwise>
								</c:choose>
								</tbody>
							</table>
							<div style="text-align: center;">
							<page:htmlPage url="EmmPositionServlet?method=getAllEmmPosition&positionName=${positionName}" pageInfo="${emmPositions}"></page:htmlPage>
							</div>
						</div>
						<div id="checkedAllMenu" style="height:10px;display: none"></div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- 这边是选择菜单的模态框  begin -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<input type="hidden" id="referencepositionId"
						name="referencepositionId">
					<h4 class="modal-title" id="myModalLabel">选择关联菜单</h4>
				</div>
				<div class="modal-body">
					<table id="example" class="table">
						<thead>
							<tr>
								<th></th>
								<th>名称</th>
								<th>地址</th>
								<th>上级菜单</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${menus}" var="menu">
							<tr>
								<td><input type="checkbox" name="checkedemployee"
									id="checkedbox${menu.menuId}" value="${menu.menuId}" onclick="movetochecked(${menu.menuId})"
									style="opacity: 1;" /></td>
									<td>${menu.menuName}</td>
									<td>${menu.menuUrl}</td>
									<td>${menu.parentMenuName}</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
				</div>
				<div class="modal-footer" style="text-align: center;">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary"
						onclick="submittochange()">确认</button>
				</div>
			</div>

		</div>
	</div>
	<!-- 这边是选择菜单的的模态框  end  -->

	<!-- <script type="text/javascript">
		$(function() {
			$("#searchButton").click(submitForm);

			function submitForm() {
				$("#positionForm").submit();
			}
		});
	</script> -->
	
	<script type="text/javascript">
		function showEmmPosition(){
			var positionName = $('#positionname').val()
			location.href="EmmPositionServlet?method=getAllEmmPosition&positionName=" + positionName;
		}
	</script>
	
	<script type="text/javascript">
		function deleteposition(positionId) {
			$.ajax({
				type : 'post',
				url : 'EmmPositionServlet?method=deletePostionCheck',
				data : {"positionId" : positionId},
				dataType : "json",
				success : function(data) {
					if (0 == data.status) {
						alert(data.msg);
					} else {
						if(confirm(data.msg)){
							location.href = 'EmmPositionServlet?method=deletePosition&positionId=' + positionId;
						}
					}
				},
				error : function() {
					alert("请求失败");
				}
			});
		}
	</script>

	<script type="text/javascript">
		function updateposition(positionId) {
			location.href = 'EmmPositionServlet?method=getUpdatePostionDetailById&positionId='
					+ positionId;

		}

		function submittochange() {
			var referencepositionId = $("#referencepositionId").val();
			var checkflag = true;
			$("[name=checkedemployee]:checkbox").each(function() {
				if (this.checked) {
					checkflag = false;
				}
			});
			if (checkflag) {
				alert("至少选择一个关联菜单");
				return;
			}
			var list = document.getElementsByName("selectMenu");
			var s = "";
			for ( var i = 0; i < list.length; i++) {
				if (list[i].checked == true) {
					s += list[i].value + ",";
				}
			}
			window.location.href = 'EmmPositionServlet?method=positionLinks&menuIds='
					+ s + '&positionId=' + referencepositionId;

		}

		function showselectmodel(positionId) {
			$("#myModal").modal("show");
			$("#referencepositionId").val(positionId);
			
			
		}
	</script>

	<script type="text/javascript">
		function movetochecked(menuId) {
			var checkedboxid = 'checkedbox' + menuId;
			var checkedivid = 'checkeddiv' + menuId;
			if ($("#" + checkedboxid + "").is(':checked')) {
				$("#checkedAllMenu").append('<div id='+checkedivid+'><input type="checkbox" checked="checked" name="selectMenu" onclick="$(this).parent().remove();" value='+ menuId + '>' + menuId + '</div>');
			} else {
				$("#" + checkedboxid + "").prop("checked", false);
				$("div#" + checkedivid + "").remove();
			}
		}
	</script>

	<script type="text/javascript">
		$.extend($.fn.dataTable.defaults, {
			"searching" : false,
			"ordering" : false
		});
		$('#example').DataTable({
			language : {
				"sProcessing" : "处理中...",
				"sLengthMenu" : "显示 _MENU_ 项结果",
				"sZeroRecords" : "没有匹配结果",
				"sInfo" : "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
				"sInfoEmpty" : "显示第 0 至 0 项结果，共 0 项",
				"sInfoFiltered" : "(由 _MAX_ 项结果过滤)",
				"sInfoPostFix" : "",
				"sSearch" : "搜索:",
				"sUrl" : "",
				"sEmptyTable" : "表中数据为空",
				"sLoadingRecords" : "载入中...",
				"sInfoThousands" : ",",
				"oPaginate" : {
					"sFirst" : "首页",
					"sPrevious" : "上页",
					"sNext" : "下页",
					"sLast" : "末页"
				}
			}
		});
	</script>
</body>
</html>
