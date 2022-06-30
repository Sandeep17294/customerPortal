<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<html lang="en">
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
<script
	src="${contextPath}/resources/cp-layout/js/custom/bootstrap.bundle.min.js"></script>
<script
	src="${contextPath}/resources/cp-layout/js/custom/blockui.min.js"></script>
<!-- /core JS files -->


<!-- Theme JS files -->
<script
	src="${contextPath}/resources/cp-layout/js/custom/uniform.min.js"></script>

<!-- Theme JS files -->

<script src="${contextPath}/resources/cp-layout/js/custom/app.js"></script>
<script src="${contextPath}/resources/cp-layout/js/custom/login.js"></script>
<!-- /theme JS files -->

<script
	src="${contextPath}/resources/cp-layout/js/custom/bootbox.min.js"></script>
<script
	src="${contextPath}/resources/cp-layout/js/custom/select2.min.js"></script>
<script
	src="${contextPath}/resources/cp-layout/js/custom/form_select2.js"></script>
<script
	src="${contextPath}/resources/cp-layout/js/custom/interactions.min.js"></script>
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
</head>

<body>

<script type="text/javascript">
$(document).ready(function () {
	  $('.group').hide();
	  $('#ind').change(function () {
	    $('.group').hide();
	    $('#'+$(this).val()).show();
	  })
	});
</script>
<script type="text/javascript">
    document.getElementById("cancel").onclick = function () {
        location.href = "${contextPath}/login.jsp";
    };
</script>
	<!-- Page content -->
	<div class="page-content login-cover">

		<!-- Main content -->
		<div class="content-wrapper">

			<!-- Content area -->
			<div class="content d-flex justify-content-center align-items-center">

				<!-- Login form -->
				
					<div class="card">
						

						<div class="card-body">
							
						     <form:form id="otp-form" action="${contextPath}/pswd/recover" class="login-form wmin-sm-400" method="POST">
								<div class="text-center mb-3">
								     <img src="${contextPath}/resources/cp-layout/images/background/salamalogo.jpg" alt=""/><br></br>
									  <!--<i class="icon-reading icon-2x text-slate-300 border-slate-300 border-3 rounded-round p-3 mb-3 mt-1"></i>-->
									<h5 class="mb-0">Password Recovery</h5>
									<span class="d-block text-muted">To reset password kindly fill below details</span>
									
								
								</div>
 
								<div class="form-group form-group-feedback form-group-feedback-left">
								<input type=text name="username" class="form-control" placeholder="Username" required="required">
								</div>

								<div class="form-group form-group-feedback form-group-feedback-left">
								<select id="ind" class="form-control select" required="required">
								    <option value="inde">Identification Type</option>
									<option value="option1">Customer (D.O.B)</option>
									<option value="option2">Business/Admin User (EMAIL)</option>
								</select>
								
								<div id="option1" class="form-group form-group-feedback form-group-feedback-left group">
                                <input type="date" name="dob" class="form-control" placeholder="Date Of Birth" ><br>
								</div>
    							<div id="option2" class="form-group form-group-feedback form-group-feedback-left group">
    							<input type=text name="email" class="form-control" placeholder="email"><br>
    							</div>
								</div>
								    						
								<div class="form-group">
									<button type="submit" class="btn bg-blue btn-block">
								<i class="icon-spinner11 mr-2"></i>Reset
							</button>
							<button onclick="location.href = '${contextPath}/login.jsp';" id="cancel"  class="btn bg-blue btn-block">
								<i class="icon-spinner11 mr-2"></i>Cancel
							</button>
								</div>
								
				
							</form:form>
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
