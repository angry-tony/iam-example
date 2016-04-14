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

    <%@include file="../flamingo/banner.jsp" %>

    <!--=== Content Part ===-->

    <!-- Pricing Light -->
    <div class="container content">

        <div class="headline-center"><h2><uengine:message code="shop.product.title"/> </h2></div>

        <div class="row">
            <div class="col-md-6">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-lg-3 control-label"><uengine:message code="shop.product.country"/> <span class="color-red">*</span></label>

                        <div class="col-lg-9">
                            <select name="country" class="form-control">
                                <%@include file="../template/countryList.jsp" %>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label"><uengine:message code="shop.product.node"/> <span class="color-red">*</span></label>

                        <div class="col-lg-9">
                            <select name="nodeNum" class="form-control">
                                <option value="10">10 nodes</option>
                                <option value="20">20 nodes</option>
                                <option value="30">30 nodes</option>
                                <option value="40">40 nodes</option>
                                <option value="50">50 nodes</option>
                                <option value="0">50 nodes +</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label"><uengine:message code="shop.product.version"/> <span class="color-red">*</span></label>

                        <div class="col-lg-9">
                            <select name="version" class="form-control">
                                <option value="2.0.0">Version 2.0.0</option>
                            </select>
                        </div>
                    </div>

                    <hr>

                    <div class="form-group">
                        <label class="col-lg-3 control-label"><uengine:message code="shop.product.hdfs"/> <span class="color-red">*</span></label>

                        <div class="col-lg-9">
                            <select name="hdfs" class="form-control">
                                <option value="CDH 5.0">Cloudera CDH 5.0</option>
                                <option value="CDH 5.1">Cloudera CDH 5.1</option>
                                <option value="CDH 5.2">Cloudera CDH 5.2</option>
                                <option value="CDH 5.3">Cloudera CDH 5.3</option>
                                <option value="CDH 5.4">Cloudera CDH 5.4</option>
                                <option value="PHD 2.x">Pivotal PHD 2.x</option>
                                <option value="PHD 3.x">Pivotal PHD 3.x</option>
                                <option value="HDP 2.0">Hortonworks HDP 2.0</option>
                                <option value="HDP 2.1">Hortonworks HDP 2.1</option>
                                <option value="HDP 2.2">Hortonworks HDP 2.2</option>
                                <option value="HDP 2.3">Hortonworks HDP 2.3</option>
                                <option value="AH 2.0">Apache Hadoop 2.0 Dist</option>
                                <option value="AH 2.1">Apache Hadoop 2.1 Dist</option>
                                <option value="AH 2.2">Apache Hadoop 2.2 Dist</option>
                                <option value="AH 2.3">Apache Hadoop 2.3 Dist</option>
                                <option value="AH 2.4">Apache Hadoop 2.4 Dist</option>
                                <option value="AH 2.5">Apache Hadoop 2.5 Dist</option>
                                <option value="AH 2.6">Apache Hadoop 2.6 Dist</option>
                                <option value="AH 2.7">Apache Hadoop 2.7 Dist</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label"><uengine:message code="shop.product.jdk"/> <span class="color-red">*</span></label>

                        <div class="col-lg-9">
                            <select name="jdk" class="form-control">
                                <option value="1.6">JDK 1.6 </option>
                                <option value="1.7">JDK 1.7 </option>
                                <option value="1.8">JDK 1.8 </option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label"><uengine:message code="shop.product.memory"/> <span class="color-red">*</span></label>

                        <div class="col-lg-9">
                            <select name="memory" class="form-control">
                                <option value="4">4 GB </option>
                                <option value="8">8 GB </option>
                                <option value="16">16 GB </option>
                                <option value="32">32 GB </option>
                                <option value="64">64 GB </option>
                                <option value="128">128 GB </option>
                                <option value="0">128 GB + </option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-lg-3 control-label"><uengine:message code="shop.product.hdd"/> <span class="color-red">*</span></label>

                        <div class="col-lg-9">
                            <select name="hdd" class="form-control">
                                <option value="1">1 TB </option>
                                <option value="2">2 TB </option>
                                <option value="3">3 TB </option>
                                <option value="4">4 TB </option>
                                <option value="5">5 TB </option>
                                <option value="0">5 TB + </option>
                            </select>
                        </div>
                    </div>


                </form>
                <script type="text/javascript">
                    $('[name=country]').val('${country}');
                    $('[name=nodeNum]').val('${nodeNum}');
                    $('[name=version]').val('${version}');
                </script>
            </div>

            <div class="pricing-light col-md-6">
                <div class="col-sm-10 col-sm-offset-1">
                    <div class="pricing hover-effect" name="product_box" data-family="SUBSCRIPTION">
                        <div class="pricing-head">
                            <h3><uengine:message code="shop.pricing.box.title"/> <span><uengine:message code="shop.pricing.box.desc"/> </span></h3>
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
                            <li class="bg-color"><uengine:message code="shop.pricing.box.3"/> </li>
                            <li><uengine:message code="shop.pricing.box.4"/> </li>
                        </ul>
                        <div class="pricing-footer">
                            <h4>
                                <tag name="price_tag"></tag>
                                <span><uengine:message code="shop.pricing.box.year"/> </span></h4>

                            <p><uengine:message code="shop.pricing.box.cluster"/> </p>
                            <a href="#" class="btn btn-primary" type="button" id="choiceSubmit" name="purchase_tag"
                               style="margin-bottom: 10px;">
                                <uengine:message code="shop.product.next"/> </a>

                            <a name="gotoContact" href="/contact" class="btn-u btn-brd btn-brd-hover btn-u-default btn-u-xs"><i
                                    class="fa fa-shopping-cart"></i> <uengine:message code="shop.pricing.box.contact"/> </a>
                            <p name="gotoContact"><uengine:message code="shop.pricing.box.disable"/> </p>

                            <form method="post" id="productChoiceForm" style="display: none;">
                                <fieldset>
                                    <input type="hidden" name="_flowExecutionId" value="<c:out value="${flowExecutionId}"/>"/>
                                    <input type="hidden" name="_eventId" value="submit"/>
                                    <input type="hidden" name="productId"/>
                                    <input type="hidden" name="productCountry"/>
                                    <input type="hidden" name="detail"/>
                                </fieldset>
                            </form>
                            <br>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <br><br>
    </div>
    <!--=== End Content Part ===-->


    <%@include file="../template/footer.jsp" %>
</div>
<!--/wrapper-->
<%@include file="../template/footer_js.jsp" %>

<script type="text/javascript" src="/resources/js/productChoice.js"></script>

</html>
