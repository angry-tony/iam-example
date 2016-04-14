<%@ page contentType="text/html; charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="uengine" uri="http://www.uengine.io/tags" %>

<!--=== Content Part ===-->

<div class="row portfolio-item margin-bottom-50" id="workflow">

    <!-- Content Info -->
    <div class="col-md-5">
        <h2><uengine:message code="menu.product.flamingo.features.workflow"/></h2>

        <p><uengine:message code="flamingo.feature.workflow.text"/></p>


        <div class="row">
            <ul class="col-xs-6 list-unstyled lists-v1">
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.workflow.list1"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.workflow.list2"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.workflow.list3"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.workflow.list4"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.workflow.list5"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.workflow.list6"/></li>
            </ul>
            <ul class="col-xs-6 list-unstyled lists-v1">
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.workflow.list7"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.workflow.list8"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.workflow.list9"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.workflow.list10"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.workflow.list11"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.workflow.list12"/></li>
                <li><i class="fa fa-check-circle-o"></i><uengine:message code="flamingo.feature.workflow.list13"/></li>
            </ul>
        </div>

<%--
        <a href="#" class="btn-u btn-u-large"><uengine:message code="flamingo.feature.commonmovie"/></a>
--%>
    </div>
    <!-- End Content Info -->

    <!-- Carousel -->
    <div class="col-md-7">
        <div class="carousel slide carousel-v1" id="carousel_workflow">
            <div class="carousel-inner">
                <div class="item active">
                    <img alt="" src="<uengine:message code="flamingo.feature.workflow.image.url1"/>">

                    <div class="carousel-caption">
                        <p><uengine:message code="flamingo.feature.workflow.image1"/></p>
                    </div>
                </div>
                <div class="item">
                    <img alt="" src="<uengine:message code="flamingo.feature.workflow.image.url2"/>">

                    <div class="carousel-caption">
                        <p><uengine:message code="flamingo.feature.workflow.image2"/></p>
                    </div>
                </div>
                <div class="item">
                    <img alt="" src="<uengine:message code="flamingo.feature.workflow.image.url3"/>">

                    <div class="carousel-caption">
                        <p><uengine:message code="flamingo.feature.workflow.image3"/></p>
                    </div>
                </div>
                <div class="item">
                    <img alt="" src="<uengine:message code="flamingo.feature.workflow.image.url4"/>">

                    <div class="carousel-caption">
                        <p><uengine:message code="flamingo.feature.workflow.image5"/></p>
                    </div>
                </div>
                <div class="item">
                    <img alt="" src="<uengine:message code="flamingo.feature.workflow.image.url5"/>">

                    <div class="carousel-caption">
                        <p><uengine:message code="flamingo.feature.workflow.image5"/></p>
                    </div>
                </div>
            </div>

            <div class="carousel-arrow">
                <a data-slide="prev" href="#carousel_workflow" class="left carousel-control">
                    <i class="fa fa-angle-left"></i>
                </a>
                <a data-slide="next" href="#carousel_workflow" class="right carousel-control">
                    <i class="fa fa-angle-right"></i>
                </a>
            </div>
        </div>
    </div>
    <!-- End Carousel -->

</div>


<div class="tag-box tag-box-v2">
    <p><uengine:message code="flamingo.feature.workflow.sub"/></p>
</div>

<!--/container-->
<!--=== End Content Part ===-->

<div class="headline-center">
    <h2><uengine:message code="flamingo.feature.mapreduce.title"/></h2>
</div>

<div class="panel panel-default margin-bottom-20 table-requirement">
    <table class="table table-bordered">
        <thead>
        <tr>
            <th width="100"><uengine:message code="flamingo.feature.mapreduce.category"/></th>
            <th><uengine:message code="flamingo.feature.mapreduce.module"/></th>
        </tr>
        </thead>

        <tbody>
        <tr>
            <td>Hadoop</td>
            <td>MapReduce, Apache Pig, Apache Hive, Apache Spark</td>
        </tr>
        <tr>
            <td>Workflow</td>
            <td>Conditional Fork/Join, Parallel Fork/Join, Sub-workflow</td>
        </tr>
        <tr>
            <td>ETC</td>
            <td>R, Bash, Python, Java, UIMA</td>
        </tr>
        <tr>
            <td>ETL</td>
            <td>Clean, Grep, Encryption, Replace, Remove, Min/Max Normalization</td>
        </tr>
        <tr>
            <td>Statistics</td>
            <td>Numeric Statistics Distribution, Nominal Statistics Distribution, Certainty Factor based Sum</td>
        </tr>
        <tr>
            <td>Mining</td>
            <td>ID3 Classification, k-Means, EM Clustering, CF based Similarity, String Similarity, Boolean Similarity,
                Numeric Similarity, Content based Similarity, Item based CF, User/Item based Recommendation, Factorized
                Recommendation, Parallel ALS, Streaming k-Means, Cluster Quality Summarizer, Spectral k-Means
            </td>
        </tr>
        </tbody>
    </table>
</div>

<div class="tag-box tag-box-v2">
    <p><uengine:message code="flamingo.feature.mapreduce.sub"/></p>
</div>


<!--/container-->
<!--=== End Content Part ===-->
