	(function($) {
		$.fn.ttTagEditor = function() {
			return this.each(function() {
				var $elem = $(this);
				var tagEditor = new TagEditor($elem);
				tagEditor.init();
			});
		};

		function TagEditor($elem) {
			this.$elem = $elem;
			this.$input = {};
			this.$hiddenInput = {};
			this.isTipDisplayed = false;
			this.isTagOptionsDisplayed = false;
			this.tagCount = 0;
			this.tagData = [];
			return this;
		}

		TagEditor.prototype = {
			init : function() {
				//pretty static should take options
				this.$elem.addClass("bg2");
				$("<input/>").attr({
					"class": "tag-input"
				}).appendTo(this.$elem);
				this.$input = this.$elem.find(".tag-input");
				this.addHiddenInput();
				this.bindKeyPress();
			},
			bindKeyPress : function() {
				var that = this;
				this.$elem.keypress(function(event) {
					if (event.which == 32) {
						that.search(that.$input.val());
					}
				});
			},
			search : function(token) {
				var that = this;
				if (token && !this.isTagOptionsDisplayed) {
					$.post("/tag/find/" + token, function(data) {
						if(data.length > 0){
							that.createTagOptions(data);
						}else{
							var $newTag = that.createTag(token,token);
							that.addHiddenInputData($newTag.data("id"));
							$newTag.prependTo(that.$elem);
							that.cleanUI();
						}
					}, "json");
				}
			},
			createTagOptions : function(tags) {
				var that = this;
				this.createOptionFrame();
				
				$.each(tags, function(index, tag) {
					var $tmpTag = that.createTag(tag.tagId, tag.name);
					$tmpTag.appendTo(that.$elem.find(".tag-options"));
				});
			},
			createOptionFrame: function(){
				if(!this.isTagOptionsDisplayed){
					$("<div/>", {
						"class" : "tag-options"
					}).appendTo(this.$elem);
						
					this.isTagOptionsDisplayed = true;
				}
			},
			createTag : function(id, name){
				var that = this;
				return $("<div/>", {
					"class" : "post-tag clearfix",
					text : name,
					data : {id: id, name: name},
					click : function(){
						var $tag = $(this);

						if ($tag.parents(".tag-options").length > 0 && that.tagCount < 4) {
							$tag.prependTo(that.$elem);
							that.addHiddenInputData($tag.data("id"));
							that.tagCount++;
						} else if ($tag.parents(that.$elem).length > 0) {
							that.removeHiddenInputData($tag.data("id"));
							$tag.remove();
							that.tagCount--;
						}
						that.cleanUI();
					}
				});
			},
			cleanUI: function(){
				this.closeTagOptions();
				this.toggleTip();
				this.$input.val("");
				this.$input.focus();		
			},
			closeTagOptions : function() {
				$(".tag-options").remove();
				this.isTagOptionsDisplayed = false;
			},
			showTip : function() {
				if (!this.isTipDisplayed) {
					$("<div/>", {
						"class" : "tag-editor-meta-info",
						text : "Click to Remove"
					}).insertAfter(this.$elem);
					this.isTipDisplayed = true;
				}
			},
			toggleTip : function() {
				if (this.$elem.find(".post-tag").length > 0) {
					this.showTip();
				} else {
					this.$elem.parent().find(".tag-editor-meta-info").remove();
					this.isTipDisplayed = false;
				}
			},
			addHiddenInput: function(){
				this.$hiddenInput = $('<input>').attr({
				    type: 'hidden',
				    name: "tags"
				});
				this.$hiddenInput.appendTo(this.$elem.parents('form'));
			},
			addHiddenInputData: function(value){
				if(this.$hiddenInput.val().indexOf(value) == -1){
					this.$hiddenInput.val(this.$hiddenInput.val() + "," + value);
				}
			},
			removeHiddenInputData: function(value){
				if(this.$hiddenInput.val().indexOf(value) != -1){
					var dataArray = this.$hiddenInput.val().split(",");
					for(var i=0; i < dataArray.length; i++){
						  if(dataArray[i] == value){
							  dataArray.splice(i,1);
							  this.$hiddenInput.val(dataArray.join(","));
						  }
					}
				}
			}
		};

	})(jQuery);