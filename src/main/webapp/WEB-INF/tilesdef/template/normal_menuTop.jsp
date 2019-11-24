<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<form method="POST">
	<nav
		class="navbar navbar-expand fixed-top navbar-light bg-white shadow">
		<a class="navbar-brand" href="<c:url value='/' />">강신<small
			class="text-muted">(강의 신)</small></a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<!-- User Expression -->
		<sec:authentication property="principal" var="principalBean" />
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<sec:authorize access="hasAnyRole('ROLE_SYSTEM_MANAGER')">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="dropdownSystem"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">수강 관리</a>
						<div class="dropdown-menu" aria-labelledby="dropdownSystem">
							<a class="dropdown-item" href="<c:url value='/project/list' />">프로젝트
								관리</a> <a class="dropdown-item"
								href="<c:url value='/multiboardgroup/list' />">고객센터</a>
						</div></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" id="dropdownSystem"
						role="button" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">강의 관리</a>
						<div class="dropdown-menu" aria-labelledby="dropdownSystem">
							<a class="dropdown-item"
								href="<c:url value='/messagegroup/list' />">메세지 그룹 관리</a> <a
								class="dropdown-item"
								href="<c:url value='/organizationCourse/list' />">과정 관리</a>
						</div></li>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#" role="button"
						id="dropdownSystem" data-toggle="dropdown" aria-haspopup="true"
						aria-expanded="false">시스템 관리</a>
						<div class="dropdown-menu" aria-labelledby="dropdownSystem">
							<a class="dropdown-item"
								href="<c:url value='/organization/list' />">기관 관리</a> <a
								class="dropdown-item" href="<c:url value='/member/list' />">사용자
								관리</a> <a class="dropdown-item"
								href="<c:url value='/messageform/list' />">메세지 형식 관리</a> <a
								class="dropdown-item"
								href="<c:url value='/messageMember/list' />">메세지 대상자 관리</a> <a
								class="dropdown-item" href="<c:url value='/commonCode/list' />">공통코드
								관리</a> <a class="dropdown-item"
								href="<c:url value='/multiboardgroup/list' />">여러 게시판 관리</a>
						</div></li>
				</ul>
			</sec:authorize>
		</div>
		<!-- dropdown-user -->
		<div class="btn-group">
			<button class="btn btn-outline-info dropdown-toggle" type="button"
				id="dropdownMenu" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false">
				<sec:authorize access="isAnonymous()">
				로그인 
          	</sec:authorize>
				<sec:authorize access="isAuthenticated()">
          		${principalBean.memberName } 
          	</sec:authorize>
			</button>
			<div class="dropdown-menu  dropdown-menu-right"
				aria-labelledby="dropdownMenu">
				<a class="dropdown-item" href="<c:url value='/member/read' />">
					내 정보 관리</a> <a class="dropdown-item"
					href="<c:url value='/lectureMember/list' />"> 신청 강좌 보기</a>
				<div class="dropdown-divider"></div>
				<sec:authorize access="hasAnyRole('ROLE_SYSTEM_MANAGER')">
					<a class="dropdown-item" href="<c:url value='/admin' />">Admin
						Site</a>
					<div class="dropdown-divider"></div>
				</sec:authorize>
				<sec:authorize access="isAnonymous()">
					<a class="dropdown-item" href="<c:url value='/security/edit' />">로그인
						또는 가입</a>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<a class="dropdown-item" href="<c:url value='/logout' />">로그아웃
					</a>
				</sec:authorize>
			</div>
		</div>
		<!-- /.dropdown-user -->
	</nav>
</form>
