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

    <%@include file="../template/header_js.jsp" %>

</head>

<!-- body classes:
        "boxed": boxed layout mode e.g. <body class="boxed">
        "pattern-1 ... pattern-9": background patterns for boxed layout mode e.g. <body class="boxed pattern-1">
-->

<div class="wrapper">
    <%@include file="../template/header.jsp" %>


    <%@include file="banner.jsp" %>

    <!--=== Container Part ===-->
    <div class="container" style="padding-top: 60px;" id="overview">
        <div class="headline-center margin-bottom-20">
            <h2><uengine:message code="flamingo.intro.top.title"/></h2>

            <p><uengine:message code="flamingo.intro.top.sub"/></p>
        </div>
    </div>

    <%@include file="list.jsp" %>

    <!--=== Service Block v5 ===-->
    <div class="service-block-v5">
        <div class="container">
            <div class="row equal-height-columns">
                <div class="col-md-4 service-inner equal-height-column">
                    <i class="icon-custom icon-md rounded-x icon-bg-u icon-diamond"></i>
                    <span><uengine:message code="flamingo.intro.characteristic.1.title"/> </span>

                    <p><uengine:message code="flamingo.intro.characteristic.1.sub"/></p>
                </div>
                <div class="col-md-4 service-inner equal-height-column service-border">
                    <i class="icon-custom icon-md rounded-x icon-bg-u icon-rocket"></i>
                    <span><uengine:message code="flamingo.intro.characteristic.2.title"/> </span>

                    <p><uengine:message code="flamingo.intro.characteristic.2.sub"/></p>
                </div>
                <div class="col-md-4 service-inner equal-height-column">
                    <i class="icon-custom icon-md rounded-x icon-bg-u icon-support"></i>
                    <span><uengine:message code="flamingo.intro.characteristic.3.title"/> </span>

                    <p><uengine:message code="flamingo.intro.characteristic.3.sub"/></p>
                </div>
            </div>
            <!--/end row-->
        </div>
        <!--/end container-->
    </div>
    <!--=== End Service Block v5 ===-->

    <%-- SYSTEM REQUIREMENT--%>
    <div class="container content div-center" id="requirement">
        <div class="headline-center margin-bottom-20">
            <h2><uengine:message code="flamingo.intro.requirement.title"/></h2>
        </div>

        <div class="service-block service-block-default">
            <i class="icon-custom icon-color-dark rounded-x fa fa-lightbulb-o"></i>

            <p><uengine:message code="download.compatibility.text"/></p>
        </div>

        <div class="panel panel-default margin-bottom-20 table-requirement">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th width="150"><uengine:message code="flamingo.intro.requirement.title.1"/></th>
                    <th><uengine:message code="flamingo.intro.requirement.title.2"/></th>
                </tr>
                </thead>

                <tbody>
                <tr>
                    <td><uengine:message code="flamingo.intro.requirement.1.key"/></td>
                    <td><uengine:message code="flamingo.intro.requirement.1.value"/></td>
                </tr>
                <tr>
                    <td><uengine:message code="flamingo.intro.requirement.2.key"/></td>
                    <td><uengine:message code="flamingo.intro.requirement.2.value"/></td>
                </tr>
                <tr>
                    <td><uengine:message code="flamingo.intro.requirement.3.key"/></td>
                    <td><uengine:message code="flamingo.intro.requirement.3.value"/></td>
                </tr>
                <tr>
                    <td><uengine:message code="flamingo.intro.requirement.4.key"/></td>
                    <td><uengine:message code="flamingo.intro.requirement.4.value"/></td>
                </tr>
                <tr>
                    <td><uengine:message code="flamingo.intro.requirement.5.key"/></td>
                    <td><uengine:message code="flamingo.intro.requirement.5.value"/></td>
                </tr>
                <tr>
                    <td><uengine:message code="flamingo.intro.requirement.6.key"/></td>
                    <td><uengine:message code="flamingo.intro.requirement.6.value"/></td>
                </tr>
                <tr>
                    <td><uengine:message code="flamingo.intro.requirement.7.key"/></td>
                    <td><uengine:message code="flamingo.intro.requirement.7.value"/></td>
                </tr>
                <tr>
                    <td><uengine:message code="flamingo.intro.requirement.8.key"/></td>
                    <td><uengine:message code="flamingo.intro.requirement.8.value"/></td>
                </tr>
                <tr>
                    <td><uengine:message code="flamingo.intro.requirement.9.key"/></td>
                    <td><uengine:message code="flamingo.intro.requirement.9.value"/></td>
                </tr>
                <tr>
                    <td><uengine:message code="flamingo.intro.requirement.10.key"/></td>
                    <td><uengine:message code="flamingo.intro.requirement.10.value"/></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

    <!--=== Container Part ===-->
    <div class="bg-color-light">
        <div class="container content">
            <div class="headline-center margin-bottom-60">
                <h2><uengine:message code="flamingo.intro.why.title"/></h2>

                <p><uengine:message code="flamingo.intro.why.reason"/></p>
            </div>

            <div class="row">
                <div class="col-sm-6">
                    <!-- Testimonials v4 -->
                    <div class="testimonials-v4">
                        <div class="testimonials-v4-in">
                            <p><uengine:message code="flamingo.intro.person.1.talk"/></p>
                        </div>
                        <img class="rounded-x" src="/resources/images/person/photo_off.jpg" alt="Testimonials">
                        <span class="testimonials-author">
                            <uengine:message code="flamingo.intro.person.1.name"/> <br><em>CEO, <a href="http://www.uengine.org">uengine solutions</a></em>
                        </span>
                    </div>
                    <!-- End Testimonials v4 -->
                </div>
                <div class="col-sm-6">
                    <!-- Testimonials v4 -->
                    <div class="testimonials-v4">
                        <div class="testimonials-v4-in">
                            <p><uengine:message code="flamingo.intro.person.2.talk"/></p>
                        </div>
                        <img class="rounded-x" src="/resources/images/person/photo_off.jpg" alt="Testimonials">
                        <span class="testimonials-author">
                            <uengine:message code="flamingo.intro.person.2.name"/> <br><em>CEO, <a href="http://www.fastcat.co">Fastcat</a></em>
                        </span>
                    </div>
                    <!-- End Testimonials v4 -->
                </div>
            </div>
            <!--/end row-->
        </div>
        <!--/end container-->
    </div>


    <!-- LICENSE TYPE -->
    <div class="container content" id="license">
        <div class="headline-center margin-bottom-60">
            <h2><uengine:message code="flamingo.intro.licenses.title"/></h2>

            <p><uengine:message code="flamingo.intro.licenses.sub"/></p>
        </div>
        <!-- Pricing Light -->
        <div class="row margin-bottom-40 pricing-light">

            <%@include file="./flamingo-licenses.jsp" %>
            <!-- SUBSCRIPTION -->
            <div class="col-md-4 col-sm-6">
                <div class="pricing hover-effect">
                    <div class="sticker-left">TOP</div>
                    <div class="pricing-head">
                        <h3>Subscription</h3>
                    </div>
                    <ul class="pricing-content list-unstyled">
                        <li class="bg-color">연단위 기술지원</li>
                        <li>소프트웨어 버그 및 패치</li>
                        <li class="bg-color">Hadoop 개발 및 운영 교육 지원</li>
                        <li>매월 현장 정기점검</li>
                        <li class="bg-color">On-Site 기술 지원</li>
                        <li>온라인 기술 지원</li>
                        <li class="bg-color">커스터마이징은 별도 비용 청구</li>
                        <li>고객사에 소스코드 제공</li>
                        <li class="bg-color">제품명 변경 불가</li>
                    </ul>
                    <div class="pricing-footer">
                        <p>Subscription 라이센스는 연단위 계약을 통해 Flamingo의 기술지원을 받는 서비스입니다.</p>
                        <a name="license_btn" data-type="Subscription" class="btn-u btn-brd btn-brd-hover btn-u-default btn-u-xs"><i class="fa fa-shopping-cart"></i> 문의 및 견적요청</a>
                    </div>
                </div>
            </div>

            <!-- COMMERCIAL LICENSE -->
            <div class="col-md-4 col-sm-6">
                <div class="pricing hover-effect">
                    <div class="pricing-head">
                        <h3>Commercial</h3>
                    </div>
                    <ul class="pricing-content list-unstyled">
                        <li class="bg-color">패키지 판매 방식</li>
                        <li>소프트웨어 버그 및 패치</li>
                        <li class="bg-color">Hadoop 개발 및 운영 교육 지원</li>
                        <li>연단위 라이센스 갱신</li>
                        <li class="bg-color">On-Site 기술 지원</li>
                        <li>온라인 기술 지원</li>
                        <li class="bg-color">커스터마이징은 별도 비용 청구</li>
                        <li>고객사에 소스코드 제공</li>
                        <li class="bg-color">제품명 변경 불가</li>
                    </ul>
                    <div class="pricing-footer">
                        <p>Commercial 라이센스는 Subscription을 적용할 수 없는 고객을 위해 필요한 라이센스입니다.</p>
                        <a name="license_btn" data-type="Commercial" class="btn-u btn-brd btn-brd-hover btn-u-default btn-u-xs"><i class="fa fa-shopping-cart"></i> 문의 및 견적요청</a>
                    </div>
                </div>
            </div>

            <!-- OEM LICENSE -->
            <div class="col-md-4 col-sm-6">
                <div class="pricing hover-effect">
                    <div class="pricing-head">
                        <h3>OEM</h3>
                    </div>
                    <ul class="pricing-content list-unstyled">
                        <li class="bg-color">도메인 전문기업과 협업</li>
                        <li>Flamingo 이름 변경 허용</li>
                        <li class="bg-color">각 도메인별 1개 전문기업만 허용</li>
                        <li>프로젝트별 라이센스 적용</li>
                        <li class="bg-color">전문기업의 On-Site 기술지원</li>
                        <li>온라인 기술 지원</li>
                        <li class="bg-color">도메인 전문기업의 라이센스 방식에 따른 비용 책정</li>
                        <li>고객사에 소스코드 제공</li>
                        <li class="bg-color">제품명 변경가능 (단, Flamingo 기반 임을 명시해야함)</li>
                    </ul>
                    <div class="pricing-footer">
                        <p>도메인 전문기업이 Flamingo를 커스터마이징 하는 경우 적용되는 라이센스입니다.</p>
                        <a name="license_btn" data-type="OEM" class="btn-u btn-brd btn-brd-hover btn-u-default btn-u-xs"><i class="fa fa-shopping-cart"></i> 문의 및 견적요청</a>
                    </div>
                </div>
            </div>
        </div><!--/row-->
        <!-- End Pricing Light -->
    </div>

    <%@include file="../template/footer.jsp" %>
</div>
<!--/wrapper-->

<%@include file="../template/footer_js.jsp" %>
</html>
