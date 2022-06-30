<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
     xmlns:th="http://www.thymeleaf.org" >
      
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
	<script>
	function checkForm(form){
	    if(!form.terms.checked) {
	      alert("Please indicate that you accept the Terms and Conditions");
	      form.terms.focus();
	      return false;
	    }
	    return true;
	  }

	</script>
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
							<form:form  action="${contextPath}/registration/success" onsubmit="return checkForm(this);" method="POST">
								<div class="text-center mb-3">
								     <img src="${contextPath}/resources/cp-layout/images/background/salamalogo.jpg" alt=""/><br></br>
									<h5 class="mb-0">Create account</h5>
									<span class="d-block text-muted">All fields are required</span>
								</div>
                               
									<div class="form-group form-group-feedback form-group-feedback-right">
										<input type="text" name="username"  class="form-control" placeholder="Choose username" />
										<div class="form-control-feedback">
											<i class="icon-user-plus text-muted"></i>
										</div>
									</div>
							   
									
									
									<div class="form-group form-group-feedback form-group-feedback-right">
										<input type="text" name="displayName" class="form-control" placeholder="Choose Display name" />
										<div class="form-control-feedback">
											<i class="icon-user-check text-muted"></i>
										</div>
									</div>
									
                          <c:if test="${secquesno > 0}">
							<div class="row">
								<div class="col-md-6">
									<div
										class="form-group form-group-feedback form-group-feedback-right">
										<label class="form-input-styled">Question 1:</label>
										<c:forEach items="${secques}" var="secQues" varStatus="loop">
										<c:if test="${loop.index == 0}">
										<input type="text" name="ques1" class="form-control" disabled="disabled" value="${secques[0].vQuesEN}" />
										</c:if>
										</c:forEach>
									</div>
								</div>

								<div class="col-md-6">
									<div
										class="form-group form-group-feedback form-group-feedback-right">
										<label class="form-input-styled">Answer</label> 
										<input type="text" name="ans1" class="form-control" placeholder="Answer" />

									</div>
								</div>
							</div>
                        </c:if>
                        <c:if test="${secquesno >= 1}">
							<div class="row">
								<div class="col-md-6">
									<div
										class="form-group form-group-feedback form-group-feedback-right">
										<label class="form-input-styled">Question 2:</label>
										<c:forEach items="${secques}" var="secQues" varStatus="loop">
										<c:if test="${loop.index == 1}">
										<input type="text" name="ques2" class="form-control" disabled="disabled" value="${secques[1].vQuesEN}" />
										</c:if>
										</c:forEach>
									</div>
								</div>

								<div class="col-md-6">
									<div
										class="form-group form-group-feedback form-group-feedback-right">
										<label class="form-input-styled">Answer</label> 
										<input type="text" name="ans2" class="form-control" placeholder="Answer" />

									</div>
								</div>
							</div>
                        </c:if>
                        <c:if test="${secquesno >= 2}">
							<div class="row">
								<div class="col-md-6">
									<div
										class="form-group form-group-feedback form-group-feedback-right">
										<label class="form-input-styled">Question 3:</label>
										<c:forEach items="${secques}" var="secQues" varStatus="loop">
										<c:if test="${loop.index == 2}">
										<input type="text" name="ques3" class="form-control" disabled="disabled" value="${secques[2].vQuesEN}" />
										</c:if>
										</c:forEach>
									</div>
								</div>

								<div class="col-md-6">
									<div
										class="form-group form-group-feedback form-group-feedback-right">
										<label class="form-input-styled">Answer</label> 
										<input type="text" name="ans3" class="form-control" placeholder="Answer" />

									</div>
								</div>
							</div>
                        </c:if>
                        <c:if test="${secquesno >= 3}">
							<div class="row">
								<div class="col-md-6">
									<div
										class="form-group form-group-feedback form-group-feedback-right">
										<label class="form-input-styled">Question 4:</label>
										<c:forEach items="${secques}" var="secQues" varStatus="loop">
										<c:if test="${loop.index == 3}">
										<input type="text" name="ques4" class="form-control" disabled="disabled" value="${secques[3].vQuesEN}" />
										</c:if>
										</c:forEach>
									</div>
								</div>

								<div class="col-md-6">
									<div
										class="form-group form-group-feedback form-group-feedback-right">
										<label class="form-input-styled">Answer</label> 
										<input type="text" name="ans4" class="form-control" placeholder="Answer" />

									</div>
								</div>
							</div>
                        </c:if>
                        <c:if test="${secquesno >= 4}">
							<div class="row">
								<div class="col-md-6">
									<div
										class="form-group form-group-feedback form-group-feedback-right">
										<label class="form-input-styled">Question 5:</label>
										<c:forEach items="${secques}" var="secQues" varStatus="loop">
										<c:if test="${loop.index == 4}">
										<input type="text" name="ques5" class="form-control" disabled="disabled" value="${secques[4].vQuesEN}" />
										</c:if>
										</c:forEach>
									</div>
								</div>

								<div class="col-md-6">
									<div
										class="form-group form-group-feedback form-group-feedback-right">
										<label class="form-input-styled">Answer</label> 
										<input type="text" name="ans5" class="form-control" placeholder="Answer" />

									</div>
								</div>
							</div>
                        </c:if>
                        
                        
							<div class="form-group">
									

										<div class="form-check">
											<label class="form-check-label">
												<input type="checkbox" id="termsConditionsBox" name="terms" class="form-input-styled" data-fouc=""/>
												Accept Terms &amp; Conditions. 
											</label>
										</div>
										<p>${termsConditions}</p>
									</div>
								

								<button type="submit" id="regSubmit" class="btn bg-teal-400 btn-labeled btn-labeled-right"><b><i class="icon-plus3"></i></b> Create account</button>
								
								<div class="form-group text-center text-muted content-divider">
									<span class="px-2"></span>
								</div>
                                
								<span class="form-text text-center text-muted">By continuing, you're confirming that you've read our <a href="#">Terms &amp; Conditions</a> and <a href="#">Cookie Policy</a></span>
							
							<input type="hidden" name="policyNo" id="policyNo" value="${policyNo}" />
							<input type="hidden" name="idenCode" id="idenCode" value="${idenCode}" />
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
