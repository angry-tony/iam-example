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
            <div class="col-lg-12 padding-1">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>Client Profile</h5>
                    </div>
                    <div class="ibox-content">
                        <div class="tabs-container">
                            <ul class="nav nav-tabs">
                                <li class="active" data-page="properties" name="page-tab">
                                    <a data-toggle="tab" href="#tab-1" aria-expanded="true">Properties</a>
                                </li>
                                <li class="" data-page="notification" name="page-tab">
                                    <a data-toggle="tab" href="#tab-2" aria-expanded="true">Notification</a>
                                </li>
                            </ul>
                            <div class="tab-content">
                                <div id="tab-1" class="tab-pane active" data-page="properties" name="page-content">
                                    <div class="panel-body" id="properties-append">
                                        <form class="form-horizontal" role="form"
                                              id="oauthClientForm" method="post">
                                            <h4 name="title">Edit Oauth Client </h4>

                                            <div id="readonly-fields">
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label">Client ID <span
                                                            class="color-red">*</span></label>

                                                    <div class="col-md-6">
                                                        <input type="text" name="clientKey" class="form-control"
                                                               readonly>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="col-md-2 control-label">Client Secret <span
                                                            class="color-red">*</span></label>

                                                    <div class="col-md-6">
                                                        <input type="text" name="clientSecret" class="form-control"
                                                               readonly>
                                                    </div>
                                                </div>

                                            </div>

                                            <div class="form-group">
                                                <label class="col-md-2 control-label">Name <span
                                                        class="color-red">*</span></label>

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
                                                            <input type="radio" name="clientType" value="confidential">
                                                            Confidential
                                                        </label>
                                                    </div>
                                                    <div>
                                                        <label>
                                                            <input type="radio" name="clientType" value="public"
                                                                   checked> Public
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
                                                            <input type="checkbox" name="grantType" value="code">
                                                            Authorization Code
                                                        </label>
                                                    </div>
                                                    <div>
                                                        <label>
                                                            <input type="checkbox" name="grantType" value="implicit">
                                                            Implicit Grant
                                                        </label>
                                                    </div>
                                                    <div>
                                                        <label>
                                                            <input type="checkbox" name="grantType" value="password">
                                                            Resource Owner
                                                            Password Credentials
                                                        </label>
                                                    </div>
                                                    <div>
                                                        <label>
                                                            <input type="checkbox" name="grantType" value="credentials">
                                                            Client
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
                                                <label class="col-md-2 control-label"> Auto deletion expired refresh
                                                    token </label>

                                                <div class="col-md-6">
                                                    <label>
                                                        <input type="checkbox" name="autoDeletionToken" value="Y">
                                                        Enable auto deletion
                                                        expired refresh token
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-md-2 control-label">Jwt Context Fields </label>

                                                <div class="col-md-6">
                                                    <div>
                                                        <label>
                                                            <input type="checkbox" name="requiredContext"
                                                                   value="clientId"> clientId
                                                        </label>
                                                    </div>
                                                    <div>
                                                        <label>
                                                            <input type="checkbox" name="requiredContext"
                                                                   value="clientKey"> clientKey
                                                        </label>
                                                    </div>
                                                    <div>
                                                        <label>
                                                            <input type="checkbox" name="requiredContext"
                                                                   value="managementId"> managementId
                                                        </label>
                                                    </div>
                                                    <div>
                                                        <label>
                                                            <input type="checkbox" name="requiredContext"
                                                                   value="scopes"> scopes
                                                        </label>
                                                    </div>
                                                    <div>
                                                        <label>
                                                            <input type="checkbox" name="requiredContext" value="type">
                                                            type
                                                        </label>
                                                    </div>
                                                    <div>
                                                        <label>
                                                            <input type="checkbox" name="requiredContext"
                                                                   value="refreshToken"> refreshToken
                                                        </label>
                                                    </div>
                                                    <div>
                                                        <label>
                                                            <input type="checkbox" name="requiredContext"
                                                                   value="userName"> userName
                                                        </label>
                                                    </div>
                                                    <div>
                                                        <label>
                                                            <input type="checkbox" name="requiredContext"
                                                                   value="userId"> userId
                                                        </label>
                                                    </div>
                                                    <div>
                                                        <label>
                                                            <input type="checkbox" name="requiredContext" value="user">
                                                            user
                                                        </label>
                                                    </div>
                                                </div>

                                            </div>

                                            <div class="form-group">
                                                <label class="col-md-2 control-label">Jwt Algorithm<span
                                                        class="color-red">*</span></label>

                                                <div class="col-md-6">
                                                    <div>
                                                        <label>
                                                            <input type="radio" name="jwtAlgorithm"
                                                                   value="HS256" checked> HS256
                                                        </label>
                                                    </div>
                                                    <div>
                                                        <label>
                                                            <input type="radio" name="jwtAlgorithm"
                                                                   value="RS256"> RS256
                                                        </label>
                                                    </div>
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
                                                    <input name="refreshTokenLifetime" type="number"
                                                           class="form-control">
                                                </div>
                                            </div>

                                            <div class="form-group">
                                                <label class="col-md-2 control-label">AccessToken Lifetime <span
                                                        class="color-red">*</span></label>

                                                <div class="col-md-6">
                                                    <input name="accessTokenLifetime" type="number"
                                                           class="form-control">
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
                                                    <button class="btn btn-primary" type="submit" name="submit">Create
                                                    </button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <div id="tab-2" class="tab-pane" data-page="notification" name="page-content">
                                    <div class="panel-body" id="notification-append">
                                        <div class="feed-activity-list">
                                            <div class="feed-element">
                                                <div class="media-body">

                                                    <form class="form-horizontal" role="form" id="notification_form">
                                                        <div class="form-group">
                                                            <label class="col-md-2 control-label">From Address<span
                                                                    class="color-red">*</span></label>

                                                            <div class="col-md-6">
                                                                <input name="from" type="text" class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <label class="col-md-2 control-label">From Name <span
                                                                    class="color-red">*</span></label>

                                                            <div class="col-md-6">
                                                                <input name="from_name" type="text"
                                                                       class="form-control">
                                                            </div>
                                                        </div>
                                                        <div class="form-group">
                                                            <div class="col-md-offset-2 col-md-10">
                                                                <button class="btn btn-primary" type="submit"
                                                                        name="submit">Save
                                                                </button>
                                                            </div>
                                                        </div>
                                                    </form>
                                                    <div class="col-md-4">
                                                        TYPE
                                                    </div>
                                                    <div class="col-md-5">
                                                        TEMPLATES
                                                    </div>
                                                    <div class="col-md-2">
                                                        Enable Notification
                                                    </div>
                                                </div>
                                            </div>
                                            <div id="notification-type-appender">

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="../template/footer.jsp" %>

    </div>
