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

    <!-- CSS Page Style -->
    <link rel="stylesheet" href="/resources/assets/css/pages/page_pricing.css">
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

    <%--<%@include file="../flamingo/banner.jsp" %>--%>

    <!--=== Content Part ===-->

    <!-- Pricing Light -->
    <div class="container content">

        <div class="row">
            <div class="col-md-6">

                <div class="row">
                    <img class="img-responsive col-sm-11" src="<uengine:message code="flamingo.feature.image.url1"/>" alt="">
                </div>
                <br><br>
                <%--<div class="row">--%>
                    <%--<div class="col-sm-12" align="center">--%>
                        <%--<a href="http://211.109.9.71:18080/" target="_blank" class="btn-u btn-u-default" type="button"><i class="fa fa-picture-o"></i> <uengine:message code="shop.pricing.preview"/> </a>--%>
                        <%--<a href="http://211.109.9.71:18080/" target="_blank" class="btn-u btn-u-default" type="button"> <uengine:message code="shop.pricing.preview.acount"/> </a>--%>
                    <%--</div>--%>
                <%--</div>--%>
                <%--<br><br>--%>
                <p><uengine:message code="flamingo.feature.list.title"/></p><br>
                <br>
                <div class="row">
                    <ul class="col-xs-6 list-unstyled lists-v1">
                        <li><a href="/product/flamingo/features#monitoring"><i class="fa fa-angle-right"></i><uengine:message code="menu.product.flamingo.features.monitoring"/></a></li>
                        <li><a href="/product/flamingo/features#workflow"><i class="fa fa-angle-right"></i><uengine:message code="menu.product.flamingo.features.workflow"/></a></li>
                        <li><a href="/product/flamingo/features#hive"><i class="fa fa-angle-right"></i><uengine:message code="menu.product.flamingo.features.hive"/></a></li>
                        <li><a href="/product/flamingo/features#pig"><i class="fa fa-angle-right"></i><uengine:message code="menu.product.flamingo.features.pig"/></a></li>
                        <li><a href="/product/flamingo/features#hdfs"><i class="fa fa-angle-right"></i><uengine:message code="menu.product.flamingo.features.hdfs"/></a></li>
                        <li><a href="/product/flamingo/features#algorithm"><i class="fa fa-angle-right"></i><uengine:message code="menu.product.flamingo.features.algorithm"/></a></li>
                    </ul>
                    <ul class="col-xs-6 list-unstyled lists-v1">
                        <li><a href="/product/flamingo/features#sqlhadoop"><i class="fa fa-angle-right"></i><uengine:message code="menu.product.flamingo.features.sqlhadoop"/></a></li>
                        <li><a href="/product/flamingo/features#batch"><i class="fa fa-angle-right"></i><uengine:message code="menu.product.flamingo.features.batch"/></a></li>
                        <li><a href="/product/flamingo/features#rstudio"><i class="fa fa-angle-right"></i><uengine:message code="menu.product.flamingo.features.rstudio"/></a></li>
                        <%--
                                            <li><a href="/product/flamingo/features#visualization"><i class="fa fa-angle-right"></i><uengine:message code="menu.product.flamingo.features.visualization"/></a></li>
                        --%>
                        <li><a href="/product/flamingo/features#archive"><i class="fa fa-angle-right"></i><uengine:message code="menu.product.flamingo.features.archive"/></a></li>
                        <li><a href="/product/flamingo/features#terminal"><i class="fa fa-angle-right"></i><uengine:message code="menu.product.flamingo.features.terminal"/></a></li>
                        <li><a href="/product/flamingo/features#system"><i class="fa fa-angle-right"></i><uengine:message code="menu.product.flamingo.features.system"/></a></li>
                    </ul>
                </div>

            </div>

            <div class="pricing-light col-md-6">
                <div class="col-sm-10 col-sm-offset-1">
                    <div class="pricing hover-effect" name="product_box" data-family="SUBSCRIPTION">
                        <div class="pricing-head">
                            <h3><uengine:message code="shop.pricing.box.title"/> <span> <uengine:message code="shop.pricing.box.desc"/> </span></h3>

                            <div class="col-sm-10 col-sm-offset-1" style="margin-top: 10px;">
                                <form class="form-horizontal" id="pricingForm" method="get" action="/shop">
                                    <div class="form-group">
                                        <label class="col-lg-3 control-label"><uengine:message code="shop.pricing.box.country"/> <span class="color-red">*</span></label>

                                        <div class="col-lg-9">
                                            <select name="country" class="form-control">
                                                <%@include file="../template/countryList.jsp" %>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-lg-3 control-label"><uengine:message code="shop.pricing.box.node"/> <span class="color-red">*</span></label>

                                        <div class="col-lg-9">
                                            <select name="nodeNum" class="form-control">
                                                <option value="10">10 nodes</option>
                                                <option value="20">20 nodes</option>
                                                <option value="30">30 nodes</option>
                                                <option value="40">40 nodes</option>
                                                <option value="50">50 nodes</option>
                                                <option value="0">50+ nodes</option>
                                            </select>
                                        </div>
                                    </div>
                                    <input name="productName" type="hidden" value="${productName}">
                                    <input name="family" type="hidden" value="${family}">
                                    <input name="license" type="hidden" value="${license}">
                                    <input name="version" type="hidden" value="${version}">
                                </form>
                            </div>
                            <div style="clear: both"></div>

                        </div>
                        <ul class="pricing-content list-unstyled">
                            <li class="bg-color">
                                <tag name="node_tag"></tag>
                                <uengine:message code="shop.pricing.box.node"/>
                            </li>
                            <li>
                                <uengine:message code="shop.pricing.box.1"/>
                            </li>
                            <li class="bg-color"><uengine:message code="shop.pricing.box.2"/> </li>
                            <li><uengine:message code="shop.pricing.box.3"/> </li>
                            <li class="bg-color"><uengine:message code="shop.pricing.box.4"/> </li>
                            <li><uengine:message code="shop.pricing.box.5"/> </li>
                        </ul>
                        <div class="pricing-footer">
                            <h4>
                                <tag name="price_tag"></tag>
                                <span><uengine:message code="shop.pricing.box.year"/> </span></h4>

                            <p><uengine:message code="shop.pricing.box.cluster"/> </p>
                            <a href="/license/flamingo-trial" class="btn btn-primary" type="button" style="margin-bottom: 10px;"><uengine:message code="shop.pricing.box.free"/> </a>
                            <br>
                            <a id="purchaseSubmit" name="purchase_tag" class="btn-u btn-brd btn-brd-hover btn-u-default btn-u-xs"><i
                                    class="fa fa-shopping-cart"></i> <uengine:message code="shop.pricing.box.purchase"/> </a>
                            <a name="gotoContact" href="/contact" class="btn-u btn-brd btn-brd-hover btn-u-default btn-u-xs"><i
                                    class="fa fa-shopping-cart"></i> <uengine:message code="shop.pricing.box.contact"/> </a>
                            <p name="gotoContact"><uengine:message code="shop.pricing.box.disable"/> </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%--<!--=== Service Block v5 ===-->--%>
        <%--<div class="service-block-v5">--%>
        <%--<div class="container">--%>
        <%--<div class="row equal-height-columns">--%>
        <%--<div class="col-md-4 service-inner equal-height-column">--%>
        <%--<i class="icon-custom icon-md rounded-x icon-bg-u icon-diamond"></i>--%>
        <%--<span><uengine:message code="flamingo.intro.characteristic.1.title"/> </span>--%>

        <%--<p><uengine:message code="flamingo.intro.characteristic.1.sub"/></p>--%>
        <%--</div>--%>
        <%--<div class="col-md-4 service-inner equal-height-column service-border">--%>
        <%--<i class="icon-custom icon-md rounded-x icon-bg-u icon-rocket"></i>--%>
        <%--<span><uengine:message code="flamingo.intro.characteristic.2.title"/> </span>--%>

        <%--<p><uengine:message code="flamingo.intro.characteristic.2.sub"/></p>--%>
        <%--</div>--%>
        <%--<div class="col-md-4 service-inner equal-height-column">--%>
        <%--<i class="icon-custom icon-md rounded-x icon-bg-u icon-support"></i>--%>
        <%--<span><uengine:message code="flamingo.intro.characteristic.3.title"/> </span>--%>

        <%--<p><uengine:message code="flamingo.intro.characteristic.3.sub"/></p>--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--<!--/end row-->--%>
        <%--</div>--%>
        <%--<!--/end container-->--%>
        <%--</div>--%>
        <%--<!--=== End Service Block v5 ===-->--%>

        <%--&lt;%&ndash; SYSTEM REQUIREMENT&ndash;%&gt;--%>
        <%--<div class="container content div-center" id="requirement">--%>
        <%--<div class="headline-center margin-bottom-20">--%>
        <%--<h2><uengine:message code="flamingo.intro.requirement.title"/></h2>--%>
        <%--</div>--%>

        <%--<div class="service-block service-block-default">--%>
        <%--<i class="icon-custom icon-color-dark rounded-x fa fa-lightbulb-o"></i>--%>

        <%--<p><uengine:message code="download.compatibility.text"/></p>--%>
        <%--</div>--%>

        <%--<div class="panel panel-default margin-bottom-20 table-requirement">--%>
        <%--<table class="table table-bordered">--%>
        <%--<thead>--%>
        <%--<tr>--%>
        <%--<th width="150"><uengine:message code="flamingo.intro.requirement.title.1"/></th>--%>
        <%--<th><uengine:message code="flamingo.intro.requirement.title.2"/></th>--%>
        <%--</tr>--%>
        <%--</thead>--%>

        <%--<tbody>--%>
        <%--<tr>--%>
        <%--<td><uengine:message code="flamingo.intro.requirement.1.key"/></td>--%>
        <%--<td><uengine:message code="flamingo.intro.requirement.1.value"/></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
        <%--<td><uengine:message code="flamingo.intro.requirement.2.key"/></td>--%>
        <%--<td><uengine:message code="flamingo.intro.requirement.2.value"/></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
        <%--<td><uengine:message code="flamingo.intro.requirement.3.key"/></td>--%>
        <%--<td><uengine:message code="flamingo.intro.requirement.3.value"/></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
        <%--<td><uengine:message code="flamingo.intro.requirement.4.key"/></td>--%>
        <%--<td><uengine:message code="flamingo.intro.requirement.4.value"/></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
        <%--<td><uengine:message code="flamingo.intro.requirement.5.key"/></td>--%>
        <%--<td><uengine:message code="flamingo.intro.requirement.5.value"/></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
        <%--<td><uengine:message code="flamingo.intro.requirement.6.key"/></td>--%>
        <%--<td><uengine:message code="flamingo.intro.requirement.6.value"/></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
        <%--<td><uengine:message code="flamingo.intro.requirement.7.key"/></td>--%>
        <%--<td><uengine:message code="flamingo.intro.requirement.7.value"/></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
        <%--<td><uengine:message code="flamingo.intro.requirement.8.key"/></td>--%>
        <%--<td><uengine:message code="flamingo.intro.requirement.8.value"/></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
        <%--<td><uengine:message code="flamingo.intro.requirement.9.key"/></td>--%>
        <%--<td><uengine:message code="flamingo.intro.requirement.9.value"/></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
        <%--<td><uengine:message code="flamingo.intro.requirement.10.key"/></td>--%>
        <%--<td><uengine:message code="flamingo.intro.requirement.10.value"/></td>--%>
        <%--</tr>--%>
        <%--</tbody>--%>
        <%--</table>--%>
        <%--</div>--%>
        <%--</div>--%>

        <%--<!--=== Container Part ===-->--%>
        <%--<div class="bg-color-light">--%>
        <%--<div class="container content">--%>
        <%--<div class="headline-center margin-bottom-60">--%>
        <%--<h2><uengine:message code="flamingo.intro.why.title"/></h2>--%>

        <%--<p><uengine:message code="flamingo.intro.why.reason"/></p>--%>
        <%--</div>--%>

        <%--<div class="row">--%>
        <%--<div class="col-sm-6">--%>
        <%--<!-- Testimonials v4 -->--%>
        <%--<div class="testimonials-v4">--%>
        <%--<div class="testimonials-v4-in">--%>
        <%--<p><uengine:message code="flamingo.intro.person.1.talk"/></p>--%>
        <%--</div>--%>
        <%--<img class="rounded-x" src="/resources/images/person/photo_off.jpg" alt="Testimonials">--%>
        <%--<span class="testimonials-author">--%>
        <%--<uengine:message code="flamingo.intro.person.1.name"/> <br><em>CEO, <a href="http://www.uengine.org">uengine solutions</a></em>--%>
        <%--</span>--%>
        <%--</div>--%>
        <%--<!-- End Testimonials v4 -->--%>
        <%--</div>--%>
        <%--<div class="col-sm-6">--%>
        <%--<!-- Testimonials v4 -->--%>
        <%--<div class="testimonials-v4">--%>
        <%--<div class="testimonials-v4-in">--%>
        <%--<p><uengine:message code="flamingo.intro.person.2.talk"/></p>--%>
        <%--</div>--%>
        <%--<img class="rounded-x" src="/resources/images/person/photo_off.jpg" alt="Testimonials">--%>
        <%--<span class="testimonials-author">--%>
        <%--<uengine:message code="flamingo.intro.person.2.name"/> <br><em>CEO, <a href="http://www.fastcat.co">Fastcat</a></em>--%>
        <%--</span>--%>
        <%--</div>--%>
        <%--<!-- End Testimonials v4 -->--%>
        <%--</div>--%>
        <%--</div>--%>
        <%--<!--/end row-->--%>
        <%--</div>--%>
        <%--<!--/end container-->--%>
        <%--</div>--%>

        <%--<div class="row">--%>

        <%--<!-- Begin Content -->--%>
        <%--<div class="col-md-12">--%>

        <%--<div class="headline-center"><h2><uengine:message code="flamingo.feature.intro"></uengine:message> </h2></div>--%>

        <%--<br><br>--%>

        <%--<%@include file="../flamingo/features/monitoring.jsp" %>--%>

        <%--<hr>--%>

        <%--<%@include file="../flamingo/features/workflow.jsp" %>--%>

        <%--<hr>--%>

        <%--<%@include file="../flamingo/features/hive.jsp" %>--%>

        <%--<hr>--%>

        <%--<%@include file="../flamingo/features/pig.jsp" %>--%>

        <%--<hr>--%>

        <%--<%@include file="../flamingo/features/hdfsbrowser.jsp" %>--%>

        <%--<hr>--%>

        <%--<%@include file="../flamingo/features/sqlhadoop.jsp" %>--%>

        <%--<hr>--%>

        <%--<%@include file="../flamingo/features/batch.jsp" %>--%>

        <%--<hr>--%>

        <%--<%@include file="../flamingo/features/rstudio.jsp" %>--%>

        <%--<hr>--%>

        <%--<%@include file="../flamingo/features/terminal.jsp" %>--%>

        <%--<hr>--%>

        <%--<%@include file="../flamingo/features/system.jsp" %>--%>

        <%--</div>--%>
        <%--</div>--%>


    </div>
    <!--=== End Content Part ===-->


    <%@include file="../template/footer.jsp" %>
</div>
<!--/wrapper-->
<%@include file="../template/footer_js.jsp" %>

<script type="text/javascript" src="/resources/js/pricing.js"></script>

</html>
