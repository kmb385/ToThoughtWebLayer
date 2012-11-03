function MyTinyMce(id){
	this.id = id;
}

MyTinyMce.prototype.load = function(){
	var that = this;
	tinyMCE.init({
		mode: "exact",
		elements: that.id,
		theme: "advanced",
		plugins : "autolink,lists,save,advimage,advlink,inlinepopups,preview,contextmenu,directionality, noneditable,nonbreaking,template,syntaxhl,preelementfix",

		// Theme options
        theme_advanced_buttons1 : "save,|,bold,italic,underline,strikethrough,|,justifyleft,justifycenter,justifyright,justifyfull,|,formatselect,fontselect,fontsizeselect,syntaxhl",
        theme_advanced_buttons2 : "bullist,numlist,|,outdent,indent,blockquote,nonbreaking,|,undo,redo,|,link,unlink,anchor,image,cleanup,help,code,|,preview,|,forecolor,backcolor",
        theme_advanced_toolbar_location : "top",
        theme_advanced_toolbar_align : "left",
        
        // Skin options
        skin : "o2k7",
        skin_variant : "silver",
        
        //Allow Tabs
        nonbreaking_force_tab: true,

        // Drop lists for link/image/media/template dialogs
        template_external_list_url : "js/template_list.js",
        external_link_list_url : "js/link_list.js",
        external_image_list_url : "js/image_list.js",
        media_external_list_url : "js/media_list.js",
	});
};