<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@
taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<title>Salama Customer Portal</title>

<!-- Global stylesheets -->

<link
	href="https://fonts.googleapis.com/css?family=Roboto:400,300,100,500,700,900"
	rel="stylesheet" type="text/css" />
<link href="${contextPath}/resources/cp-layout/css/custom/styles.css"
	rel="stylesheet" type="text/css" />
<link
	href="${contextPath}/resources/cp-layout/css/custom/english/bootstrap.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${contextPath}/resources/cp-layout/css/custom/english/bootstrap_limitless.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${contextPath}/resources/cp-layout/css/custom/english/layout.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${contextPath}/resources/cp-layout/css/custom/english/components.min.css"
	rel="stylesheet" type="text/css" />
<link
	href="${contextPath}/resources/cp-layout/css/custom/english/colors.min.css"
	rel="stylesheet" type="text/css" />

<!-- /global stylesheets -->


<!-- Core JS files -->
<script src="https://code.jquery.com/jquery-3.3.1.js" /></script>
<!-- <script src="${contextPath}/resources/cp-layout/js/custom/bootstrap.bundle.min.js" /> -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>

<!-- <script src="resources/global_assets/js/plugins/loaders/blockui.min.js" /> -->
<!-- /core JS files -->


<!-- Theme JS files -->
<script src="${contextPath}/resources/cp-layout/js/custom/select2.min.js"></script>
<script src="${contextPath}/resources/cp-layout/js/custom/bootbox.min.js"></script>
<script src="${contextPath}/resources/cp-layout/js/custom/interactions.min.js"></script>
<script src="${contextPath}/resources/cp-layout/js/custom/form_select2.js"></script>


<!-- Theme JS files -->

<!-- <script src="resources/cp-layout/js/custom/app.js" /> -->
<script src="${contextPath}/resources/cp-layout/js/custom/login.js" /></script>

<script type="text/javascript">
/* $(function () {
    $("#indenType").change(function () {
        if ($(this).val() == 'Indentification Number') {
            $("#idenNum").attr("disabled", "disabled");
        } else {
            $("#idenNum").removeAttr("disabled");
            $("#idenNum").focus();
        }
    });
}); */

$(function () {
	$('input[name="mobileNo"]').keyup(function(e)
	        {
	if (/\D/g.test(this.value))
	{
	// Filter non-digits from input value.
	this.value = this.value.replace(/\D/g, '');
	}
	 });
	});
	
$(document).ready(function() { 
	 
    $('#registeredEmailId').keyup(function() {  
 
        $(".error").hide();
        var hasError = false;
        var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
 
        var emailaddressVal = $("#registeredEmailId").val();
        if(emailaddressVal == '') {
            $("#registeredEmailId").after('<span class="error" style="color:red;">Please enter your email address.</span>');
            
            hasError = true;
        }
 
        else if(!emailReg.test(emailaddressVal)) {
            $("#registeredEmailId").after('<span class="error" style="color:red;">Enter a valid email address.</span>');
            
            hasError = true;
        }
 
        if(hasError == true) {
        	
        	return false; 
        }
 
    });
});
</script>

<script type="text/javascript">

</script>

