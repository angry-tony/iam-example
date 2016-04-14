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

    <link rel="stylesheet" href="/resources/assets/plugins/sky-forms-pro/skyforms/css/sky-forms.css">
    <link rel="stylesheet" href="/resources/assets/plugins/sky-forms-pro/skyforms/custom/custom-sky-forms.css">
    <!--[if lt IE 9]>
    <link rel="stylesheet" href="/resources/assets/plugins/sky-forms-pro/skyforms/css/sky-forms-ie8.css"><![endif]-->

    <!-- CSS Page Style -->

</head>

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

    <div class="container content">
        <div class="col-md-6 col-md-offset-2">
            <form class="form-horizontal" role="form" id="submitForm" method="post">
                <h4><uengine:message code="shop.info.my.title"/> </h4>

                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <div class="checkbox">
                            <label>
                                <input name="reseller" type="checkbox"> <uengine:message code="shop.info.my.reseller"/>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label"><uengine:message code="shop.info.email"/> <span class="color-red">*</span></label>

                    <div class="col-lg-9">
                        <input name="userEmail" type="email" class="form-control" value="${user.email}" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label"><uengine:message code="shop.info.first"/> <span class="color-red">*</span></label>

                    <div class="col-lg-9">
                        <input name="userFirstName" type="text" class="form-control" value="${user.firstName}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label"><uengine:message code="shop.info.last"/> <span class="color-red">*</span></label>

                    <div class="col-lg-9">
                        <input name="userLastName" type="text" class="form-control" value="${user.lastName}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label"><uengine:message code="shop.info.phone"/> <span class="color-red">*</span></label>

                    <div class="col-lg-9">
                        <input name="userPhone" type="tel" class="form-control" value="${user.phone}">
                    </div>
                </div>

                <br><br>

                <h4 class="enduserTag"><uengine:message code="shop.info.tech.title"/> </h4>
                <h4 class="resellerTag"><uengine:message code="shop.info.tech.reseller"/> </h4>

                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <div class="checkbox">
                            <label>
                                <input class="sametechinfo" type="checkbox"> <uengine:message code="shop.info.same"/>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label"><uengine:message code="shop.info.email"/> <span class="color-red">*</span></label>

                    <div class="col-lg-9">
                        <input name="techEmail" type="email" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label"><uengine:message code="shop.info.first"/> <span class="color-red">*</span></label>

                    <div class="col-lg-9">
                        <input name="techFirstName" type="text" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label"><uengine:message code="shop.info.last"/> <span class="color-red">*</span></label>

                    <div class="col-lg-9">
                        <input name="techLastName" type="text" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label"><uengine:message code="shop.info.phone"/> <span class="color-red">*</span></label>

                    <div class="col-lg-9">
                        <input name="techPhone" type="tel" class="form-control">
                    </div>
                </div>

                <br><br>

                <h4><uengine:message code="shop.info.bill.title"/> </h4>

                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <div class="checkbox">
                            <label>
                                <input class="samebillinfo" type="checkbox"> <uengine:message code="shop.info.same"/>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label"><uengine:message code="shop.info.email"/> <span class="color-red">*</span></label>

                    <div class="col-lg-9">
                        <input name="billEmail" type="email" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label"><uengine:message code="shop.info.first"/> <span class="color-red">*</span></label>

                    <div class="col-lg-9">
                        <input name="billFirstName" type="text" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label"><uengine:message code="shop.info.last"/> <span class="color-red">*</span></label>

                    <div class="col-lg-9">
                        <input name="billLastName" type="text" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label"><uengine:message code="shop.info.phone"/> <span class="color-red">*</span></label>

                    <div class="col-lg-9">
                        <input name="billPhone" type="tel" class="form-control">
                    </div>
                </div>

                <br><br>

                <h4 class="enduserTag"><uengine:message code="shop.info.comp.title"/> </h4>
                <h4 class="resellerTag"><uengine:message code="shop.info.comp.enduser"/> </h4>

                <div class="form-group">
                    <label class="col-lg-3 control-label"><uengine:message code="shop.info.comp.name"/> <span class="color-red">*</span></label>

                    <div class="col-lg-9">
                        <input name="enduserCompName" type="text" class="form-control" value="${user.organization}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label"><uengine:message code="shop.info.comp.country"/> <span class="color-red">*</span></label>

                    <div class="col-lg-9">
                        <select name="_enduserCompCountry" class="form-control" disabled="disabled">
                            <%@include file="../template/countryList.jsp" %>
                        </select>
                        <input type="hidden" name="enduserCompCountry">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label"><uengine:message code="shop.info.comp.add1"/> <span class="color-red">*</span></label>

                    <div class="col-lg-9">
                        <input name="enduserCompAddress1" type="text" class="form-control" value="${user.address1}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label"><uengine:message code="shop.info.comp.add2"/> </label>

                    <div class="col-lg-9">
                        <input name="enduserCompAddress2" type="text" class="form-control" value="${user.address2}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label"><uengine:message code="shop.info.comp.city"/> <span class="color-red">*</span></label>

                    <div class="col-lg-9">
                        <input name="enduserCompCity" type="text" class="form-control" value="${user.city}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label"><uengine:message code="shop.info.comp.state"/> </label>

                    <div class="col-lg-9">
                        <input name="enduserCompState" type="text" class="form-control" value="${user.state}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label"><uengine:message code="shop.info.comp.zip"/> </label>

                    <div class="col-lg-9">
                        <input name="enduserCompZip" type="text" class="form-control" value="${user.zip}">
                    </div>
                </div>

                <br><br>

                <div class="row resellerTag">
                    <h4><uengine:message code="shop.info.comp.reseller"/> </h4>

                    <div class="form-group">
                        <label class="col-lg-3 control-label"><uengine:message code="shop.info.comp.name"/> <span class="color-red">*</span></label>

                        <div class="col-lg-9">
                            <input name="resellerCompName" type="text" class="form-control"
                                   value="${user.organization}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label"><uengine:message code="shop.info.comp.country"/> <span class="color-red">*</span></label>

                        <div class="col-lg-9">
                            <select name="resellerCompCountry" class="form-control">
                                <%@include file="../template/countryList.jsp" %>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label"><uengine:message code="shop.info.comp.add1"/> <span class="color-red">*</span></label>

                        <div class="col-lg-9">
                            <input name="resellerCompAddress1" type="text" class="form-control"
                                   value="${user.address1}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label"><uengine:message code="shop.info.comp.add2"/> </label>

                        <div class="col-lg-9">
                            <input name="resellerCompAddress2" type="text" class="form-control"
                                   value="${user.address2}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label"><uengine:message code="shop.info.comp.city"/> <span class="color-red">*</span></label>

                        <div class="col-lg-9">
                            <input name="resellerCompCity" type="text" class="form-control" value="${user.city}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label"><uengine:message code="shop.info.comp.state"/> </label>

                        <div class="col-lg-9">
                            <input name="resellerCompState" type="text" class="form-control" value="${user.state}">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-lg-3 control-label"><uengine:message code="shop.info.comp.zip"/> </label>

                        <div class="col-lg-9">
                            <input name="resellerCompZip" type="text" class="form-control" value="${user.zip}">
                        </div>
                    </div>
                </div>


                <div class="form-group">
                    <div class="col-lg-offset-2 col-lg-10">
                        <a id="submitBtn" class="btn-u btn-u-primary"><uengine:message code="shop.info.continue"/> </a>
                    </div>
                </div>
                <fieldset>
                    <input type="hidden" name="productId" value="${productMix.id}"/>
                    <input type="hidden" name="userId" value="${user.id}"/>
                    <input type="hidden" name="_flowExecutionId" value="<c:out value="${flowExecutionId}"/>"/>
                    <input type="hidden" name="_eventId" value="submit"/>
                </fieldset>
            </form>
        </div>
    </div>
    <script type="text/javascript">
        $(function () {
            $('[name=_enduserCompCountry]').val('${productMix.country}');
            $('[name=enduserCompCountry]').val('${productMix.country}');
            $('[name=resellerCompCountry]').val('${productMix.country}');
            $('.resellerTag').hide();


            $('input[name=reseller]').change(function () {
                if ($(this).attr('checked')) {
                    //리셀러일경우
                    $('.enduserTag').hide();
                    $('.resellerTag').show();
                } else {
                    //리셀러가 아닐경우
                    $('.enduserTag').show();
                    $('.resellerTag').hide();
                }
            });

            $('.sametechinfo').change(function () {
                if ($(this).attr('checked')) {
                    $('input[name=techEmail]').val($('input[name=userEmail]').val()).attr('readonly', 'readonly');
                    $('input[name=techFirstName]').val($('input[name=userFirstName]').val()).attr('readonly', 'readonly');
                    $('input[name=techLastName]').val($('input[name=userLastName]').val()).attr('readonly', 'readonly');
                    $('input[name=techPhone]').val($('input[name=userPhone]').val()).attr('readonly', 'readonly');
                } else {
                    $('input[name=techEmail]').removeAttr('readonly');
                    $('input[name=techFirstName]').removeAttr('readonly');
                    $('input[name=techLastName]').removeAttr('readonly');
                    $('input[name=techPhone]').removeAttr('readonly');
                }
            });

            $('.samebillinfo').change(function () {
                if ($(this).attr('checked')) {
                    $('input[name=billEmail]').val($('input[name=userEmail]').val()).attr('readonly', 'readonly');
                    $('input[name=billFirstName]').val($('input[name=userFirstName]').val()).attr('readonly', 'readonly');
                    $('input[name=billLastName]').val($('input[name=userLastName]').val()).attr('readonly', 'readonly');
                    $('input[name=billPhone]').val($('input[name=userPhone]').val()).attr('readonly', 'readonly');
                } else {
                    $('input[name=billEmail]').removeAttr('readonly');
                    $('input[name=billFirstName]').removeAttr('readonly');
                    $('input[name=billLastName]').removeAttr('readonly');
                    $('input[name=billPhone]').removeAttr('readonly');
                }
            });

            $('#submitBtn').click(function () {
                $('#submitForm').submit();
            });

            $.validator.addMethod('sameCompany', function (value, element, param) {
                if ($('input[name=reseller]').attr('checked')) {
                    //리셀러일경우
                    if ($('input[name=resellerCompName]').val() == $('input[name=enduserCompName]').val())
                        return false;
                }
                return true;
            });
            $('#submitForm').validate({

                focusInvalid: false, // do not focus the last invalid input
                ignore: "",
                rules: {
                    userEmail: {
                        required: true,
                        email: true
                    },
                    userFirstName: {
                        required: true
                    },
                    userLastName: {
                        required: true
                    },
                    userPhone: {
                        required: true
                    },
                    techEmail: {
                        required: true,
                        email: true
                    },
                    techFirstName: {
                        required: true
                    },
                    techLastName: {
                        required: true
                    },
                    techPhone: {
                        required: true
                    },
                    billEmail: {
                        required: true,
                        email: true
                    },
                    billFirstName: {
                        required: true
                    },
                    billLastName: {
                        required: true
                    },
                    billPhone: {
                        required: true
                    },
//                    resellerCompName:{
//                        required: true,
//                        sameCompany: true
//                    },
//                    resellerCompCountry: {
//                        required: true
//                    },
//                    resellerCompAddress1: {
//                        required: true
//                    },
//                    resellerCompCity: {
//                        required: true
//                    },
                    enduserCompName: {
                        required: true
                    },
                    enduserCompCountry: {
                        required: true
                    },
                    enduserCompAddress1: {
                        required: true
                    },
                    enduserCompCity: {
                        required: true
                    }
                },
                messages: {
                    userEmail: {
                        required: message.msg('form.invalid.common'),
                        email: message.msg('form.invalid.email')
                    },
                    userFirstName: {
                        required: message.msg('form.invalid.common')
                    },
                    userLastName: {
                        required: message.msg('form.invalid.common')
                    },
                    userPhone: {
                        required: message.msg('form.invalid.common')
                    },
                    techEmail: {
                        required: message.msg('form.invalid.common'),
                        email: message.msg('form.invalid.email')
                    },
                    techFirstName: {
                        required: message.msg('form.invalid.common')
                    },
                    techLastName: {
                        required: message.msg('form.invalid.common')
                    },
                    techPhone: {
                        required: message.msg('form.invalid.common')
                    },
                    billEmail: {
                        required: message.msg('form.invalid.common'),
                        email: message.msg('form.invalid.email')
                    },
                    billFirstName: {
                        required: message.msg('form.invalid.common')
                    },
                    billLastName: {
                        required: message.msg('form.invalid.common')
                    },
                    billPhone: {
                        required: message.msg('form.invalid.common')
                    },
                    resellerCompName:{
                        required: message.msg('form.invalid.common'),
                        sameCompany: message.msg('form.invalid.samecomp')
                    },
                    resellerCompCountry: {
                        required: message.msg('form.invalid.common')
                    },
                    resellerCompAddress1: {
                        required: message.msg('form.invalid.common')
                    },
                    resellerCompCity: {
                        required: message.msg('form.invalid.common')
                    },
                    enduserCompName: {
                        required: message.msg('form.invalid.common')
                    },
                    enduserCompCountry: {
                        required: message.msg('form.invalid.common')
                    },
                    enduserCompAddress1: {
                        required: message.msg('form.invalid.common')
                    },
                    enduserCompCity: {
                        required: message.msg('form.invalid.common')
                    }
                },
                invalidHandler: function() {
                    blockStop();
                },
                errorPlacement: function (error, element) {
                    error.css('color','red');
                    element.after(error);
                }
            });

        });
    </script>
    <!--=== End Content Part ===-->


    <%@include file="../template/footer.jsp" %>
</div>
<!--/wrapper-->
<%@include file="../template/footer_js.jsp" %>

</html>
