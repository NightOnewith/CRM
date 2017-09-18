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
					<i class="icon-table"></i> 客户管理 <a id="add-row"
						class="btn btn-sm btn-primary-outline pull-right"
						href="CustomerServlet?method=getAllToAdd"><i class="icon-plus"></i>
						新建客户</a>
				</div>
				<div class="widget-content padded clearfix">
					<div class="dataTables_filter">
						<form class="form-inline search-form"
							action="CustomerServlet?method=getAllCustomer" method="post">
							<div class="btn-group">
								<select id="field" class="form-control" name="field">
									<option value="00">--请选择筛选条件--</option>
									<option value="customer_name">客户名称</option>
									<option value="customer_industry">所属行业</option>
									<option value="customer_source">客户来源</option>
									<option value="create_time">创建时间</option>
								</select>

								<!-- 显示要输入的搜索条件 -->
								<div class="form-group" id="conditionContent"></div>
							</div>
							<div class="btn-group">
								<button type="submit" class="btn btn-success">搜索</button>
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
												class="custom-checkbox"></span>
										</label></th>
										<th nowrap>客户名称</th>
										<th nowrap>所属行业</th>
										<th nowrap>客户来源</th>
										<th nowrap>地址</th>
										<th nowrap>创建时间</th>
										<th nowrap>更新时间</th>
										<th nowrap>负责人</th>
										<th class="action-column">操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${list.list }" var="custormers">
										<tr data-key="13">
											<td><label class="label-checkbox"> <input
													type="checkbox" name="selection[]" value="13"><span
													class="custom-checkbox"></span>
											</label></td>
											<td>${custormers.customerName }</td>
											<td>${custormers.fieldName }</td>
											<td>${custormers.sourceName}</td>
											<td>${custormers.address }</td>
											<td><fmt:formatDate value="${custormers.createTime}"
													pattern="yyyy-MM-dd HH:mm" /></td>
											<td><fmt:formatDate value="${custormers.updateTime}"
													pattern="yyyy-MM-dd HH:mm" /></td>
											<td>${custormers.employeeName }</td>
											<td>
												<button type="button" name="updatebtn"
													class="btn btn-warning btn-sm"
													onclick="checkCustomer(${custormers.customerId})">查看</button>
												<button type="button" name="updatebtn"
													class="btn btn-warning btn-sm"
													onclick="updateCustomer(${custormers.customerId})">修改</button>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
							<div style="text-align: center;">
								<page:htmlPage url="CustomerServlet?method=getAllCustomer"
									pageInfo="${list}"></page:htmlPage>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

<script>

	$("#field").change(function(){
		$("#conditionContent").empty();
		//获取条件
		var condition=$(this).find("option:checked").val();
		
		if(condition=="customer_name"){
			var $input= $("<input>",{
				type:"text",
				name:"customerName",
				class:"form-control"
			});
			$("#conditionContent").append($input);
		}
		
		if(condition=="create_time"){
			var $input1= $("<input>",{
				type:"date",
				name:"startTime",
				class:"form-control"
			});
			var $input2= $("<input>",{
				type:"date",
				name:"endTime",
				class:"form-control"
			});
			$("#conditionContent").append($input1).append("--").append($input2);
		}
		//案例
		if(condition=="customer_industry"){
			var industryArray=[];
			var data=null;
			 $.ajax({
				type : 'post',
				url : 'CustomerServlet?method=getAllWorkingField',
				data : data,
				cache : false,
				sync : true,
				success : function(msg) {
					var json = JSON.parse(msg);

					if (0 == json.flag) {
						alert(json.msg);
						return;
					}else{
						industryArray=json.workingFields;
						var $select=$("<select>",{
							name:"industryId",
							class:"form-control"
						});
						$.each(industryArray,function(index,ele){
							var $option=$("<option>",{
								text:ele.fieldName,
								value:ele.fieldId
							});
							$select.append($option);
						});
						$("#conditionContent").append($select);
					} 
				},
				error : function() {
					alert("请求失败");
					return false;
				}
			});
			
		}
		
		if(condition=="customer_source"){
			var sourceArray=[];
			var data=null;
			$.ajax({
				type: 'post',
				url: 'CustomerServlet?method=getAllBusinessSource',
				data:data,
				cache : false,
				sync : true,
				success : function(msg) {
					var json = JSON.parse(msg);

					if (0 == json.flag) {
						alert(json.msg);
						return;
					}else{
						sourceArray=json.businessSources;
			var $select=$("<select>",{
				name:"sourceId",
				class:"form-control"
			});
			$.each(sourceArray,function(index,ele){
				var $option=$("<option>",{
					text:ele.sourceName,
					value:ele.sourceId
				});
				$select.append($option);
			});
			$("#conditionContent").append($select);
					}
			},
				error : function() {
					alert("请求失败");
					return false;
					}
				});
			
			}
	})
	function checkCustomer(customerId){
		location.href='CustomerServlet?method=selectByView&customerId='+customerId;
	}
	function updateCustomer(customerId){
		location.href='CustomerServlet?method=selectByPrimaryKey&customerId='+customerId;
	}
	
		
	
	
</script>

</html>