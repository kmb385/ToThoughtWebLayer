(function($) {
	$.fn.ttTagEditor = function(options, settings) {
		if (typeof options == "object") {
			settings = options;
		} else if (typeof options === "string") {

			var elements = this.each(function() {
				var data = $(this).data("_ttTags");

				if (data) {
					if (options == "load") {
						data.load(settings);
					}
				}
			});
			return elements;
		}

		return this.each(function() {
			var $elem = $(this);

			var $settings = $.extend({}, $.fn.ttTagEditor.defaultSettings,
					settings || {});

			var tagEditor = new TagEditor($elem, $settings);
			tagEditor.init();
			$elem.data("_ttTags", tagEditor);
		});
	};

	$.fn.ttTagEditor.defaultSettings = {
		background : '#FFFFFF',
		max_tags : 4,
		param : "tags"
	};

	function TagEditor($elem, settings) {
		this.$elem = $elem;
		this.settings = settings;
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
			// pretty static should take options
			this.$elem.css("background", this.settings.background);
			this.$elem.addClass("clearfix");
			$("<input/>").attr({
				"class" : "tag-input"
			}).appendTo(this.$elem);
			this.$input = this.$elem.find(".tag-input");
			this.addHiddenInput();
			this.bindKeyPress();
			this.bindClickMonitor();
		},
		bindKeyPress : function() {
			var that = this;
			this.$elem
					.keypress(function(event) {
						if (event.which == 32
								&& that.tagCount < that.settings.max_tags) {
							that.search(that.$input.val());
						} else if (that.tagCount >= that.settings.max_tags) {
							return false;
						}
					});
		},
		search : function(token) {
			var that = this;
			if (token && !this.isTagOptionsDisplayed) {
				$.post(ttRoot + "/tag/find/" + token, function(data) {
					if (data.length > 1) {
						that.createTagOptions(data);
					} else if (data.length == 1) {
						var tag = data[0];
						that.addTag(tag.tagId, tag.name);
					} else {
						that.addTag(token, token);
					}
				}, "json");
			}
		},
		createTagOptions : function(tags) {
			var that = this;
			this.createOptionFrame();
			var $tagOptions = that.$elem.find(".tag-options");

			$.each(tags, function(index, tag) {
				var $tmpTag = that.createTag(tag.tagId, tag.name);
				$tmpTag.appendTo($tagOptions);
			});
			var parent = $(that.$elem).parent();
			$("html, body").animate({
				scrollTop : $(document).height()
			}, "slow");
		},
		createOptionFrame : function() {
			if (!this.isTagOptionsDisplayed) {
				$("<div/>", {
					"class" : "tag-options"
				}).appendTo(this.$elem);

				this.isTagOptionsDisplayed = true;
			}
		},
		createTag : function(id, name) {
			var that = this;
			return $("<div/>", {
				"class" : "post-tag clearfix",
				text : name,
				data : {
					id : id,
					name : name
				},
				click : function() {
					var $tag = $(this);

					if ($tag.parents(".tag-options").length > 0
							&& that.tagCount < that.settings.max_tags) {
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
		cleanUI : function() {
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
		addHiddenInput : function() {
			this.$hiddenInput = $('<input>').attr({
				type : 'hidden',
				name : this.settings.param
			});
			this.$hiddenInput.appendTo(this.$elem.parents('form'));
		},
		addHiddenInputData : function(value) {
			//TODO: Index of Bug preventing addition of some tags.  Create regex.
			if (this.$hiddenInput.val().split(",").indexOf(value + '') == -1) {
				this.$hiddenInput.val(this.$hiddenInput.val()
						+ ((this.$hiddenInput.val()) ? "," : "") + value);
			}
		},
		removeHiddenInputData : function(value) {
			//TODO: Index of Bug preventing addition of some tags. Find a good regex.
			if (this.$hiddenInput.val().split(",").indexOf(value + '') != -1) {
				var dataArray = this.$hiddenInput.val().split(",");
				for ( var i = 0; i < dataArray.length; i++) {
					if (dataArray[i] == value) {
						dataArray.splice(i, 1);
						this.$hiddenInput.val(dataArray.join(","));
					}
				}
			}
		},
		bindClickMonitor : function() {
			var that = this;
			$(document).mouseup(function(e) {
				var $tagOptions = that.$elem.find(".tag-options");

				if ($tagOptions.has(e.target).length === 0) {
					that.isTagOptionsDisplayed = false;
					$tagOptions.remove();
				}
			});
		},
		addTag : function(id, name, cleanUI) {
			var $newTag = this.createTag(id, name);
			this.addHiddenInputData($newTag.data("id"));
			$newTag.prependTo(this.$elem);
			if(cleanUI !== false){
				this.cleanUI();				
			}
			this.tagCount++;
		},
		load : function(settings) {
			var that = this;
			var _url = settings.url || settings || this.settings.url;

			$.post(_url, function(data) {
				data = ($.isArray(data)) ? data : [ data ];
				for ( var i = 0; i < data.length; i++) {
					if (data) {
						that.addTag(data[i].tagId, data[i].name, false);
					}
				}
			}, "json");
		}
	};

})(jQuery);