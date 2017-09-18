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
					<i class="icon-table"></i> 员工管理 <a
						class="btn btn-sm btn-primary pull-right"
						href="EmployeeServlet?method=getDeapartAndPostionInfo"><i
						class="icon-plus"></i> 添加员工</a>
				</div>
				<div class="widget-content padded clearfix">
					<div class="dataTables_filter">
						<form class="form-inline"
							action="EmployeeServlet?method=getAllEmployee" method="post">
							<div class="btn-group">
								<span>工号：</span> <input type="text" class="form-control"
									id="emmployeeId" name="emmployeeId" value="${employeeId}">
							</div>
							<div class="btn-group">
								<span>姓名：</span> <input type="text" class="form-control"
									id="emmployeename" name="emmployeename" value="${employeeName}">
							</div>
							<div class="btn-group">
								<button type="button" class="btn btn-success"
									onclick="showEmployeeDto()">
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
										<th nowrap>工号</th>
										<th nowrap>姓名</th>
										<th nowrap>部门</th>
										<th nowrap>职位</th>
										<th nowrap>手机</th>
										<th nowrap>电子邮箱</th>
										<th nowrap>状态</th>
										<th nowrap>创建时间</th>
										<th nowrap>更新时间</th>
										<th class="action-column">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:choose>
										<c:when test="${employeeDtos.list.size() > 0}">
											<c:forEach items="${employeeDtos.list}" var="emp">
												<tr data-key="1">
													<td>${emp.employeeId}</td>
													<td>${emp.employeeName}</td>
													<td>${emp.departmentName}</td>
													<td>${emp.positonName}</td>
													<td>${emp.phone}</td>
													<td>${emp.email}</td>

													<c:if test="${emp.status == '1'}" var="rs">
														<td>禁用</td>
													</c:if>
													<c:if test="${!rs}">
														<td>正常</td>
													</c:if>

													<td><fmt:formatDate value="${emp.createTime}"
															pattern="yyyy-MM-dd HH:mm:ss" /></td>
													<td><fmt:formatDate value="${emp.updateTime}"
															pattern="yyyy-MM-dd HH:mm:ss" /></td>

													<c:if test="${emp.status == '1'}" var="rs">
														<td>
															<button type="button" id="recevorbtn" name="recevorbtn"
																class="btn btn-success btn-sm"
																onclick="updateEmployeeStatus('${emp.employeeId}','0')">恢复正常</button>
														</td>
													</c:if>
													<c:if test="${!rs}">
														<td>
															<button type="button" id="forbidbtn" name="forbidbtn"
																class="btn btn-danger btn-sm"
																onclick="updateEmployeeStatus('${emp.employeeId}','1')">禁用账户</button>
															<button type="button" id="updatebtn" name="updatebtn"
																class="btn btn-info btn-sm"
																onclick="updateEmployeeDetail('${emp.employeeId}')">修改用户信息</button>
														</td>
													</c:if>
												</tr>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<tr data-key="1">
												<td>查无此人</td>
											</tr>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
							<div style="text-align: center;">
								<page:htmlPage
									url="EmployeeServlet?method=getAllEmployee&employeeName=${employeeName}"
									pageInfo="${employeeDtos}"></page:htmlPage>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function showEmployeeDto(id, name) {
			var employeeId = $('#emmployeeId').val();
			if (employeeId != null && employeeId != "" && employeeId != undefined) {
				if (!(/^[0-9]+$/.test(employeeId))) {
					alert("输入工号格式不正确");
					return;
				}
			}

			var employeeName = $('#emmployeename').val();
			location.href = "EmployeeServlet?method=getAllEmployee&employeeId="
					+ employeeId + "&employeeName=" + employeeName;
		}

		//切换员工状态
		function updateEmployeeStatus(employeeId, status) {
			location.href = 'EmployeeServlet?method=updateEmployeeStatus&employeeId='
					+ employeeId + '&status=' + status;

		}

		function updateEmployeeDetail(employeeId) {
			location.href = 'EmployeeServlet?method=findEmployeeById&employeeId='
					+ employeeId;
		}
	</script>
</body>

</html>
