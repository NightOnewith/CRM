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
					<i class="icon-table"></i> 联系人 <a
						class="btn btn-sm btn-primary-outline pull-right"
						href="LinkManServlet?method=getAllCustomer"><i
						class="icon-plus"></i> 添加联系人</a>
				</div>
				<div class="widget-content padded clearfix">
					<div class="dataTables_filter">
						<form id="actionFormSo"
							action="LinkManServlet?method=getAllLinkMan" method="post">
							<div class="btn-group">
								<input type="text" class="form-control input-sm" name="mobile"
									id="mobileId" placeholder="手机号">
							</div>
							<div class="btn-group">
								<input type="text" class="form-control input-sm" name="email"
									id="emailId" placeholder="邮箱">
							</div>
							<div class="btn-group">
								<button type="button" class="btn btn-success btn-sm" id="soo">
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
										<th><label class="label-checkbox"> <input
												type="checkbox" class="select-on-check-all"
												name="selection_all" value="1"><span
												class="custom-checkbox"></span></label></th>
										<!-- <th nowrap>所属客户</th> -->
										<th nowrap>姓名</th>
										<th nowrap>性别</th>
										<th nowrap>尊称</th>
										<th nowrap>职位</th>
										<th nowrap>手机</th>
										<th nowrap>电子邮箱</th>
										<th nowrap>QQ</th>
										<th nowrap>创建时间</th>
										<th nowrap>更新时间</th>
										<th class="action-column">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${list.list }" var="linkmain">
										<tr data-key="4">
											<td><label class="label-checkbox"> <input
													type="checkbox" name="selection[]" value="4"><span
													class="custom-checkbox"></span></label></td>
											<td>${linkmain.name }</td>
											<td>${linkmain.sex }</td>

											<td>${linkmain.position }</td>

											<td>${linkmain.nickname }</td>
											<td>${linkmain.phonenum }</td>
											<td>${linkmain.email }</td>
											<td><a href="mailto:xxxx@xxx.com">${linkmain.qq }</a></td>
											<td><fmt:formatDate value="${linkmain.createTime}"
													pattern="yyyy-MM-dd HH:mm" /></td>
											<td><fmt:formatDate value="${linkmain.updateTime}"
													pattern="yyyy-MM-dd HH:mm" /></td>
											<td></td>
											<td><button class="btn btn-success btn-sm" title="查看"
													aria-label="查看" onclick="examine(${linkmain.linkMainId})">查看</button>
												<button class="btn btn-warning btn-sm" title="更新"
													aria-label="更新"
													onclick="updateLinkMan(${linkmain.linkMainId})">修改</button></td>
										</tr>

									</c:forEach>
								</tbody>
							</table>
							<div style="text-align: center;">
							<page:htmlPage
								url="LinkManServlet?method=getAllLinkMan"
								pageInfo="${list}"></page:htmlPage>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
$(function(){
	$("#soo").on("click",function(){
		var mobile = $("#mobileId").val();
		var email = $("#emailId").val();
		if ((mobile!=null&&mobile!=""&&mobile!="undefined")||(email!=null&&email!=""&&email!="undefined")) {
			$("#actionFormSo").attr("action","LinkManServlet?method=getAllLinkMan");
			$("#actionFormSo").submit();
		}else{
			$("#actionFormSo").attr("action","LinkManServlet?method=getAllLinkMan");
			$("#actionFormSo").submit();
		}
		
		
	});
	
	

});


function examine(linkMainId){
location.href='LinkManServlet?method=selectByPrimaryKey&type=1&linkMainId='+linkMainId;
} 


function updateLinkMan(linkMainId){
location.href='LinkManServlet?method=selectByPrimaryKey&type=0&linkMainId='+linkMainId;
}

</script>
</body>

</html>
