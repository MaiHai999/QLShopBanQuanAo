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
<title>NHÂN VIÊN</title>
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
				<h2>NHÂN VIÊN</h2>

			</div>
			${message}
			<form class="row g-3" action="nhanvien.htm">
				<input name="manv" type="hidden" class="form-control"
					id="exampleFormControlInput1"
					placeholder="Vui lòng nhập mã nhân viên" value="${nhanvien.manv}" />
				<input name="matk" type="hidden" class="form-control"
					id="exampleFormControlInput1"
					placeholder="Vui lòng nhập mã nhân viên" value="${nhanvien.matk}" />
				<div class="col-md-4">
					<label for="exampleFormControlInput1" class="form-label">Họ</label>
					<input name="honv" type="text" class="form-control"
						id="exampleFormControlInput1"
						placeholder="Vui lòng nhập họ nhân viên" value="${nhanvien.honv}" />
				</div>
				<div class="col-md-4">
					<label for="exampleFormControlInput1" class="form-label">Tên</label>
					<input name="tennv" type="text" class="form-control"
						id="exampleFormControlInput1"
						placeholder="Vui lòng nhập tên nhân viên"
						value="${nhanvien.tennv}" />
				</div>
				<div class="col-md-4">
					<label for="exampleFormControlInput1" class="form-label">CMND</label>
					<input name="cmnd" type="text" class="form-control"
						id="exampleFormControlInput1"
						placeholder="Vui lòng nhập CMND nhân viên"
						value="${nhanvien.cmnd}" />
				</div>
				<div class="col-md-4">
					<label for="exampleFormControlInput1" class="form-label">Email</label>
					<input name="email" type="text" class="form-control"
						id="exampleFormControlInput1"
						placeholder="Vui lòng nhập email nhân viên"
						value="${nhanvien.email}" />
				</div>
				<div class="col-md-4">
					<label for="exampleFormControlInput1" class="form-label">SĐT</label>
					<input name="sdt" type="text" class="form-control"
						id="exampleFormControlInput1"
						placeholder="Vui lòng nhập SĐT nhân viên" value="${nhanvien.sdt}" />
				</div>
				<div class="col-md-4">
					<label for="exampleFormControlInput1" class="form-label">Giới
						tính</label> <input name="gioitinh" type="text" class="form-control"
						id="exampleFormControlInput1"
						placeholder="Vui lòng nhập giới tính nhân viên"
						value="${nhanvien.gioitinh}" />
				</div>
				<div class="col-md-4">
					<label for="exampleFormControlInput1" class="form-label">Ngày
						sinh</label> <input name="ngaysinh" type="text" class="form-control"
						id="exampleFormControlInput1"
						placeholder="Vui lòng nhập ngày sinh nhân viên"
						value= <f:formatDate value="${nhanvien.ngaysinh}" pattern="dd/MM/yyyy" /> >
				</div>
				<div class="col-md-4">
					<label for="exampleFormControlInput1" class="form-label">Chức
						danh</label> <select name="comboboxTenCD"
						class="form-select form-select-lg mb-6"
						aria-label=".form-select-lg example">
						<option value="NULL" class="form-select form-select-lg mb-6"
							aria-label=".form-select-lg example"></option>
						<c:forEach items="${listCD}" var="cd">
							<option value="${cd.macd}"
								${cd.macd == selectedId ? 'selected' : ''}
								class="form-select form-select-lg mb-6"
								aria-label=".form-select-lg example">${cd.tencd}</option>
						</c:forEach>
					</select>

				</div>



				<div class="col-12">
					<button name="${btnStatus}" class="btn btn-primary">Save</button>
				</div>

			</form>

		</div>




		<div class="bg-light p-5 rounded">
			<useBean id="pagedListHolder" scope="request"
				type="org.springframework.beans.support.PagedListHolder" />
			<c:url value="/nhanvien.htm" var="pagedLink">
				<c:param name="p" value="~" />
			</c:url>
			<div class="d-flex flex-row justify-content-between">
				<div>
					<span id="result1"></span>

					<form class="d-inline-flex">
						<input name="searchInput" id="searchInput"
							class="form-control me-2" type="search" placeholder="Search"
							aria-label="Search">


						<button name="btnsearch" id="searchNV"
							class="btn btn-outline-success" type="submit">Search</button>
					</form>
				</div>




			</div>


			<table class="table">
				<thead>
					<tr>
						<th scope="col">Họ</th>
						<th scope="col">Tên</th>
						<th scope="col">CMND</th>
						<th scope="col">Email</th>
						<th scope="col">SĐT</th>
						<th scope="col">Giới tính</th>
						<th scope="col">Ngày sinh</th>
						<th scope="col">Chỉnh sửa</th>
						<th scope="col">Xóa</th>
						<th scope="col">Tài khoản</th>
					</tr>
				</thead>
				<tbody id="table_nhanvien">
					<c:forEach items="${pagedListHolder.pageList}" var="nv">

						<tr>

							<td>${nv.honv}</td>
							<td>${nv.tennv}</td>
							<td>${nv.cmnd}</td>
							<td>${nv.email}</td>
							<td>${nv.sdt}</td>
							<td>${nv.gioitinh}</td>
							<td> <f:formatDate value="${nv.ngaysinh}" pattern="dd/MM/yyyy" /></td>


							<form>
								<td><input name="manv" type="hidden" class="form-control"
									id="exampleFormControlInput1"
									placeholder="Vui lòng nhập tên sản phẩm" value="${nv.manv}" />
									<button name="btnEditManv" id="saleProduct" type="submit">
										<img width="30" height="30" alt=""
											src="<c:url value="/resources/images/edit.png"/>">
									</button></td>
							</form>


							<form>
								<td><input name="manv" type="hidden" class="form-control"
									id="exampleFormControlInput1"
									placeholder="Vui lòng nhập tên sản phẩm" value="${nv.manv}" />
									<button name="btnDeleteManv" id="saleProduct" type="submit">
										<img width="30" height="30" alt=""
											src="<c:url value="/resources/images/delete.png"/>">
									</button></td>
							</form>


							<form>
								<td><input name="matk" type="hidden" class="form-control"
									id="exampleFormControlInput1"
									placeholder="Vui lòng nhập tên sản phẩm" value="${nv.matk}" />

									<button name="btnTK" id="saleProduct" type="submit">
										<img width="30" height="30" alt=""
											src="<c:url value="/resources/images/account.jpg"/>">
									</button></td>
							</form>

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


