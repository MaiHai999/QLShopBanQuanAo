<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>

<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f" %>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.80.0">
<title>Khuyến Mãi</title>
<base href="${pageContext.servletContext.contextPath}/">
<link rel="canonical"
	href="https://getbootstrap.com/docs/5.0/examples/starter-template/">

<!-- Bootstrap core CSS -->
<link
	href="<c:url value='/resources/assets/dist/css/bootstrap.min.css' />"
	rel="stylesheet">

<script type="text/javascript"
	src="<c:url value='/resources/ckeditor/ckeditor.js' />">
	
</script>
<script type="text/javascript"
	src="<c:url value='/resources/ckfinder/ckfinder.js'  />">
	
</script>

<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>


<style type="text/css">
div.a {
	text-align: center;
	margin-bottom: 35px;
}
</style>


<link
	href="<c:url value='/resources/assets/dist/starter-template.css' />"
	rel="stylesheet">



</head>
<body>
	<c:choose>
		<c:when test="${check == 1}"><%@include file="/WEB-INF/views/include/menuNhanVien.jsp"%></c:when>
		<c:when test="${check == 2}"><%@include file="/WEB-INF/views/include/menuKeToan.jsp"%></c:when>
		<c:otherwise><%@include file="/WEB-INF/views/include/menu.jsp"%></c:otherwise>
	</c:choose>



	

	<main class="container  ">
		<div class="row justify-content-md-center">
			<%-- 	<%@include file="/WEB-INF/views/include/menudemo.jsp"%> --%>
		</div>


		<div class="bg-light p-5 rounded">
			<div class="a">
				<h2>Khuyến Mãi</h2>

			</div>
			${message}
			<form class="row g-3" action="khuyenmai.htm">
				<input name="makm" type="hidden" class="form-control"
					id="exampleFormControlInput1"
					placeholder="Vui lòng nhập tên sản phẩm" value="${product.makm}" />
				<div class="col-md-4">
					<label for="exampleFormControlInput1" class="form-label">Tên
						khuyến mãi</label> <input name="tenkm" type="text" class="form-control"
						id="exampleFormControlInput1"
						placeholder="Vui lòng nhập tên khuyến mãi"
						value="${product.tenkm}" />
				</div>
				<div class="col-md-4">
					<label for="exampleFormControlInput1" class="form-label">Phần
						trăm khuyến mãi </label> <input name="phantramkm" type="text"
						class="form-control" id="exampleFormControlInput1"
						placeholder="Vui lòng nhập phần trăm khuyến mãi"
						value="${product.phantramkm}" />
				</div>
				<div class="col-md-4">
					<label for="exampleFormControlInput1" class="form-label">Điều
						kiện khuyến mãi</label> <input name="dieukienkm" type="text"
						class="form-control" id="exampleFormControlInput1"
						placeholder="Vui lòng nhập điều kiện khuyến mãi"
						value="${product.dieukienkm}" />
				</div>

				<div class="col-md-4">
					<label for="exampleFormControlInput1" class="form-label">Ngày
						bắt đầu</label> <input name="ngaybatdau" type="text" class="form-control"
						id="exampleFormControlInput1"
						placeholder="Vui lòng nhập ngày bắt đầu"
						value= <f:formatDate value="${product.ngaybatdau}" pattern="dd/MM/yyyy" /> >
				</div>
				<div class="col-md-4">
					<label for="exampleFormControlInput1" class="form-label">Ngày
						kết thúc</label> <input name="ngayketthuc" type="text"
						class="form-control" id="exampleFormControlInput1"
						placeholder="Vui lòng nhập ngày kết thúc"
						value= <f:formatDate value="${product.ngayketthuc}" pattern="dd/MM/yyyy" /> >
				</div>


				<div class="col-12">
					<button name="${btnStatus}" class="btn btn-primary">Save</button>
				</div>

			</form>

		</div>




		<div class="bg-light p-5 rounded">
			<useBean id="pagedListHolder" scope="request"
				type="org.springframework.beans.support.PagedListHolder" />
			<c:url value="/home.htm" var="pagedLink">
				<c:param name="p" value="~" />
			</c:url>
			<div class="d-flex flex-row justify-content-between">
				<div>
					<span id="result1"></span>

					<form class="d-inline-flex">
						<input name="searchInputKM" id="searchInput"
							class="form-control me-2" type="search" placeholder="Search"
							aria-label="Search">

						<button name="btnsearch" id="searchProduct"
							class="btn btn-outline-success" type="submit">Search</button>
					</form>
				</div>




			</div>


			<table class="table">
				<thead>
					<tr>
						<th scope="col">Tên khuyến mãi</th>
						<th scope="col">Phần trăm khuyến mãi</th>
						<th scope="col">Điều kiện khuyến mãi</th>
						<th scope="col">Ngày bắt đầu</th>
						<th scope="col">Ngày kết thúc</th>
						<th scope="col">Chỉnh sửa</th>
						<th scope="col">Xóa</th>
					</tr>
				</thead>
				<tbody id="table_products">
					<c:forEach items="${pagedListHolder.pageList}" var="sp">

						<tr>

							<td>${sp.tenkm}</td>
							<td>${sp.phantramkm}</td>
							<td>${sp.dieukienkm}</td>
							<td><f:formatDate value="${sp.ngaybatdau}" pattern="dd/MM/yyyy" /></td>
							<td><f:formatDate value="${sp.ngayketthuc}" pattern="dd/MM/yyyy" /></td>

							<%-- <td> <a href="products.htm?id=${pd.product_id}"><button type="button"  name="btnEdit" class="btn btn-warning">Chỉnh sửa</button> </a></td> --%>
							<td><a href="khuyenmai/${sp.makm}.htm?linkEdit"><img
									width="40" height="33"
									src="<c:url value="/resources/images/edit.png"/>"></a></td>
							<td><a name="btnDelete"
								href="khuyenmai/${sp.makm}.htm?linkDelete" role="button"><img
									width="30" height="30"
									src="<c:url value="/resources/images/delete.png"/>"></a> <%-- 	<a href="products/${pd.product_id}.htm"> <button type="button" name="btnDelete"  class="btn btn-danger">Xóa</button> </a> --%>

							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<tg:paging pagedListHolder="${pagedListHolder}"
				pagedLink="${pagedLink}" />

		</div>



	</main>
	<script
		src="<c:url value='/resources/assets/dist/js/bootstrap.bundle.min.js' />"></script>
	<script>
		var ckeditor = CKEDITOR.replace('description');
		CKFinder.setupCKEditor(ckeditor,
				'${pageContext.request.contextPath}/resources/ckfinder/');
	</script>

</body>
</html>


