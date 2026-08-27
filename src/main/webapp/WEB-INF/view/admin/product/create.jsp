<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Create a new Product</title>
    <link href="/css/styles.css" rel="stylesheet" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script>
        $(document).ready(() => {
            const avatarFile = $("#avatarFile");
            avatarFile.change(function (e) {
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
                <h1 class="mt-4">Manage Products</h1>
                <ol class="breadcrumb mb-4">
                    <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                    <li class="breadcrumb-item"><a href="/admin/product">Manage Products</a></li>
                    <li class="breadcrumb-item active">Create Product</li>
                </ol>
                <div class="mt-5">
                    <div class="row">
                        <div class="col-md-6 col-12 mx-auto">
                            <h1>Create a Product</h1>
                            <hr/>
                            <form:form method="post" action="/admin/product/create" modelAttribute="newProduct" class="row" enctype="multipart/form-data">
                                <div class="mb-3">
                                    <c:set var="errorName">
                                        <form:errors path="name" cssClass="invalid-feedback"/>
                                    </c:set>
                                    <label class="form-label">Name:</label>
                                    <form:input type="text" class="form-control ${not empty errorName? 'is-invalid':''}"
                                                path="name"
                                    />
                                        ${errorName}
                                </div>
                                <div class="mb-3">
                                    <c:set var="errorShortDescription">
                                        <form:errors path="shortDesc" cssClass="invalid-feedback"/>
                                    </c:set>
                                    <label class="form-label">Short description:</label>
                                    <form:input type="text" class="form-control ${not empty errorShortDescription? 'is-invalid':''}"
                                                path="shortDesc"
                                    />
                                        ${errorShortDescription}
                                </div>
                                <div class="mb-3">
                                    <c:set var="errorDetailDescription">
                                        <form:errors path="detailDesc" cssClass="invalid-feedback"/>
                                    </c:set>
                                    <label class="form-label">Detail description:</label>
                                    <form:textarea class="form-control ${not empty errorDetailDescription? 'is-invalid':''}"
                                                path="detailDesc"
                                                   rows="3"
                                    />
                                        ${errorDetailDescription}
                                </div>
                                <div class="mb-3 col-12 col-md-6">
                                    <label class="form-label">Factory:</label>
                                    <form:select class="form-select" path="factory">
                                        <form:option value="ACER">Acer</form:option>
                                        <form:option value="APPLE">Apple</form:option>
                                        <form:option value="ASUS">Asus</form:option>
                                        <form:option value="DELL">Dell</form:option>
                                        <form:option value="HP">HP</form:option>
                                        <form:option value="LENOVO">Lenovo</form:option>
                                    </form:select>
                                </div>
                                <div class="mb-3 col-12 col-md-6">
                                    <label class="form-label">Target:</label>
                                    <form:select class="form-select" path="target">
                                        <form:option value="ULTRABOOK">Ultrabook</form:option>
                                        <form:option value="GAMING">Gaming Laptop</form:option>
                                        <form:option value="2IN1">2-in-1 Laptop</form:option>
                                        <form:option value="BUSINESS">Business Laptop</form:option>
                                        <form:option value="CHROME">Chromebook</form:option>
                                        <form:option value="MOBILE">Mobile Workstation</form:option>
                                    </form:select>
                                </div>
                                <div class="mb-3 col-12 col-md-6">
                                    <c:set var="errorPrice">
                                        <form:errors path="price" cssClass="invalid-feedback"/>
                                    </c:set>
                                    <label class="form-label">Price:</label>
                                    <form:input type="number" step="0.01" class="form-control ${not empty errorPrice? 'is-invalid':''}"
                                                path="price"
                                    />
                                        ${errorPrice}
                                </div>
                                <div class="mb-3 col-12 col-md-6">
                                    <c:set var="errorQuantity">
                                        <form:errors path="quantity" cssClass="invalid-feedback"/>
                                    </c:set>
                                    <label class="form-label">Quantity:</label>
                                    <form:input type="number" class="form-control ${not empty errorQuantity? 'is-invalid':''}"
                                                path="quantity"
                                    />
                                        ${errorQuantity}
                                </div>
                                <div class="mb-3">
                                    <label for="avatarFile" class="form-label">Image:</label>
                                    <div class="input-group">
                                        <input class="form-control" type="file" id="avatarFile"
                                               accept=".png,.jpg,.jpeg"
                                               name="hungkhongFile"
                                        />
                                        <span class="input-group-text bg-white" id="btnClearImage" style="cursor: pointer; border-left: none; color: #6c757d;">
                                            <i class="fa-solid fa-xmark"></i>
                                        </span>
                                    </div>
                                </div>
                                <div class="col-12 mb-3 d-flex justify-content-center">
                                    <img style="max-height: 250px; display: none;" alt="avatar preview" id="avatarPreview">
                                </div>
                                <div class="col-12 mb-5">
                                    <a href="/admin/product" class="btn btn-secondary my-3 me-2">Cancel</a>
                                    <button type="submit" class="btn btn-primary">Create</button>
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
