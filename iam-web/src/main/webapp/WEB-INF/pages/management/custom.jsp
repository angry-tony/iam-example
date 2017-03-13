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
        $('[name=list-menu-custom]').addClass('active');
    </script>

    <div id="page-wrapper" class="gray-bg dashbard-1">

        <%@include file="../template/header.jsp" %>

        <div class="row">
            <div class="col-lg-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Custom Token Issuance</h5>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal"
                              role="form"
                              id="CustomTokenIssuanceForm" method="post">
                            <h4>Edit Custom Token Issuance </h4>

                            <br>

                            <p>Embedded Objects:</p>

                            <p>management, client, user, scope ,token_type, claim, type</p>

                            <div class="form-group">

                                <label class="col-md-2 control-label">Use Case </label>

                                <div class="col-md-6">
                                    <div>
                                        <label><input name="useCustomToken" type="checkbox"
                                                      value="code"> Authorization Code</label>
                                    </div>
                                    <div>
                                        <label><input name="useCustomToken" type="checkbox"
                                                      value="implicit"> Implicit Grant</label>
                                    </div>
                                    <div>
                                        <label><input name="useCustomToken" type="checkbox"
                                                      value="password"> Resource Owner Password Credentials</label>
                                    </div>
                                    <div>
                                        <label><input name="useCustomToken" type="checkbox"
                                                      value="credentials"> Client Credentials Grant</label>
                                    </div>
                                    <div>
                                        <label><input name="useCustomToken" type="checkbox"
                                                      value="refreshToken"> Refresh Token</label>
                                    </div>
                                    <div>
                                        <label><input name="useCustomToken" type="checkbox"
                                                      value="validateToken"> Validate Token</label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Script </label>

                                <div class="col-md-6">
                                    <textarea id="customTokenIssuance" name="customTokenIssuance" rows="12"
                                              class="form-control"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-offset-2 col-md-10">
                                    <button class="btn btn-primary" type="submit" name="submit">Edit
                                    </button>
                                </div>
                            </div>
                        </form>

                        <form action="#" class="form-horizontal"
                              role="form"
                              id="TestTokenIssuanceForm" method="post">
                            <h4>Test Custom Token Issuance </h4>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Test User </label>

                                <div class="col-md-6">
                                    <select name="userId" class="form-control">
                                        <option value="" selected>None</option>
                                    </select>

                                    <p>For test Client Credentials Grant, select none.</p>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Test Client <span
                                        class="color-red">*</span></label>

                                <div class="col-md-6">
                                    <select name="clientId" class="form-control">

                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Test Scopes <span
                                        class="color-red">*</span></label>

                                <div class="col-md-6">
                                    <select name="scopes" class="form-control" multiple="multiple">

                                    </select>

                                    <p>All scopes are enable during test.</p>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Test Token Type <span
                                        class="color-red">*</span></label>

                                <div class="col-md-6">
                                    <select name="tokenType" class="form-control">
                                        <option value="Bearer">Bearer</option>
                                        <option value="JWT">JWT</option>
                                    </select>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Test Claim </label>

                                <div class="col-md-6">
                                    <textarea name="claim" rows="8"
                                              class="form-control"></textarea>

                                    <p>For Bearer Token test, stay empty value</p>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-offset-2 col-md-10">
                                    <button id="testBtn" type="submit" class="btn btn-primary">Test
                                    </button>
                                </div>
                            </div>
                        </form>

                        <form action="#" class="form-horizontal"
                              role="form"
                              id="TestResult" method="post">
                            <h4>Test Result </h4>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Test Log </label>

                                <div class="col-md-6">
                                    <textarea name="log" rows="8"
                                              class="form-control"></textarea>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Test result</label>

                                <div class="col-md-6">
                                    <input name="result" type="text" class="form-control">

                                    <p id="result"></p>
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

