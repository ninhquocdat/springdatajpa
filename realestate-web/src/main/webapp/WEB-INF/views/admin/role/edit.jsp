<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:url var="formEditUrl" value="/ajax/admin/role/edit.html"/>
<c:choose>
    <c:when test="${not empty messageResponse}">
        ${messageResponse}
    </c:when>
    <c:otherwise>
        <div class="modal-backdrop fade in" style="height: 648px;"></div>
        <form:form commandName="item" action="${formEditUrl}" method="post" id="editForm">
            <div class="modal-dialog" role="document">
            <!--Content-->
            <div class="modal-content">
                <!--Header-->
                <div class="modal-header bg-primary">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <fmt:message key="label.role.add" var="titleFormEdit"/>
                    <c:if test="${not empty item.pojo.id}">
                        <fmt:message key="label.role.edit" var="titleFormEdit"/>
                    </c:if>
                    <h4 class="modal-title" id="myModalLabel">${titleFormEdit}</h4>
                </div>
                <!--Body-->
                <div class="modal-body">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="md-form">
                                <input type="text" class="form-control" placeholder="<fmt:message key="label.role.name"/>" name="pojo.name" value="${item.pojo.name}"/>
                                <form:errors path="pojo.name" cssClass="red-text"/>
                            </div>
                        </div>
                    </div>
                    <br/>
                    <div class="row">
                        <div class="col-md-12">
                            <div class="md-form">
                                <input type="text" class="form-control" placeholder="<fmt:message key="label.role.code"/>" name="pojo.code" value="${item.pojo.code}"/>
                                <form:errors path="pojo.code" cssClass="red-text"/>
                            </div>
                        </div>
                    </div>
                </div>
                <!--Footer-->
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="label.cancel"/></button>
                    <button type="button" class="btn btn-primary" id="btnSave"><fmt:message key="label.save"/></button>
                </div>
            </div>
            <!--/.Content-->
            <form:hidden path="crudaction" id="crudactionEdit"/>
            <form:hidden path="pojo.id"/>
        </form:form>
    </c:otherwise>
</c:choose>