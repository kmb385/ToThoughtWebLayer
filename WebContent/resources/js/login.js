$(document).ready(function() {
	$("#logo").draggable({
		revert : true,
		stop : function(event, ui) {
			if(ui.position.left > $("#login").position().left){
				$("#login").fadeIn(700).css("display", "inline-block");
			}
		}
	});	
	var $username = $("input[name='j_username']");
	if($username.length >0){
		$username.focus();
	}
});
