$(document).ready(function() {
	$("#login").hide();
	$("#logo").draggable({
		revert : true,
		stop : function(event, ui) {
			if(ui.position.left > $("#login").position().left){
				$("#login").fadeIn(500);
			}
			console.log(ui.position.left);
		}
	});

});
