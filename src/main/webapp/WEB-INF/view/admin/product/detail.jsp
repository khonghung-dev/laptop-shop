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
    <title>Product Detail ID ${id}</title>
    <link href="/css/styles.css" rel="stylesheet" />
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
                    <li class="breadcrumb-item"><a href="/admin/">Dashboard</a></li>
                    <li class="breadcrumb-item"><a href="/admin/product">Manage Products</a></li>
                    <li class="breadcrumb-item active">Product Detail</li>
                </ol>
                <div class="mt-5">
                    <div class="row">
                        <div class="col-12 mx-auto">
                            <h3>Product Detail</h3>
                            <hr/>
                            <div class="row">
                                <div class="col-12 col-md-7">
                                    <div class="card h-100">
                                        <div class="card-header">
                                            Product Information
                                        </div>
                                        <ul class="list-group list-group-flush">
                                            <li class="list-group-item">ID: ${id}</li>
                                            <li class="list-group-item">Name: ${products.name}</li>
                                            <li class="list-group-item">Factory: ${products.factory}</li>
                                            <li class="list-group-item">Target: ${products.target}</li>
                                            <li class="list-group-item">Price: ${products.price}</li>
                                            <li class="list-group-item">Quantity: ${products.quantity}</li>
                                            <li class="list-group-item">Short Description: ${products.shortDesc}</li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="col-12 col-md-5 d-flex justify-content-center align-items-center mt-3 mt-md-0">
                                    <div class="card p-2 text-center w-100 h-100 d-flex justify-content-center align-items-center">
                                        <c:if test="${not empty products.image}">
                                            <img src="/images/product/${products.image}"
                                                 alt="${products.name}"
                                                 class="img-fluid rounded"
                                                 style="max-height: 300px; object-fit: contain;" />
                                        </c:if>
                                        <c:if test="${empty products.image}">
                                            <span class="text-muted">Không có ảnh sản phẩm</span>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                            <a href="/admin/product" class="btn btn-secondary my-3">Back</a>
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
