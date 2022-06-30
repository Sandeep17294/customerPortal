<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %> 
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
     xmlns:th="http://www.thymeleaf.org" >
      
<head>
	<meta charset="utf-8"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>
	<meta http-equiv="Refresh" content="10;url=${contextPath}/login.jsp">
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
	
	<!-- <script type="text/javascript">
	function removeItems(){
	     var List1 = document.getElementById("ques1");
	     var sortList1 = List1.options[List1.selectedIndex].text;

	     var List2 = document.getElementById("ques2");
	     var sortList2 = List2.options[List2.selectedIndex].text;
	     List2.options.remove(sortList1);

	}
	
	</script> -->
	
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
				
					<div class="card">
						

						<div class="card-body">
							<form:form  action="${contextPath}/registration/sucess"  method="POST">
								<div class="text-center mb-3">
								     <img src="${contextPath}/resources/cp-layout/images/background/salamalogo.jpg" alt=""/><br></br>
									
									<c:if test="${errorMsg !=null}">
									 <span class="d-block"  style="color:red;"><b>Password not update due to cause: ${errorMsg} kindly click on link again</b></span>
									</c:if>
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
