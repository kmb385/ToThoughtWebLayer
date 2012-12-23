$(document).ready(function() {
	var application = new Application();
	application.bindLogo();
	application.setFocus();
});

function Application(){
	
}

Application.prototype.bindLogo = function(){
	$("#logo").draggable({
		revert : true,
		stop : function(event, ui) {
			if(ui.position.left > $("#login").position().left){
				$("#login").fadeIn(700).css("display", "inline-block");
			}
		}
	});	
};

Application.prototype.setFocus = function(){
	var $errors = $(".error");
	
	if($errors.length > 0){
		$("html, body").animate({
			scrollTop : $errors.eq(0).position().top
		}, "slow");
		return;
	}
	
	var $username = $("input[name='j_username']");

	if($username.length >0){
		$username.focus();
	}
};
 
