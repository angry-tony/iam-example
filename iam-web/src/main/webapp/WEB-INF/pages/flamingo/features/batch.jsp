<%@ page contentType="text/html; charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="uengine" uri="http://www.uengine.io/tags" %>

<!--=== Content Part ===-->

<div class="row portfolio-item margin-bottom-50" id="batch">
    <!-- Carousel -->
    <div class="col-md-7">
        <div class="carousel slide carousel-v1" id="carousel_batch">
            <div class="carousel-inner">
                <div class="item active">
                    <img alt="" src="<uengine:message code="flamingo.feature.batch.image.url1"/>">

                    <div class="carousel-caption">
                        <p><uengine:message code="flamingo.feature.batch.image1"/></p>
                    </div>
                </div>
                <div class="item">
                    <img alt="" src="<uengine:message code="flamingo.feature.batch.image.url2"/>">

                    <div class="carousel-caption">
                        <p><uengine:message code="flamingo.feature.batch.image2"/></p>
                    </div>
                </div>
                <%--
                                <div class="item">
                                    <img alt="" src="/resources/assets/img/main/img13.jpg">

                                    <div class="carousel-caption">
                                        <p><uengine:message code="flamingo.feature.batch.image3"/></p>
                                    </div>
                                </div>
                --%>
            </div>

            <div class="carousel-arrow">
                <a data-slide="prev" href="#carousel_batch" class="left carousel-control">
                    <i class="fa fa-angle-left"></i>
                </a>
                <a data-slide="next" href="#carousel_batch" class="right carousel-control">
                    <i class="fa fa-angle-right"></i>
                </a>
            </div>
        </div>
    </div>
    <!-- End Carousel -->

    <!-- Content Info -->
    <div class="col-md-5">
        <h2><uengine:message code="menu.product.flamingo.features.batch"/></h2>

        <p><uengine:message code="flamingo.feature.batch.text"/></p>

        <div class="row">
            <ul class="col-xs-8 list-unstyled lists-v1">
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.batch.list1"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.batch.list2"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.batch.list3"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.batch.list4"/></li>
                <%--
                                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.batch.list5"/></li>
                                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.batch.list6"/></li>
                --%>
            </ul>
            <ul class="col-xs-6 list-unstyled lists-v1">
                <%--
                                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.batch.list7"/></li>
                                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.batch.list8"/></li>
                                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.batch.list9"/></li>
                                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.batch.list10"/></li>
                                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.batch.list11"/></li>
                                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.batch.list12"/></li>
                --%>
            </ul>
        </div>

<%--
        <a href="#" class="btn-u btn-u-large"><uengine:message code="flamingo.feature.commonmovie"/></a>
--%>
    </div>
    <!-- End Content Info -->
</div>


<%--
<div class="tag-box tag-box-v2">
    <p><uengine:message code="flamingo.feature.batch.sub"/></p>
</div>
--%>

<!--/container-->
<!--=== End Content Part ===-->
