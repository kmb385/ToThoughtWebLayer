	(function($) {
		$.fn.ttRating = function(options, settings) {
			if(typeof options == "object"){
				settings = options;
			}else if(typeof options === "string"){
				
				var elements = this.each(function(){
					var data = $(this).data("_ttRating");
					
					if(data){
						if(options == "load"){
							data.load(settings);
						}
					}
				});
				
				return elements;
			}
			
			return this.each(function() {
				var $elem = $(this);
				
				var $settings = $.extend({}, $.fn.ttRating.defaultSettings, settings || {});
				
				var ratingObject = new RatingObject($elem, $settings);
				ratingObject.init();
				$elem.data("_ttRating", ratingObject);
			});
		};
		
		$.fn.ttRating.defaultSettings = {
				background : '#FFFFFF'
		};

		function RatingObject($elem, settings) {
			this.$elem = $elem;
			this.rating = 0;
			this.$hiddenInput = {};
			this.isInside = false;
			return this;
		}

		RatingObject.prototype = {
			init : function() {
				this.setInitialValue();
				this.createDomElements();
			},
			setInitialValue: function(){
				if(this.$elem.html()){
					this.rating = this.$elem.html();
					this.$elem.html("");
				}
			},
			createDomElements: function(){
				var that = this;
				this.$elem.addClass("rating-editor");
				this.$elem.mouseleave(function(event){
					that.setRating(that.rating);						
				});
				this.createStars(5);
				this.createHiddenInput();
				
				if(this.rating > 0){
					this.setRating(this.rating);
				}
			},
			createStars: function(starsNumber){
				var that = this;
				for(var i=0; i < starsNumber; i++){
					var star = $("<div></div>",{
						"class": "star gray-star",
						mouseover: function(){
							$(this).prevAll().andSelf().addClass("gold-star");
						},
						mouseout: function(event){
							$(this).prevAll().andSelf().removeClass("gold-star");
							$(this).nextAll().andSelf().addClass("gray-star");
						},
						click: function(){
							$(this).siblings().removeClass("gold-star");
							$(this).prevAll().andSelf().addClass("gold-star");
							that.rating = $(this).data("rating");
							that.setRating(that.rating);
						}
					}).data("rating", i + 1);
					this.$elem.append(star);
				}
			},
			setRating: function(rating){
				if(this.rating > 0){
					this.$elem.find(".star").eq(rating - 1).prevAll().andSelf().addClass("gold-star");
					this.$hiddenInput.val(rating);
				}
			},
			createHiddenInput: function(){
				var hiddenInput = $("<input/>",{
					type: "hidden",
					name: "rating",
					"class":"hidden-rating"
				});
				this.$elem.after(hiddenInput);
				this.$hiddenInput = $(hiddenInput);
			}
		};
	})(jQuery);