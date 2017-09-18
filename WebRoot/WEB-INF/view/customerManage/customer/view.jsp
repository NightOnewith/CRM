<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="/common.jsp"></jsp:include>
</head>

<body class="navbar-top">
	<div class="modal-shiftfix">
		<jsp:include page="/menu.jsp"></jsp:include>
		<div class="container-fluid main-content">
			<div class="widget-container fluid-height clearfix mwi1200">
				<div class="heading clearfix">
					基本信息 <i class="icon-reply pull-right" onclick="history.go(-1);"></i>
				</div>
				<div class="widget-content padded">
					<div class="table-responsive">
						<table class="table table-hover">
							<tbody>

								<tr>
									<th>负责人：</th>
									<td><a class="weiBoxs-dialog"
										href="manage/ajax-info.html?manage_id=2" dialog-title="管理员信息"
										dialog-button="false">${customerDto.employeeName }</a></td>
									<th>创建时间：</th>
									<td><fmt:formatDate value="${customerDto.createTime}"
											pattern="yyyy-MM-dd HH:mm" /></td>
								</tr>
								<tr>
									<th>修改时间：</th>
									<td><fmt:formatDate value="${customerDto.updateTime}"
											pattern="yyyy-MM-dd HH:mm" /></td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<th style="width: 15%" nowrap>客户名称：</th>
									<td>${customerDto.customerName }</td>
									<th style="width: 15%" nowrap>邮编：</th>
									<td>${customerDto.postCode }</td>
								</tr>
								<tr>
									<th style="width: 15%" nowrap>所属行业：</th>

									<td>${customerDto.fieldName }<input type="hidden"
										id="fieldId" value="1" />
									</td>
									<th style="width: 15%" nowrap>客户来源：</th>
									<td>${customerDto.sourceName }</td>
								</tr>
								<tr>
									<th style="width: 15%" nowrap>年营业额：</th>
									<td>${customerDto.busubessVolume }</td>

									<th style="width: 15%" nowrap>员工数：</th>
									<td>${customerDto.employeeNumbers }</td>
								</tr>
								<tr>
									<th style="width: 15%" nowrap>标签：</th>
									<td colspan="3">${customerDto.tag }</td>
								</tr>
								<tr>
									<th style="width: 15%" nowrap>备注：</th>
									<td colspan="3">${customerDto.remarks }</td>
								</tr>


							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>
