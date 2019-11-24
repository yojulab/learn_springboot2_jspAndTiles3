<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- common JavaScript -->
<script type="text/javascript"
	src="<c:url value='/resources/js/common.js' />"></script>

<!-- Page-Level Demo Scripts - Tables - Use for reference -->
<script>
	$(document).ready(function() {
		$("#selectall").change(function() {
			$(".checkbox").prop('checked', $(this).prop("checked"));
		});

		$(".viewPopup").on("click", function() {
			var urlTemp = $(this).attr("formaction");
			var url;

			// Not or be parameter  
			if (urlTemp.includes("?"))
				url = urlTemp.replace("?", "/popup?");
			else
				url = urlTemp.concat("/popup");

			common.layerPopup(url, "#myModal");
		});

		$(".btn-primary").on("click", function() {
			var forms = document.getElementsByClassName('needs-validation');
			// Loop over them and prevent submission
			var validation = Array.prototype.filter.call(forms, function(form) {
				form.addEventListener('submit', function(event) {
					if (form.checkValidity() === false) {
						event.preventDefault();
						event.stopPropagation();
					}
					form.classList.add('was-validated');
				}, false);
			});
		});
	});

	$(document).keypress(function(event) {
		var keycode = (event.keyCode ? event.keyCode : event.which);
		if (keycode == '13') {
			/*         alert('You pressed a "enter" key in somewhere');
			 $("#eventEnterKey").click();
			 */}
	});

	//Example starter JavaScript for disabling form submissions if there are invalid fields
</script>
