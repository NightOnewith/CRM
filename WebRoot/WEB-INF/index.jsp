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
		<!-- 菜单 -->
		<jsp:include page="/menu.jsp"></jsp:include>

		<div class="container-fluid main-content">
			<div class="row">
				<div class="col-lg-12">
					<div class="widget-container stats-container">
						<div class="col-md-3">
							<div class="number">
								<div class="icon visitors"></div>
								<span id="customerCount">${count}</span> <small>个</small>
							</div>
							<div class="text">近七日新增客户</div>
						</div>
						<div class="col-md-3">
							<div class="number">
								<div class="icon globe"></div>
								<span id="businessCount">${count3}</span> <small>个</small>
							</div>
							<div class="text">近七日新增商机</div>
						</div>
						<div class="col-md-3">
							<div class="number">
								<div class="icon money"></div>
								<span id="salesCount">0</span> <small>单</small>
							</div>
							<div class="text">近七日新增销售单</div>
						</div>
						<div class="col-md-3">
							<div class="number">
								<div class="icon chat-bubbles"></div>
								<span id="customerTotal">${count2}</span> <small>个</small>

							</div>
							<div class="text">月度新增客户</div>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-8">
					<div class="widget-container fluid-height">
						<div class="heading">
							<i class="icon-bar-chart"></i>本月客户,商机
							<table class="table table-hover">
								<thead>
									<tr>
										<th nowrap>来源</th>
										<th nowrap>名称</th>
										<th nowrap>负责人</th>
										<th nowrap>商机来源</th>
										<th nowrap>更新时间</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${cbDtos.list}" var="cb">
										<tr data-key="8">
											<td>${cb.ly}</td>
											<td>${cb.nameQc}</td>
											<td>${cb.nameer}</td>
											<td>${cb.sourceName}</td>
											<td><fmt:formatDate value="${cb.updateTime}"
													pattern="yyyy-MM-dd HH:mm:ss" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div style="text-align: center;">
							<page:htmlPage url="ControlServlet?method=countAll"
								pageInfo="${cbDtos}"></page:htmlPage>
							</div>
						</div>
						<div class="widget-content" id="main" style="height: 280px;"></div>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="widget-container fluid-height clearfix">
						<div class="heading">
							<i class="icon-table"></i>我最近的任务
						</div>
						<div class="widget-content padded clearfix">
							<table class="table" style="color: #007aff;">
								<thead>
								<tr>
									<th>名称</th>
									<th>状态</th>
									<th>待完成时间</th>
								</tr>
								</thead>
								<tbody>
									<c:forEach items="${myTasks.list}" var="task">
										<tr>
											<td>${task.topic}</td>
											<c:if test="${task.status == '0'}">
												<td>未启动</td>
											</c:if>
											<c:if test="${task.status == '10'}">
												<td>推迟</td>
											</c:if>
											<c:if test="${task.status == '20'}">
												<td>进行中</td>
											</c:if>
											<c:if test="${task.status == '30'}">
												<td>完成</td>
											</c:if>
											<td><fmt:formatDate value="${task.endTime}"
													pattern="yyyy-MM-dd HH:mm:ss" /></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<%-- <page:htmlPage url="ControlServlet?method=countAll"
								pageInfo="${myTasks}"></page:htmlPage> --%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>

</html>
