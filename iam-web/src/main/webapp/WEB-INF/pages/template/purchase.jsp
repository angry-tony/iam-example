<%@ page contentType="text/html; charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="uengine" uri="http://www.uengine.io/tags" %>

<!--=== Start Purchase Block ===-->
<div class="purchase">
    <div class="container">
        <div class="row">
            <div class="col-md-8 animated fadeInLeft">
                <span><uengine:message code="main.download.title"></uengine:message></span>
                <p><uengine:message code="main.download.desc"></uengine:message></p>
            </div>
            <div class="col-md-4 btn-buy animated fadeInRight">
                <a href="/download" class="btn-u btn-u-lg"><i class="fa fa-cloud-download"></i><uengine:message code="main.download.button"></uengine:message></a>

                <a href="/license/flamingo-trial" class="btn-u btn-u-lg"><i class="fa fa-cloud-download"></i><uengine:message code="main.trial.button"></uengine:message></a>
            </div>
        </div>
    </div>
</div>