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
<title>NHẬP HÀNG</title>
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
				<h2>NHẬP HÀNG</h2>

			</div>
			<h2 style="font-size: 20px">DANH SÁCH SẢM PHẨM CẦN NHẬP</h2>
			${message}
			<useBean id="pagedListHolder" scope="request"
				type="org.springframework.beans.support.PagedListHolder" />
			<c:url value="/nhaphang.htm" var="pagedLink">
				<c:param name="p" value="~" />
			</c:url>
			<div class="d-flex flex-row justify-content-between">
				<div>
					<span id="result1"></span>

					<form class="d-inline-flex">
						<input name="searchInputBH" id="searchInputBH"
							class="form-control me-2" type="search" placeholder="Search"
							aria-label="Search">


						<button name="btnsearchBH" id="searchProduct"
							class="btn btn-outline-success" type="submit">Search</button>
					</form>
				</div>




			</div>
			<table class="table">
				<thead>
					<tr>
						<th scope="col">Tên Sản Phẩm</th>
						<th scope="col">Tồn kho</th>
						<th scope="col">Số lượng</th>
						<th scope="col">Giá</th>
						<th scope="col">Nhập</th>
					</tr>
				</thead>
				<tbody id="table_products">
					<c:forEach items="${pagedListHolderBH.pageList}" var="sp">

						<tr>

							<td>${sp.tensp}</td>
							<td>${sp.soluong}</td>

							<form>
								<td><div class="buttons_added">
										<input class="minus is-form" type="button" value="-">
										<input aria-label="quantity" class="input-qty" max="100"
											min="0" name="soluong" type="number" value="0"> <input
											class="plus is-form" type="button" value="+">
									</div> <input name="masp" type="hidden" class="form-control"
									id="exampleFormControlInput1"
									placeholder="Vui lòng nhập tên sản phẩm" value="${sp.masp}" />

									<input name="price" type="hidden" class="form-control"
									id="exampleFormControlInput1"
									placeholder="Vui lòng nhập tên sản phẩm" value="${sp.gia}" />


									<input name="tenProduct" type="hidden" class="form-control"
									id="exampleFormControlInput1"
									placeholder="Vui lòng nhập tên sản phẩm" value="${sp.tensp}" />

								</td>
								<td>
									<div class="col-md-9">
										<input name="giaSP" type="text" class="form-control"
											id="exampleFormControlInput1" placeholder="Vui lòng nhập giá"
											value="${tenKH}" />
									</div>
								</td>
								<td>
									<button name="btnSale" id="saleProduct" type="submit">
										<img width="30" height="30" alt=""
											src="<c:url value="/resources/images/trolley.png"/>">
									</button>
								</td>
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
			<form class="row g-3" action="nhaphang.htm">




				<div class="col-md-4">
					<label for="exampleFormControlInput1" class="form-label">Nhà
						cung cấp</label> <select name="comboboxNCC"
						class="form-select form-select-lg mb-6"
						aria-label=".form-select-lg example">
						<c:forEach items="${listNCC}" var="km">
							<option value="${km.mancc}"
								class="form-select form-select-lg mb-6"
								aria-label=".form-select-lg example">${km.tenncc}</option>
						</c:forEach>
					</select>

				</div>





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
							<th scope="col">Xóa</th>
						</tr>
					</thead>
					<tbody id="table_products">
						<c:forEach items="${pagedListHolderGH.pageList}" var="sp">

							<tr>

								<td>${sp.tensp}</td>
								<td>${sp.gia}</td>
								<td>${sp.soluong}</td>
								<td>${sp.soluong * sp.gia}</td>

								<form>
									<td><input name="masp" type="hidden" class="form-control"
										id="exampleFormControlInput1"
										placeholder="Vui lòng nhập tên sản phẩm" value="${sp.masp}" />
										<button name="btnDeletePD" id="saleProduct" type="submit">
											<img width="30" height="30" alt=""
												src="<c:url value="/resources/images/delete.png"/>">
										</button></td>
								</form>


							</tr>
						</c:forEach>
					</tbody>
				</table>
				<tg:paging pagedListHolder="${pagedListHolderGH}"
					pagedLink="${pagedLink}" />

				<div class="col-12">
					<button name="xuatHoaDon" class="btn btn-primary">XUẤT HÓA
						ĐƠN</button>
				</div>

			</form>
			${message1}


		</div>






	</main>
	<script
		src="<c:url value='/resources/assets/dist/js/bootstrap.bundle.min.js' />"></script>
	<script>
		var ckeditor = CKEDITOR.replace('description');
		CKFinder.setupCKEditor(ckeditor,
				'${pageContext.request.contextPath}/resources/ckfinder/');
	</script>

	<script>
		//<![CDATA[
		$('input.input-qty')
				.each(
						function() {
							var $this = $(this), qty = $this.parent().find(
									'.is-form'), min = Number($this.attr('min')), max = Number($this
									.attr('max'))
							if (min == 0) {
								var d = 0
							} else
								d = min
							$(qty).on('click', function() {
								if ($(this).hasClass('minus')) {
									if (d > min)
										d += -1
								} else if ($(this).hasClass('plus')) {
									var x = Number($this.val()) + 1
									if (x <= max)
										d += 1
								}
								$this.attr('value', d).val(d)
							})
						})
		//]]>
	</script>

</body>
</html>


