<%@ page contentType="text/html; charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="uengine" uri="http://www.uengine.io/tags" %>

<%--<script src='http://www.google.com/recaptcha/api.js'></script>--%>
<script type="text/javascript">
    $(function () {
        $('#flamingo_download_btn').click(function () {
            $('#flamingo_download_form').modal({
                show: true
            });
        });

        $('#flamingo_download_submit').validate({

            focusInvalid: false, // do not focus the last invalid input
            ignore: "",
            rules: {
                username: {
                    required: true
                },
                email: {
                    required: true,
                    email: true
                },
                company: {
                    required: true
                },
                motion : {
                    required: true
                }
            },
            messages: {
                username: "Required",
                email: {
                    required: "Required",
                    email: "Invalid Email Address Format"
                },
                company: "Required",
                motion : "Required"
            },
            invalidHandler: function () {
                blockStop();
            },
            errorPlacement: function(error, element) {
                element.parent().after(error);
            },
            submitHandler: function(form , event) {
                event.preventDefault();
                form = $('#flamingo_download_submit');
                var data = {
                    username: form.find('[name=username]').val(),
                    email: form.find('[name=email]').val(),
                    company: form.find('[name=company]').val(),
                    //recaptcha: $(this).find('[name=g-recaptcha-response]').val(),
                    type: "flamingo",
                    version: "2.0.0"
                };
                blockStart('Please wait a moment...');

                $.ajax({
                    type: "POST",
                    url: "/download/request",
                    data: JSON.stringify(data),
                    contentType: "application/json; charset=utf-8",
                    dataType: "text",
                    success: function (response) {
                        blockStop();
                        $('#flamingo_download_form').find('.close').click();
                        var res = JSON.parse(response);
                        if (res.success)
                            msgBox('Sent download link to your email address.');
                        else
                            msgBox('Unable to send download link to your email address.');
                    },
                    error: function (request, status, errorThrown) {
                        blockStop();
                        $('#flamingo_download_form').find('.close').click();
                        msgBox('Unable to send download link to your email address.');
                    }
                });
            }
        });
    });
</script>
<div class="panel panel-default margin-bottom-40">
    <table class="table table-striped invoice-table">
        <thead>
        <tr>
            <th><uengine:message code="table.download.theads.1"></uengine:message></th>
            <th><uengine:message code="table.download.theads.2"></uengine:message></th>
            <th><uengine:message code="table.download.theads.3"></uengine:message></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td style="vertical-align: middle;">
                2.0.0
            </td>
            <td style="vertical-align: middle;">
                <uengine:message code="download.release.sub.1.files"></uengine:message>
            </td>
            <td style="vertical-align: middle;">
                <uengine:message code="download.release.sub.1.compatibility"></uengine:message>
            </td>
            <td style="vertical-align: middle;">

                <label>Language : </label>
                <select name="language" class="form-control" style="margin-bottom: 20px;">
                    <option>English</option>
                    <option>Korean</option>
                </select>

                <button id="flamingo_download_btn" class="btn-u btn-u-primary">파일 다운로드</button>
                <a id="flamingo_manual_btn" class="btn-u btn-u-primary">매뉴얼 다운로드</a>
            </td>
        </tr>
        </tbody>
    </table>
</div>

<!-- 다운로드 Modal -->
<div id="flamingo_download_form" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title">Download</h4>
            </div>
            <div class="modal-body">
                <div class="row col-sm-10 col-sm-offset-1">
                    <div class="sky-form" style="border: none">
                        <header class="headline-center">EULA (End User License Agreement)</header>
                        <textarea style="height: 300px; width: 100%">
                            <%@include file="agreement.jsp" %>
                        </textarea>
                    </div>
                </div>

                <div class="row margin-bottom-40">
                    <div class="row col-sm-10 col-sm-offset-1">
                        <form id="flamingo_download_submit" class="sky-form" style="border: none">
                            <header class="headline-center">Customer Information</header>

                            <fieldset>
                                <section>
                                    <label class="input">
                                        <i class="icon-append fa fa-user"><span class="color-red">*</span></i>
                                        <input type="text" name="username" placeholder="Username" value="${user.name}">
                                    </label>
                                </section>

                                <section>
                                    <label class="input">
                                        <i class="icon-append fa fa-envelope"><span class="color-red">*</span></i>
                                        <input type="email" name="email" placeholder="Email" value="${user.email}">
                                    </label>
                                </section>

                                <section>
                                    <label class="input">
                                        <i class="icon-append fa fa-users"><span class="color-red">*</span></i>
                                        <input type="text" name="company" placeholder="Company"
                                               value="${user.organization}">
                                    </label>
                                </section>
                            </fieldset>

                            <fieldset>
                                <section class="col-sm-6">
                                    <label class="checkbox"><span class="color-red">*</span>I agree to the EULA<input
                                            type="checkbox" name="motion"><i></i></label>
                                </section>
                                <%--<div class="g-recaptcha col-sm-6"--%>
                                <%--data-sitekey="6LdpUQcTAAAAACfVZLNiF9MeAgDakUs8cA1PQNaI"></div>--%>
                            </fieldset>
                            <footer>
                                <button type="submit" class="btn-u col-centered">Download</button>
                            </footer>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<!-- 다운로드 modal -->
