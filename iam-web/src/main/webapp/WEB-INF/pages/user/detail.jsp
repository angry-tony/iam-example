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
        $('[name=list-menu-user]').addClass('active');
    </script>

    <div id="page-wrapper" class="gray-bg dashbard-1">

        <%@include file="../template/header.jsp" %>

        <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Oauth User</h5>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal"
                              role="form"
                              id="userForm" method="post">
                            <h4 name="title">Create New Oauth User </h4>

                            <h4>Require fields (If there is no require field value, it will be stored as a field value
                                in the
                                database.) </h4>

                            <p>userName: string</p>

                            <p>userPassword: string</p>

                            <p>level: number 0 ~ 5 </p>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Body <span class="color-red">*</span></label>

                                <div class="col-md-6">
                                    <textarea name="body" rows="12" class="form-control"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-offset-2 col-md-10">
                                    <button class="btn btn-default" name="delete" style="display: none">
                                        <i class="fa fa-trash"></i> Delete
                                    </button>
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
        var oauthUser;
        var form = $('#userForm');
        if (_id) {
            $('[name=title]').html('Edit New Oauth User');
            form.find('[name=submit]').text('EDIT');
            form.find('[name=delete]').show()
                .click(function () {
                    iam.deleteUser(oauthUser['_id'])
                        .done(function () {
                            window.location.href = contextPath + '/user/list';
                        })
                        .fail(function () {
                            toastr.error('Failed to delete oauth user');
                        })
                        .always(function () {
                            blockStop();
                        })
                });

            iam.getUser(_id)
                .done(function (user) {
                    oauthUser = user;
                    var filteredUser = JSON.parse(JSON.stringify(user));
                    delete filteredUser['userPassword'];
                    delete filteredUser['docType'];
                    delete filteredUser['managementId'];
                    delete filteredUser['_rev'];
                    delete filteredUser['regDate'];
                    delete filteredUser['_id'];
                    delete filteredUser['updDate'];

                    var str = JSON.stringify(filteredUser, null, 2);
                    console.log('str', str);
                    form.find('[name=body]').val(str);
                })
                .fail(function () {
                    toastr.error('Failed to find oauth user');
                })
        } else {
            $('[name=title]').html('Create New Oauth User');
            var obj = {
                userName: 'some user name',
                userPassword: 'some user password',
                level: 5
            };
            var str = JSON.stringify(obj, null, 2);
            form.find('[name=body]').val(str);
        }

        //폼 발리데이션
        $.validator.addMethod('userCheck', function (value, element, param) {
            var user;
            try {
                user = JSON.parse(value);
            } catch (e) {
                return false;
            }
            if (user['userName']) {
                if (user['userName'] && typeof user['userName'] !== 'string') {
                    return false;
                }
                if (user.userName.length < 1) {
                    return false;
                }
            }
            if (user['userPassword']) {
                if (typeof user['userPassword'] !== 'string') {
                    return false;
                }
                if (user.userPassword.length < 1) {
                    return false;
                }
            }
            if (user['level']) {
                if (typeof user['level'] !== 'number') {
                    return false;
                }
                if (user.level > 5 || user.level < 0) {
                    return false;
                }
            }
            return true;
        });
        form.validate({

            focusInvalid: false, // do not focus the last invalid input
            ignore: "",
            rules: {
                body: {
                    required: true,
                    userCheck: true
                }
            },
            messages: {
                body: {
                    required: "<span style='color: red;'>Required filed</span>",
                    userCheck: "<span style='color: red;'>Invalid schema</span>"
                }
            },
            invalidHandler: function () {
                blockStop();
            },
            submitHandler: function (f, event) {
                event.preventDefault();
                var body = form.find('[name=body]').val();
                blockStart('Please wait a moment...');

                if (_id) {
                    iam.updateUser(oauthUser['_id'], JSON.parse(body))
                        .done(function () {
                            toastr.success('Oauth user updated.');
                        })
                        .fail(function () {
                            toastr.error('Failed to oauth user');
                        })
                        .always(function () {
                            blockStop();
                        })
                } else {
                    iam.createUser(JSON.parse(body))
                        .done(function () {
                            window.location.href = contextPath + '/user/list';
                        })
                        .fail(function () {
                            toastr.error('Failed to create oauth user');
                        })
                        .always(function () {
                            blockStop();
                        })
                }
            }
        });
    });
</script>
</body>
</html>
