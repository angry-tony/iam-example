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

    <link rel="stylesheet" href="/resources/assets/css/pages/page_about.css">
</head>

<!-- body classes:
        "boxed": boxed layout mode e.g. <body class="boxed">
        "pattern-1 ... pattern-9": background patterns for boxed layout mode e.g. <body class="boxed pattern-1">
-->

<div class="wrapper">
    <%@include file="template/header.jsp"%>


    <!--=== Content Part ===-->
    <div class="container content">
        <div class="title-box-v2">
            <h2>About <span class="color-green">uengine</span></h2>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
        </div>

        <!-- About Sldier -->
        <div class="shadow-wrapper margin-bottom-50">
            <div class="carousel slide carousel-v1 box-shadow shadow-effect-2" id="myCarousel">
                <ol class="carousel-indicators">
                    <li class="rounded-x active" data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li class="rounded-x" data-target="#myCarousel" data-slide-to="1"></li>
                    <li class="rounded-x" data-target="#myCarousel" data-slide-to="2"></li>
                </ol>

                <div class="carousel-inner">
                    <div class="item active">
                        <img class="img-responsive" src="/resources/assets/img/sliders/11.jpg" alt="">
                    </div>
                    <div class="item">
                        <img class="img-responsive" src="/resources/assets/img/sliders/4.jpg" alt="">
                    </div>
                    <div class="item">
                        <img class="img-responsive" src="/resources/assets/img/sliders/10.jpg" alt="">
                    </div>
                </div>

                <div class="carousel-arrow">
                    <a data-slide="prev" href="#myCarousel" class="left carousel-control">
                        <i class="fa fa-angle-left"></i>
                    </a>
                    <a data-slide="next" href="#myCarousel" class="right carousel-control">
                        <i class="fa fa-angle-right"></i>
                    </a>
                </div>
            </div>
        </div>
        <!-- End About Sldier -->

        <div class="row margin-bottom-10">
            <div class="col-sm-4">
                <div class="service-block service-block-default">
                    <i class="icon-custom rounded icon-color-dark icon-line icon-badge"></i>
                    <h2 class="heading-md">Purple Box</h2>
                    <p>Donec id elit non mi porta gravida at eget metus id elit mi egetine. Fusce dapibus. At vero eos et accusamus et iusto odio dignissimos ducimus qui.</p>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="service-block service-block-default">
                    <i class="icon-custom rounded icon-color-dark icon-line icon-fire"></i>
                    <h2 class="heading-md">Purple Box</h2>
                    <p>Donec id elit non mi porta gravida at eget metus id elit mi egetine. Fusce dapibus. At vero eos et accusamus et iusto odio dignissimos ducimus qui.</p>
                </div>
            </div>
            <div class="col-sm-4">
                <div class="service-block service-block-default">
                    <i class="icon-custom rounded icon-color-dark icon-line icon-directions"></i>
                    <h2 class="heading-md">Aqua Box</h2>
                    <p>Donec id elit non mi porta gravida at eget metus id elit mi egetine. Fusce dapibus. At vero eos et accusamus et iusto odio dignissimos ducimus qui.</p>
                </div>
            </div>
        </div>
    </div>
    <!--=== End Content Part ===-->

    <!--=== Parallax About Block ===-->
    <div class="parallax-bg parallaxBg1">
        <div class="container content parallax-about">
            <div class="title-box-v2">
                <h2>About our <span class="color-green">company</span></h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="banner-info dark margin-bottom-10">
                        <i class="rounded-x icon-bell"></i>
                        <div class="overflow-h">
                            <h3>Our mission</h3>
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus tincidunt sit amet dui auctor pellentesque. Nulla ut posuere purus.</p>
                        </div>
                    </div>
                    <div class="banner-info dark margin-bottom-10">
                        <i class="rounded-x fa fa-magic"></i>
                        <div class="overflow-h">
                            <h3>Our vision</h3>
                            <p>Phasellus vitae rhoncus ipsum. Aliquam ultricies, velit sit amet scelerisque tincidunt, dolor neque consequat est, a dictum felis metus eget nulla.</p>
                        </div>
                    </div>
                    <div class="banner-info dark margin-bottom-10">
                        <i class="rounded-x fa fa-thumbs-o-up"></i>
                        <div class="overflow-h">
                            <h3>We are good at ...</h3>
                            <p>Nunc ac ligula ut diam rutrum porttitor. Integer et neque at lacus placerat pretium eu ac dui. Class aptent taciti sociosqu ad litora torquent per conubia nostra.</p>
                        </div>
                    </div>
                    <div class="margin-bottom-20"></div>
                </div>
                <div class="col-md-6">
                    <img class="img-responsive" src="/resources/assets/img/mockup/1.png" alt="">
                </div>
            </div>
        </div><!--/container-->
    </div>
    <!--=== End Parallax About Block ===-->

    <!--=== Container Part ===-->
    <div class="container content">
        <div class="title-box-v2">
            <h2>Our Company <span class="color-green">life</span></h2>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
        </div>

        <div class="row margin-bottom-40">
            <!-- Begin Easy Block v2 -->
            <div class="col-md-3 col-sm-6 md-margin-bottom-20">
                <div class="simple-block">
                    <img class="img-responsive img-bordered margin-bottom-10" src="/resources/assets/img/main/img3.jpg" alt="">
                    <p>Pellentesque et erat ac massa cursus porttitor eget sed magna.</p>
                </div>
            </div>
            <!-- End Simple Block -->

            <!-- Begin Easy Block v2 -->
            <div class="col-md-3 col-sm-6 md-margin-bottom-20">
                <div class="simple-block">
                    <img class="img-responsive img-bordered margin-bottom-10" src="/resources/assets/img/main/img4.jpg" alt="">
                    <p>Pellentesque et erat ac massa cursus porttitor eget sed magna.</p>
                </div>
            </div>
            <!-- End Simple Block -->


            <!-- Begin Easy Block v2 -->
            <div class="col-md-3 col-sm-6 md-margin-bottom-20">
                <div class="simple-block">
                    <img class="img-responsive img-bordered margin-bottom-10" src="/resources/assets/img/main/img16.jpg" alt="">
                    <p>Pellentesque et erat ac massa cursus porttitor eget sed magna.</p>
                </div>
            </div>
            <!-- End Simple Block -->

            <!-- Begin Easy Block v2 -->
            <div class="col-md-3 col-sm-6 md-margin-bottom-20">
                <div class="simple-block">
                    <img class="img-responsive img-bordered margin-bottom-10" src="/resources/assets/img/main/img7.jpg" alt="">
                    <p>Pellentesque et erat ac massa cursus porttitor eget sed magna.</p>
                </div>
            </div>
            <!-- End Simple Block -->
        </div>

        <div class="row">
            <!-- Begin Easy Block v2 -->
            <div class="col-md-6 col-sm-6 md-margin-bottom-20">
                <div class="simple-block">
                    <div class="embed-responsive embed-responsive-16by9 margin-bottom-10">
                        <iframe frameborder="0" allowfullscreen="" mozallowfullscreen="" webkitallowfullscreen="" src="//player.vimeo.com/video/67167840?title=0&amp;byline=0&amp;portrait=0"></iframe>
                    </div>
                </div>
            </div>
            <!-- End Simple Block -->

            <!-- Begin Easy Block v2 -->
            <div class="col-md-6 col-sm-6 md-margin-bottom-20">
                <div class="simple-block">
                    <div class="embed-responsive embed-responsive-16by9 margin-bottom-10">
                        <iframe frameborder="0" allowfullscreen="" mozallowfullscreen="" webkitallowfullscreen="" src="//player.vimeo.com/video/70528799"></iframe>
                    </div>
                </div>
            </div>
            <!-- End Simple Block -->
        </div>
    </div><!--/container-->
    <!--=== Container Part ===-->

    <!--=== Meet Our Team ===-->
    <div class="parallax-team parallaxBg">
        <div class="container content">
            <div class="title-box-v2">
                <h2>Meet Our <span class="color-green">Team</span></h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
            </div>

            <div class="row">
                <!-- Team v2 -->
                <div class="col-md-3 col-sm-6">
                    <div class="team-v2">
                        <img class="img-responsive" src="/resources/assets/img/team/img1-md.jpg" alt="" />
                        <div class="inner-team">
                            <h3>Jack Anderson</h3>
                            <small class="color-green">CEO, Chief Officer</small>
                            <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, justo sit amet risus etiam porta sem...</p>
                            <hr>
                            <ul class="list-inline team-social">
                                <li>
                                    <a data-placement="top" data-toggle="tooltip" class="fb tooltips" data-original-title="Facebook" href="#">
                                        <i class="fa fa-facebook"></i>
                                    </a>
                                </li>
                                <li>
                                    <a data-placement="top" data-toggle="tooltip" class="tw tooltips" data-original-title="Twitter" href="#">
                                        <i class="fa fa-twitter"></i>
                                    </a>
                                </li>
                                <li>
                                    <a data-placement="top" data-toggle="tooltip" class="gp tooltips" data-original-title="Google plus" href="#">
                                        <i class="fa fa-google-plus"></i>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- End Team v2 -->

                <!-- Team v2 -->
                <div class="col-md-3 col-sm-6">
                    <div class="team-v2">
                        <img class="img-responsive" src="/resources/assets/img/team/img2-md.jpg" alt="" />
                        <div class="inner-team">
                            <h3>Kate Metus</h3>
                            <small class="color-green">Project Manager</small>
                            <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, justo sit amet risus etiam porta sem...</p>
                            <hr>
                            <ul class="list-inline team-social">
                                <li><a data-placement="top" data-toggle="tooltip" class="fb tooltips" data-original-title="Facebook" href="#"><i class="fa fa-facebook"></i></a></li>
                                <li><a data-placement="top" data-toggle="tooltip" class="tw tooltips" data-original-title="Twitter" href="#"><i class="fa fa-twitter"></i></a></li>
                                <li><a data-placement="top" data-toggle="tooltip" class="gp tooltips" data-original-title="Google plus" href="#"><i class="fa fa-google-plus"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- End Team v2 -->

                <!-- Team v2 -->
                <div class="col-md-3 col-sm-6">
                    <div class="team-v2">
                        <img class="img-responsive" src="/resources/assets/img/team/img3-md.jpg" alt="" />
                        <div class="inner-team">
                            <h3>Porta Gravida</h3>
                            <small class="color-green">VP of Operations</small>
                            <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, justo sit amet risus etiam porta sem...</p>
                            <hr>
                            <ul class="list-inline team-social">
                                <li>
                                    <a data-placement="top" data-toggle="tooltip" class="fb tooltips" data-original-title="Facebook" href="#">
                                        <i class="fa fa-facebook"></i>
                                    </a>
                                </li>
                                <li>
                                    <a data-placement="top" data-toggle="tooltip" class="tw tooltips" data-original-title="Twitter" href="#">
                                        <i class="fa fa-twitter"></i>
                                    </a>
                                </li>
                                <li>
                                    <a data-placement="top" data-toggle="tooltip" class="gp tooltips" data-original-title="Google plus" href="#">
                                        <i class="fa fa-google-plus"></i>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- End Team v2 -->

                <!-- Team v2 -->
                <div class="col-md-3 col-sm-6">
                    <div class="team-v2">
                        <img class="img-responsive" src="/resources/assets/img/team/img5-md.jpg" alt="" />
                        <div class="inner-team">
                            <h3>Donec Elisson</h3>
                            <small class="color-green">Director, R &amp; D Talent</small>
                            <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, justo sit amet risus etiam porta sem...</p>
                            <hr>
                            <ul class="list-inline team-social">
                                <li>
                                    <a data-placement="top" data-toggle="tooltip" class="fb tooltips" data-original-title="Facebook" href="#">
                                        <i class="fa fa-facebook"></i></a>
                                </li>
                                <li>
                                    <a data-placement="top" data-toggle="tooltip" class="tw tooltips" data-original-title="Twitter" href="#">
                                        <i class="fa fa-twitter"></i>
                                    </a>
                                </li>
                                <li>
                                    <a data-placement="top" data-toggle="tooltip" class="gp tooltips" data-original-title="Google plus" href="#">
                                        <i class="fa fa-google-plus"></i>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
                <!-- End Team v2 -->
            </div>
        </div>
    </div>
    <!--=== End Meet Our Team ===-->

    <!--=== Contacts ===-->
    <div class="contacts content">
        <div class="container">
            <div class="title-box-v2">
                <h2>Our <span class="color-green">Contacts</span></h2>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
            </div>
            <div class="row">
                <div class="col-md-6 col-sm-6">
                    <!-- Google Map -->
                    <div id="map" class="map">
                    </div>
                    <!-- End Google Map -->
                </div>
                <div class="col-md-6 col-sm-6">
                    <!-- Business Hours -->
                    <h3>Get In Touch</h3>
                    <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, sed do eiusmod tempor incididu ntjusto sit amet risus etiam porta sem.</p>
                    <ul class="list-inline margin-bottom-20">
                        <li><strong>Monday-Friday:</strong> 10am to 8pm</li>
                        <li><strong>Saturday:</strong> 11am to 3pm</li>
                        <li><strong>Sunday:</strong> Closed</li>
                    </ul>

                    <div class="row">
                        <div class="col-sm-6">
                            <div class="input-group margin-bottom-10">
                                <span class="input-group-addon"><i class="fa fa-user"></i></span>
                                <input type="name" placeholder="Name" class="form-control">
                            </div>
                        </div>
                        <div class="col-sm-6">
                            <div class="input-group margin-bottom-20">
                                <span class="input-group-addon"><i class="fa fa-envelope"></i></span>
                                <input type="email" placeholder="Email" class="form-control">
                            </div>
                        </div>
                    </div>

                    <textarea rows="5" class="form-control margin-bottom-20" placeholder="Type your question here..."></textarea>
                    <button class="btn-u btn-u-sm pull-right" type="button">Send question</button>
                </div>
            </div>
        </div>
    </div>
    <!--=== End Contacts ===-->


    <%@include file="template/footer.jsp"%>
</div><!--/wrapper-->

<%@include file="template/footer_js.jsp"%>

<%@include file="template/footer_contact.jsp"%>

</html>
