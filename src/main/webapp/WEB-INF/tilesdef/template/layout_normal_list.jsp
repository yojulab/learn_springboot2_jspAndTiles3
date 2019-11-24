<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<tiles:insertAttribute name="layout.header" />
<title><tiles:getAsString name="layout.title" /></title>
</head>
<body>
	<!-- .Navigation -->
	<tiles:insertAttribute name="layout.menuTop" />
	<!-- /.Navigation -->

	<!-- Main Container -->
	<div role="main" class="container">
		<form class="needs-validation" novalidate method="POST">
			<sec:csrfInput />
			<tiles:insertAttribute name="layout.body" />
			<tiles:insertAttribute name="layout.pagination" />
		</form>
	</div>
	<!-- /#Main Container -->

	<!-- .footer -->
	<footer class="footer">
		<tiles:insertAttribute name="layout.footer" />
	</footer>
	<!-- /.footer -->
</body>
<tiles:insertAttribute name="layout.scripter" />
</html>