<link rel="stylesheet" href="/resources/js/plugins/codemirror/codemirror.css">
<script type="text/javascript" src="/resources/js/plugins/codemirror/codemirror.js"></script>
<script type="text/javascript" src="/resources/js/plugins/codemirror/javascript.js"></script>
<script>
    $(document).ready(function () {

        var useCustomTokenIssuance = currentManagement['useCustomTokenIssuance'];
        var split = useCustomTokenIssuance.split(",");
        for (var i = 0; i < split.length; i++) {
            $("[name=useCustomToken]").each(function () {
                if (split[i] === $(this).val()) {
                    $(this).prop("checked", true);
                }
            })
        }

        var postForm = $('#CustomTokenIssuanceForm');
        postForm.validate({
            rules: {},
            submitHandler: function (form, event) {
                event.preventDefault();
                var data = postForm.serializeObject();
                var useCustomTokenIssuance = [];
                $("[name=useCustomToken]").each(function () {
                    if ($(this).prop("checked")) {
                        useCustomTokenIssuance.push($(this).val());
                    }
                });
                currentManagement['useCustomTokenIssuance'] = useCustomTokenIssuance.join();
                currentManagement['customTokenIssuance'] = data['customTokenIssuance'];

                blockStart('Please wait a moment...');

                iam.updateManagement(currentManagement['_id'], currentManagement)
                    .done(function () {
                        toastr.success('Management group updated.');
                    })
                    .fail(function () {
                        toastr.error('Failed to update management group');
                    })
                    .always(function () {
                        blockStop();
                    })
            }
        });

        var editor = new CodeMirror.fromTextArea(document.getElementById("customTokenIssuance"), {
            mode: "javascript",
            styleActiveLine: true,
            lineNumbers: true
        });

        var form = $('#TestTokenIssuanceForm');
        iam.getUserSearch(null, 0, 100)
            .done(function (users) {
                $.each(users['data'], function (index, user) {
                    form.find('[name=userId]').append('<option value="'+user['_id']+'">'+user['userName']+'</option>');
                });
                form.find('[name=userId]').select2();
            });
        iam.getClientSearch(null, 0, 100)
            .done(function (clients) {
                $.each(clients['data'], function (index, client) {
                    form.find('[name=clientId]').append('<option value="'+client['_id']+'">'+client['name']+'</option>');
                });
                form.find('[name=clientId]').select2();
            });
        iam.getScopeSearch(null, 0, 100)
            .done(function (scopes) {
                console.log(scopes);
                $.each(scopes['data'], function (index, scope) {
                    form.find('[name=scopes]').append('<option value="'+scope['_id']+'">'+scope['name']+'</option>');
                });
                form.find('[name=scopes]').select2();
            });

        //폼 발리데이션
        $.validator.addMethod('claimCheck', function (value, element, param) {
            if (value.length < 1) {
                return true;
            }
            try {
                JSON.parse(value);
            } catch (e) {
                return false;
            }
            return true;
        });
        form.validate({

            focusInvalid: false, // do not focus the last invalid input
            ignore: "",
            rules: {
                clientId: {
                    required: true
                },
                scopes: {
                    required: true
                },
                claim: {
                    claimCheck: true
                }
            },
            messages: {
                clientId: {
                    required: "<span style='color: red;'>Required filed</span>"
                },
                scopes: {
                    required: "<span style='color: red;'>Required filed</span>"
                },
                claim: {
                    claimCheck: "<span style='color: red;'>Invalid schema</span>"
                }
            },
            invalidHandler: function () {
                blockStop();
            },
            submitHandler: function (form, event) {
                blockStop();
                event.preventDefault();
                form = $('#TestTokenIssuanceForm');
                var data = {
                    userId: form.find('[name=userId]').val(),
                    clientId: form.find('[name=clientId]').val(),
                    scopes: form.find('[name=scopes]').val(),
                    tokenType: form.find('[name=tokenType]').val(),
                    claim: form.find('[name=claim]').val(),
                    script: editor.getDoc().getValue()
                };
                blockStart('Please wait a moment...');

                $.ajax({
                    type: "POST",
                    url: "/rest/v1/management/"+currentManagement['_id']+"/test",
                    data: JSON.stringify(data),
                    contentType: "application/json; charset=utf-8",
                    dataType: "text",
                    success: function (response) {
                        blockStop();
                        var res = JSON.parse(response);
                        $('[name=log]').val(res.log);
                        $('[name=result]').val(res.value);

                        if (typeof res.value !== 'boolean') {
                            $('#result').html('Return value must be a boolean type');
                        } else {
                            if (res.value) {
                                $('#result').html('This token will be issued .');
                            } else {
                                $('#result').html('This token will not be issued .');
                            }
                        }
                    },
                    error: function (request, status, errorThrown) {
                        blockStop();
                        msgBox('Unable test.');
                    }
                });
            }
        });
    });
</script>
</body>
</html>
