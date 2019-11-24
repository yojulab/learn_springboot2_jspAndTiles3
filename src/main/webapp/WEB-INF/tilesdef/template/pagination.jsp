<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!-- Modal -->        
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
</div>
<!-- /.modal -->	    

<c:set var="pagination" value="${resultMap.pagination}" />
<c:if test="pagination != null">
<div class="row">
	<div class="col">총 갯수 : ${pagination.totalCount}</div>
	<div class="col">
		<nav aria-label="Page navigation">
			<ul class="pagination justify-content-end">
				<li
					class="page-item ${pagination.currentPage > 1 ? 'disabled' : ''}"><button
						class="page-link"
						formaction="<c:url value='/commonCode/list?currentPage=1' />"
						tabindex="-1">맨 처음</button></li>
				<li
					class="page-item ${pagination.currentBlock > 1 ? 'disabled' : ''}"><button
						class="page-link"
						formaction="<c:url value='/commonCode/list?currentPage=${pagination.previousPage}' />">&laquo;</button></li>
				<c:forEach var="pageNumber" begin="${pagination.blockStart}"
					end="${pagination.blockEnd}">
					<li
						class="page-item ${pageNumber==pagination.currentPage ? 'disabled' : ''}"><button
							class="page-link"
							formaction="<c:url value='/commonCode/list?currentPage=${pageNumber}' />">${pageNumber}</button>
					</li>
				</c:forEach>
				<li
					class="page-item ${pagination.currentBlock <= pagination.totalBlock ? 'disabled' : ''}"><button
						class="page-link"
						formaction="<c:url value='/commonCode/list?currentPage=${pagination.nextPage}' />">&raquo;</button></li>
				<li
					class="page-item ${pagination.currentPage < pagination.totalPage ? 'disabled' : ''}"><button
						class="page-link"
						formaction="<c:url value='/commonCode/list?currentPage=${pagination.totalPage}' />">맨
						끝</button></li>
			</ul>
		</nav>
	</div>
</div>
</c:if>
