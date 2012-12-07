$(document).ready(function() {
	$("#add-detail").click(function() {
		var value = $("#details-input").val();
		var detail = $("<li />");
		
		var text = $("<div />", {
			"class" : "float-left"
		}).html(value);
		
		var deleteBtn = $("<div />", {
			"class" : "small-delete-btn h-margin-3 float-left",
			click : function() {
				$(this).parent().remove();
			}
		});

		var hiddenDetail = $("<input />", {
			type : "hidden",
			name : "degreeDetails",
			value : value
		});
		
		detail.append(text);
		detail.append(deleteBtn);
		detail.append(hiddenDetail);
		
		$("#details").append(detail);
		$("#details").append(hiddenDetail);
		$("#details-input").val("").focus();
	});

	$(".date-picker").datepicker();
});
