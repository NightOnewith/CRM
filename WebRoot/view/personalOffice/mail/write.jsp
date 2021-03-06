﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <jsp:include page="/common.jsp"></jsp:include>
</head>

<body class="navbar-top">
<div class="modal-shiftfix">
    <jsp:include page="/menu.jsp"></jsp:include>
    <div class="container-fluid main-content">
        <div class="row">
            <div class="col-sm-2">
                <div class="list-group">
                    <a class="list-group-item " href="mail/index.html">
                        <p>收件箱</p>
                    </a>
                    <a class="list-group-item " href="mail/starred.html">
                        <p>
                            星标邮件
                        </p>
                    </a>
                    <a class="list-group-item " href="mail/important.html">
                        <p>
                            重要邮件
                        </p>
                    </a>
                    <a class="list-group-item " href="mail/sent.html">
                        <p>发件箱</p>
                    </a>
                </div>
            </div>
            <div class="col-sm-10">
                <div class="widget-container fluid-height clearfix">
                    <div class="widget-content padded clearfix">
                        <form method="post" action="" id="writeForm">
                            <input type="hidden" name="Mail[addressee_id]" id="addressee_id" value=""/>
                            <input type="hidden" name="_csrf"
                                   value="WERrNjB6dXk6Mz1dCQM2AQkqW2Z9QhoyBy4aU1wqHCEvECV.QjwPTg=="/>

                            <div class="form-group clearfix">
                                <button type="submit" class="btn btn-sm btn-success"><i
                                        class="fa fa-share-square-o"></i> 发送邮件
                                </button>
                                <button type="reset" class="btn btn-sm btn-default">重置</button>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <input id="addressee" name="Mail[addressee]" readonly class="form-control"
                                           placeholder="选择收件人...." type="text">
                                    <span class="input-group-btn"><button class="btn btn-default weiBoxs-dialog"
                                                                          type="button" dialog-title="选择收件人"
                                                                          dialog-url="/mail/sel-manage.html"
                                                                          dialog-callback="selectManages">点击选择
                                    </button></span>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="input-group">
                                    <span class="input-group-addon">主&nbsp;&nbsp;&nbsp;&nbsp;题</span>
                                    <input type="text" name="Mail[subject]" class="form-control" placeholder="邮件主题....">
                                </div>
                            </div>
                            <div class="form-group">
                                <h5 style="margin-top: 30px">邮件内容</h5>
                                <textarea class="form-control"></textarea>
                            </div>
                            <div class="form-group clearfix">
                                <span class="pull-left"></span>
                                <span class="pull-right">发件人：小明</span>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>
