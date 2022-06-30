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
				<form:form class="login-form" action="${contextPath}/pswd/complete"  method="POST">
					<div class="card mb-0">
						<div class="card-body">
							<div class="text-center mb-3">
								     <img src="${contextPath}/resources/cp-layout/images/background/salamalogo.jpg" alt=""/><br></br>
									<h5 class="mb-0">Registration Confirmation</h5>
									<c:if test="${username !=null}">
									<span class="d-block text-muted">${username} welcome back please enter password to complete forgot process</span>
									</c:if>
							</div>

							<div class="form-group form-group-feedback form-group-feedback-right">
								<input type="password" name="pswd" class="form-control" placeholder="Password"><br>
								<input type="password" name="confPswd" class="form-control" placeholder="Confirm Password">
								<div class="form-control-feedback">
									<i class="icon-mail5 text-muted"></i>
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
