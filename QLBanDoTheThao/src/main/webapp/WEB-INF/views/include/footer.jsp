
<!-- <script src="../assets/dist/js/bootstrap.bundle.min.js"></script> -->
<script
	src="<c:url value='/resources/assets/dist/js/bootstrap.bundle.min.js' />"></script>

<script>
var editor = CKEDITOR.replace( 'messagebody' );
CKFinder.setupCKEditor(ckeditor, '${pageContext.request.contextPath}/resources/ckfinder/');
	
</script>
</body>
</html>