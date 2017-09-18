<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                <i class="icon-reorder"></i> 查看联系人
                <i class="icon-reply pull-right" onclick="history.go(-1);"></i>
            </div>
            <div class="widget-content padded clearfix">
                <table class="table detail-view">
                    <tbody>
                  <%--   <tr>
                        <th width="15%">所属客户</th>
                        <td><a href="customer/view.html?id=1" target="_blank">${linkman.customerName }</a></td>
                    </tr> --%>
                    <tr>
                        <th>姓名</th>
                        <td>${linkMain.name }</td>
                    </tr>
                    <tr>
                        <th>性别</th>
                        	<td>${linkMain.sex }</td>
                        	<!-- <td>女</td> -->
                    </tr>
                    <tr>
                        <th>职位</th>
                        <td>${linkMain.position }</td>
                    </tr>
                    <tr>
                        <th>尊称</th>
                        <td>${linkMain.nickname }</td>
                    </tr>
                    <tr>
                        <th>手机</th>
                        <td>${linkMain.phonenum }</td>
                    </tr>
                    <tr>
                        <th>QQ</th>
                        <td>${linkMain.qq }</td>
                    </tr>
                    <tr>
                        <th>邮件</th>
                        <td>${linkMain.email }</td>
                    </tr>
<!--                     <tr> -->
<!--                         <th>二维码</th> -->
<!--                         <td><img src="${pageContext.request.contextPath }/picture/4.png" alt=""/></td> -->
<!--                     </tr> -->
                    <tr>
                        <th>创建人</th>
                        <td>阿兰</td>
                    </tr>
                    <tr>
                        <th>创建时间</th>
                        <td><fmt:formatDate value="${linkmain.createTime}"
													pattern="yyyy-MM-dd HH:mm" /></td>
                    </tr>
                    <tr>
                        <th>更新时间</th>
                        <td><fmt:formatDate value="${linkmain.updateTime}"
													pattern="yyyy-MM-dd HH:mm" /></td>
                    </tr>
                    <tr>
                        <th>备注</th>
                        <td>${linkMain.remarks }</td>
                    </tr>
                    </tbody>
                </table>
                <div style="margin:0 auto; text-align:center;">
<!--                     <input onclick="location='/sms/send.html?selection%5B%5D=4&sendtype=contacts'" type="button" -->
<!--                            class="btn btn-primary" value="发送短信"/> -->
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>
