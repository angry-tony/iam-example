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
        $('[name=list-menu-client]').addClass('active');
    </script>

    <div id="page-wrapper" class="gray-bg dashbard-1">

        <%@include file="../template/header.jsp" %>
        <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Oauth Clients</h5>

                        <div class="ibox-tools">
                            <a href="/client/new" type="button" class="btn btn-primary btn-sm">New +</a>
                        </div>

                    </div>
                    <div class="ibox-content">

                        <div class="table-responsive">
                            <table id="client-table" class="table table-striped table-bordered table-hover">

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
        var dt = new uengineDT($('#client-table'), {
            order: [[0, "desc"]],
            select: true,
            columns: [
                {
                    data: 'label',
                    title: 'NAME',
                    defaultContent: '',
                    event: {
                        click: function (key, value, rowValue, rowIdx, td) {
                            window.location.href = '/client/' + rowValue['_id'] + '/edit';
                        }
                    }
                },
                {
                    data: 'description',
                    title: 'DESCRIPTION',
                    defaultContent: ''
                },
                {
                    data: 'clientType',
                    title: 'TYPE',
                    defaultContent: ''
                },
                {
                    data: '_id',
                    title: 'ID',
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
            ],
            "processing": true,
            "serverSide": true,
            "ajax": function (data, callback, settings) {
                var offset = data.start;
                var limit = data.length;
                var searchKey = data.search.value;
                searchKey = searchKey.length > 0 ? searchKey : null;

                iam.getClientSearch(searchKey, offset, limit)
                    .done(function (response) {
                        var clients = response['data'];
                        $.each(clients, function (index, client) {
                            client['label'] = '<a href="Javascript:void(0);">' + client['name'] + '</a>';
                        });
                        dt.gridData = clients;
                        callback({
                            recordsTotal: response.total,
                            recordsFiltered: response.filtered,
                            data: clients
                        });
                    })
                    .fail(function () {
                        toastr.error("Can't find client list.");
                    });
            }
        });
        dt.renderGrid();
        dt.getDt()
            .on('user-select', function (e, dt, type, cell, originalEvent) {
                if ($(originalEvent.target).index() === 0) {
                    e.preventDefault();
                }
            })
            .on('select', function (e, dt, type, indexes) {
                tableButtonCss();
            })
            .on('deselect', function (e, dt, type, indexes) {
                tableButtonCss();
            });
        var tableButtonCss = function () {
            var buttons = dt.getPanel().parent().find('.html5buttons').find('a');
            var count = dt.getDt().rows({selected: true}).count();
            if (count > 0) {
                buttons.eq(0).css('opacity', '1');
                buttons.eq(1).css('opacity', '1');
            } else {
                buttons.eq(0).css('opacity', '0.5');
                buttons.eq(1).css('opacity', '0.5');
            }
        };
        tableButtonCss();
    });
</script>
</body>
</html>
