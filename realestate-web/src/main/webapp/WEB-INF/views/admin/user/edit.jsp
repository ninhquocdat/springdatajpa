<%@include file="/common/taglib.jsp"%>
<c:url var="formEditUrl" value="/ajax/admin/user/edit.html"/>
<c:choose>
    <c:when test="${not empty messageResponse}">
        ${messageResponse}
    </c:when>
    <c:otherwise>
        <div class="modal-backdrop fade in" style="height: 648px;"></div>
            <div class="modal-dialog">
            <!--Content-->
            <div class="modal-content">
                <!--Header-->
                <div class="modal-header bg-primary">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <fmt:message key="label.account.add" var="titleFormEdit"/>
                    <c:if test="${not empty item.pojo.id}">
                        <fmt:message key="label.account.edit" var="titleFormEdit"/>
                    </c:if>
                    <h4 class="modal-title" id="myModalLabel">${titleFormEdit}</h4>
                </div>
                <!--Body-->
                <form:form commandName="item" action="${formEditUrl}" method="post" id="editForm">
                    <div class="modal-body">
                        <div class="row">
                            <div class="col-md-12">
                                <div class="md-form">
                                    <input type="text" class="form-control" placeholder="<fmt:message key="label.username"/>" name="pojo.userName" value="${item.pojo.userName}"/>
                                    <form:errors path="pojo.userName" cssClass="red-text"/>
                                </div>
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="md-form">
                                    <input type="text" class="form-control" value="<fmt:message key="label.password.automation"/>" disabled="disabled"/>
                                </div>
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="md-form">
                                    <input type="text" class="form-control" placeholder="<fmt:message key="label.fullname"/>" name="pojo.fullName" value="${item.pojo.fullName}"/>
                                    <form:errors path="pojo.fullName" cssClass="red-text"/>
                                </div>
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="md-form">
                                    <input type="text" class="form-control" placeholder="<fmt:message key="label.firstname"/>" name="pojo.firstName" value="${item.pojo.firstName}"/>
                                    <form:errors path="pojo.firstName" cssClass="red-text"/>
                                </div>
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="md-form">
                                    <input type="text" class="form-control" placeholder="<fmt:message key="label.lastname"/>" name="pojo.lastName" value="${item.pojo.lastName}"/>
                                    <form:errors path="pojo.lastName" cssClass="red-text"/>
                                </div>
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="md-form">
                                    <input type="text" class="form-control" placeholder="<fmt:message key="label.email"/>" name="pojo.email" value="${item.pojo.email}"/>
                                    <form:errors path="pojo.email" cssClass="red-text"/>
                                </div>
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="md-form">
                                    <input type="text" class="form-control" placeholder="<fmt:message key="label.phonenumber"/>" name="pojo.phoneNumber" value="${item.pojo.phoneNumber}"/>
                                    <form:errors path="pojo.phoneNumber" cssClass="red-text"/>
                                </div>
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="md-form">
                                        <form:select path="pojo.roleDTO.roleId" cssClass="mdb-select">
                                           <form:option value="-1"><fmt:message key="label.role.option"/></form:option>
                                           <form:options items="${item.listRole}" itemLabel="name" itemValue="roleId"/>
                                        </form:select>
                                        <form:errors path="pojo.roleDTO.roleId" cssClass="red-text"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <form:hidden path="crudaction" id="crudactionEdit"/>
                    <form:hidden path="pojo.id"/>
                </form:form>
                <!--Footer-->
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal"><fmt:message key="label.cancel"/></button>
                    <button type="button" class="btn btn-primary" id="btnSave"><fmt:message key="label.save"/></button>
                </div>
            </div>
            <!--/.Content-->
    </c:otherwise>
</c:choose>