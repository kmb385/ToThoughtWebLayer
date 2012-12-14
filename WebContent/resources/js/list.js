(function($) {
	$.fn.ttSimpleList = function(param, buttonClass) {
		var input = this.find("textarea");
		var list = this.find("ul");
		var button = this.find("." + (buttonClass || "add-item"));

		button.click(function() {
			if (input.val()) {
				var item = $("<li />");
				var hiddenInput = $("<input />", {
					type : "hidden",
					name : param,
					value : input.val()
				});

				var text = $("<span />").html(input.val());

				var deleteBtn = $("<a />", {
					"class" : "small-delete-btn h-margin-3",
					click : function() {
						$(this).parent().remove();
					}
				});

				item.append(text);
				item.append(deleteBtn);
				item.append(hiddenInput);

				list.append(item);
				input.val("").focus();

			}
		});
	};
})(jQuery);
