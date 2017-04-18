<%@ page contentType="text/html; charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="uengine" uri="http://www.uengine.io/tags" %>
<%
    String contextPath = request.getContextPath();
%>
<!-- Meta -->

<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!-- 타이틀 -->
<meta name="title" content="uengine Inc"/>
<!-- 주제 -->
<meta name="subject" content="uEngine IAM"/>
<!-- 요약 설명 -->
<meta name="description" content="서브스크립션 빌링 플랫폼">
<!-- 검색 키워드 -->
<meta name="keywords" content="Platform,Billing,Subscription,Payment,addon"/>
<!-- 웹 페이지(사이트) © Copyright 저작권 -->
<meta name="copyright" content="uengine Inc"/>
<!-- 웹 페이지(사이트) 검색사이트 검색로봇의 접근 허용 -->
<meta name="robots" content="ALL"/>
<!-- 웹 페이지(사이트) 내의 링크를 포함한 검색(수집) 허용 -->
<meta name="robots" content="INDEX,FOLLOW"/>
<!-- 웹 페이지(사이트) 캐쉬(Cache) 미저장 -->
<meta http-equiv="cache-control" content="No-Cache"/>
<meta http-equiv="pragma" content="No-Cache"/>
<meta http-equiv="expires" content="MON, 01 apr 2002 00:00:01 GMT">
<meta name="Location" content="South Korea"/>
<meta name="Distribution" content="Global"/>
<meta name="author" content="Seungpil, Park">

<!-- Summer Note -->
<link href="${pageContext.request.contextPath}/resources/css/plugins/summernote/summernote.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">

<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/font-awesome/css/font-awesome.css" rel="stylesheet">

<link href="${pageContext.request.contextPath}/resources/css/plugins/iCheck/custom.css" rel="stylesheet">

<link href="${pageContext.request.contextPath}/resources/css/plugins/chosen/bootstrap-chosen.css" rel="stylesheet">

<!-- Toastr style -->
<link href="${pageContext.request.contextPath}/resources/css/plugins/toastr/toastr.min.css" rel="stylesheet">

<!-- Gritter -->
<link href="${pageContext.request.contextPath}/resources/js/plugins/gritter/jquery.gritter.css" rel="stylesheet">

<!-- Data Picker -->
<link href="${pageContext.request.contextPath}/resources/css/plugins/datapicker/datepicker3.css" rel="stylesheet">

<!-- Data Table -->
<link href="${pageContext.request.contextPath}/resources/css/plugins/dataTables/datatables.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/plugins/dataTables/select.dataTables.min.css"
      rel="stylesheet">

<link href="${pageContext.request.contextPath}/resources/css/animate.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/style.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/custom.css" rel="stylesheet">

<!-- select2 -->
<link href="${pageContext.request.contextPath}/resources/js/plugins/select2/select2.css" rel="stylesheet">

<link href="${pageContext.request.contextPath}/resources/css/plugins/awesome-bootstrap-checkbox/awesome-bootstrap-checkbox.css"
      rel="stylesheet">

<!-- Jquery -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-2.1.1.js"></script>

<!-- ajax 호출 UI 블락커 && 커스터마이징 -->
<script type="text/javascript"
        src="${pageContext.request.contextPath}/resources/js/plugins/blockUI/blockUI.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/blockUIcustom.js"></script>

<!-- config && message -->
<script type="text/javascript" src="${pageContext.request.contextPath}/config/js.json"></script>

<script src="${pageContext.request.contextPath}/resources/js/plugins/deserialize/jquery.serializeObject.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/plugins/deserialize/jquery.deserialize.js"></script>

<!-- billing Api -->
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/iam-api.js"></script>
<script type="text/javascript">
    var msgBox = function (message) {
        $('#messageBox').find('[name=content]').html(message);
        $('#messageBox').modal({
            show: true
        });
    };

    var managements;
    var currentUser;
    var currentManagement;
    var contextPath = '${pageContext.request.contextPath}';
    var iam = new IAM(null, contextPath);

    //로그인,회원가입 관련 페이지가 아닌 경우 토큰 밸리데이팅을 수행한다.
    var pathname = window.location.pathname;
    if (pathname.indexOf(contextPath + '/auth') != 0 && pathname.indexOf(contextPath + '/registe') != 0) {
        iam.adminValidateToken()
            .fail(function () {
                iam.logout();
                window.location.href = contextPath + '/auth/login';
            })
            .done(function (user) {
                currentUser = user;

                //사용자 소유의 management 리스트를 받아온다.
                iam.getManagements()
                    .then(function (response) {
                        //리스트가 없고 management 생성 페이지가 아닌경우 management 생성 페이지로 이동한다.
                        if ((!response || !response.length) && pathname.indexOf('/management/new') == -1) {
                            window.location.href = contextPath + '/management/new';
                            return;
                        }

                        //토큰에 management 아이디가 없다면 첫 organization 토큰에 저장한다.
                        //토큰의 management 아이디가 불러온 organization 중에 없다면 첫 organization 을 토큰에 저장한다.
                        var defaultManagementId = iam.getDefaultManagement();
                        var isCorrect = false;
                        for (var i in response) {
                            if (response[i]['_id'] == defaultManagementId) {
                                isCorrect = true;
                                currentManagement = response[i];
                            }
                        }
                        if (!isCorrect) {
                            iam.setDefaultManagement(
                                response[0]['_id'],
                                response[0]['managementKey'],
                                response[0]['managementSecret']);
                            currentManagement = response[0];
                        }
                        managements = response;
                        console.log(currentManagement, currentUser);
                        $('#management-current').html(currentManagement['managementName']);
                    });
            });
    }


    $(function () {
        if (managements && managements.length) {
            $('#management-current').html(currentManagement['managementName']);
        }

        $('form').each(function () {
            var form = $(this);
            if (form.find('[type=submit]').length > 0) {
                form.submit(function () {
                    blockSubmitStart();
                });
            }
        });
        $('#logoutbtn').click(function () {
            iam.logout();
            window.location.href = contextPath + '/auth/login';
        });
        $('#messageBox').find('[name=close]').click(function () {
            $('#messageBox').find('.close').click();
        });
    });
</script>
