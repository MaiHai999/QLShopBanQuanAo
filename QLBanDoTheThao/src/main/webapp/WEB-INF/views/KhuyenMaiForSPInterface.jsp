<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>

<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="f"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.80.0">
<title>KHUYỄN MÃI</title>
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
				<h2>KHUYẾN MÃI</h2>

			</div>
			<h2 style="font-size: 20px">SẢN PHẨM CẦN KHUYẾN MÃI</h2>
			${message}
			<useBean id="pagedListHolder" scope="request"
				type="org.springframework.beans.support.PagedListHolder" />
			<c:url value="/khuyenmaisp.htm" var="pagedLink">
				<c:param name="p" value="~" />
			</c:url>
			<div class="d-flex flex-row justify-content-between">
				<div>
					<span id="result1"></span>

					<form class="d-inline-flex">

						<input name="tenbutton" type="hidden" class="form-control"
							id="exampleFormControlInput1"
							placeholder="Vui lòng nhập tên sản phẩm" value="${btnStatus}" />
						<input name="searchInputKMSP" id="searchInputKMSP"
							class="form-control me-2" type="search" placeholder="Search"
							aria-label="Search">


						<button name="btnsearchKMSP" id="searchProduct"
							class="btn btn-outline-success" type="submit">Search</button>
					</form>
				</div>




			</div>
			<table class="table">
				<thead>
					<tr>
						<th scope="col">Tên Sản Phẩm</th>
						<th scope="col">Tồn kho</th>
						<th scope="col">Hãng</th>
						<th scope="col">Size</th>
						<th scope="col">Phần trăm KM</th>
						<th scope="col">Thêm</th>
					</tr>
				</thead>
				<tbody id="table_products">
					<c:forEach items="${pagedListHolderBH.pageList}" var="sp">

						<tr>

							<td>${sp.tensp}</td>
							<td>${sp.soluong}</td>
							<td>${sp.hang}</td>
							<td>${sp.size}</td>
							<form>
								<td>
									<div class="col-md-6">
										<input name="ptkm" type="text" class="form-control"
											id="exampleFormControlInput1" placeholder="Vui lòng nhập "
											value="${tenKH}" />
									</div>
								</td>


								<td><input name="masp" type="hidden" class="form-control"
									id="exampleFormControlInput1"
									placeholder="Vui lòng nhập tên sản phẩm" value="${sp.masp}" />

									<input name="price" type="hidden" class="form-control"
									id="exampleFormControlInput1"
									placeholder="Vui lòng nhập tên sản phẩm" value="${sp.gia}" />

									<input name="tenProduct" type="hidden" class="form-control"
									id="exampleFormControlInput1"
									placeholder="Vui lòng nhập tên sản phẩm" value="${sp.tensp}" />

									<input name="SLProduct" type="hidden" class="form-control"
									id="exampleFormControlInput1"
									placeholder="Vui lòng nhập tên sản phẩm" value="${sp.soluong}" />

									<input name="sizeProduct" type="hidden" class="form-control"
									id="exampleFormControlInput1"
									placeholder="Vui lòng nhập tên sản phẩm" value="${sp.size}" />

									<input name="hangProduct" type="hidden" class="form-control"
									id="exampleFormControlInput1"
									placeholder="Vui lòng nhập tên sản phẩm" value="${sp.hang}" />

									<input name="tenbutton" type="hidden" class="form-control"
									id="exampleFormControlInput1"
									placeholder="Vui lòng nhập tên sản phẩm" value="${btnStatus}" />

									<button name="btnSale" id="saleProduct" type="submit">
										<img width="30" height="30" alt=""
											src="<c:url value="/resources/images/trolley.png"/>">
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
			<h2 style="font-size: 20px">CHI TIẾT KHUYẾN MÃI</h2>
			<form class="row g-3" action="khuyenmaisp.htm">

				<input name="makm" type="hidden" class="form-control"
					id="exampleFormControlInput1"
					placeholder="Vui lòng nhập tên sản phẩm" value="${product.makm}" />
				<div class="col-md-4">
					<label for="exampleFormControlInput1" class="form-label">Tên
						khuyến mãi</label> <input name="tenKMSP" type="text" class="form-control"
						id="exampleFormControlInput1"
						placeholder="Vui lòng nhập tên khuyến mãi"
						value="${product.tenkm}" />
				</div>



				<div class="col-md-4">
					<label for="exampleFormControlInput1" class="form-label">Ngày
						bắt đầu</label> <input name="ngaybatdauKM" type="text"
						class="form-control" id="exampleFormControlInput1"
						placeholder="Vui lòng nhập ngày bắt đầu"
						value=<f:formatDate value="${product.ngaybatdau}" pattern="dd/MM/yyyy" />>
				</div>
				<div class="col-md-4">
					<label for="exampleFormControlInput1" class="form-label">Ngày
						kết thúc</label> <input name="ngayketthucKM" type="text"
						class="form-control" id="exampleFormControlInput1"
						placeholder="Vui lòng nhập ngày kết thúc"
						value=<f:formatDate value="${product.ngayketthuc}" pattern="dd/MM/yyyy" />>
				</div>








				<useBean id="pagedListHolder" scope="request"
					type="org.springframework.beans.support.PagedListHolder" />
				<c:url value="/khuyenmaisp.htm" var="pagedLink">
					<c:param name="p2" value="~" />
				</c:url>



				<table class="table">
					<thead>
						<tr>
							<th scope="col">Tên Sản Phẩm</th>
							<th scope="col">Phần trăm KM</th>
							<th scope="col">Tồn Kho</th>
							<th scope="col">Giá</th>
							<th scope="col">Size</th>
							<th scope="col">Hãng</th>
							<th scope="col">Xóa</th>
						</tr>
					</thead>
					<tbody id="table_products">
						<c:forEach items="${pagedListHolderCTKM.pageList}" var="sp">

							<tr>

								<td>${sp.tensp}</td>
								<td>${sp.ptkm}</td>
								<td>${sp.soluong}</td>
								<td>${sp.gia}</td>
								<td>${sp.size}</td>
								<td>${sp.hang}</td>

								<form>
									<td><input name="masp" type="hidden" class="form-control"
										id="exampleFormControlInput1"
										placeholder="Vui lòng nhập tên sản phẩm" value="${sp.masp}" />
										<input name="tenbutton" type="hidden" class="form-control"
										id="exampleFormControlInput1"
										placeholder="Vui lòng nhập tên sản phẩm" value="${btnStatus}" />
										<button name="btnDeletePD" id="saleProduct" type="submit">
											<img width="30" height="30" alt=""
												src="<c:url value="/resources/images/delete.png"/>">
										</button></td>
								</form>


							</tr>
						</c:forEach>
					</tbody>
				</table>
				<tg:paging pagedListHolder="${pagedListHolderCTKM}"
					pagedLink="${pagedLink}" />

				<div class="col-12">

					<button name="${btnStatus}" class="btn btn-primary">SAVE</button>
					${btnStatus}

				</div>

			</form>
			${message1}


		</div>

		<div class="bg-light p-5 rounded">
			<h2 style="font-size: 20px">DANH SÁCH KHUYẾN MÃI</h2>
			${message}
			<useBean id="pagedListHolder" scope="request"
				type="org.springframework.beans.support.PagedListHolder" />
			<c:url value="/khuyenmaisp.htm" var="pagedLink">
				<c:param name="p1" value="~" />
			</c:url>
			<div class="d-flex flex-row justify-content-between">
				<div>
					<span id="result1"></span>

					<form class="d-inline-flex">
						<input name="tenbutton" type="hidden" class="form-control"
							id="exampleFormControlInput1"
							placeholder="Vui lòng nhập tên sản phẩm" value="${btnStatus}" />
						<input name="searchInputKMSP" id="searchInput"
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
						<th scope="col">Ngày bắt đầu</th>
						<th scope="col">Ngày kết thúc</th>
						<th scope="col">Chỉnh sửa</th>
						<th scope="col">Xóa</th>
					</tr>
				</thead>
				<tbody id="table_products">
					<c:forEach items="${pagedListHolderKM.pageList}" var="sp">

						<tr>

							<td>${sp.tenkm}</td>
							<td><f:formatDate value="${sp.ngaybatdau}"
									pattern="dd/MM/yyyy" /></td>
							<td><f:formatDate value="${sp.ngayketthuc}"
									pattern="dd/MM/yyyy" /></td>

							<form>
								<td><input name="makm" type="hidden" class="form-control"
									id="exampleFormControlInput1"
									placeholder="Vui lòng nhập tên sản phẩm" value="${sp.makm}" />
									<button name="btnEditMakm" id="saleProduct" type="submit">
										<img width="30" height="30" alt=""
											src="<c:url value="/resources/images/edit.png"/>">
									</button></td>
							</form>


							<form>
								<td><input name="makm" type="hidden" class="form-control"
									id="exampleFormControlInput1"
									placeholder="Vui lòng nhập tên sản phẩm" value="${sp.makm}" />
									<button name="btnDeleteMakm" id="saleProduct" type="submit">
										<img width="30" height="30" alt=""
											src="<c:url value="/resources/images/delete.png"/>">
									</button></td>
							</form>


						</tr>
					</c:forEach>
				</tbody>
			</table>
			<tg:paging pagedListHolder="${pagedListHolderKM}"
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


