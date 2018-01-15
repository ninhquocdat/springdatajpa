<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="dec"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><dec:title default="Login Page" /></title>
	<link rel="stylesheet" href="<c:url value='/template/assets/css/bootstrap.min.css' />" />
	<link rel="stylesheet" href="<c:url value='/template/font-awesome/4.5.0/css/font-awesome.min.css' />" />
	<link rel="stylesheet" href="<c:url value='/template/assets/css/ace.min.css' />" />
	<link rel="stylesheet" href="<c:url value='/template/assets/css/ace-rtl.min.css' />" />
    <dec:head />
</head>
<body class="login-layout">
		
		<div class="main-container">
				<div class="main-content">
					<div class="row">
						<div class="col-sm-10 col-sm-offset-1">
							<div class="login-container">
								
								<!-- begin header -->
									<%@ include file="/common/login/header.jsp"%> 
								<!-- close header -->
								
								<!-- begin body -->
									 <dec:body />
								<!-- close body -->
														
								<!-- begin footer -->
									<%@ include file="/common/login/footer.jsp"%> 
								<!-- close footer -->
								
							</div>
						</div>
					</div>
				</div>
		</div>
		
		<script src="<c:url value='/template/assets/js/jquery.2.1.1.min.js'/>"></script>
</body>
</html>