<%@ page contentType="text/html; charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="uengine" uri="http://www.uengine.io/tags" %>

<!--=== Cube-Portfdlio ===-->
<script type="text/javascript">
    $(function () {
        initCube($('#workflow-container'), $('#workflow-filters'), {
            gapHorizontal: 20,
            gapVertical: 0,
            gridAdjustment: 'responsive',
            mediaQueries: [{
                width: 800,
                cols: 8
            }, {
                width: 500,
                cols: 3
            }, {
                width: 320,
                cols: 1
            }],
        });
    });
</script>
<div class="cube-portfolio container margin-bottom-60">
    <div class="content-xs">
        <div id="workflow-filters" class="cbp-l-filters-text content-xs">
            <div data-filter="*" class="cbp-filter-item-active cbp-filter-item"><uengine:message
                    code="workflowdata.all"/></div>
            |
            <div data-filter=".hadoop" class="cbp-filter-item"><uengine:message
                    code="workflowdata.hadoop"/></div>
            |
            <div data-filter=".dataprocessing" class="cbp-filter-item"><uengine:message
                    code="workflowdata.dataprocessing"/></div>
            |
            <div data-filter=".statistics" class="cbp-filter-item"><uengine:message
                    code="workflowdata.statistics"/></div>
            |
            <div data-filter=".datamining" class="cbp-filter-item"><uengine:message
                    code="workflowdata.datamining"/></div>
            |
            <div data-filter=".mahout" class="cbp-filter-item"><uengine:message
                    code="workflowdata.mahout"/></div>
            |
            <div data-filter=".inmemory" class="cbp-filter-item"><uengine:message
                    code="workflowdata.inmemory"/></div>
            |
            <div data-filter=".bpmn" class="cbp-filter-item"><uengine:message
                    code="workflowdata.bpmn"/></div>
        </div>
        <!--/end Filters Container-->
    </div>

    <div id="workflow-container" class="cbp-l-grid-agency">

    </div>
    <script type="text/javascript" src="/resources/structure/workflow.data.js"></script>
    <script type="text/javascript">
        for (var i = 0; i < workflowData.length; i++) {
            var category = workflowData[i].category;
            var text = workflowData[i].text;
            var list = workflowData[i].list;
            for (var l = 0; l < list.length; l++) {
                var item = list[l];
                var item_text = item.text;
                var item_img = item.img;
                var str = '' +
                        '<div class="cbp-item ' + category + '">' +
                        '<div class="cbp-caption">' +
                        '<div class="cbp-caption-defaultWrap">' +
                        '<img src="' + item_img + '" alt="" style="width:80px;margin: 0px auto;display:block;" align="middle">' +
                        '</div>' +
                        '<div class="cbp-caption-activeWrap">' +
                        '<div class="cbp-l-caption-alignCenter">' +
                        '<div class="cbp-l-caption-body">' +
                        '<div class="cbp-l-grid-agency-title" style="white-space: normal">' + item_text + '</div>' +
                        '<div class="cbp-l-grid-agency-desc" style="white-space: normal">' + category + '</div>' +
                        '</div>' +
                        '</div>' +
                        '</div>' +
                        '</div>' +
                        '</div>';
                $('#workflow-container').append(str);
            }
        }
    </script>
    <!--/end Grid Container-->
</div>
<!--=== End Cube-Portfdlio ===-->