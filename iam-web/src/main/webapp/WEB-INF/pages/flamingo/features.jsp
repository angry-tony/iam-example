<%@ page contentType="text/html; charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="uengine" uri="http://www.uengine.io/tags" %>

<!DOCTYPE html>
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if !IE]><!-->
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org"
      lang="en">
<!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>OCE IAM | HOME</title>

    <%@include file="../template/header_js.jsp" %>
</head>

<!-- body classes:
        "boxed": boxed layout mode e.g. <body class="boxed">
        "pattern-1 ... pattern-9": background patterns for boxed layout mode e.g. <body class="boxed pattern-1">
-->

<div class="wrapper">
    <%@include file="../template/header.jsp" %>


    <%@include file="banner.jsp" %>

    <%@include file="list.jsp" %>

    <!--=== Content Part ===-->
    <div class="container content">
        <div class="row">

            <!-- Begin Content -->
            <div class="col-md-12">

                <div class="headline-center"><h2><uengine:message code="flamingo.feature.intro"></uengine:message> </h2></div>

                <br><br>

                <%@include file="./features/monitoring.jsp" %>

                <hr>

                <%@include file="./features/workflow.jsp" %>

                <hr>

                <%@include file="./features/hive.jsp" %>

                <hr>

                <%@include file="./features/pig.jsp" %>

                <hr>

                <%@include file="./features/hdfsbrowser.jsp" %>

                <hr>

                <%@include file="./features/sqlhadoop.jsp" %>

                <hr>

                <%@include file="./features/batch.jsp" %>

                <hr>

                <%@include file="./features/rstudio.jsp" %>

                <hr>

                <%@include file="./features/terminal.jsp" %>

                <hr>

                <%@include file="./features/system.jsp" %>

            </div>
        </div>
    </div>
    <!--/container-->
    <!--=== End Content Part ===-->


    <%@include file="../template/footer.jsp" %>
</div>
<!--/wrapper-->

<%@include file="../template/footer_js.jsp" %>
</html>
