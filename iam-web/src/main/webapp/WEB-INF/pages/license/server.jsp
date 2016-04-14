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
      lang="en">
<!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>OCE IAM | HOME</title>

    <%@include file="../template/header_js.jsp" %>

    <link rel="stylesheet" href="/resources/assets/plugins/sky-forms-pro/skyforms/css/sky-forms.css">
    <link rel="stylesheet" href="/resources/assets/plugins/sky-forms-pro/skyforms/custom/custom-sky-forms.css">
    <!--[if lt IE 9]>
    <link rel="stylesheet" href="/resources/assets/plugins/sky-forms-pro/skyforms/css/sky-forms-ie8.css"><![endif]-->

    <!-- CSS Page Style -->
    <link rel="stylesheet" href="/resources/assets/css/pages/page_faq1.css">
    <link rel="stylesheet" href="/resources/assets/css/pages/page_search_inner.css">
</head>

<!-- body classes:
        "boxed": boxed layout mode e.g. <body class="boxed">
        "pattern-1 ... pattern-9": background patterns for boxed layout mode e.g. <body class="boxed pattern-1">
-->

<div class="wrapper">
    <%@include file="../template/header.jsp" %>

    <!--=== Breadcrumbs ===-->
    <div class="breadcrumbs">
        <div class="container">
            <h1 class="pull-left"><uengine:message code="menu.pricing"/></h1>
            <ul class="pull-right breadcrumb">
                <li><a href="index.html"><uengine:message code="menu.home"/></a></li>
                <li class="active"><uengine:message code="menu.pricing"/></li>
            </ul>
        </div>
    </div>
    <!--/breadcrumbs-->

    <%@include file="./banner.jsp" %>

    <!--=== Content Part ===-->

    <!--=== Search Block Version 2 ===-->
    <div class="search-block-v2">
        <div class="container">
            <div class="col-md-8 col-md-offset-2">
                <c:choose>
                    <c:when test="${invalid}">
                        <h2> <uengine:message code="license.notserverkey1"/> <br> <uengine:message code="license.notserverkey2"/> <a href="#"> <uengine:message code="license.notserverkey3"/> </a> <uengine:message code="license.notserverkey4"/> </h2>
                    </c:when>

                    <c:when test="${duplicated}">
                        <h2> <uengine:message code="license.duplicate.license.1"/> <br> <uengine:message code="license.duplicate.license.2"/> <a href="#"> <uengine:message code="license.duplicate.license.3"/> </a> <uengine:message code="license.duplicate.license.4"/> </h2>
                    </c:when>

                    <c:when test="${notfound}">
                        <h2> <uengine:message code="license.invailid.license.1"/> <br> <uengine:message code="license.invailid.license.2"/> <a href="#"> <uengine:message code="license.invailid.license.3"/> </a> <uengine:message code="license.invailid.license.4"/> </h2>
                    </c:when>

                    <c:otherwise>
                        <h2><uengine:message code="license.enter.key"/> </h2>
                    </c:otherwise>
                </c:choose>

                <form action="/license/createLicensekey" method="post" id="serverIdinputForm">
                    <div class="row">
                        <div class="col-md-3">
                            <div class="input-group">
                                <select name="version" class="form-control">
                                    <option value="2.0.0">Version 2.0.0</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-9">
                            <div class="input-group">
                                <input name="serverId" type="text" class="form-control" placeholder="<uengine:message code="license.serverid"></uengine:message>">
                                <span class="input-group-btn">
                                    <button class="btn-u" type="submit"><i class="fa fa-search"> <uengine:message code="license.get"></uengine:message> </i>
                                    </button>
                                </span>
                            </div>
                        </div>
                        <input name="identifier" type="hidden" value="${identifier}">
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        $(function () {
            $('#serverIdinputForm').validate({
                focusInvalid: true, // do not focus the last invalid input
                ignore: "",
                rules: {
                    serverId: {
                        required: true
                    }
                },
                messages: {
                    serverId: {
                        required: "<span style='color: red;'>"+message.msg('form.invalid.common')+"</span>"
                    }
                },
                invalidHandler: function() {
                    blockStop();
                },
                errorPlacement: function (error, element) {
                    element.parent().after(error);
                }
            });
        })
    </script>
    <!--/container-->
    <!--=== End Search Block Version 2 ===-->

    <div class="container content faq-page">
        <!-- FAQ Blocks -->
        <div class="row equal-height-columns margin-bottom-40">
            <div class="col-sm-4">
                <div class="easy-block-v3 service-or equal-height-column">
                    <div class="service-bg"></div>
                    <i class="icon-badge"></i>

                    <div class="inner-faq-b">
                        <h3>트라이얼 버전에 대해 알고싶어요.</h3>

                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus dapibus justo vel tincidunt
                            consectetur.</p>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="easy-block-v3 service-or equal-height-column">
                    <div class="service-bg"></div>
                    <i class="icon-directions"></i>

                    <div class="inner-faq-b">
                        <h3>라이선스 키를 설치하려면?</h3>

                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus dapibus justo vel tincidunt
                            consectetur.</p>
                    </div>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="easy-block-v3 service-or equal-height-column">
                    <div class="service-bg"></div>
                    <i class="icon-layers"></i>

                    <div class="inner-faq-b">
                        <h3>서버아이디를 어떻게 알 수 있나요?</h3>

                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus dapibus justo vel tincidunt
                            consectetur.</p>
                    </div>
                </div>
            </div>
        </div>
        <!-- End FAQ Blocks -->

        <!-- FAQ Content -->
        <div class="row">
            <!-- Begin Tab v1 -->
            <div class="col-md-6">
                <div class="tab-v1">
                    <ul class="nav nav-tabs margin-bottom-20">
                        <li class="active"><a data-toggle="tab" href="#home">Top 7 questions</a></li>
                        <li><a data-toggle="tab" href="#profile">Payment assistance</a></li>
                        <li><a data-toggle="tab" href="#messages">Work for Unify</a></li>
                        <li><a data-toggle="tab" href="#settings">About Unify</a></li>
                    </ul>
                    <div class="tab-content">
                        <!-- Tab Content 1 -->
                        <div id="home" class="tab-pane fade in active">
                            <div id="accordion-v1" class="panel-group acc-v1">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a href="#collapse-One" data-parent="#accordion-v1" data-toggle="collapse"
                                               class="accordion-toggle">
                                                How to Customize Sky-Forms
                                            </a>
                                        </h4>
                                    </div>
                                    <div class="panel-collapse collapse in" id="collapse-One">
                                        <div class="panel-body">
                                            Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry
                                            richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard
                                            dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon
                                            tempor, sunt aliqua put a bird on it squid single-origin coffee nulla
                                            assumenda shoreditch et.
                                        </div>
                                    </div>
                                </div>

                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a href="#collapse-Two" data-parent="#accordion-v1" data-toggle="collapse"
                                               class="accordion-toggle">
                                                What is "Error 404" page?
                                            </a>
                                        </h4>
                                    </div>
                                    <div class="panel-collapse collapse" id="collapse-Two">
                                        <div class="panel-body">
                                            Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry
                                            richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard
                                            dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon
                                            tempor, sunt aliqua put a bird on it squid single-origin coffee nulla
                                            assumenda shoreditch et.
                                        </div>
                                    </div>
                                </div>

                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a href="#collapse-Three" data-parent="#accordion-v1" data-toggle="collapse"
                                               class="accordion-toggle">
                                                How to apply background with "Opacity"
                                            </a>
                                        </h4>
                                    </div>
                                    <div class="panel-collapse collapse" id="collapse-Three">
                                        <div class="panel-body">
                                            Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt
                                            sapiente ea proident. Food truck quinoa nesciunt laborum eiusmodolf moon
                                            tempor, sunt aliqua put a bird. Ad vegan excepteur butcher vice lomo.
                                            Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth
                                            nesciunt you probably haven't heard of them accusamus labore sustainable
                                            VHS.
                                        </div>
                                    </div>
                                </div>

                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a href="#collapse-Four" data-parent="#accordion-v1" data-toggle="collapse"
                                               class="accordion-toggle">
                                                How to use Vector Map
                                            </a>
                                        </h4>
                                    </div>
                                    <div class="panel-collapse collapse" id="collapse-Four">
                                        <div class="panel-body">
                                            Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry
                                            richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard
                                            dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon
                                            tempor, sunt aliqua put a bird on it squid single-origin coffee nulla
                                            assumenda shoreditch et.
                                        </div>
                                    </div>
                                </div>

                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a href="#collapse-Five" data-parent="#accordion-v1" data-toggle="collapse"
                                               class="accordion-toggle">
                                                Can I use Unify Template on multiple projects?
                                            </a>
                                        </h4>
                                    </div>
                                    <div class="panel-collapse collapse" id="collapse-Five">
                                        <div class="panel-body">
                                            Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry
                                            richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard
                                            dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon
                                            tempor, sunt aliqua put a bird on it squid single-origin coffee nulla
                                            assumenda shoreditch et.
                                        </div>
                                    </div>
                                </div>

                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a href="#collapse-Six" data-parent="#accordion-v1" data-toggle="collapse"
                                               class="accordion-toggle">
                                                How can I make sure that I always have the latest version of Unify?
                                            </a>
                                        </h4>
                                    </div>
                                    <div class="panel-collapse collapse" id="collapse-Six">
                                        <div class="panel-body">
                                            Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry
                                            richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard
                                            dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon
                                            tempor, sunt aliqua put a bird on it squid single-origin coffee nulla
                                            assumenda shoreditch et.
                                        </div>
                                    </div>
                                </div>

                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a href="#collapse-Seven" data-parent="#accordion-v1" data-toggle="collapse"
                                               class="accordion-toggle">
                                                Is it safe to Update?
                                            </a>
                                        </h4>
                                    </div>
                                    <div class="panel-collapse collapse" id="collapse-Seven">
                                        <div class="panel-body">
                                            Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry
                                            richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard
                                            dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon
                                            tempor, sunt aliqua put a bird on it squid single-origin coffee nulla
                                            assumenda shoreditch et.
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- End Tab Content 1 -->

                        <!-- Tab Content 2 -->
                        <div id="profile" class="tab-pane fade">
                            <div id="accordion-v2" class="panel-group acc-v1">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a href="#collapse-v2-One" data-parent="#accordion-v2"
                                               data-toggle="collapse" class="accordion-toggle">
                                                Payment assistance first
                                            </a>
                                        </h4>
                                    </div>
                                    <div class="panel-collapse collapse in" id="collapse-v2-One">
                                        <div class="panel-body">
                                            Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry
                                            richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard
                                            dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon
                                            tempor, sunt aliqua put a bird on it squid single-origin coffee nulla
                                            assumenda shoreditch et.
                                        </div>
                                    </div>
                                </div>

                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a href="#collapse-v2-Two" data-parent="#accordion-v2"
                                               data-toggle="collapse" class="accordion-toggle">
                                                Payment assistance second
                                            </a>
                                        </h4>
                                    </div>
                                    <div class="panel-collapse collapse" id="collapse-v2-Two">
                                        <div class="panel-body">
                                            Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry
                                            richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard
                                            dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon
                                            tempor, sunt aliqua put a bird on it squid single-origin coffee nulla
                                            assumenda shoreditch et.
                                        </div>
                                    </div>
                                </div>

                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a href="#collapse-v2-Three" data-parent="#accordion-v2"
                                               data-toggle="collapse" class="accordion-toggle">
                                                Payment assistance third
                                            </a>
                                        </h4>
                                    </div>
                                    <div class="panel-collapse collapse" id="collapse-v2-Three">
                                        <div class="panel-body">
                                            Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt
                                            sapiente ea proident. Food truck quinoa nesciunt laborum eiusmodolf moon
                                            tempor, sunt aliqua put a bird. Ad vegan excepteur butcher vice lomo.
                                            Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth
                                            nesciunt you probably haven't heard of them accusamus labore sustainable
                                            VHS.
                                        </div>
                                    </div>
                                </div>

                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a href="#collapse-v2-Four" data-parent="#accordion-v2"
                                               data-toggle="collapse" class="accordion-toggle">
                                                Payment assistanc fourth
                                            </a>
                                        </h4>
                                    </div>
                                    <div class="panel-collapse collapse" id="collapse-v2-Four">
                                        <div class="panel-body">
                                            Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry
                                            richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard
                                            dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon
                                            tempor, sunt aliqua put a bird on it squid single-origin coffee nulla
                                            assumenda shoreditch et.
                                        </div>
                                    </div>
                                </div>

                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a href="#collapse-v2-Five" data-parent="#accordion-v2"
                                               data-toggle="collapse" class="accordion-toggle">
                                                Payment assistanc fifth
                                            </a>
                                        </h4>
                                    </div>
                                    <div class="panel-collapse collapse" id="collapse-v2-Five">
                                        <div class="panel-body">
                                            Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry
                                            richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard
                                            dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon
                                            tempor, sunt aliqua put a bird on it squid single-origin coffee nulla
                                            assumenda shoreditch et.
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Tab Content 2 -->

                        <!-- Tab Content 3 -->
                        <div id="messages" class="tab-pane fade">
                            <div id="accordion-v3" class="panel-group acc-v1">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a href="#collapse-v3-One" data-parent="#accordion-v3"
                                               data-toggle="collapse" class="accordion-toggle">
                                                Work for Unify first
                                            </a>
                                        </h4>
                                    </div>
                                    <div class="panel-collapse collapse in" id="collapse-v3-One">
                                        <div class="panel-body">
                                            Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry
                                            richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard
                                            dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon
                                            tempor, sunt aliqua put a bird on it squid single-origin coffee nulla
                                            assumenda shoreditch et.
                                        </div>
                                    </div>
                                </div>

                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a href="#collapse-v3-Two" data-parent="#accordion-v3"
                                               data-toggle="collapse" class="accordion-toggle">
                                                Work for Unify second
                                            </a>
                                        </h4>
                                    </div>
                                    <div class="panel-collapse collapse" id="collapse-v3-Two">
                                        <div class="panel-body">
                                            Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry
                                            richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard
                                            dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon
                                            tempor, sunt aliqua put a bird on it squid single-origin coffee nulla
                                            assumenda shoreditch et.
                                        </div>
                                    </div>
                                </div>

                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a href="#collapse-v3-Three" data-parent="#accordion-v3"
                                               data-toggle="collapse" class="accordion-toggle">
                                                Work for Unify third
                                            </a>
                                        </h4>
                                    </div>
                                    <div class="panel-collapse collapse" id="collapse-v3-Three">
                                        <div class="panel-body">
                                            Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt
                                            sapiente ea proident. Food truck quinoa nesciunt laborum eiusmodolf moon
                                            tempor, sunt aliqua put a bird. Ad vegan excepteur butcher vice lomo.
                                            Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth
                                            nesciunt you probably haven't heard of them accusamus labore sustainable
                                            VHS.
                                        </div>
                                    </div>
                                </div>

                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a href="#collapse-v3-Four" data-parent="#accordion-v3"
                                               data-toggle="collapse" class="accordion-toggle">
                                                Work for Unify fourth
                                            </a>
                                        </h4>
                                    </div>
                                    <div class="panel-collapse collapse" id="collapse-v3-Four">
                                        <div class="panel-body">
                                            Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry
                                            richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard
                                            dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon
                                            tempor, sunt aliqua put a bird on it squid single-origin coffee nulla
                                            assumenda shoreditch et.
                                        </div>
                                    </div>
                                </div>

                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a href="#collapse-v3-Five" data-parent="#accordion-v3"
                                               data-toggle="collapse" class="accordion-toggle">
                                                Work for Unify fifth
                                            </a>
                                        </h4>
                                    </div>
                                    <div class="panel-collapse collapse" id="collapse-v3-Five">
                                        <div class="panel-body">
                                            Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry
                                            richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard
                                            dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon
                                            tempor, sunt aliqua put a bird on it squid single-origin coffee nulla
                                            assumenda shoreditch et.
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Tab Content 3 -->

                        <!-- Tab Content 4 -->
                        <div id="settings" class="tab-pane fade">
                            <div id="accordion-v4" class="panel-group acc-v1">
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a href="#collapse-v4-One" data-parent="#accordion-v4"
                                               data-toggle="collapse" class="accordion-toggle">
                                                About Unify first
                                            </a>
                                        </h4>
                                    </div>
                                    <div class="panel-collapse collapse in" id="collapse-v4-One">
                                        <div class="panel-body">
                                            Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry
                                            richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard
                                            dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon
                                            tempor, sunt aliqua put a bird on it squid single-origin coffee nulla
                                            assumenda shoreditch et.
                                        </div>
                                    </div>
                                </div>

                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a href="#collapse-v4-Two" data-parent="#accordion-v4"
                                               data-toggle="collapse" class="accordion-toggle">
                                                About Unify second
                                            </a>
                                        </h4>
                                    </div>
                                    <div class="panel-collapse collapse" id="collapse-v4-Two">
                                        <div class="panel-body">
                                            Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry
                                            richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard
                                            dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon
                                            tempor, sunt aliqua put a bird on it squid single-origin coffee nulla
                                            assumenda shoreditch et.
                                        </div>
                                    </div>
                                </div>

                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a href="#collapse-v4-Three" data-parent="#accordion-v4"
                                               data-toggle="collapse" class="accordion-toggle">
                                                About Unify third
                                            </a>
                                        </h4>
                                    </div>
                                    <div class="panel-collapse collapse" id="collapse-v4-Three">
                                        <div class="panel-body">
                                            Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt
                                            sapiente ea proident. Food truck quinoa nesciunt laborum eiusmodolf moon
                                            tempor, sunt aliqua put a bird. Ad vegan excepteur butcher vice lomo.
                                            Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth
                                            nesciunt you probably haven't heard of them accusamus labore sustainable
                                            VHS.
                                        </div>
                                    </div>
                                </div>

                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a href="#collapse-v4-Four" data-parent="#accordion-v4"
                                               data-toggle="collapse" class="accordion-toggle">
                                                About Unify fourth
                                            </a>
                                        </h4>
                                    </div>
                                    <div class="panel-collapse collapse" id="collapse-v4-Four">
                                        <div class="panel-body">
                                            Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry
                                            richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard
                                            dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon
                                            tempor, sunt aliqua put a bird on it squid single-origin coffee nulla
                                            assumenda shoreditch et.
                                        </div>
                                    </div>
                                </div>

                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a href="#collapse-v4-Five" data-parent="#accordion-v4"
                                               data-toggle="collapse" class="accordion-toggle">
                                                About Unify fifth
                                            </a>
                                        </h4>
                                    </div>
                                    <div class="panel-collapse collapse" id="collapse-v4-Five">
                                        <div class="panel-body">
                                            Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry
                                            richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard
                                            dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon
                                            tempor, sunt aliqua put a bird on it squid single-origin coffee nulla
                                            assumenda shoreditch et.
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- Tab Content 4 -->
                    </div>
                </div>
            </div>
            <!--/col-md-6-->
            <!--End Tab v1-->

            <!-- Popular Topics -->
            <div class="col-md-6">
                <div class="headline"><h2>Popular Topics</h2></div>
                <div class="row main-check margin-bottom-30">
                    <div class="col-xs-6 md-margin-bottom-20">
                        <ul class="list-unstyled check-style">
                            <li><i class="fa fa-angle-right color-green"></i> <a href="#">How does encryption work?</a>
                            </li>
                            <li><i class="fa fa-angle-right color-green"></i> <a href="#">Is Unify Template legal?</a>
                            </li>
                            <li><i class="fa fa-angle-right color-green"></i> <a href="#">Can I download Unify multiple
                                times?</a></li>
                            <li><i class="fa fa-angle-right color-green"></i> <a href="#">How to Update Unify
                                Template?</a></li>
                            <li><i class="fa fa-angle-right color-green"></i> <a href="#">How does encryption work?</a>
                            </li>
                            <li><i class="fa fa-angle-right color-green"></i> <a href="#">Is Unify Template legal?</a>
                            </li>
                            <li><i class="fa fa-angle-right color-green"></i> <a href="#">Can I download Unify multiple
                                times?</a></li>
                        </ul>
                    </div>
                    <div class="col-xs-6">
                        <ul class="list-unstyled check-style">
                            <li><i class="fa fa-angle-right color-green"></i> <a href="#">How does encryption work?</a>
                            </li>
                            <li><i class="fa fa-angle-right color-green"></i> <a href="#">Is Unify Template legal?</a>
                            </li>
                            <li><i class="fa fa-angle-right color-green"></i> <a href="#">Can I download Unify multiple
                                times?</a></li>
                            <li><i class="fa fa-angle-right color-green"></i> <a href="#">How to Update Unify
                                Template?</a></li>
                            <li><i class="fa fa-angle-right color-green"></i> <a href="#">How does encryption work?</a>
                            </li>
                            <li><i class="fa fa-angle-right color-green"></i> <a href="#">Is Unify Template legal?</a>
                            </li>
                            <li><i class="fa fa-angle-right color-green"></i> <a href="#">Can I download Unify multiple
                                times?</a></li>
                        </ul>
                    </div>
                </div>

                <hr>

                <div class="row">
                    <div class="col-md-6">
                        <div class="faq-add">
                            <div class="top-part">
                                <i class="icon-support"></i>

                                <h3 class="new-title">
                                    <a href="#">Online Support</a>
                                </h3>
                            </div>
                            <p>Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad
                                squid</p>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="faq-add">
                            <div class="top-part">
                                <i class="icon-lock"></i>

                                <h3 class="new-title">
                                    <a href="#">Security &amp; Privacy</a>
                                </h3>
                            </div>
                            <p>Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad
                                squid</p>
                        </div>
                    </div>
                </div>
            </div>
            <!--/col-md-6-->
            <!-- End Popular Topics -->
        </div>
        <!-- End FAQ Content -->
    </div>
    <!--/container-->
    <!--=== End FAQ Page ===-->

    <!--=== End Content Part ===-->


    <%@include file="../template/footer.jsp" %>
</div>
<!--/wrapper-->
<%@include file="../template/footer_js.jsp" %>

</html>
