<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>

<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.80.0">
<title>BÁN HÀNG</title>
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
				<h2>HÓA ĐƠN</h2>

			</div>


			<form class="row g-3" action="nhaphang.htm">
				<div class="col-md-4">MÃ HÓA ĐƠN: ${mahd}</div>

				<div class="col-md-4">NGÀY LẬP: ${ngaylap}</div>


				<div class="col-md-4">TỔNG TIỀN: ${tongtien}</div>


				<div class="col-md-4">NHÀ CUNG CẤP: ${mancc}</div>


				<div class="col-md-4">MANV: ${manv}</div>



				<useBean id="pagedListHolder" scope="request"
					type="org.springframework.beans.support.PagedListHolder" />
				<c:url value="/nhaphang.htm" var="pagedLink">
					<c:param name="p1" value="~" />
				</c:url>



				<table class="table">
					<thead>
						<tr>
							<th scope="col">Tên Sản Phẩm</th>
							<th scope="col">Giá</th>
							<th scope="col">Số lượng</th>
							<th scope="col">Thành tiền</th>
						</tr>
					</thead>
					<tbody id="table_products">
						<c:forEach items="${pagedListHolderGH1.pageList}" var="sp">

							<tr>

								<td>${sp.tensp}</td>
								<td>${sp.gia}</td>
								<td>${sp.soluong}</td>
								<td>${sp.soluong * sp.gia}</td>

							</tr>
						</c:forEach>
					</tbody>
				</table>
				<tg:paging pagedListHolder="${pagedListHolderGH1}"
					pagedLink="${pagedLink}" />

				<div class="col-12">
					<button name="back" class="btn btn-primary">QUAY LẠI</button>
				</div>









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


