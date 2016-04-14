<%@ page contentType="text/html; charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="uengine" uri="http://www.uengine.io/tags"%>

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
    <title><uengine:message code="company"/> | <uengine:message code="menu.home"/> </title>

    <%@include file="template/header_js.jsp"%>
</head>

<!-- body classes:
        "boxed": boxed layout mode e.g. <body class="boxed">
        "pattern-1 ... pattern-9": background patterns for boxed layout mode e.g. <body class="boxed pattern-1">
-->

<div class="wrapper">
    <%@include file="template/header.jsp"%>


    <!--=== News Block ===-->
    <div class="container content-sm">
        <div class="text-center margin-bottom-50">
            <h2 class="title-v2 title-center">최근 실적</h2>
            <p class="space-lg-hor">If you are going to use a <span class="color-green">passage of Lorem Ipsum</span>, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making <span class="color-green">this the first</span> true generator on the Internet.</p>
        </div>

        <div class="row news-v2">
            <div class="col-md-4 sm-margin-bottom-30">
                <div class="news-v2-badge">
                    <img class="img-responsive" src="/resources/assets/img/main/img12.jpg" alt="">
                    <p>
                        <span>26</span>
                        <small>Feb</small>
                    </p>
                </div>
                <div class="news-v2-desc bg-color-light">
                    <h3><a href="#">센차와 프렌드쉽.</a></h3>
                    <small>By Admin | <a class="color-inherit" href="#">16 Comments</a> | In <a href="#">Web Trends</a></small>
                    <p>At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores.</p>
                </div>
            </div>
            <div class="col-md-4 sm-margin-bottom-30">
                <div class="news-v2-badge">
                    <img class="img-responsive" src="/resources/assets/img/main/img3.jpg" alt="">
                    <p>
                        <span>24</span>
                        <small>Feb</small>
                    </p>
                </div>
                <div class="news-v2-desc bg-color-light">
                    <h3><a href="#">구글과 프렌드쉽.</a></h3>
                    <small>By Admin | <a class="color-inherit" href="#">51 Comments</a> | In <a href="#">Art &amp; Design</a></small>
                    <p>At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores.</p>
                </div>
            </div>
            <div class="col-md-4">
                <div class="news-v2-badge">
                    <img class="img-responsive" src="/resources/assets/img/main/img16.jpg" alt="">
                    <p>
                        <span>21</span>
                        <small>Feb</small>
                    </p>
                </div>
                <div class="news-v2-desc bg-color-light">
                    <h3><a href="#">삼성과 프렌드쉽.</a></h3>
                    <small>By Admin | <a class="color-inherit" href="#">32 Comments</a> | In <a href="#">Google &amp; Android</a></small>
                    <p>At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores.</p>
                </div>
            </div>

            <div class="col-md-4 sm-margin-bottom-30">
                <div class="news-v2-badge">
                    <img class="img-responsive" src="/resources/assets/img/main/img12.jpg" alt="">
                    <p>
                        <span>26</span>
                        <small>Feb</small>
                    </p>
                </div>
                <div class="news-v2-desc bg-color-light">
                    <h3><a href="#">센차와 프렌드쉽.</a></h3>
                    <small>By Admin | <a class="color-inherit" href="#">16 Comments</a> | In <a href="#">Web Trends</a></small>
                    <p>At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores.</p>
                </div>
            </div>
            <div class="col-md-4 sm-margin-bottom-30">
                <div class="news-v2-badge">
                    <img class="img-responsive" src="/resources/assets/img/main/img3.jpg" alt="">
                    <p>
                        <span>24</span>
                        <small>Feb</small>
                    </p>
                </div>
                <div class="news-v2-desc bg-color-light">
                    <h3><a href="#">구글과 프렌드쉽.</a></h3>
                    <small>By Admin | <a class="color-inherit" href="#">51 Comments</a> | In <a href="#">Art &amp; Design</a></small>
                    <p>At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores.</p>
                </div>
            </div>
            <div class="col-md-4">
                <div class="news-v2-badge">
                    <img class="img-responsive" src="/resources/assets/img/main/img16.jpg" alt="">
                    <p>
                        <span>21</span>
                        <small>Feb</small>
                    </p>
                </div>
                <div class="news-v2-desc bg-color-light">
                    <h3><a href="#">삼성과 프렌드쉽.</a></h3>
                    <small>By Admin | <a class="color-inherit" href="#">32 Comments</a> | In <a href="#">Google &amp; Android</a></small>
                    <p>At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores.</p>
                </div>
            </div>
        </div>
    </div>
    <!--=== End News Block ===-->

    <!--=== Parallax Quote ===-->
    <div class="parallax-quote parallaxBg" style="background-position: 50% 20px;">
        <div class="container">
            <div class="parallax-quote-in">
                <p>다가오는 사회는 우리가 정보를 주도할 수 없고, <span class="color-green">우리가 정보로 변환되는 </span> 시대. <br> 나의 데이터가 나보다 나를 더 잘 대변한다.</p>
                <small>- Byungon Kim -</small>
            </div>
        </div>
    </div>
    <!--=== End Parallax Quote ===-->

    <!--=== Colorful Service Blocks ===-->
    <div class="container-fluid">
        <div class="row no-gutter equal-height-columns margin-bottom-10">
            <div class="col-sm-4">
                <div class="service-block service-block-purple no-margin-bottom content equal-height-column">
                    <i class="icon-custom icon-md rounded icon-color-light icon-line icon-badge"></i>
                    <h2 class="heading-md font-light">최적의 솔루션</h2>
                    <p class="no-margin-bottom font-light">Provide; shifting landscape reduce carbon emissions human potential sustainability Jane Addams solve. Global network; care Rockefeller, vaccines equal opportunity human being.</p>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="service-block service-block-red no-margin-bottom content equal-height-column">
                    <i class="icon-custom icon-md rounded icon-color-light icon-line icon-fire"></i>
                    <h2 class="heading-md font-light">축약된 기술</h2>
                    <p class="no-margin-bottom font-light">Provide; shifting landscape reduce carbon emissions human potential sustainability Jane Addams solve. Global network; care Rockefeller, vaccines equal opportunity human being.</p>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="service-block service-block-aqua no-margin-bottom content equal-height-column">
                    <i class="icon-custom icon-md rounded icon-color-light icon-line icon-directions"></i>
                    <h2 class="heading-md font-light">분석 알고리즘</h2>
                    <p class="no-margin-bottom font-light">Provide; shifting landscape reduce carbon emissions human potential sustainability Jane Addams solve. Global network; care Rockefeller, vaccines equal opportunity human being.</p>
                </div>
            </div>
        </div>
    </div>
    <!--=== End Colorful Service Blocks ===-->

    <!--=== Service Blcoks ===-->
    <div class="container content-sm">
        <div class="text-center margin-bottom-50">
            <h2 class="title-v2 title-center">OUR SERVICES</h2>
            <p class="space-lg-hor">If you are going to use a <span class="color-green">passage of Lorem Ipsum</span>, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making <span class="color-green">this the first</span> true generator on the Internet.</p>
        </div>

        <!-- Service Blcoks -->
        <div class="row service-box-v1 margin-bottom-40">
            <div class="col-md-4 col-sm-6 md-margin-bottom-40">
                <div class="service-block service-block-default no-margin-bottom">
                    <i class="icon-lg rounded-x icon icon-badge"></i>
                    <h2 class="heading-sm">Web Design &amp; Development</h2>
                    <p>Donec id elit non mi porta gravida at eget metus id elit mi egetine. Fusce dapibus</p>
                    <ul class="list-unstyled">
                        <li>Responsive Web Desgin</li>
                        <li>E-commerce</li>
                        <li>App &amp; Icon Design</li>
                        <li>Logo &amp; Brand Design</li>
                        <li>Mobile Development</li>
                        <li>UI/UX Design</li>
                    </ul>
                </div>
            </div>
            <div class="col-md-4 col-sm-6 md-margin-bottom-40">
                <div class="service-block service-block-default no-margin-bottom">
                    <i class="icon-lg rounded-x icon-line icon-trophy"></i>
                    <h2 class="heading-sm">Marketing &amp; Cunsulting</h2>
                    <p>Donec id elit non mi porta gravida at eget metus id elit mi egetine usce dapibus elit nondapibus</p>
                    <ul class="list-unstyled">
                        <li>Analysis &amp; Consulting</li>
                        <li>Email Marketing</li>
                        <li>App &amp; Icon Design</li>
                        <li>Responsive Web Desgin</li>
                        <li>Social Networking</li>
                        <li>Documentation</li>
                    </ul>
                </div>
            </div>
            <div class="col-md-4 col-sm-12">
                <div class="service-block service-block-default no-margin-bottom">
                    <i class="icon-lg rounded-x icon-line icon-layers"></i>
                    <h2 class="heading-sm">SEO &amp; Advertising</h2>
                    <p>Donec id elit non mi porta gravida at eget metus id elit mi egetine. Fusce dapibus</p>
                    <ul class="list-unstyled">
                        <li>Display Advertising</li>
                        <li>App &amp; Icon Design</li>
                        <li>Analysis &amp; Consulting</li>
                        <li>Google AdSense</li>
                        <li>Social Media</li>
                        <li>Google/Bing Analysis</li>
                    </ul>
                </div>
            </div>
        </div>
        <!-- End Service Blcoks -->
    </div>
    <!--=== End Service Blcoks ===-->


    <!--=== Call To Action ===-->
    <div class="call-action-v1 bg-color-red">
        <div class="container">
            <div class="call-action-v1-box">
                <div class="call-action-v1-in">
                    <p class="color-light">Unify creative technology company providing key digital services and focused on helping our clients to build a successful business on web and mobile.</p>
                </div>
                <div class="call-action-v1-in inner-btn page-scroll">
                    <a href="/product/flamingo" class="btn-u btn-brd btn-brd-hover btn-u-light btn-u-block">Flamingo</a>
                    <a href="/product/bahamas" class="btn-u btn-brd btn-brd-hover btn-u-light btn-u-block">Bahamas</a>
                </div>
            </div>
        </div>
    </div>
    <!--=== End Call To Action ===-->


    <%@include file="template/footer.jsp"%>
</div><!--/wrapper-->

<%@include file="template/footer_js.jsp"%>
</html>
