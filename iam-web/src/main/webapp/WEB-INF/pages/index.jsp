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

    <%@include file="template/header_js.jsp" %>

</head>

<div class="wrapper">
    <%@include file="template/header.jsp" %>

    <%@include file="template/purchase.jsp" %>

    <!--=== Content Part ===-->
    <div class="container content-sm">
        <!-- Service Blocks -->
        <div class="row margin-bottom-30">
            <div class="col-md-4">
                <div class="service">
                    <i class="fa fa-compress service-icon"></i>

                    <div class="desc">
                        <h4><uengine:message code="main.middle.1.title"></uengine:message> </h4>

                        <p><uengine:message code="main.middle.1.text"></uengine:message> </p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="service">
                    <i class="fa fa-cogs service-icon"></i>

                    <div class="desc">
                        <h4><uengine:message code="main.middle.2.title"></uengine:message> </h4>

                        <p><uengine:message code="main.middle.2.text"></uengine:message> </p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="service">
                    <i class="fa fa-rocket service-icon"></i>

                    <div class="desc">
                        <h4><uengine:message code="main.middle.3.title"></uengine:message> </h4>

                        <p><uengine:message code="main.middle.3.text"></uengine:message> </p>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Service Blokcs -->

        <!-- Recent Works -->
        <div class="headline"><h2><uengine:message code="main.movie.title"/></h2></div>
        <script type="text/javascript">
            $(function () {
                initCube($('#grid-container'), null, {
                    gapHorizontal: 20,
                    gapVertical: 20,
                    mediaQueries: [{
                        width: 800,
                        cols: 4
                    }, {
                        width: 500,
                        cols: 2
                    }, {
                        width: 320,
                        cols: 1
                    }]
                });
            });
        </script>
        <div id="grid-container" class="cbp-l-grid-agency">
            <div class="col-md-3 col-sm-6 cbp-item">
                <div class="thumbnails thumbnail-style thumbnail-kenburn">
                    <div class="thumbnail-img">
                        <div class="overflow-hidden">
                            <img class="img-responsive" src="/resources/images/flamingo/main-small-designer.png" alt="">
                        </div>
                        <a class="btn-more hover-effect cbp-lightbox"
                           href="https://www.youtube.com/watch?v=P89_3QxvR7g&feature=youtu.be"><uengine:message code="main.tooltip.readmore"/></a>
                    </div>
                    <div class="caption">
                        <h3><a class="hover-effect" href="#"><uengine:message code="main.movie.1.title"></uengine:message> </a></h3>

                        <p><uengine:message code="main.movie.1.text"></uengine:message> </p>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-sm-6 cbp-item">
                <div class="thumbnails thumbnail-style thumbnail-kenburn">
                    <div class="thumbnail-img">
                        <div class="overflow-hidden">
                            <img class="img-responsive" src="/resources/images/flamingo/main-small-hive.png" alt="">
                        </div>
                        <a class="btn-more hover-effect cbp-lightbox"
                           href="https://www.youtube.com/watch?v=yCCeLQaTX4U&feature=youtu.be"><uengine:message code="main.tooltip.readmore"/></a>
                    </div>
                    <div class="caption">
                        <h3><a class="hover-effect" href="#"><uengine:message code="main.movie.2.title"></uengine:message> </a></h3>

                        <p><uengine:message code="main.movie.2.text"></uengine:message> </p>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-sm-6 cbp-item">
                <div class="thumbnails thumbnail-style thumbnail-kenburn">
                    <div class="thumbnail-img">
                        <div class="overflow-hidden">
                            <img class="img-responsive" src="/resources/images/flamingo/main-small-monitoring.png" alt="">
                        </div>
                        <a class="btn-more hover-effect cbp-lightbox"
                           href="https://www.youtube.com/watch?v=sxfWTgxMl0o&feature=youtu.be"><uengine:message code="main.tooltip.readmore"/></a>
                    </div>
                    <div class="caption">
                        <h3><a class="hover-effect" href="#"><uengine:message code="main.movie.3.title"></uengine:message> </a></h3>

                        <p><uengine:message code="main.movie.3.text"></uengine:message> </p>
                    </div>
                </div>
            </div>
            <div class="col-md-3 col-sm-6 cbp-item">
                <div class="thumbnails thumbnail-style thumbnail-kenburn">
                    <div class="thumbnail-img">
                        <div class="overflow-hidden">
                            <img class="img-responsive" src="/resources/images/flamingo/main-small-hdfs.png" alt="">
                        </div>
                        <a class="btn-more hover-effect cbp-lightbox"
                           href="https://www.youtube.com/watch?v=QZQiGuxtb4Q&feature=youtu.be"><uengine:message code="main.tooltip.readmore"/></a>
                    </div>
                    <div class="caption">
                        <h3><a class="hover-effect" href="#"><uengine:message code="main.movie.4.title"></uengine:message> </a></h3>

                        <p><uengine:message code="main.movie.4.text"></uengine:message> </p>
                    </div>
                </div>
            </div>
        </div>
        <!-- End Recent Works -->

<%--
        <div class="container content-sm">
            <div class="headline-center"><h2><uengine:message code="main.workflow.title"></uengine:message> </h2></div>
            <%@include file="flamingo/features/workflowcube.jsp" %>
        </div>
--%>

        <!-- Owl Clients v1 -->
        <div class="headline"><h2><uengine:message code="text.contactus.customer"/></h2></div>
        <div class="owl-clients-v1">
            <div class="item">
                <img src="/resources/images/customers/1.png" alt="Client 1">
            </div>
            <div class="item">
                <img src="/resources/images/customers/2.png" alt="Client 2">
            </div>
            <div class="item">
                <img src="/resources/images/customers/3.png" alt="Client 3">
            </div>
            <div class="item">
                <img src="/resources/images/customers/4.png" alt="Client 4">
            </div>
            <div class="item">
                <img src="/resources/images/customers/5.png" alt="Client 5">
            </div>
            <div class="item">
                <img src="/resources/images/customers/6.png" alt="Client 6">
            </div>
        </div>
        <!-- End Owl Clients v1 -->
    </div>
    <!--/container-->
    <!-- End Content Part -->


    <%@include file="template/footer.jsp" %>


</div>
<!--/wrapper-->

<%@include file="template/footer_js.jsp" %>
</html>
