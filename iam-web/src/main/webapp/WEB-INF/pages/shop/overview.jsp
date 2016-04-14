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
        <div class="row margin-bottom-60">
            <div class="col-sm-8">
                <div class="headline"><h2><uengine:message code="shop.overview.title"/> </h2></div>
                <p><uengine:message code="shop.overview.desc"/> </p>

                <fieldset class="no-padding">
                    <label><uengine:message code="shop.overview.text"/> </label>
                    <div class="row sky-space-20">
                        <div class="col-md-11 col-md-offset-0">
                            <div>
                                <textarea rows="8" class="form-control" name="note"></textarea>
                            </div>
                        </div>
                    </div>

                    <br><br>

                    <label><uengine:message code="shop.overview.process"/> </label>
                    <div class="row sky-space-20">
                        <div class="col-md-11 col-md-offset-0">
                            <select class="form-control">
                                <option value="paypal">Paypal</option>
                            </select>
                        </div>
                    </div>

                </fieldset>
            </div>
            <div class="col-sm-4">
                <div class="headline"><h2><uengine:message code="shop.overview.detail.title"/> </h2></div>
                <ul class="list-unstyled project-details">
                    <li><strong><uengine:message code="shop.overview.detail.id"/> :</strong> ${productMix.id}</li>
                    <li><strong><uengine:message code="shop.overview.detail.name"/> :</strong> ${productMix.productName}</li>
                    <li><strong><uengine:message code="shop.overview.detail.version"/> :</strong> ${productMix.productVersion}</li>
                    <li><strong><uengine:message code="shop.overview.detail.license"/> :</strong> ${productMix.licenseType}</li>
                    <li><strong><uengine:message code="shop.overview.detail.holder"/> :</strong> ${purchaseHistory.enduserCompName}</li>
                    <li><strong><uengine:message code="shop.overview.detail.node"/> :</strong> ${productMix.maxNode}</li>
                    <li><strong><uengine:message code="shop.overview.detail.days"/> :</strong> ${productMix.days}</li>
                    <li><strong><uengine:message code="shop.overview.detail.country"/> :</strong> ${productMix.country}</li>
                    <li><strong><uengine:message code="shop.overview.detail.price"/> :</strong> $ ${productMix.price}</li>
                </ul>
                <br><br>

                <a id="submitBtn" class="btn-u btn-u-primary"><uengine:message code="shop.overview.continue"/> </a>
            </div>
        </div>
    </div>


    <!--=== Content Part ===-->

        <form id="order_container" method="post">
            <input type="hidden" name="cmd" value="_xclick"/>
            <input type="hidden" name="business"/>
            <input type="hidden" name="amount" value="${productMix.price}"/>
            <input type="hidden" name="item_name" value="${productMix.productName}"/>
            <input type="hidden" name="return"/>
            <input type="hidden" name="cancel_return"/>
            <input type="hidden" name="notify_url"/>
            <input type="hidden" name="charset" value="UTF-8"/>
            <input type="hidden" name="currency_type" value="USD"/>
            <input type="hidden" name="invoice">
            <input type="hidden" name="custom" value="${user.email}">

            <!-- Enable override of buyers's address stored with PayPal . -->
            <input type="hidden" name="address_override" value="1">
            <!-- Set variables that override the address stored with PayPal. -->
            <input type="hidden" name="email" value="${purchaseHistory.billEmail}">
            <input type="hidden" name="first_name" value="${purchaseHistory.billFirstName}">
            <input type="hidden" name="last_name" value="${purchaseHistory.billLastName}">
            <input type="hidden" name="night_phone_b" value="${purchaseHistory.billPhone}">

            <input type="hidden" name="address1" value="${purchaseHistory.enduserCompAddress1}">
            <input type="hidden" name="address2" value="${purchaseHistory.enduserCompAddress2}">
            <input type="hidden" name="city" value="${purchaseHistory.enduserCompCity}">
            <input type="hidden" name="state" value="${purchaseHistory.enduserCompState}">
            <input type="hidden" name="zip" value="${purchaseHistory.enduserCompZip}">
            <%--<input type="hidden" name="country" value="${purchaseHistory.enduserCompCountry}">--%>
        </form>

    <script type="text/javascript">
        var currentServerPath = window.location.protocol + "//" + window.location.host;
        $(function(){
            var form = $('#order_container');
            form.attr('action' , config['payment.EndPoint']);
            form.find('[name=business]').val(config['payment.account']);
            form.find('[name=return]').val(currentServerPath + '${flowExecutionUrl}' + '&_eventId=success');
            form.find('[name=cancel_return]').val(currentServerPath + '${flowExecutionUrl}' + '&_eventId=cancel');
            form.find('[name=notify_url]').val(currentServerPath + '/payment/notify');

            var reseller = false;
            if('${purchaseHistory.reseller}' == 'true')reseller = true;
            if(reseller){
                form.find('[name=address1]').val('${purchaseHistory.resellerCompAddress1}');
                form.find('[name=address2]').val('${purchaseHistory.resellerCompAddress2}');
                form.find('[name=city]').val('${purchaseHistory.resellerCompCity}');
                form.find('[name=state]').val('${purchaseHistory.resellerCompState}');
                form.find('[name=zip]').val('${purchaseHistory.resellerCompZip}');
                form.find('[name=country]').val('${purchaseHistory.resellerCompCountry}');
            }

            $('#submitBtn').click(function(){
                var data = {
                    reseller: reseller,
                    productId : ${productMix.id},
                    userEmail : '${purchaseHistory.userEmail}',
                    userFirstName : '${purchaseHistory.userFirstName}',
                    userLastName : '${purchaseHistory.userLastName}',
                    userPhone : '${purchaseHistory.userPhone}',
                    techEmail : '${purchaseHistory.techEmail}',
                    techFirstName : '${purchaseHistory.techFirstName}',
                    techLastName : '${purchaseHistory.techLastName}',
                    techPhone : '${purchaseHistory.techPhone}',
                    billEmail : '${purchaseHistory.billEmail}',
                    billFirstName : '${purchaseHistory.billFirstName}',
                    billLastName : '${purchaseHistory.billLastName}',
                    billPhone : '${purchaseHistory.billPhone}',
                    resellerCompName : '${purchaseHistory.resellerCompName}',
                    resellerCompCountry : '${purchaseHistory.resellerCompCountry}',
                    resellerCompAddress1 : '${purchaseHistory.resellerCompAddress1}',
                    resellerCompAddress2 : '${purchaseHistory.resellerCompAddress2}',
                    resellerCompCity : '${purchaseHistory.resellerCompCity}',
                    resellerCompState : '${purchaseHistory.resellerCompState}',
                    resellerCompZip : '${purchaseHistory.resellerCompZip}',
                    enduserCompName : '${purchaseHistory.enduserCompName}',
                    enduserCompCountry : '${purchaseHistory.enduserCompCountry}',
                    enduserCompAddress1 : '${purchaseHistory.enduserCompAddress1}',
                    enduserCompAddress2 : '${purchaseHistory.enduserCompAddress2}',
                    enduserCompCity : '${purchaseHistory.enduserCompCity}',
                    enduserCompState : '${purchaseHistory.enduserCompState}',
                    enduserCompZip : '${purchaseHistory.enduserCompZip}',
                    note : $('[name=note]').val(),
                    detail : '${purchaseHistory.detail}'
                };
                blockStart(message.msg('shop.overview.wait'));
                $.ajax({
                    type: "POST",
                    url: "/payment/request",
                    data: JSON.stringify(data),
                    contentType: "application/json; charset=utf-8",
                    dataType: "text",
                    success: function (response) {
                        var res = JSON.parse(response);
                        if (res.success){
                            var identifier = res.map.identifier;
                            form.find('[name=invoice]').val(identifier);
                            form.submit();
                        }
                        else{
                            blockStop();
                            msgBox(message.msg('shop.overview.fail'));
                        }
                    },
                    error: function (request, status, errorThrown) {
                        blockStop();
                        msgBox(message.msg('shop.overview.fail'));
                    }
                });
            });
        })
    </script>

    <!--=== End Content Part ===-->


    <%@include file="../template/footer.jsp" %>
</div>
<!--/wrapper-->
<%@include file="../template/footer_js.jsp" %>

</html>
