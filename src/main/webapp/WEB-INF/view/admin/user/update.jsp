<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <title>Update User ID ${id}</title>
    <link href="/css/styles.css" rel="stylesheet" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
        $(document).ready(() => {
            const avatarInput = document.getElementById("avatarFile");
            const oldAvatarName = "${updateUser.avatar}";
            if (oldAvatarName && oldAvatarName.trim() !== "") {
                try {
                    const dataTransfer = new DataTransfer();
                    const file = new File([""], oldAvatarName);
                    dataTransfer.items.add(file);
                    avatarInput.files = dataTransfer.files;
                } catch (err) {
                    console.error(err);
                }
            }
            $("#avatarFile").change(function (e) {
                if (e.target.files && e.target.files[0]) {
                    const imgURL = URL.createObjectURL(e.target.files[0]);
                    $("#avatarPreview").attr("src", imgURL);
                    $("#avatarPreview").css({ "display": "block" });
                }
            });
            $("#btnClearImage").click(function () {
                $("#avatarFile").val("");
                $("#avatarPreview").attr("src", "");
                $("#avatarPreview").css({ "display": "none" });
            });
        });
    </script>
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
<jsp:include page="../layout/header.jsp" />
<div id="layoutSidenav">
    <jsp:include page="../layout/sidebar.jsp" />
    <div id="layoutSidenav_content">
        <main>
            <div class="container-fluid px-4">
                <h1 class="mt-4">Manage Users</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                    <li class="breadcrumb-item"><a href="/admin/user">Manage Users</a></li>
                    <li class="breadcrumb-item active">Update User</li>
                </ol>
                <div class="mt-5">
                    <div class="row">
                        <div class="col-md-6 col-12 mx-auto">
                            <h1>Update User with ID = ${id}</h1>
                            <hr/>
                            <form:form method="post" action="/admin/user/${id}/edit" modelAttribute="updateUser" class="row" enctype="multipart/form-data">
                                <div class="mb-3 col-12 col-md-6">
                                    <c:set var="errorFullName">
                                        <form:errors path="fullName" cssClass="invalid-feedback"/>
                                    </c:set>
                                    <label class="form-label">Full Name:</label>
                                    <form:input type="text" class="form-control ${not empty errorFullName? 'is-invalid':''}" path="fullName" />
                                    ${errorFullName}
                                </div>
                                <div class="mb-3 col-12 col-md-6">
                                    <c:set var="errorPhone">
                                        <form:errors path="phone" cssClass="invalid-feedback"/>
                                    </c:set>
                                    <label class="form-label">Phone Number:</label>
                                    <form:input type="text" class="form-control ${not empty errorPhone? 'is-invalid':''}" path="phone" />
                                    ${errorPhone}
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Email:</label>
                                    <form:input type="email" class="form-control" path="email" disabled="true" />
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Address:</label>
                                    <form:input type="text" class="form-control" path="address" />
                                </div>
                                <div class="mb-3">
                                    <label for="avatarFile" class="form-label">Avatar:</label>
                                    <div class="input-group">
                                        <input class="form-control" type="file" id="avatarFile"
                                               accept=".png,.jpg,.jpeg"
                                               name="hungkhongFile" />
                                        <span class="input-group-text bg-white" id="btnClearImage" style="cursor: pointer; border-left: none; color: #6c757d;">
                                            <i class="fa-solid fa-xmark"></i>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-12 mb-3 d-flex justify-content-center">
                                    <img style="max-height: 250px; ${empty updateUser.avatar ? 'display: none;' : ''}"
                                         alt="avatar preview"
                                         id="avatarPreview"
                                         src="/images/avatar/${updateUser.avatar}" />
                                </div>
                                <div class="col-12 mb-5">
                                    <a href="/admin/user" class="btn btn-secondary my-3 me-2">Cancel</a>
                                    <button type="submit" class="btn btn-warning">Update</button>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </main>
        <jsp:include page="../layout/footer.jsp" />
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="/js/scripts.js"></script>
</body>
</html>
