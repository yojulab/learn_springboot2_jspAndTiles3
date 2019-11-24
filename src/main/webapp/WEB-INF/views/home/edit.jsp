<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<input type="hidden" name="ORGANIZATION_COURSE_SEQ"
	value="${resultMap.ORGANIZATION_COURSE_SEQ}" disabled>
<input type="hidden" name="kindOf" value="TITLE">
<input type="hidden" name="searchText" value="${paramMap.searchText }">

<div class="form-group form-row">
	<div class="col">
		<label>모집 시작일시<spring:message code="empty.temp" /></label> <input
			class="form-control" type="date" name="RECRUIT_START_DATE" max="24"
			value="${resultMap.RECRUIT_START_DATE}" placeholder="" readonly/>
		<div class="invalid-tooltip">
			<spring:message code="validation.inputValue" />
		</div>
	</div>
	<div class="col">
		<label>모집 종료일시<spring:message code="empty.temp" /></label> <input
			class="form-control" type="date" name="RECRUIT_END_DATE" max="24"
			value="${resultMap.RECRUIT_END_DATE}" placeholder="" readonly/>
		<div class="invalid-tooltip">
			<spring:message code="validation.inputValue" />
		</div>
	</div>
	<div class="col">
		<label>모집 인원수<spring:message code="empty.temp" /></label> <input
			class="form-control" type="number" name="COUNT"
			value="${resultMap.COUNT}" placeholder="" readonly/>
		<div class="invalid-tooltip">
			<spring:message code="validation.inputValue" />
		</div>
	</div>
</div>
<div class="form-group form-row">
	<div class="col">
		<label>강의 시작일시<spring:message code="empty.temp" /></label> <input
			class="form-control" type="date" name="START_DATE" max="24"
			value="${resultMap.START_DATE}" placeholder="" readonly/>
		<div class="invalid-tooltip">
			<spring:message code="validation.inputValue" />
		</div>
	</div>
	<div class="col">
		<label>강의 종료일시<spring:message code="empty.temp" /></label> <input
			class="form-control" type="date" name="END_DATE" max="24"
			value="${resultMap.END_DATE}" placeholder="" readonly/>
		<div class="invalid-tooltip">
			<spring:message code="validation.inputValue" />
		</div>
	</div>
</div>
<div class="form-group form-row">
	<div class="col">
		<label>강의 시작시각<spring:message code="empty.temp" /></label> <input
			class="form-control" type="time" name="START_TIME" max="24"
			value="${resultMap.START_TIME}" placeholder="" readonly/>
		<div class="invalid-tooltip">
			<spring:message code="validation.inputValue" />
		</div>
	</div>
	<div class="col">
		<label>강의 종료시각<spring:message code="empty.temp" /></label> <input
			class="form-control" type="time" name="END_TIME" max="24"
			value="${resultMap.END_TIME}" placeholder="" readonly/>
		<div class="invalid-tooltip">
			<spring:message code="validation.inputValue" />
		</div>
	</div>
	<div class="col">
		<label>전체 강의 시간<spring:message code="empty.temp" /></label> <input
			class="form-control" type="number" name="TIME"
			value="${resultMap.TIME}" placeholder="" readonly/>
		<div class="invalid-tooltip">
			<spring:message code="validation.inputValue" />
		</div>
	</div>
</div>
<div class="form-group form-row">
	<div class="col">
		<label>강의 / 강연명<spring:message code="empty.temp" /></label> <input
			class="form-control" type="text" name="TITLE"
			value="${resultMap.TITLE}" placeholder="" readonly/>
		<div class="invalid-tooltip">
			<spring:message code="validation.inputValue" />
		</div>
	</div>
</div>
<div class="form-group form-row">
	<div class="col">
		<label>내용<spring:message code="empty.temp" /></label> <textarea
			class="form-control" name="CONTENT"
			placeholder="" readonly>${resultMap.CONTENT}</textarea>
		<div class="invalid-tooltip">
			<spring:message code="validation.inputValue" />
		</div>
	</div>
</div>
<input type="hidden" name="COURSE_SEQ" value="${resultMap.COURSE_SEQ}">

<div class="row justify-content-between">
	<div class="col">
		<c:choose>
			<c:when test="${resultMap.MEMBER_SEQ != null}">
				<button class="btn btn-success">신청완료</button>
			</c:when>
			<c:otherwise>
				<button class="btn btn-primary"
					formaction="<c:url value='/home/insert' />">신청하기</button>
			</c:otherwise>
		</c:choose>
		<button class="btn btn-outline-info" formaction="<c:url value='/' />">
			<spring:message code="button.list" />
		</button>
	</div>
	<div class="col text-right">
		<%-- <button type="button" class="btn btn-secondary" data-dismiss="modal">
			<spring:message code="button.close" />
		</button> --%>
	</div>
</div>
