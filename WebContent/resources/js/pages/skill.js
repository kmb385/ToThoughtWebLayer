$(document).ready(function() {
	$(".tab").click(function() {
		$(".tab").removeClass("active");
		$(this).addClass("active");
		var url = $(this).prop("href");
		$.post(url, function(data) {
			appendDetails(data);

			$(".more").remove();
			if (data.length > 0) {
				var moreUrl = url.substring(0, url.indexOf("/detailpage/"));
				createMoreLink(moreUrl);
			}
		}, 'json');

		return false;
	});

	$(document).on("click", ".more", function() {
		var url = $(this).prop("href");
		$.post(url, function(data) {
			appendDetails(data);
			$(".more").remove();
		}, 'json');
		return false;
	});
});

function appendDetails(data) {
	var details = [];
	var $detailsContainer = $("#skill-details");
	$detailsContainer.empty();

	$.each(data, function(index, element) {
		var li = $("<li></li>", {
			"class" : "v-margin-5"
		});
		var a = $("<a></a>", {
			href : (element.url || element.link),
			target : "_blank",
			"class" : "commit"
		}).html(element.title);
		var span = $("<span></span>", {
			"class" : "subtle font-small"
		}).html(element.creation_date || element.createdDt);
		li.append(a).append("&nbsp;").append(span);
		details.push(li);
	});

	$detailsContainer.append(details);
}

function createMoreLink(url) {
	var $detailsContainer = $("#skill-details");
	var moreLink = $('<a/>', {
		href : url,
		"class" : "more"
	}).html("View More");
	$detailsContainer.after(moreLink);
}