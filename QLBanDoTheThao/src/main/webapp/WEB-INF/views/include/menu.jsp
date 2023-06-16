<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<style>
li:hover {
	color: red;
}
</style>
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
	<div class="container-fluid">
		<a class="navbar-brand">H&M Fashion</a>

		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarsExampleDefault"
			aria-controls="navbarsExampleDefault" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarsExampleDefault">
			<ul class="navbar-nav me-auto mb-2 mb-md-0">
				<li class="nav-item"><a class="nav-link" aria-current="page"
					href="banhang.htm">Sales</a></li>
				<li class="nav-item"><a class="nav-link" aria-current="page"
					href="hoadonban.htm">Sales invoice</a></li>
				<li class="nav-item"><a class="nav-link" aria-current="page"
					href="dieuhuong.htm">Promotion</a></li>
				<li class="nav-item"><a class="nav-link" aria-current="page"
					href="sanpham.htm">Product</a></li>
				<li class="nav-item"><a class="nav-link" aria-current="page"
					href="nhanvien.htm">Employee</a></li>
				<li class="nav-item"><a class="nav-link" aria-current="page"
					href="khachhang.htm">Customer</a></li>
				<li class="nav-item"><a class="nav-link" aria-current="page"
					href="nhaphang.htm">Import goods</a></li>
				<li class="nav-item"><a class="nav-link" aria-current="page"
					href="hoadonnhap.htm">Supplier invoice</a></li>
				<li class="a"><a class="nav-link" aria-current="page"
					href="chart.htm">Chart</a></li>


			</ul>
			<a class="nav-link active" aria-current="page">Chào: ${hoten} </a>

			<form class="d-flex" action="logout.htm">
				<button class="btn btn-outline-success" type="submit">Logout</button>
			</form>
		</div>
	</div>
</nav>