<script type="text/javascript">
	//<![CDATA[
	$('#registeredEmailId').keyup(
			function() {

				var re = /([A-Z0-9a-z_-][^@])+?@[^$#<>?]+?\.[\w]{2,4}/
						.test(this.value);
				if (!re) {
					$('#error').show();
				} else {
					$('#error').hide();
				}
			})
	//]]>
</script>
<!-- /theme JS files -->
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
</head>

<body>

	<!-- Page content -->
	<div class="page-content login-cover">

		<!-- Main content -->
		<div class="content-wrapper">

			<!-- Content area -->
			<div class="content d-flex justify-content-center align-items-center">

				<!-- Login form -->

				<div class="card mb-0">
					<ul class="nav nav-tabs nav-justified alpha-grey mb-0">
						<li class="nav-item"><a href="#login-tab1"
							class="nav-link border-y-0 border-left-0 active"
							data-toggle="tab">Sign in</a></li>
						<li class="nav-item"><a href="#login-tab2"
							class="nav-link border-y-0 border-right-0" data-toggle="tab">Register</a></li>
					</ul>

					<div class="tab-content card-body">
						<div class="tab-pane fade show active" id="login-tab1">
							<c:if test="${param.error != null}">
								<p>Invalid username / password</p>
							</c:if>
							<form class="login-form form-validate wmin-sm-400"
								action="login" method="POST">
								<div class="text-center mb-3">
									<img src="resources/cp-layout/images/background/salamalogo.jpg" alt=""/><br></br>
									<!-- <i class="icon-reading icon-2x text-slate-300 border-slate-300 border-3 rounded-round p-3 mb-3 mt-1"></i> -->
									<h5 class="mb-0">Login to your account</h5>
									<span class="d-block text-muted">Your credentials</span>
								</div>

								<div
									class="form-group form-group-feedback form-group-feedback-left">
									<input type="text" id="username" name="username"
										class="form-control" placeholder="Username"
										required="required" />
									<div class="form-control-feedback">
										<i class="icon-user text-muted"></i>
									</div>
								</div>

								<div
									class="form-group form-group-feedback form-group-feedback-left">
									<input type="password" id="password" name="password"
										class="form-control" placeholder="Password"
										required="required" />
									<div class="form-control-feedback">
										<i class="icon-lock2 text-muted"></i>
									</div>
								</div>

								<div class="form-group d-flex align-items-center">
									<!-- <div class="form-check mb-0">
										<label class="form-check-label"> <input
											type="checkbox" name="remember" class="form-input-styled" />
											Remember
										</label>
									</div> -->

									<a href="${contextPath}/pswd/forgot" class="ml-auto">Forgot
										password?</a>
								</div>

								<div class="form-group">
									<button type="submit" class="btn btn-primary btn-block">Sign
										in</button>
								</div>


								<div class="form-group text-center text-muted content-divider">
									<span class="px-2"></span>
								</div>

								<span class="form-text text-center text-muted">By
									continuing, you're confirming that you've read our <a href="#">Terms
										&amp; Conditions</a> and <a href="#">Cookie Policy</a>
								</span>
							</form>
						</div>

						<div class="tab-pane fade" id="login-tab2">
							<form id="register-form" class="login-form wmin-sm-400"
								action="${contextPath}/registration/policy" method="POST">
								<div class="text-center mb-3">
									<img src="resources/cp-layout/images/background/salamalogo.jpg" alt=""/><br></br>
									<!-- <i class="icon-plus3 icon-2x text-success border-success border-3 rounded-round p-3 mb-3 mt-1"></i>-->
									<h5 class="mb-0">Create account</h5>
									<span class="d-block text-muted">All fields are required</span>
								</div>


								<%-- <div
									class="form-group form-group-feedback form-group-feedback-left">
									<select id="indenType" name="idenCode" class="form-control select-data-array"
										required="required">
										<option value="Indentification Number" selected="selected">Indentification Number</option>
										<c:forEach items="${IdenType}" var="iden">
											<option value="${iden.code}">${iden.description}</option>
										</c:forEach>
									</select>
								</div> --%>

								<div
									class="form-group form-group-feedback form-group-feedback-left">
									<input id="idenNum" class="form-control"name="idenNum"  placeholder="Policy Number" required="required"/>

									<div class="form-control-feedback">
										<i class="icon-checkmark4 text-muted"></i>
									</div>
								</div>
								<div
									class="form-group form-group-feedback form-group-feedback-left">
									<input id="registeredMobileNo" class="form-control"
										name="mobileNo" placeholder="Your mobile number"
										required name="digits" />

									<div class="form-control-feedback">
										<i class="icon-mobile text-muted"></i>
									</div>
								</div>

								<div
									class="form-group form-group-feedback form-group-feedback-left">
									<input class="form-control" placeholder="Your email"
										name="email" id="registeredEmailId" />
									<div class="form-control-feedback">
										<i class="icon-mention text-muted"></i>
									</div>
								</div>
								
								<button type="submit"  class="btn bg-dark btn-block">Verify</button>
							</form>
						</div>
					</div>
				</div>

				<!-- /login form -->

			</div>
			<!-- /content area -->

		</div>
		<!-- /main content -->
	</div>
	<!-- /page content -->

</body>
</html>
