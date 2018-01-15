<%@ page import="vn.hcmute.core.data.common.Constant" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="formListUrl" value="/admin/role/list.html"/>
<c:url var="ajaxAddEditUrl" value="/ajax/admin/role/edit.html"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="label.manage.role"/></title>
</head>
<body>
<div class="main-content">
    <form:form commandName="items" action="${formListUrl}" id="listForm" method="post">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="/admin.html"><fmt:message key="label.admin.home"/></a>
                    </li>
                    <li class="active"><fmt:message key="label.role.list"/></li>
                </ul><!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <c:if test="${messageResponse!=null}">
                            <div class="alert alert-block alert-${alert}">
                                <button type="button" class="close" data-dismiss="alert">
                                    <i class="ace-icon fa fa-times"></i>
                                </button>
                                    ${messageResponse}
                            </div>
                        </c:if>
                        <!-- PAGE CONTENT BEGINS -->
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="widget-box table-filter">
                                    <div class="widget-header">
                                        <h4 class="widget-title"><fmt:message key="label.search"/></h4>
                                        <div class="widget-toolbar">
                                            <a href="#" data-action="collapse">
                                                <i class="ace-icon fa fa-chevron-up"></i>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="widget-body">
                                        <div class="widget-main">
                                            <div class="form-horizontal">
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label"><fmt:message key="label.role.name"/></label>
                                                    <div class="col-sm-8">
                                                        <div class="fg-line">
                                                            <form:input path="pojo.name" cssClass="form-control input-sm"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label"><fmt:message key="label.role.code"/></label>
                                                    <div class="col-sm-8">
                                                        <div class="fg-line">
                                                            <form:input path="pojo.code" cssClass="form-control input-sm"/>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-2 control-label"></label>
                                                    <div class="col-sm-8">
                                                        <button id="btnSearch" type="button" class="btn btn-sm btn-success">
                                                            <fmt:message key="label.search"/>
                                                            <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="table-btn-controls">
                                    <div class="pull-right tableTools-container">
                                        <div class="dt-buttons btn-overlap btn-group">
                                            <a flag="info" class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                               onclick="update(this)" data-toggle="tooltip" title='<fmt:message key="label.role.add"/>'>
                                                <span>
                                                    <i class="fa fa-plus-circle bigger-110 purple"></i>
                                                </span>
                                            </a>
                                            <button type="button" class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
                                                    disabled="" id="deleteAll" onclick="warningBeforeDelete()" data-toggle="tooltip" title='<fmt:message key="label.delete"/>'>
                                                 <span>
                                                    <i class="fa fa-trash-o bigger-110 pink"></i>
                                                </span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="table-responsive">
                                    <display:table name="items.listResult" cellspacing="0" cellpadding="0" requestURI="${formUrl}"
                                                   partialList="true" sort="external" size="${items.totalItems}" defaultsort="3"
                                                   id="tableList" excludedParams="checkList"
                                                   pagesize="${items.maxPageItems}" export="false"
                                                   class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                                   style="margin: 3em 0 1.5em;">
                                        <display:column title="<fieldset class='form-group'>
												        <input type='checkbox' id='checkAll' class='check-box-element'>
												        </fieldset>" class="center select-cell"
                                                        headerClass="center select-cell">
                                            <fieldset>
                                                <input type="checkbox" name="checkList" value="${tableList.id}" id="checkbox_${tableList.id}" class="check-box-element"/>
                                            </fieldset>
                                            <div id="deleteCurrentRole">
                                            </div>
                                        </display:column>
                                        <display:column headerClass="text-left" property="name" sortName="name" sortable="true"
                                                        titleKey="label.role.name"/>
                                        <display:column headerClass="text-left" property="code" sortName="name" sortable="true"
                                                        titleKey="label.role.code"/>
                                        <display:column headerClass="col-actions" titleKey="label.action">
                                            <c:url var="editRoleUrl" value="/ajax/admin/role/edit.html">
                                                <c:param name="pojo.id" value="${tableList.id}"/>
                                            </c:url>
                                            <a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip" sc-url="${editRoleUrl}" title='<fmt:message key="label.account.edit"/>' onclick="update(this)"><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a>
                                            <a class="btn btn-sm btn-danger btn-cancel" data-toggle="tooltip" title='<fmt:message key="label.account.remove"/>' onclick="warningBeforeDelete(${tableList.id})"><i class="fa fa-trash" aria-hidden="true"></i></a>
                                        </display:column>
                                    </display:table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <form:hidden path="crudaction" id="crudaction"/>
    </form:form>
</div>
<div class="modal fade" id="addModal" tabindex="-1" role="dialog"></div>
<script type="text/javascript">
    $(document).ready(function () {
        $('#btnSearch').click(function () {
             $('#listForm').submit();
        });
    });
    function update(btn) {
        var url = $(btn).attr('sc-url');
        if(typeof url == "undefined"){
            url = '${ajaxAddEditUrl}'
        }
        $('#addModal').load(url,{},function () {
            $('#addModal').modal('toggle');
            registerSubmitPopup(url);
        });
    }
    function registerSubmitPopup() {
        $('#btnSave').click(function () {
            $('#editForm').submit();
        });
        $('#editForm').submit(function(e) {
            e.preventDefault();
            $("#crudactionEdit").val("insert-update");
            $.ajax({
                cache: false,
                type: "POST",
                dataType: "html",
                data: $(this).serialize(),
                url: $(this).attr('action'),
                success: function(res) {
                    if(res.trim() == '<%=Constant.REDIRECT_UPDATE%>') {
                        $('#crudaction').val('<%=Constant.REDIRECT_UPDATE%>');
                        $('#listForm').submit();
                    }else if(res.trim() == '<%=Constant.REDIRECT_INSERT%>') {
                        $('#crudaction').val('<%=Constant.REDIRECT_INSERT%>');
                        $('#listForm').submit();
                    }else if(res.trim() == '<%=Constant.REDIRECT_ERROR%>') {
                        $('#crudaction').val('<%=Constant.REDIRECT_ERROR%>');
                        $('#listForm').submit();
                    }else {
                        $("#addModal").html(res);
                        registerSubmitPopup();
                    }
                }
            });
        });
    }
    function warningBeforeDelete(id) {
        showAlertBeforeDelete(function () {
            if (id != undefined && id != "") {
                $(".check-box-element").prop('checked', false);
                var html = '<input type="hidden" value="' + id + '" name="checkList"/>';
                $('#deleteCurrentRole').append(html);
            }
            $('#crudaction').val('delete');
            $('#listForm').submit();
        });
    }
</script>
</body>
</html>
