<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author"
	content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
<meta name="generator" content="Hugo 0.80.0">
<title>Login</title>



<!-- Bootstrap core CSS -->
<!-- <link href="../assets/dist/css/bootstrap.min.css" rel="stylesheet"> -->
<link
	href="<c:url value='/resources/assets/dist/css/bootstrap.min.css' />"
	rel="stylesheet">

<style>
body {
	background-image: linear-gradient(#6A181E, #6A181D);
}

#box {
	border: 1px solid rgb(200, 200, 200);
	box-shadow: rgba(0, 0, 0, 0.1) 0px 5px 5px 2px;
	background: rgba(200, 200, 200, 0.1);
	border-radius: 4px;
	top: 50px;
}

h2 {
	text-align: center;
	color: #fff;
}
</style>


<!-- Custom styles for this template -->
<!-- <link href="/assets/dist/starter-template.css" rel="stylesheet"> -->
<link
	href="<c:url value='/resources/assets/dist/starter-template.css' />"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.css"
	rel="stylesheet">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>


</head>
<body>


	<div class="container-fluid">
		<div class="row-fluid">
			<div class="col-md-offset-4 col-md-4" id="box">
				<h2>CỬA HÀNG H&M</h2>
				<hr>
				<form class="form-horizontal" action="login.htm" method="post"
					id="login_form">
					<fieldset>
						<div class="form-group">
							<div class="col-md-12">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-user"></i></span> <input name="login"
										placeholder="Username" class="form-control" type="text">
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="glyphicon glyphicon-lock"></i></span> <input name="password"
										placeholder="Password" class="form-control" type="Password">
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">
								<button name="dangnhap" type="submit" class="btn btn-md btn-danger pull-right">Đăng
									nhập</button>
									<p style="color:white;">${message}</p>
									
							</div>
							
							
						</div>




					</fieldset>
				</form>
			</div>
		</div>
	</div>

	<!-- <script src="../assets/dist/js/bootstrap.bundle.min.js"></script> -->
	<script
		src="<c:url value='/resources/assets/dist/js/bootstrap.bundle.min.js' />"></script>
	<script src="js/jquery-1.11.1.min.js"></script>


</body>
</html>