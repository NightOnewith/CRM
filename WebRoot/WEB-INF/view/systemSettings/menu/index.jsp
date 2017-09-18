<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.yuanma.net/taglibs/page" prefix="page"%>
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
					<i class="icon-table"></i> 菜单管理 <a
						class="btn btn-sm btn-primary pull-right"
						href="MenuServlet?method=getAllParentMenu"><i
						class="icon-plus"></i> 创建菜单</a>
				</div>
				<div class="widget-content padded clearfix">
					<div class="dataTables_filter">
						<form class="form-inline" action="MenuServlet?method=getAllMenu"
							method="post">
							<div class="btn-group">
								<span>菜单名：</span> <input type="text" class="form-control"
									id="name" name="name" value="${name}" placeholder="名称">
							</div>
							<div class="btn-group">
								<button type="button" class="btn btn-success"
									onclick="showMenu()">
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
										<th nowrap>名称</th>
										<th nowrap>地址</th>
										<th nowrap>图标</th>
										<th nowrap>创建时间</th>
										<th nowrap>更新时间</th>
										<th nowrap>上级菜单</th>
										<th class="action-column">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:choose>
										<c:when test="${menus.list.size() > 0}">
											<c:forEach items="${menus.list}" var="menu">
												<tr data-key="1">
													<td>${menu.menuId}</td>
													<td>${menu.menuName}</td>
													<td>${menu.menuUrl}</td>
													<td>${menu.pictures}</td>
													<td><fmt:formatDate value="${menu.createTime}"
															pattern="yyyy-MM-dd HH:mm:ss" /></td>
													<td><fmt:formatDate value="${menu.updateTime}"
															pattern="yyyy-MM-dd HH:mm:ss" /></td>
													<td>${menu.parentMenuName}</td>
													<td>
														<button type="button" id="updatebtn" name="updatebtn"
															class="btn btn-warning btn-sm"
															onclick="updateMenu(${menu.menuId})">修改</button>
														<button type="button" id="delbtn" name="delbtn"
															class="btn btn-danger btn-sm"
															onclick="deleteMenu(${menu.menuId})">删除</button>
													</td>
												</tr>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<tr data-key="1">
												<td>查无此菜单信息</td>
											</tr>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
							<div style="text-align: center;">
							<page:htmlPage
								url="MenuServlet?method=getAllMenu&name=${name}"
								pageInfo="${menus}"></page:htmlPage>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	function showMenu(){
		var name = $('#name').val();
		location.href="MenuServlet?method=getAllMenu&name=" + name;
	}
	
	function deleteMenu(menuId){
		if(confirm("删除后不可恢复，确认删除？")){
			$.ajax({
				type : 'post',
				url : 'MenuServlet?method=checkDeleteMenuId',
				data : {"menuId" : menuId},
				dataType : "json",
				success : function(data) {
					if (0 == data.status) {
						alert(data.msg);
					} else {
						location.href='MenuServlet?method=deleteMenuById&menuId='+menuId;
					}
				},
				error : function() {
					alert("请求失败!");
				}
			});
		}
	}

	function updateMenu(menuId){
		location.href='MenuServlet?method=getUpdateMenudetailById&menuId=' + menuId;
	}
</script>
</body>

</html>
