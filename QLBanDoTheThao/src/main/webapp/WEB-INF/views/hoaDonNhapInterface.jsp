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
<title>HÓA ĐƠN</title>
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

div.b {
	margin-left: 90px;
}

div.b {
	margin-left: 90px;
}

div.buttons_added {
	opacity: 1;
	display: inline-block;
	display: -ms-inline-flexbox;
	display: inline-flex;
	white-space: nowrap;
	vertical-align: top;
}

.is-form {
	overflow: hidden;
	position: relative;
	background-color: #f9f9f9;
	height: 2.2rem;
	width: 1.9rem;
	padding: 0;
	text-shadow: 1px 1px 1px #fff;
	border: 1px solid #ddd;
}

.is-form:focus, .input-text:focus {
	outline: none;
}

.is-form.minus {
	border-radius: 4px 0 0 4px;
}

.is-form.plus {
	border-radius: 0 4px 4px 0;
}

.input-qty {
	background-color: #fff;
	height: 2.2rem;
	text-align: center;
	font-size: 1rem;
	display: inline-block;
	vertical-align: top;
	margin: 0;
	border-top: 1px solid #ddd;
	border-bottom: 1px solid #ddd;
	border-left: 0;
	border-right: 0;
	padding: 0;
}

.input-qty::-webkit-outer-spin-button, .input-qty::-webkit-inner-spin-button
	{
	-webkit-appearance: none;
	margin: 0;
}
</style>


<link
	href="<c:url value='/resources/assets/dist/starter-template.css' />"
	rel="stylesheet">



</head>
<body>
	<c:choose>
		<c:when test="${check == 1}"><%@include
				file="/WEB-INF/views/include/menuNhanVien.jsp"%></c:when>
		<c:when test="${check == 2}"><%@include
				file="/WEB-INF/views/include/menuKeToan.jsp"%></c:when>
		<c:otherwise><%@include
				file="/WEB-INF/views/include/menu.jsp"%></c:otherwise>
	</c:choose>

	<main class="container  ">
		<div class="row justify-content-md-center">
			<%-- 	<%@include file="/WEB-INF/views/include/menudemo.jsp"%> --%>
		</div>


		<div class="bg-light p-5 rounded">
			<div class="a">
				<h2>HÓA ĐƠN NHẬP HÀNG</h2>

			</div>
			<h2 style="font-size: 20px">DANH SÁCH HÓA ĐƠN</h2>
			${message}
			<form class="d-inline-flex">
				<input name="searchHDN" id="searchInputBH" class="form-control me-2"
					type="search" placeholder="Search bill" aria-label="Search">


				<button name="btnsearchHDN" id="searchProduct"
					class="btn btn-outline-success" type="submit">Search</button>


			</form>

			<useBean id="pagedListHolder" scope="request"
				type="org.springframework.beans.support.PagedListHolder" />
			<c:url value="/hoadonban.htm" var="pagedLink">
				<c:param name="p" value="~" />
			</c:url>
			<div class="d-flex flex-row justify-content-between"></div>
			<table class="table">
				<thead>
					<tr>
						<th scope="col">MaHDN</th>
						<th scope="col">Ngày Lập</th>
						<th scope="col">Tổng Tiền</th>
						<th scope="col">Mã Nhân Viên</th>
						<th scope="col">Mã Nhà Cung Cấp</th>
						<th scope="col">Chi tiết</th>



					</tr>
				</thead>
				<tbody id="table_products">
					<c:forEach items="${pagedListHolderBH.pageList}" var="sp">

						<tr>

							<td>${sp.manhdn}</td>
							<td><f:formatDate value="${sp.ngaylap}" pattern="dd/MM/yyyy" /></td>
							<td>${sp.tongtien}</td>
							<td>${sp.manv}</td>
							<td>${sp.mancc}</td>
							<form>
								<td><input name="mahdn" type="hidden" class="form-control"
									id="exampleFormControlInput1"
									placeholder="Vui lòng nhập tên sản phẩm" value="${sp.manhdn}" />
									<button name="btnDetal" id="saleProduct" type="submit">
										<img width="30" height="30" alt=""
											src="<c:url value="/resources/images/edit.png"/>">
									</button></td>
							</form>

						</tr>
					</c:forEach>
				</tbody>
			</table>
			<tg:paging pagedListHolder="${pagedListHolderBH}"
				pagedLink="${pagedLink}" />







		</div>




		<div class="bg-light p-5 rounded">
			<h2 style="font-size: 20px">CHI TIẾT HÓA ĐƠN</h2>



			<form class="row g-3" action="hoadonnhap.htm">


				<useBean id="pagedListHolder" scope="request"
					type="org.springframework.beans.support.PagedListHolder" />
				<c:url value="/hoadonnhap.htm" var="pagedLink">
					<c:param name="p1" value="~" />
				</c:url>



				<table class="table">
					<thead>
						<tr>
							<th scope="col">Mã Hóa Đơn</th>
							<th scope="col">Mã Sản Phẩm</th>
							<th scope="col">Số Lượng</th>
							<th scope="col">Giá</th>
						</tr>
					</thead>
					<tbody id="table_products">
						<c:forEach items="${pagedListHolderGH.pageList}" var="sp">

							<tr>

								<td>${sp.id.mahdn}</td>
								<td>${sp.id.masp}</td>
								<td>${sp.soluong}</td>
								<td>${sp.gia}</td>



							</tr>
						</c:forEach>
					</tbody>
				</table>
				<tg:paging pagedListHolder="${pagedListHolderGH}"
					pagedLink="${pagedLink}" />



			</form>

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


