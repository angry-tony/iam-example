<%@ page contentType="text/html; charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="uengine" uri="http://www.uengine.io/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

    <%@include file="template/header_js.jsp" %>

    <!-- CSS Page Style -->
    <link rel="stylesheet" href="/resources/assets/css/pages/page_contact.css">
</head>

<!-- body classes:
        "boxed": boxed layout mode e.g. <body class="boxed">
        "pattern-1 ... pattern-9": background patterns for boxed layout mode e.g. <body class="boxed pattern-1">
-->

<div class="wrapper">
    <%@include file="template/header.jsp" %>

    <!--=== Breadcrumbs ===-->
    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left"><uengine:message code="menu.download"/></h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html"><uengine:message code="menu.home"/></a></li>
                <li class="active"><uengine:message code="menu.download"/></li>
            </ul>
        </div>
    </div>
    <!--/breadcrumbs-->

    <!--=== Content Part ===-->
    <div class="container content">
        <div class="row margin-bottom-30">
            <!-- Heading Examples -->
            <div class="headline-center margin-bottom-30"><h2><uengine:message code="download.link"></uengine:message></h2></div>

            <div class="service-block service-block-default">
                <i class="icon-custom icon-color-dark rounded-x fa fa-lightbulb-o"></i>

                <p><uengine:message code="download.compatibility.text"></uengine:message></p>
            </div>

            <%@include file="flamingo/flamingo-download-list-2.0.0.jsp" %>

            <div class="headline-center margin-bottom-60"><h2><uengine:message code="download.release.title"></uengine:message></h2></div>

            <%@include file="flamingo/flamingo-release-2.0.0.jsp" %>

        </div>
        <!--/row-->
    </div>
    <!--/container-->
    <!--=== End Content Part ===-->


    <%@include file="template/footer.jsp" %>
</div>
<!--/wrapper-->
<%@include file="template/footer_js.jsp" %>

</html>
