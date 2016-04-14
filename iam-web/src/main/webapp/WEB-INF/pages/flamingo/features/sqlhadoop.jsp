<%@ page contentType="text/html; charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="uengine" uri="http://www.uengine.io/tags" %>

<!--=== Content Part ===-->

<div class="row portfolio-item margin-bottom-50" id="sqlhadoop">

    <!-- Content Info -->
    <div class="col-md-5">
        <h2><uengine:message code="menu.product.flamingo.features.sqlhadoop"/></h2>

        <p><uengine:message code="flamingo.feature.sqlhadoop.text"/></p>


        <div class="row">
            <ul class="col-xs-6 list-unstyled lists-v1">
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.sqlhadoop.list1"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.sqlhadoop.list2"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.sqlhadoop.list3"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.sqlhadoop.list4"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.sqlhadoop.list5"/></li>
                <%--
                                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.sqlhadoop.list6"/></li>
                --%>
            </ul>
            <ul class="col-xs-6 list-unstyled lists-v1">
                <%--
                                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.sqlhadoop.list7"/></li>
                                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.sqlhadoop.list8"/></li>
                                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.sqlhadoop.list9"/></li>
                                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.sqlhadoop.list10"/></li>
                                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.sqlhadoop.list11"/></li>
                                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.sqlhadoop.list12"/></li>
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
        <div class="carousel slide carousel-v1" id="carousel_sqlhadoop">
            <div class="carousel-inner">
                <div class="item active">
                    <img alt="" src="<uengine:message code="flamingo.feature.sqlhadoop.image.url1"/>">

                    <div class="carousel-caption">
                        <p><uengine:message code="flamingo.feature.sqlhadoop.image1"/></p>
                    </div>
                </div>
                <div class="item">
                    <img alt="" src="<uengine:message code="flamingo.feature.sqlhadoop.image.url2"/>">

                    <div class="carousel-caption">
                        <p><uengine:message code="flamingo.feature.sqlhadoop.image2"/></p>
                    </div>
                </div>
                <div class="item">
                    <img alt="" src="<uengine:message code="flamingo.feature.sqlhadoop.image.url3"/>">

                    <div class="carousel-caption">
                        <p><uengine:message code="flamingo.feature.sqlhadoop.image3"/></p>
                    </div>
                </div>
            </div>

            <div class="carousel-arrow">
                <a data-slide="prev" href="#carousel_sqlhadoop" class="left carousel-control">
                    <i class="fa fa-angle-left"></i>
                </a>
                <a data-slide="next" href="#carousel_sqlhadoop" class="right carousel-control">
                    <i class="fa fa-angle-right"></i>
                </a>
            </div>
        </div>
    </div>
    <!-- End Carousel -->
</div>


<div class="tag-box tag-box-v2">
    <p><uengine:message code="flamingo.feature.sqlhadoop.sub"/></p>
</div>

<!--/container-->
<!--=== End Content Part ===-->
