<%@ page contentType="text/html; charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="uengine" uri="http://www.uengine.io/tags" %>

<!--=== Content Part ===-->

<div class="row portfolio-item margin-bottom-50" id="system">

    <!-- Content Info -->
    <div class="col-md-5">
        <h2><uengine:message code="menu.product.flamingo.features.system"/></h2>

        <p><uengine:message code="flamingo.feature.system.text"/></p>


        <div class="row">
            <ul class="col-xs-8 list-unstyled lists-v1">
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.system.list1"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.system.list2"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.system.list3"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.system.list4"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.system.list5"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.system.list6"/></li>
            </ul>
            <ul class="col-xs-6 list-unstyled lists-v1">
                <%--
                                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.system.list7"/></li>
                                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.system.list8"/></li>
                                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.system.list9"/></li>
                                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.system.list10"/></li>
                                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.system.list11"/></li>
                                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.system.list12"/></li>
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
        <div class="carousel slide carousel-v1" id="carousel_system">
            <div class="carousel-inner">
                <div class="item active">
                    <img alt="" src="<uengine:message code="flamingo.feature.system.image.url1"/>">

                    <div class="carousel-caption">
                        <p><uengine:message code="flamingo.feature.system.image1"/></p>
                    </div>
                </div>
                <div class="item">
                    <img alt="" src="<uengine:message code="flamingo.feature.system.image.url2"/>">

                    <div class="carousel-caption">
                        <p><uengine:message code="flamingo.feature.system.image2"/></p>
                    </div>
                </div>
                <div class="item">
                    <img alt="" src="<uengine:message code="flamingo.feature.system.image.url3"/>">

                    <div class="carousel-caption">
                        <p><uengine:message code="flamingo.feature.system.image3"/></p>
                    </div>
                </div>
            </div>

            <div class="carousel-arrow">
                <a data-slide="prev" href="#carousel_system" class="left carousel-control">
                    <i class="fa fa-angle-left"></i>
                </a>
                <a data-slide="next" href="#carousel_system" class="right carousel-control">
                    <i class="fa fa-angle-right"></i>
                </a>
            </div>
        </div>
    </div>
    <!-- End Carousel -->
</div>


<%--
<div class="tag-box tag-box-v2">
    <p><uengine:message code="flamingo.feature.system.sub"/></p>
</div>
--%>

<!--/container-->
<!--=== End Content Part ===-->
