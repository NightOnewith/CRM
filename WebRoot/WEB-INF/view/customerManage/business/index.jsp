<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.yuanma.net/taglibs/page" prefix="page"%>
<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="/common.jsp"></jsp:include>
<script src="${pageContext.request.contextPath }/js/page.js"></script>
</head>

<body class="navbar-top">

	<div class="modal-shiftfix">
		<jsp:include page="/menu.jsp"></jsp:include>
		<div class="container-fluid main-content">
			<div class="widget-container fluid-height clearfix">
				<div class="heading clearfix">
					<i class="icon-table"></i> 商机管理 <a id="add-row"
						class="btn btn-sm btn-primary-outline pull-right"
						href="BuinessServlet?method=getAllLinkMan"><i
						class="icon-plus"></i> 新建商机</a>
				</div>
				<div class="widget-content padded clearfix">
					<div class="dataTables_filter">
						<form class="form-inline"
							action="LinkManServlet?method=getOneLinkMan" id="actionFormSo"
							method="post">
							<div class="btn-group">
								<select id="field" class="form-control"
									onchange="changeCondition()" name="field">
									<option class="" value="">--请选择筛选条件--</option>
									<option class="text" value="name" rel="2"
										url="/fields/boxfield.html">商机名</option>
									<option class="box" value="type" rel="2"
										url="/fields/boxfield.html">商机类型</option>
									<option class="box" value="status" rel="2"
										url="/fields/boxfield.html">状态</option>
									<option class="box" value="origin" rel="2"
										url="/fields/boxfield.html">商机来源</option>
									<option class="floatnumber" value="estimate_price" rel="2"
										url="/fields/boxfield.html">预计价格</option>
									<option class="date" value="nextstep_time" rel="2"
										url="/fields/boxfield.html">下次联系时间</option>
									<option class="date" value="updated_at">修改时间</option>
								</select>

								<div class="form-group" id="conditionContent"
									style="display: none">
									<input type="text" id="hiddenId" class="form-control" />
								</div>
								<div class="form-group" id="conditionContentRq"
									style="display: none">
									<input type="date" id="hiddenIdRq" class="form-control" />
								</div>
								<div class="form-group" id="searchContent" style="display: none">
									<select id="selectId" class="form-control">

									</select>

								</div>

							</div>
							<div class="btn-group">
								<button type="button" class="btn btn-success" onclick="soo()">
									<i class="glyphicon glyphicon-search"></i> 搜索
								</button>
							</div>
						</form>
					</div>
					<form id="actionForm" action="/business/index.html" method="post">
						<div id="w0" class="grid-view">
							<div class="table-responsive">
								<table class="table table-hover">
									<thead>
										<tr>
											<th><label class="label-checkbox"> <input
													type="checkbox" class="select-on-check-all"
													name="selection_all" value="1"><span
													class="custom-checkbox"></span></label></th>
											<th nowrap>创建人</th>
											<th nowrap>联系人</th>
											<th nowrap>所属客户</th>
											<th nowrap>商机名</th>
											<th nowrap>商机类型</th>
											<th nowrap>商机来源</th>
											<th nowrap>预计价格</th>
											<th nowrap>下次联系时间</th>
											<th nowrap>下次联系时内容</th>
											<th nowrap>更新时间</th>
											<th class="action-column">操作</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${list.list }" var="businessDto">
											<tr data-key="8">
												<td><label class="label-checkbox"> <input
														type="checkbox" name="selection[]" value="8"><span
														class="custom-checkbox"></span></label></td>
												<td><a class="weiBoxs-dialog"
													href="manage/ajax-info.html?manage_id=1"
													dialog-title="管理员信息" dialog-button="false"></a>${businessDto.employeeName }</td>
												<td><a href="customer/view.html?id=1" target="_blank"></a>${businessDto.name }</td>
												<td>${businessDto.customerName }</td>
												<td>${businessDto.businessName }</td>

												<td>${businessDto.typeName}</td>
												<td>${businessDto.sourceName}</td>
												<td>${businessDto.persalePrice }</td>
												<td><fmt:formatDate value="${businessDto.visitTime}"
														pattern="yyyy-MM-dd HH:mm" /></td>
												<td>${businessDto.visitContent }</td>
												<td><fmt:formatDate value="${businessDto.updateTime}"
														pattern="yyyy-MM-dd HH:mm" /></td>
												<td><a class="label label-success"
													href="BuinessServlet?method=selectByPrimaryKey&type=1&businessId=${businessDto.businessId }"
													title="查看" aria-label="查看">查看</a> <a
													class="label label-danger weiBoxs-dialog"
													href="AdvanceLogServlet?method=selectBuines&businessId=${businessDto.businessId }"
													dialog-title="添加推进">添加推进</a> <a
													class="label label-warning edit"
													href="BuinessServlet?method=selectByPrimaryKey&type=0&businessId=${businessDto.businessId }"
													title="更新" aria-label="更新">修改</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<div style="text-align: center;">
								<page:htmlPage
									url="BuinessServlet?method=getAllBusiness"
									pageInfo="${list}"></page:htmlPage>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	function changeCondition() {
		var selectTest = $("#field").find("option:selected").text();
		if (selectTest == "商机名") {
			$("#searchContent").hide();
			$("#conditionContentRq").hide();
			$("#selectId").empty();
			$("#hiddenId").attr("type", "text");
			$("#hiddenId").val("");
			$("#conditionContent").show();
			$("#hiddenId").attr("name", "businessName");
		} else if (selectTest == "预计价格") {
			$("#searchContent").hide();
			$("#conditionContentRq").hide();
			$("#selectId").empty();
			$("#hiddenId").attr("type", "text");
			$("#hiddenId").val("");
			$("#conditionContent").show();
			hiddenIdRq
			$("#hiddenId").attr("name", "persalePrice");

		} else if (selectTest == "下次联系时间" || selectTest == "创建时间") {
			$("#searchContent").hide();
			$("#selectId").empty();
			$("#hiddenId").val("");
			$("#hiddenId").attr("type", "date");
			$("#conditionContent").show();
			$("#conditionContentRq").show();
			$("#hiddenId").attr("name", "startTime");
			$("#hiddenIdRq").attr("name", "endTime");

		} else if (selectTest == "修改时间") {
			$("#searchContent").hide();
			$("#selectId").empty();
			$("#hiddenId").val("");
			$("#hiddenId").attr("type", "date");
			$("#conditionContent").show();
			$("#conditionContentRq").show();
			$("#hiddenId").attr("name", "openTime");
			$("#hiddenIdRq").attr("name", "overTime");
		} else if (selectTest == "商机类型") {//onclick="laydate()"
			$("#conditionContent").hide();
			$("#conditionContentRq").hide();
			$("#searchContent").show();
			$("#hiddenId").attr("type", "text");
			$("#selectId").empty();
			$("#selectId").attr("name", "businessTypeId");
			var industryArray = [];
			var data = null;
			$.ajax({
				type : 'post',
				url : 'BuinessServlet?method=getAllType',
				data : data,
				cache : false,
				sync : true,
				success : function(msg) {
					var json = JSON.parse(msg);
					if (0 == json.flag) {
						alert(json.msg);
						return;
					} else {
						industryArray = json.businessTypes;
						$.each(industryArray, function(index, ele) {
							var $option = $("<option>", {
								text : ele.typeName,
								value : ele.typeId
							});
							$("#selectId").append($option);
						});

					}
				},
				error : function() {
					alert("请求失败");
					return false;
				}
			});
		} else if (selectTest == "状态") {
			$("#searchContent").show();
			$("#conditionContent").hide();
			$("#conditionContentRq").hide();
			$("#hiddenId").attr("type", "text");
			$("#selectId").empty();
			$("#selectId").attr("name", "status");
			$("#selectId").append("<option value='1'>初步沟通</option>");
			$("#selectId").append("<option value='2'>签订服务协议</option>");
			$("#selectId").append("<option value='3' selected>签订合同</option>");
		} else if (selectTest == "商机来源") {
			$("#conditionContent").hide();
			$("#conditionContentRq").hide();
			$("#searchContent").show();
			$("#hiddenId").attr("type", "text");
			$("#selectId").empty();
			$("#selectId").attr("name", "businessSourceId");
			var sourceArray = [];
			var data = null;
			$.ajax({
				type : 'post',
				url : 'BuinessServlet?method=getAllSource',
				data : data,
				cache : false,
				sync : true,
				success : function(msg) {
					var json = JSON.parse(msg);
					/* alert(json.businessTypes); */
					if (0 == json.flag) {
						alert(json.msg);
						return;
					} else {
						sourceArray = json.businessSources;
						$.each(sourceArray, function(index, ele) {
							var $option = $("<option>", {
								text : ele.sourceName,
								value : ele.sourceId
							});
							$("#selectId").append($option);
						});

					}
				},
				error : function() {
					alert("请求失败");
					return false;
				}
			});

		}
	}
	function soo() {
		var selectTest = $("#field").find("option:selected").text();
		if ($("#searchContent").css('display') == "inline-block") {
			var text = $("#selectId").find("option:selected").val();

			if (selectTest == "商机类型") {
				$("#actionFormSo").attr("action",
						"BuinessServlet?method=getAllBusiness ");
				$("#actionFormSo").submit();
			} else if (selectTest == "状态") {
				$("#actionFormSo").attr("action",
						"BuinessServlet?method=getAllBusiness ");
				$("#actionFormSo").submit();
			} else if (selectTest == "商机来源") {
				$("#actionFormSo").attr("action",
						"BuinessServlet?method=getAllBusiness");
				$("#actionFormSo").submit();
			}

		} else if ($("#conditionContent").css('display') == "inline-block") {
			var text = $("#hiddenId").val();
			var textTime = $("#hiddenIdRq").val();
			if (text != null && text != "undefined" && text != "") {
				if (selectTest == "商机名") {
					$("#actionFormSo").attr("action",
							"BuinessServlet?method=getAllBusiness ");
					$("#actionFormSo").submit();
				} else if (selectTest == "预计价格") {
					$("#actionFormSo").attr("action",
							"BuinessServlet?method=getAllBusiness");
					$("#actionFormSo").submit();
				} else if (selectTest == "下次联系时间") {
					if (text > textTime) {
						alert("前面输入时间不能大于后面输入时间");
						return;
					}
					$("#actionFormSo").attr("action",
							"BuinessServlet?method=getAllBusiness");
					$("#actionFormSo").submit();
				} else if (selectTest == "创建时间") {
					if (text > textTime) {
						alert("前面输入时间不能大于后面输入时间");
						return;
					}
					$("#actionFormSo").attr("action",
							"BuinessServlet?method=getAllBusiness");
					$("#actionFormSo").submit();
				} else if (selectTest == "修改时间") {
					if (text > textTime) {
						alert("前面输入时间不能大于后面输入时间");
						return;
					}
					$("#actionFormSo").attr("action",
							"BuinessServlet?method=getAllBusiness");
					$("#actionFormSo").submit();
				}

			}
		} else {
			window.location.href = 'BuinessServlet?method=getAllBusiness';
		}
	}
</script>
</html>
