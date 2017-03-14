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
        $('[name=list-menu-scope]').addClass('active');
    </script>

    <div id="page-wrapper" class="gray-bg dashbard-1">

        <%@include file="../template/header.jsp" %>

        <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Oauth Scope Profile</h5>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal"
                              role="form"
                              id="scopeForm" method="post">
                            <h4 name="title">Create New Oauth Scope </h4>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Name <span class="color-red">*</span></label>

                                <div class="col-md-6">
                                    <input name="name" type="text" class="form-control">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Description <span class="color-red">*</span></label>

                                <div class="col-md-6">
                                    <input name="description" type="text" class="form-control">
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-offset-2 col-md-10">
                                    <button class="btn btn-default" name="delete" style="display: none">
                                        <i class="fa fa-trash"></i> Delete</button>
                                    <button class="btn btn-primary" type="submit" name="submit">Create</button>
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

        var _id = '${_id}';
        if (!_id || _id.length == '') {
            _id = undefined;
        }
        var form = $('#scopeForm');
        if (_id) {
            $('[name=title]').html('Edit Oauth Scope');
            form.find('[name=submit]').text('EDIT');
            form.find('[name=delete]').show()
                .click(function(){
                    iam.deleteScope(_id)
                        .done(function () {
                            window.location.href = contextPath + '/scope/list';
                        })
                        .fail(function () {
                            toastr.error('Failed to delete scope');
                        })
                        .always(function () {
                            blockStop();
                        })
                });

            iam.getScope(_id)
                .done(function(scope){
                    console.log(scope);
                    form.deserialize(scope);
                })
                .fail(function(response){
                    toastr.error('Failed to find oauth scope');
                })
        }else{
            $('#readonly-fields').hide();
            $('[name=title]').html('Create New Management Group');
        }

        form.submit(function (event) {
            event.preventDefault();
            var data = form.serializeObject();

            blockStart('Please wait a moment...');

            if (_id) {
                iam.updateScope(_id, data)
                    .done(function () {
                        toastr.success('Oauth scope updated.');
                    })
                    .fail(function () {
                        toastr.error('Failed to update oauth scope');
                    })
                    .always(function () {
                        blockStop();
                    })
            } else {
                iam.createScope(data)
                    .done(function () {
                        window.location.href = contextPath + '/scope/list';
                    })
                    .fail(function () {
                        toastr.error('Failed to create oauth scope');
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
