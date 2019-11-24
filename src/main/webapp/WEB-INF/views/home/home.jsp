<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<div class="form-group form-row justify-content-between">
	<div class="col"></div>
	<div class="col text-right">
		<div class="input-group">
			<input type="hidden" name="kindOf" value="TITLE"> <input
				type="text" class="form-control" placeholder="..." name="searchText"
				value="${paramMap.searchText }">
			<div class="input-group-append">
				<button class="btn btn-outline-info"
					formaction="<c:url value='/' />">
					button.search
				</button>
			</div>
		</div>
	</div>
</div>

<div class="container">
	<!-- Example row of columns -->
	<div class="row">
		<c:set var="resultListOrganizationCourse"
			value="${resultMap.resultListOrganizationCourse}"></c:set>
		<c:forEach items="${resultListOrganizationCourse}" var="resultData"
			varStatus="loop">
			<div class="col-md-4">
				<h2>${resultData.TITLE}</h2>
				<p>${resultData.CONTENT}</p>
				<p>
					<a class="btn btn-info"
						href="<c:url value="/home/read?ORGANIZATION_COURSE_SEQ=${resultData.ORGANIZATION_COURSE_SEQ}" />"
						role="button">View details »</a>
					<c:if test="${resultData.MEMBER_SEQ != null}">
						<button type="button" class="btn btn-success btn-sm">신청완료</button>
					</c:if>
				</p>
			</div>
		</c:forEach>
	</div>
</div>
<!-- /container -->
