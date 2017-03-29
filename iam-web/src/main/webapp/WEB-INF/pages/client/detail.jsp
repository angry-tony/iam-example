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
                        <h5>Client Profile</h5>
                    </div>
                    <div class="ibox-content">
                        <form class="form-horizontal" role="form"
                              id="oauthClientForm" method="post">
                            <h4 name="title">Edit Oauth Client </h4>

                            <div id="readonly-fields">
                                <div class="form-group">
                                    <label class="col-md-2 control-label">Client ID <span
                                            class="color-red">*</span></label>

                                    <div class="col-md-6">
                                        <input type="text" name="clientKey" class="form-control" readonly>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 control-label">Client Secret <span class="color-red">*</span></label>

                                    <div class="col-md-6">
                                        <input type="text" name="clientSecret" class="form-control" readonly>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-md-2 control-label">Client Jwt Secret <span
                                            class="color-red">*</span></label>

                                    <div class="col-md-6">
                                        <input type="text" name="clientJwtSecret" class="form-control" readonly>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Name <span class="color-red">*</span></label>

                                <div class="col-md-6">
                                    <input name="name" type="text" class="form-control">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Description <span
                                        class="color-red">*</span></label>

                                <div class="col-md-6">
                                    <input name="description" type="text" class="form-control">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Client Trust<span
                                        class="color-red">*</span></label>

                                <div class="col-md-6">
                                    <div>
                                        <label>
                                            <input type="radio" name="clientTrust"
                                                   value="trust" checked> Trust application
                                        </label>
                                    </div>
                                    <div>
                                        <label>
                                            <input type="radio" name="clientTrust"
                                                   value="3th_party"> 3Th party application
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Client Type <span
                                        class="color-red">*</span></label>

                                <div class="col-md-6">
                                    <div>
                                        <label>
                                            <input type="radio" name="clientType" value="confidential"> Confidential
                                        </label>
                                    </div>
                                    <div>
                                        <label>
                                            <input type="radio" name="clientType" value="public" checked> Public
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Active Client </label>

                                <div class="col-md-6">
                                    <label>
                                        <input type="checkbox" name="activeClient" value="Y"> active
                                    </label>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Enable GrantTypes </label>

                                <div class="col-md-6">
                                    <div>
                                        <label>
                                            <input type="checkbox" name="grantType" value="code"> Authorization Code
                                        </label>
                                    </div>
                                    <div>
                                        <label>
                                            <input type="checkbox" name="grantType" value="implicit"> Implicit Grant
                                        </label>
                                    </div>
                                    <div>
                                        <label>
                                            <input type="checkbox" name="grantType" value="password"> Resource Owner
                                            Password Credentials
                                        </label>
                                    </div>
                                    <div>
                                        <label>
                                            <input type="checkbox" name="grantType" value="credentials"> Client
                                            Credentials
                                            Grant
                                        </label>
                                    </div>
                                </div>

                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Web Server Redirect Uri </label>

                                <div class="col-md-6">
                                    <input name="webServerRedirectUri" type="text" class="form-control">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Refresh Token </label>

                                <div class="col-md-6">
                                    <label>
                                        <input type="checkbox" name="refreshTokenValidity"
                                               value="Y"> Enable refreshToken</label>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label"> Auto deletion expired refresh token </label>

                                <div class="col-md-6">
                                    <label>
                                        <input type="checkbox" name="autoDeletionToken" value="Y"> Enable auto deletion
                                        expired refresh token
                                    </label>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Code Lifetime <span
                                        class="color-red">*</span></label>

                                <div class="col-md-6">
                                    <input name="codeLifetime" type="number" class="form-control">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">RefreshToken Lifetime <span
                                        class="color-red">*</span></label>

                                <div class="col-md-6">
                                    <input name="refreshTokenLifetime" type="number" class="form-control">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">AccessToken Lifetime <span
                                        class="color-red">*</span></label>

                                <div class="col-md-6">
                                    <input name="accessTokenLifetime" type="number" class="form-control">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">JwtToken Lifetime <span
                                        class="color-red">*</span></label>

                                <div class="col-md-6">
                                    <input name="jwtTokenLifetime" type="number" class="form-control">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-md-2 control-label">Scopes <span
                                        class="color-red">*</span></label>

                                <div class="col-md-6">
                                    <select name="scopes" class="form-control" multiple="multiple">

                                    </select>
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

        var oauthScopes;
        var oauthClient;
        var clientScopes;
        var form = $('#oauthClientForm');
        if (_id) {
            iam.getAllScope()
                .done(function (scopes) {
                    oauthScopes = scopes;
                    iam.getClient(_id)
                        .done(function (client) {
                            oauthClient = client;
                            iam.getClientScopes(_id)
                                .done(function (client_scopes) {
                                    clientScopes = client_scopes;
                                    fill();
                                })
                                .fail(function (response) {
                                    toastr.error('Failed to find client scopes');
                                })
                        })
                        .fail(function (response) {
                            toastr.error('Failed to find client');
                        })
                })
                .fail(function (response) {
                    toastr.error('Failed to find scopes');
                })
        } else {
            iam.getAllScope()
                .done(function (scopes) {
                    oauthScopes = scopes;
                    fill();
                })
                .fail(function (response) {
                    toastr.error('Failed to find scopes');
                })
        }

        var fill = function () {
            if (oauthClient) {
                $('#readonly-fields').show();
                $('[name=title]').html('Edit Oauth Client');
                form.deserialize(oauthClient);
                form.find('[name=submit]').text('EDIT');

                form.find('[name=delete]').show()
                    .click(function () {
                        iam.deleteClient(oauthClient['_id'])
                            .done(function () {
                                window.location.href = contextPath + '/client/list';
                            })
                            .fail(function () {
                                toastr.error('Failed to delete oauth client');
                            })
                            .always(function () {
                                blockStop();
                            })
                    });
            } else {
                $('#readonly-fields').hide();
                $('[name=title]').html('Create New Oauth Client');
            }

            //스코프 처리
            $.each(oauthScopes, function (index, oauthScope) {
                var isSelected = false;
                if (clientScopes && clientScopes.length) {
                    $.each(clientScopes, function (idx, clientScope) {
                        if (oauthScope['_id'] == clientScope['_id']) {
                            isSelected = true;
                        }
                    });
                }
                var option;
                if (isSelected) {
                    option = '<option value="' + oauthScope['_id'] + '" selected>' + oauthScope['name'] + '</option>';
                } else {
                    option = '<option value="' + oauthScope['_id'] + '">' + oauthScope['name'] + '</option>';
                }
                form.find('[name=scopes]').append(option);
            });
            form.find('[name=scopes]').select2();

            //그런트 처리
            if (oauthClient) {
                var selectedGrantTypes = oauthClient['authorizedGrantTypes'].split(',');
                $.each(selectedGrantTypes, function (index, selectedGrantType) {
                    form.find('[name=grantType]').each(function () {
                        if ($(this).val() == selectedGrantType) {
                            $(this).prop('checked', true);
                        }
                    });
                });
            }
        };

        form.submit(function (event) {
            event.preventDefault();
            var data = form.serializeObject();
            var grantType = [];
            form.find('[name=grantType]').each(function () {
                if ($(this).prop('checked')) {
                    grantType.push($(this).val());
                }
            });
            data['authorizedGrantTypes'] = grantType.join(',');
            delete data['grantType'];
            if(typeof data['scopes'] == 'object'){
                data['scopes'] = data['scopes'].join(',');
            }

            if(data['activeClient'] != 'Y'){
                data['activeClient'] = 'N';
            }
            if(data['refreshTokenValidity'] != 'Y'){
                data['refreshTokenValidity'] = 'N';
            }
            if(data['autoDeletionToken'] != 'Y'){
                data['autoDeletionToken'] = 'N';
            }

            blockStart('Please wait a moment...');

            if (oauthClient) {
                iam.updateClient(oauthClient['_id'], data)
                    .done(function () {
                        toastr.success('Oauth client updated.');
                    })
                    .fail(function () {
                        toastr.error('Failed to update oauth client');
                    })
                    .always(function () {
                        blockStop();
                    })
            } else {
                iam.createClient(data)
                    .done(function () {
                        window.location.href = contextPath + '/client/list';
                    })
                    .fail(function () {
                        toastr.error('Failed to create oauth client');
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
