<%@ page contentType="text/html; charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="uengine" uri="http://www.uengine.io/tags" %>

<!--=== Content Part ===-->

<div class="row portfolio-item margin-bottom-50" id="pig">

    <!-- Content Info -->
    <div class="col-md-5">
        <h2><uengine:message code="menu.product.flamingo.features.pig"/></h2>

        <p><uengine:message code="flamingo.feature.pig.text"/></p>

        <div class="row">
            <ul class="col-xs-8 list-unstyled lists-v1">
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.pig.list1"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.pig.list2"/></li>
                <%--
                                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.pig.list3"/></li>
                                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.pig.list4"/></li>
                                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.pig.list5"/></li>
                                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.pig.list6"/></li>
                --%>
            </ul>
        </div>

<%--
        <a href="#" class="btn-u btn-u-large"><uengine:message code="flamingo.feature.commonmovie"/></a>
--%>
    </div>
    <!-- End Content Info -->

    <!-- Carousel -->
    <div class="col-md-7">
        <div class="carousel slide carousel-v1" id="carousel_pig">
            <div class="carousel-inner">
                <div class="item active">
                    <img alt="" src="<uengine:message code="flamingo.feature.pig.image.url1"/>">

                    <div class="carousel-caption">
                        <p><uengine:message code="flamingo.feature.pig.image1"/></p>
                    </div>
                </div>
                <%--
                                <div class="item">
                                    <img alt="" src="/resources/assets/img/main/img12.jpg">

                                    <div class="carousel-caption">
                                        <p><uengine:message code="flamingo.feature.pig.image2"/></p>
                                    </div>
                                </div>
                                <div class="item">
                                    <img alt="" src="/resources/assets/img/main/img13.jpg">

                                    <div class="carousel-caption">
                                        <p><uengine:message code="flamingo.feature.pig.image3"/></p>
                                    </div>
                                </div>
                --%>
            </div>

            <div class="carousel-arrow">
                <a data-slide="prev" href="#carousel_pig" class="left carousel-control">
                    <i class="fa fa-angle-left"></i>
                </a>
                <a data-slide="next" href="#carousel_pig" class="right carousel-control">
                    <i class="fa fa-angle-right"></i>
                </a>
            </div>
        </div>
    </div>
    <!-- End Carousel -->
</div>

<!--/container-->
<!--=== End Content Part ===-->
