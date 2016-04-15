<%@ page contentType="text/html; charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="uengine" uri="http://www.uengine.io/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en"> <!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>OCE IAM | HOME</title>
    <!-- Meta -->
    <%@include file="../../template/header_js.jsp" %>

    <!-- CSS Page Style -->
    <link rel="stylesheet" href="/resources/assets/css/pages/profile.css">

</head>


<div class="wrapper">
    <%@include file="../../template/header.jsp" %>

    <!--=== Breadcrumbs ===-->
    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left">Management Console - ${management.groupName}</h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html">HOME</a></li>
                <li class="active">Management Console</li>
            </ul>
        </div>
    </div>
    <!--/breadcrumbs-->

    <%--<%@include file="./banner.jsp" %>--%>

    <!--=== Profile ===-->
    <div class="container content profile">
        <div class="row">
            <!--Left Sidebar-->
            <div class="col-md-3 md-margin-bottom-40">
                <%@include file="../menu.jsp" %>
                <script type="text/javascript">$('[name=list-menu-user]').addClass('active');</script>
            </div>
            <!--End Left Sidebar-->

            <!-- Profile Content -->
            <div class="col-md-9">
                <div class="row">
                    <div class="col-md-12">
                        <form action="/console/user/create" class="form-horizontal"
                              role="form"
                              id="oauthUserForm" method="post">
                            <h4>Create New Oauth User </h4>

                            <c:choose>
                                <c:when test="${duplicate}">
                                    <h4 style="color: #ff0000">Oauth user already exist. </h4>
                                </c:when>
                                <c:when test="${failed}">
                                    <h4 style="color: #ff0000">Oauth user create failed. </h4>
                                </c:when>
                            </c:choose>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Name <span class="color-red">*</span></label>

                                <div class="col-md-6">
                                    <input name="userName" type="text" class="form-control" value="">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Password <span class="color-red">*</span></label>

                                <div class="col-md-6">
                                    <input name="userPassword" type="text" class="form-control" value="">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-lg-2 control-label">Level <span class="color-red">*</span></label>

                                <div class="col-lg-9">
                                    <select name="level" class="form-control">
                                        <option value="1">1</option>
                                        <option value="2">2</option>
                                        <option value="3">3</option>
                                        <option value="4">4</option>
                                        <option value="5" selected>5</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Additional Information <span
                                        class="color-red">*</span></label>

                                <div class="col-md-6">
                                    <textarea rows="8" name="additionalInformation" class="form-control"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-offset-2 col-md-10">
                                    <button id="submitBtn" type="submit" class="btn-u btn-u-primary">Create User
                                    </button>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
            <!-- End Profile Content -->

        </div>
        <!--/end row-->
    </div>
    <!--=== End Profile ===-->

    <%@include file="../../template/footer.jsp" %>
</div>
<!--/wrapper-->

<%@include file="../../template/footer_js.jsp" %>

<script type="text/javascript">
    $(function () {
        var form = $('#oauthUserForm');

        //폼 발리데이션
        form.validate({

            focusInvalid: false, // do not focus the last invalid input
            ignore: "",
            rules: {
                userName: {
                    required: true
                },
                userPassword: {
                    required: true
                },
                level: {
                    required: true
                },
                additionalInformation: {
                    required: true
                }
            },
            messages: {
                userName: {
                    required: "<span style='color: red;'>Required filed</span>"
                },
                userPassword: {
                    required: "<span style='color: red;'>Required filed</span>"
                },
                level: {
                    required: "<span style='color: red;'>Required filed</span>"
                },
                additionalInformation: {
                    required: "<span style='color: red;'>Required filed</span>"
                }
            },
            invalidHandler: function () {
                blockStop();
            }
        });
    })
</script>
</html>