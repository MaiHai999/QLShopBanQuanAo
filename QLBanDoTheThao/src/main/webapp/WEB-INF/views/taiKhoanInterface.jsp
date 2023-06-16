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
				<h2>TÀI KHOẢN</h2>

			</div>
			${message}
			<form class="row g-3">
				<input name="matk" type="hidden" class="form-control"
					id="exampleFormControlInput1"
					placeholder="Vui lòng nhập mã tài khoản" value="${taikhoan.matk}" />
				<input name="trangthai" type="hidden" class="form-control"
					id="exampleFormControlInput1"
					placeholder="Vui lòng nhập mã tài khoản"
					value="${taikhoan.trangthai}" /> <input name="manv" type="hidden"
					class="form-control" id="exampleFormControlInput1"
					placeholder="Vui lòng nhập mã tài khoản" value="${taikhoan.manv}" />
				<div class="col-md-6">
					<label for="exampleFormControlInput1" class="form-label">Tên
						đăng nhập</label> <input name="login1" type="text" class="form-control"
						id="exampleFormControlInput1"
						placeholder="Vui lòng nhập tên đăng nhập"
						value="${taikhoan.login}" />
				</div>
				<div class="col-md-6">
					<label for="exampleFormControlInput1" class="form-label">Mật
						khẩu</label> <input name="matkhau" type="password" class="form-control"
						id="exampleFormControlInput1" placeholder="Vui lòng nhập mật khẩu"
						value="" />
				</div>
				<div>Lưu ý : vì lý do bảo mật nên bạn không thể nhìn thấy mật
					khẩu , nếu bạn quên mật khẩu thì bạn chỉ có thể đặt lại mật khẩu
					mới</div>
				<div class="col-12">
					<button name="btnThietLap" class="btn btn-primary">THIẾT
						LẬP</button>
					<button name="btnKhoa" class="btn btn-primary">KHÓA TÀI
						KHOẢN</button>
					<button name="btnMoKhoa" class="btn btn-primary">MỞ KHÓA
						TÀI KHOẢN</button>
				</div>
				<div class="col-12">
					<button name="btnBack" class="btn btn-primary">BACK</button>
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