</div>

<div style="display: none;">
    <div class="feed-element" id="notification-type-item" name="notification-type-item">
        <div class="media-body">
            <div class="col-md-4">
                <strong name="notification_type"></strong>
                <p class="text-muted" name="notification_description"></p>
            </div>
            <div class="col-md-3" name="template-item-appender">

            </div>
            <div class="col-md-2">
                <button type="button" class="btn btn-default btn-sm" name="add">
                    + Add template
                </button>
            </div>
            <div class="col-md-2">
                <input name="notification_use" type="checkbox" checked/>
            </div>
        </div>
    </div>

    <div id="template-item" name="template-item">
        <a name="delete"><i class="fa fa-trash"></i></a>
        <a name="locale"></a>
        <span class="label label-primary label-xs" name="is_default">DEFAULT</span>
        <a name="set_default"> Mark as default</a>
    </div>
</div>

<div class="modal inmodal fade" id="template-modal" tabindex="-1" role="dialog"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span
                        class="sr-only">Close</span></button>
                <h4 class="modal-title" name="title">Notification Template</h4>
            </div>
            <div class="modal-body">
                <form method="get" class="form-horizontal">
                    <div class="form-group">
                        <div class="col-sm-3">
                            <select class="chosen-select" tabindex="2" name="locale" required>
                                <%@include file="../template/localeList.jsp" %>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <input type="text" placeholder="Email Subject" class="form-control" name="subject">
                        </div>
                    </div>
                </form>

                <div name="summernote">

                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-white" data-dismiss="modal">Cancel</button>
                <button type="button" class="btn btn-primary" name="save">Save</button>
            </div>
        </div>
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
        var notificationConfig;
        var notificationTemplates;

        var form = $('#oauthClientForm');
        var notificationForm = $('#notification_form');

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
                                });
                            fillNotifications();
                        })
                        .fail(function (response) {
                            toastr.error('Failed to find client');
                        })
                })
                .fail(function (response) {
                    toastr.error('Failed to find scopes');
                })
        } else {
            $('#notification-append').hide();
            iam.getAllScope()
                .done(function (scopes) {
                    oauthScopes = scopes;
                    fill();
                })
                .fail(function (response) {
                    toastr.error('Failed to find scopes');
                })
        }

        var fillNotifications = function () {
            notificationConfig = null
            notificationTemplates = null;
            notificationForm.find('[name=from]').val('');
            notificationForm.find('[name=from_name]').val('');

            $('#notification-type-appender').find('[name=notification-type-item]').remove();

            iam.getNotificationConfig(_id)
                .done(function (config) {
                    notificationConfig = config;
                    iam.getAllTemplate(_id)
                        .done(function (templates) {
                            notificationTemplates = templates;
                            if (!notificationConfig || !notificationTemplates) {
                                return;
                            }
                            for (var key in notificationConfig) {
                                drawNotificationItem(key, notificationConfig[key])
                            }
                        })
                        .fail(function () {
                            toastr.error("Failed to get templates.");
                        })
                })
                .fail(function () {
                    toastr.error("Failed to get notification config.");
                });
        };

        var drawNotificationItem = function (key, isNotify) {
            var description = '';
            if (key == 'FROM') {
                notificationForm.find('[name=from]').val(notificationConfig[key]);
                return;
            }
            else if (key == 'FROM_NAME') {
                notificationForm.find('[name=from_name]').val(notificationConfig[key]);
                return;
            }
            else if (key == 'SIGN_UP') {
                description = '고객의 회원가입 요청 이메일 입니다. 이 이메일에 포함된 링크는 요청시 입력한 redirect 경로입니다.';
            }
            else if (key == 'SIGNED_UP') {
                description = '고객 가입 완료 안내 이메일 입니다.';
            }
            else if (key == 'FORGOT_PASSWORD') {
                description = '고객의 비밀번호 분실 이메일 입니다. 이 이메일에 포함된 링크는 요청시 입력한 redirect 경로입니다.';
            }
            else if (key == 'PASSWORD_CHANGED') {
                description = '고객 비밀번호 변경 알림 이메일 입니다.';
            }
            else {
                return;
            }
            var item = $('#notification-type-item').clone();
            item.removeAttr('id');

            $('#notification-type-appender').append(item);

            item.find('[name=notification_type]').html(key);
            item.find('[name=notification_description]').html(description);

            //신규 템플릿 추가
            item.find('[name=add]').click(function () {
                popupTemplate(key);
            });

            item.find('[name=notification_use]').prop('checked', isNotify);
            item.find('[name=notification_use]').click(function () {
                notificationConfig[key] = $(this).prop('checked');
            });

            var templates = notificationTemplates[key];
            $.each(templates, function (index, template) {
                drawTemplateItem(item, template);
            });
        };
        var drawTemplateItem = function (notificationItem, template) {
            var item = $('#template-item').clone();
            item.removeAttr('id');
            notificationItem.find('[name=template-item-appender]').append(item);

            item.find('[name=locale]').html(template['locale']);
            if ('Y' == template['is_default']) {
                item.find('[name=is_default]').show();
                item.find('[name=set_default]').hide();
            } else {
                item.find('[name=is_default]').hide();
                item.find('[name=set_default]').show();
            }

            //클릭 이벤트
            item.find('[name=locale]').click(function () {
                popupTemplate(template['notification_type'], template);
            });

            //디폴트 이벤트
            item.find('[name=set_default]').click(function () {
                iam.setDefaultTemplate(_id, template['notification_type'], template['locale'])
                    .done(function () {
                        toastr.success("Template updated.");
                        fillNotifications();
                    })
                    .fail(function () {
                        toastr.error("Failed to update template.");
                    })
            });

            //delete 이벤트
            item.find('[name=delete]').click(function () {
                iam.deleteTemplate(_id, template['notification_type'], template['locale'])
                    .done(function () {
                        toastr.success("Template deleted.");
                        fillNotifications();
                    })
                    .fail(function () {
                        toastr.error("Failed to delete template.");
                    })
            });
        };

        var popupTemplate = function (notification_type, template) {
            var modal = $('#template-modal');
            modal.modal('show');
            var locale = modal.find('[name=locale]');
            var subject = modal.find('[name=subject]');
            locale.chosen({width: "100%"});

            var summernote = modal.find('[name=summernote]');
            summernote.summernote('destroy');
            summernote.summernote({
                height: 350,
                focus: true
            });

            if (template) {
                locale.closest('.form-group').hide();
                summernote.summernote('code', template['body']);
                subject.val(template['subject']);
                modal.find('[name=title]').html('Template - ' + notification_type + ' (' + template['locale'] + ')');
            } else {
                locale.closest('.form-group').show();
                summernote.summernote('code', '');
                subject.val('');
                modal.find('[name=title]').html('Template - ' + notification_type);
            }

            modal.find('[name=save]').unbind('click');
            modal.find('[name=save]').bind('click', function () {
                var data = {
                    subject: subject.val(),
                    body: summernote.summernote('code')
                };
                var targetLocale = template ? template['locale'] : locale.val();
                iam.createTemplate(_id, notification_type, targetLocale, data)
                    .done(function () {
                        toastr.success("Template updated.");
                        fillNotifications();
                    })
                    .fail(function () {
                        toastr.error("Failed to update template.");
                    })
                    .always(function () {
                        modal.modal('hide');
                    })
            });
        };

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

                var selectedRequiredContexts = [];
                if (oauthClient['requiredContext']) {
                    selectedRequiredContexts = oauthClient['requiredContext'].split(',');
                }
                $.each(selectedRequiredContexts, function (index, selectedRequiredContext) {
                    form.find('[name=requiredContext]').each(function () {
                        if ($(this).val() == selectedRequiredContext) {
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

            var requiredContext = [];
            form.find('[name=requiredContext]').each(function () {
                if ($(this).prop('checked')) {
                    requiredContext.push($(this).val());
                }
            });
            data['requiredContext'] = requiredContext.join(',');
            //requiredContext


            if (typeof data['scopes'] == 'object') {
                data['scopes'] = data['scopes'].join(',');
            }

            if (data['activeClient'] != 'Y') {
                data['activeClient'] = 'N';
            }
            if (data['refreshTokenValidity'] != 'Y') {
                data['refreshTokenValidity'] = 'N';
            }
            if (data['autoDeletionToken'] != 'Y') {
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

        notificationForm.submit(function (event) {
            event.preventDefault();

            blockStart('Please wait a moment...');
            notificationConfig['FROM'] = notificationForm.find('[name=from]').val();
            notificationConfig['FROM_NAME'] = notificationForm.find('[name=from_name]').val();

            iam.updateNotificationConfig(_id, notificationConfig)
                .done(function () {
                    toastr.success("Notification config updated.");
                })
                .fail(function () {
                    toastr.error("Failed to update notification config.");
                })
                .always(function () {
                    blockStop();
                });
        });
    });
</script>
</body>
</html>
