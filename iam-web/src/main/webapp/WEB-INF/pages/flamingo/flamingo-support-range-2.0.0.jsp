<%@ page contentType="text/html; charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="uengine" uri="http://www.uengine.io/tags" %>


<script src="/resources/data/flamingo-support-range-2.0.0.data.js"></script>
<script type="text/javascript">
    $(function () {
        pullTable($('#supportRange') , supportRangeData);
    });
</script>
<div class="panel panel-default margin-bottom-40">
    <table class="table table-bordered invoice-table" id="supportRange">

    </table>
</div>