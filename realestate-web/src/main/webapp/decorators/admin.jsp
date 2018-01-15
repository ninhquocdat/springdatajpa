<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="dec"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><dec:title default="Admin Page" /></title>
		<link rel="stylesheet" href="<c:url value='/template/assets/css/bootstrap.min.css' />" />
        <link rel="stylesheet" href="<c:url value='/template/font-awesome/4.5.0/css/font-awesome.min.css' />" />
		<link rel="stylesheet" href="<c:url value='/template/assets/css/ace.min.css' />" class="ace-main-stylesheet" id="main-ace-style" />
		<script src="<c:url value='/template/assets/js/ace-extra.min.js' />"></script>
		<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">	
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

        <%--sweetalert--%>
  		<%--<script type='text/javascript' src='<c:url value="/template/js/jquery-2.2.3.min.js" />'></script>--%>
        <script type='text/javascript' src='<c:url value="/template/js/2.1.4/jquery.min.js" />'></script>
		<script type='text/javascript' src='<c:url value="/template/assets/sweetalert2/sweetalert2.min.js"/>'></script>
		<link rel="stylesheet" href="<c:url value="/template/assets/sweetalert2/sweetalert2.min.css"/>">
		<script type="text/javascript" src="<c:url value="/template/js/realestate-javascript.js"/>"></script>
        <link rel="stylesheet" href="<c:url value="/template/assets/css/realestate_admin_style.css"/>">

        <%--datatable--%>
        <link href="<c:url value="/template/data-table/media/css/dataTables.bootstrap4_v4.6.1.min.css"/>" rel="stylesheet">
        <script type="text/javascript" src="<c:url value="/template/data-table/data_table-1.0.0.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/template/data-table/data-table-bootstrap4-1.0.0.js"/>"></script>

        <!-- /.main-container -->
        <script src="<c:url value='/template/assets/js/bootstrap.min.js' />"></script>
        <script src="<c:url value='/template/assets/js/ace-elements.min.js' />"></script>
        <script src="<c:url value='/template/assets/js/ace.min.js' />"></script>
	<dec:head />
</head>
<body class="no-skin">

		<!-- Header -->
			<%@ include file="/common/admin/header.jsp" %>
		<!-- End Header -->
		
		<div class="main-container ace-save-state" id="main-container">
				
				<script type="text/javascript">
					try{ace.settings.loadState('main-container')}catch(e){}
				</script>
				
				<!-- Begin menu -->
					<%@ include file="/common/admin/menu.jsp" %>
				<!-- End menu -->
				
				
				<!-- Begin body -->
					<dec:body/>
				<!-- End body -->
				
				<!-- Begin footer -->
					<%@ include file="/common/admin/footer.jsp" %>
				<!-- end footer -->
	
				<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
					<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
				</a>
					
		</div>

        <script src="<c:url value='/template/assets/js/jquery-ui.custom.min.js' />"></script>
        <script src="<c:url value='/template/assets/js/jquery.ui.touch-punch.min.js' />"></script>
        <script src="<c:url value='/template/assets/js/jquery.easypiechart.min.js' />"></script>
        <script src="<c:url value='/template/assets/js/jquery.sparkline.min.js' />"></script>
        <script src="<c:url value='/template/assets/js/jquery.flot.min.js' />"></script>
        <script src="<c:url value='/template/assets/js/jquery.flot.pie.min.js' />"></script>
        <script src="<c:url value='/template/assets/js/jquery.flot.resize.min.js' />"></script>

		<script type="text/javascript">
            function showAlertBeforeDelete(callback){
                swal({
                    title: '<fmt:message key="delete.confirm.message.title"/> ',
                    text: "<fmt:message key="delete.confirm.message.content"/>",
                    type: 'warning',
                    showCancelButton: true,
                    confirmButtonText: '<fmt:message key="label.comfirm"/>',
                    cancelButtonText: '<fmt:message key="label.cancel"/>',
                    confirmButtonClass: 'btn btn-success',
                    cancelButtonClass: 'btn btn-danger'
                }).then(function (isConfirm) {
                    if (isConfirm) {
                        callback();
                    }
                });
            }
		</script>	
</body>
</html>