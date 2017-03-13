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
    <title>uEngine IAM | HOME</title>

    <%@include file="../template/header_js.jsp" %>
</head>

<body>
<div id="wrapper">
    <%@include file="../template/nav.jsp" %>
    <script type="text/javascript">
        $('[name=list-menu-management]').find('ul').eq(0).addClass('in');
        $('[name=list-menu-management-list]').addClass('active');
    </script>

    <div id="page-wrapper" class="gray-bg dashbard-1">

        <%@include file="../template/header.jsp" %>

        <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Management Groups</h5>

                        <div class="ibox-tools">
                            <a href="/management/new" type="button" class="btn btn-primary btn-sm">New +</a>
                        </div>

                    </div>
                    <div class="ibox-content">

                        <div class="table-responsive">
                            <table id="management-table" class="table table-striped table-bordered table-hover">

                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="../template/footer.jsp" %>

    </div>
</div>
<%@include file="../template/footer_js.jsp" %>


<script>
    $(document).ready(function () {
        var fill = function (invoices) {
            var dt = new uengineDT($('#management-table'),
                {
                    order: [[0, "desc"]],
                    select: {
                        style: 'single'
                    },
                    columns: [
                        {
                            data: 'label',
                            title: 'NAME',
                            defaultContent: '',
                            event: {
                                click: function (key, value, rowValue, rowIdx, td) {
                                    iam.setDefaultManagement(
                                        rowValue['_id'],
                                        rowValue['managementKey'],
                                        rowValue['managementSecret']
                                    );
                                    window.location.href = '/management/profile';
                                }
                            }
                        },
                        {
                            data: 'description',
                            title: 'DESCRIPTION',
                            defaultContent: ''
                        },
                        {
                            data: 'managementKey',
                            title: 'KEY',
                            defaultContent: ''
                        }
                    ],
                    pageLength: 10,
                    info: true,
                    responsive: true,
                    dom: '<"html5buttons"B>lTfgitp',
                    buttons: [
                        {extend: 'copy'},
                        {extend: 'csv'},
                        {extend: 'excel', title: 'ExampleFile'},
                        {extend: 'pdf', title: 'ExampleFile'},

                        {
                            extend: 'print',
                            customize: function (win) {
                                $(win.document.body).addClass('white-bg');
                                $(win.document.body).css('font-size', '10px');

                                $(win.document.body).find('table')
                                    .addClass('compact')
                                    .css('font-size', 'inherit');
                            }
                        }
                    ]
                });
            dt.renderGrid(invoices);
        };
        iam.getManagements()
            .done(function (managements) {
                if (managements && managements.length) {
                    $.each(managements, function (index, management) {
                        management['label'] = '<a href="Javascript:void(0);">' + management['managementName'] + '</a>';
                    });
                    fill(managements);
                }
            })
            .fail(function (response) {
                toastr.error("Failed to get Management groups : " + response.responseText);
            });
    });
</script>
</body>
</html>
