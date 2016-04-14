<%@ page contentType="text/html; charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="uengine" uri="http://www.uengine.io/tags" %>

<%--<script src='http://www.google.com/recaptcha/api.js'></script>--%>
<script type="text/javascript">
    $(function () {

        $('[name=license_btn]').click(function () {
            var type = $(this).data('type');
            $('#flamingo_license_submit').find('[name=header]').html(type + ' 라이센스 문의');
            $('#flamingo_license_submit').find('[name=type]').val(type);
            $('#flamingo_license_form').modal({
                show: true
            });
        });

        $('#flamingo_license_submit').validate({

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
                telephone: {
                    required: true
                },
                customer: {
                    required: true
                },
                subject: {
                    required: true
                },
                message: {
                    required: true
                }
            },
            messages: {
                username: "필수 항목입니다.",
                email: {
                    required: "필수 항목입니다.",
                    email: "이메일 형식이 올바르지 않습니다."
                },
                telephone: "필수 항목입니다.",
                customer: "필수 항목입니다.",
                subject: "필수 항목입니다.",
                message: "필수 항목입니다."
            },
            invalidHandler: function () {
                blockStop();
            },
            errorPlacement: function (error, element) {
                element.parent().after(error);
            },
            submitHandler: function (form, event) {
//            var recaptcha = form.find('[name=g-recaptcha-response]').val();
//            if (typeof recaptcha == 'undefined' || recaptcha.length < 1) {
//                alert('자동 입력 방지 질문을 완료해 주십시오.');
//                return;
//            }
                event.preventDefault();
                form = $('#flamingo_license_submit');
                var data = {
                    username: form.find('[name=username]').val(),
                    email: form.find('[name=email]').val(),
                    telephone: form.find('[name=telephone]').val(),
                    customer: form.find('[name=customer]').val(),
                    type: form.find('[name=type]').val(),
                    subject: form.find('[name=subject]').val(),
                    message: form.find('[name=message]').val(),
                    //recaptcha: form.find('[name=g-recaptcha-response]').val()
                };
                blockStart('잠시만 기다려 주십시오...');

                $.ajax({
                    type: "POST",
                    url: "/req/post",
                    data: JSON.stringify(data),
                    contentType: "application/json; charset=utf-8",
                    dataType: "text",
                    success: function (response) {
                        blockStop();
                        $('#flamingo_license_form').find('.close').click();
                        var res = JSON.parse(response);
                        if (res.success)
                            msgBox('관리자에게 문의사항을 발송하였습니다.');
                        else
                            msgBox('시스템 오류로 인해 관리자에게 문의사항을 발송할 수 없습니다.');
                    },
                    error: function (request, status, errorThrown) {
                        blockStop();
                        $('#flamingo_license_form').find('.close').click();
                        msgBox('시스템 오류로 인해 관리자에게 문의사항을 발송할 수 없습니다.');
                    }
                });
            }
        });

        $('#flamingo_license_submit').submit(function (e) {
            e.preventDefault();

        })
    });
</script>


<!-- 라이센스 Modal -->
<div id="flamingo_license_form" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                <button aria-hidden="true" data-dismiss="modal" class="close" type="button">×</button>
                <h4 class="modal-title">라이센스</h4>
            </div>
            <div class="modal-body">

                <div class="row margin-bottom-40">
                    <div class="row col-sm-10 col-sm-offset-1">
                        <form id="flamingo_license_submit" class="sky-form" style="border: none">
                            <header class="headline-center" name="header"></header>
                            <input type="hidden" name="type">
                            <fieldset>
                                <section>
                                    <label class="input">
                                        <i class="icon-append fa fa-user"><span class="color-red">*</span></i>
                                        <input type="text" name="username" placeholder="사용자 이름">
                                    </label>
                                </section>

                                <section>
                                    <label class="input">
                                        <i class="icon-append fa fa-envelope"><span class="color-red">*</span></i>
                                        <input type="email" name="email" placeholder="이메일">
                                    </label>
                                </section>

                                <section>
                                    <label class="input">
                                        <i class="icon-append fa fa-phone"><span class="color-red">*</span></i>
                                        <input type="tel" name="telephone" placeholder="연락처">
                                    </label>
                                </section>

                                <section>
                                    <label class="input">
                                        <i class="icon-append fa fa-users"><span class="color-red">*</span></i>
                                        <input type="text" name="customer" placeholder="소속">
                                    </label>
                                </section>
                            </fieldset>

                            <header class="headline-center">문의 내용</header>
                            <fieldset>
                                <section>
                                    <label class="input">
                                        <i class="icon-append fa fa-pencil-square-o"><span
                                                class="color-red">*</span></i>
                                        <input type="text" name="subject" placeholder="제목">
                                    </label>
                                </section>
                                <section>
                                    <textarea name="message" style="height: 200px; width: 100%"
                                              placeholder="메시지"></textarea>
                                </section>
                            </fieldset>

                            <%--<fieldset>--%>
                            <%--<div class="g-recaptcha" data-sitekey="6LdpUQcTAAAAACfVZLNiF9MeAgDakUs8cA1PQNaI"></div>--%>
                            <%--</fieldset>--%>
                            <footer>
                                <button type="submit" class="btn-u col-centered">문의 및 견적요청 발송</button>
                            </footer>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<!-- 라이센스 modal -->
