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
    <title>uEngine Billing | HOME</title>

    <%@include file="../template/header_js.jsp" %>
</head>

<body>
<div id="wrapper">
    <%@include file="../template/nav.jsp" %>
    <script type="text/javascript">
        $('[name=list-menu-management]').find('ul').eq(0).addClass('in');
        $('[name=list-menu-management-profile]').addClass('active');
    </script>

    <div id="page-wrapper" class="gray-bg dashbard-1">

        <%@include file="../template/header.jsp" %>

        <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Management Profile</h5>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal"
                              role="form"
                              id="managementForm" method="post">
                            <h4 name="title">Create New Management Group </h4>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Name <span class="color-red">*</span></label>

                                <div class="col-md-6">
                                    <input name="managementName" type="text" class="form-control" value="">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Description <span
                                        class="color-red">*</span></label>

                                <div class="col-md-6">
                                    <textarea rows="8" name="description" class="form-control"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Login Check Lifetime <span
                                        class="color-red">*</span></label>

                                <div class="col-md-6">
                                    <input name="sessionTokenLifetime" type="number" class="form-control" value="3600">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Scope Check Lifetime <span
                                        class="color-red">*</span></label>

                                <div class="col-md-6">
                                    <input name="scopeCheckLifetime" type="number" class="form-control" value="3600">
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-offset-2 col-md-10">
                                    <button class="btn btn-default" name="delete" style="display: none">
                                        <i class="fa fa-trash"></i> Delete</button>
                                    <button class="btn btn-primary" type="submit" name="submit">Create Management
                                        Group
                                    </button>
                                </div>
                            </div>
                        </form>
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

        var isProfile = '${profile}';
        var form = $('#managementForm');
        if (isProfile == 'true') {
            $('[name=title]').html('Edit Management Group');
            form.deserialize(currentManagement);
            form.find('[name=submit]').text('EDIT');

            form.find('[name=delete]').show()
                .click(function(){
                    iam.deleteManagement(currentManagement['_id'])
                        .done(function () {
                            window.location.href = '/management/list';
                        })
                        .fail(function () {
                            toastr.error('Failed to delete management group');
                        })
                        .always(function () {
                            blockStop();
                        })
                });
        }else{
            $('[name=title]').html('Create New Management Group');
        }

        form.submit(function (event) {
            event.preventDefault();
            var data = form.serializeObject();

            blockStart('Please wait a moment...');

            if (isProfile == 'true') {
                iam.updateManagement(currentManagement['_id'], data)
                    .done(function () {
                        toastr.success('Management group updated.');
                    })
                    .fail(function () {
                        toastr.error('Failed to update management group');
                    })
                    .always(function () {
                        blockStop();
                    })
            } else {
                iam.createManagement(data)
                    .done(function () {
                        window.location.href = '/management/list';
                    })
                    .fail(function () {
                        toastr.error('Failed to create management group');
                    })
                    .always(function () {
                        blockStop();
                    })
            }
        });
    });
</script>
</body>
</html>
