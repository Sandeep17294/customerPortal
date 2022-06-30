<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
	<title>Salama Customer Portal</title>

	<!-- Global stylesheets -->

	<link href="https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900" rel="stylesheet" type="text/css"/>  
	<link href="https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900" rel="stylesheet" type="text/css"/>  
	<link href="${contextPath}/resources/cp-layout/css/custom/styles.css" rel="stylesheet" type="text/css" />  
	<link href="${contextPath}/resources/cp-layout/css/custom/english/bootstrap.min.css" rel="stylesheet" type="text/css" /> 
	<link href="${contextPath}/resources/cp-layout/css/custom/english/bootstrap_limitless.min.css" rel="stylesheet"  type="text/css"/> 
	<link href="${contextPath}/resources/cp-layout/css/custom/english/layout.min.css" rel="stylesheet"  type="text/css"/> 
	<link href="${contextPath}/resources/cp-layout/css/custom/english/components.min.css" rel="stylesheet"  type="text/css"/> 
	<link href="${contextPath}/resources/cp-layout/css/custom/english/colors.min.css" rel="stylesheet" type="text/css" /> 
	
	<!-- /global stylesheets -->

	
   <!-- Core JS files -->
	<script src="${contextPath}/resources/cp-layout/js/custom/jquery.min.js"></script>
	<script src="${contextPath}/resources/cp-layout/js/custom/bootstrap.bundle.min.js"></script>
	<script src="${contextPath}/resources/cp-layout/js/custom/blockui.min.js"></script>
	<!-- /core JS files -->


	<!-- Theme JS files -->
	<script src="${contextPath}/resources/cp-layout/js/custom/uniform.min.js"></script>
	
	<!-- Theme JS files -->
	
	<script src="${contextPath}/resources/cp-layout/js/custom/app.js"></script>
	<script src="${contextPath}/resources/cp-layout/js/custom/login.js"></script>
	<!-- /theme JS files -->
	
	<script src="${contextPath}/resources/cp-layout/js/custom/bootbox.min.js"></script>
	<script src="${contextPath}/resources/cp-layout/js/custom/select2.min.js"></script>
	<script src="${contextPath}/resources/cp-layout/js/custom/form_select2.js"></script>
	<script src="${contextPath}/resources/cp-layout/js/custom/interactions.min.js"></script>
	<!-- <script src="resources/global_assets/js/demo_pages/components_modals.js"></script> -->
	
	<style>
body, html {
	background: url(${contextPath}/resources/cp-layout/images/background/Dubai-4-1.jpg) no-repeat center 
	  center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
	background-color: #057398 !important;
	height: 100%
}
</style>

<script type="text/javascript">
function checkPasswordStrength() {
	var number = /([0-9])/;
	var alphabets = /([a-zA-Z])/;
	var special_characters = /([~,!,@,#,$,%,^,&,*,-,_,+,=,?,>,<])/;
	
	if($('#password').val().length<8) {
		$('#password-strength-status').removeClass();
		$('#password-strength-status').addClass('weak-password');
		$('#password-strength-status').html("Weak should be atleast 8 characters. Which include alphabets, numbers and special characters.");
	} else {  	
	    if($('#password').val().match(number) && $('#password').val().match(alphabets) && $('#password').val().match(special_characters)) {            
			$('#password-strength-status').removeClass();
			$('#password-strength-status').addClass('strong-password');
			$('#password-strength-status').html("Strong");
        } else {
			$('#password-strength-status').removeClass();
			$('#password-strength-status').addClass('medium-password');
			$('#password-strength-status').html("Medium should include alphabets, numbers and special characters.");
        } 
	}
}

</script>

<script type="text/javascript">
 function checkConfirmPswd() {
	  if ($('#password').val() == $('#changePasswordForm').val()) {
	    $('#message').html('Matching / مطابقة').css('color', 'green');
	    $("#confmPswd").removeAttr("disabled");
        $("#confmPswd").focus();
	  } else {
		  
	    $('#message').html('Not Matching / غير مطابق').css('color', 'red');
	  $("#confmPswd").attr("disabled", "disabled");
	  }
	}
</script>
<style>

#frmCheckPassword {border-top:#F0F0F0 2px solid;background:#FAF8F8;padding:10px;}
.demoInputBox{padding:7px; border:#F0F0F0 1px solid; border-radius:4px;}
#password-strength-status {padding: 5px 10px;color: #FFFFFF; border-radius:4px;margin-top:5px;}
.medium-password{background-color: #E4DB11;border:#BBB418 1px solid;}
.weak-password{background-color: #FF6600;border:#AA4502 1px solid;}
.strong-password{background-color: #12CC1A;border:#0FA015 1px solid;}
</style>
</head>

<body>

	<!-- Main navbar -->
	<!-- /main navbar -->


	<!-- Page content -->
	<div class="page-content">

		<!-- Main content -->
		<div class="content-wrapper">

			<!-- Content area -->
			<div class="content d-flex justify-content-center align-items-center">

				<!-- Password recovery form -->
				<form:form class="login-form" action="${contextPath}/registration/completeRegistartion"  method="POST">
					<div class="card mb-0">
						<div class="card-body">
							<div class="text-center mb-3">
								     <img src="${contextPath}/resources/cp-layout/images/background/salamalogo.jpg" alt=""/><br></br>
									<h5 class="mb-0">Registration Confirmation</h5>
									<c:if test="${username !=null}">
									<span class="d-block text-muted">${username} welcome back please enter password to complete registration process</span>
									</c:if>
							</div>
							
							<div class="form-group form-group-feedback form-group-feedback-right">
								<div name="frmCheckPassword" id="frmCheckPassword">
								<input type="password" id="password" name="password" class="form-control" onkeyup="checkPasswordStrength()" placeholder="Password"><br>
								<div id="password-strength-status"></div>
								<input type="password" id="changePasswordForm" onkeyup="checkConfirmPswd()" class="form-control" placeholder="Confirm Password">
								<span id='message'></span>
								</div>
							</div>
							<input type="hidden" name="username" id="username" value="${username}" />
							<button type="submit" class="btn bg-blue btn-block"><i class="icon-spinner11 mr-2"></i>Confirm</button>
						</div>
					</div>
				</form:form>
				<!-- /password recovery form -->
			</div>
			<!-- /content area -->
		</div>
		<!-- /main content -->
	</div>
	<!-- /page content -->
</body>
</html>
