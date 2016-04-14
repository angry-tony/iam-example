<%@ page contentType="text/html; charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="uengine" uri="http://www.uengine.io/tags" %>

<!--=== Content Part ===-->

<div class="row portfolio-item margin-bottom-50" id="hive">
    <!-- Carousel -->
    <div class="col-md-7">
        <div class="carousel slide carousel-v1" id="carousel_hive">
            <div class="carousel-inner">
                <div class="item active">
                    <img alt="" src="<uengine:message code="flamingo.feature.hive.image.url1"/>">

                    <div class="carousel-caption">
                        <p><uengine:message code="flamingo.feature.hive.image1"/></p>
                    </div>
                </div>
                <div class="item">
                    <img alt="" src="<uengine:message code="flamingo.feature.hive.image.url2"/>">

                    <div class="carousel-caption">
                        <p><uengine:message code="flamingo.feature.hive.image2"/></p>
                    </div>
                </div>
                <div class="item">
                    <img alt="" src="<uengine:message code="flamingo.feature.hive.image.url3"/>">

                    <div class="carousel-caption">
                        <p><uengine:message code="flamingo.feature.hive.image3"/></p>
                    </div>
                </div>
            </div>

            <div class="carousel-arrow">
                <a data-slide="prev" href="#carousel_hive" class="left carousel-control">
                    <i class="fa fa-angle-left"></i>
                </a>
                <a data-slide="next" href="#carousel_hive" class="right carousel-control">
                    <i class="fa fa-angle-right"></i>
                </a>
            </div>
        </div>
    </div>
    <!-- End Carousel -->

    <!-- Content Info -->
    <div class="col-md-5">
        <h2><uengine:message code="menu.product.flamingo.features.hive"/></h2>

        <p><uengine:message code="flamingo.feature.hive.text"/></p>

        <div class="row">
            <ul class="col-xs-6 list-unstyled lists-v1">
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.hive.list1"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.hive.list2"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.hive.list3"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.hive.list4"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.hive.list5"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.hive.list6"/></li>
            </ul>
            <ul class="col-xs-6 list-unstyled lists-v1">
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.hive.list7"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.hive.list8"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.hive.list9"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.hive.list10"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.hive.list11"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.hive.list12"/></li>
            </ul>
        </div>

<%--
        <a href="#" class="btn-u btn-u-large"><uengine:message code="flamingo.feature.commonmovie"/> </a>
--%>
    </div>
    <!-- End Content Info -->
</div>


<%--
<div class="tag-box tag-box-v2">
    <p><uengine:message code="flamingo.feature.hive.sub"/></p>
</div>
--%>

<!--/container-->
<!--=== End Content Part ===-->
