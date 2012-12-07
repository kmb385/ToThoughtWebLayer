(function($) {
	$.fn.ttSimpleList = function(param, buttonClass) {
		var input = this.find("textarea");
		var list = this.find("ul");
		var button = this.find("." + (buttonClass || "add-item"));

		button.click(function() {
			var item = $("<li />");
			var hiddenInput = $("<input />", {
				type : "hidden",
				name : param,
				value : input.val()
			});

			var text = $("<div />", {
				"class" : "float-left"
			}).html(input.val());

			var deleteBtn = $("<div />", {
				"class" : "small-delete-btn h-margin-3 float-left",
				click : function() {
					$(this).parent().remove();
				}
			});

			item.append(text);
			item.append(deleteBtn);
			item.append(hiddenInput);

			list.append(item);
			that.val("").focus();
		});
	};
})(jQuery);
