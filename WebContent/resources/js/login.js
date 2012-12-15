$(document).ready(function() {
	$("#logo").draggable({
		revert : true,
		stop : function(event, ui) {
			if(ui.position.left > $("#login").position().left){
				$("#login").fadeIn(700).css("display", "inline-block");
			}
		}
	});
	
	$("input:first").focus();
});
