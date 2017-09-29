/**
 * 折叠菜单
 */
define("collapse", ['jquery'], function ($) {

	return {
		menus: [],
		titles: [],
		init: function () {

			this.menus = $(".collapse-two>li>a");
			this.titles = $(".collapse>.collapse-head");

			//折叠
			this.titles.click(function () {
				var $this = $(this);
				var $collapse = $this.parent();
				if ($collapse.hasClass("active"))
					$collapse.removeClass("active");
				else
					$collapse.addClass("active");
			});

			//用于click中的方法
			var $collapse_two = this.menus;
			//二级选中
			this.menus.click(function () {
				var $this = $(this);
				$collapse_two.removeClass("active");
				$this.addClass("active");
				$this.parents(".collapse").addClass("active");
			});

			return this;

		}, selectIndex: function (index) {
			var $menu = $(this.menus[index]);
			$menu.click();
			return $menu;
		}
	};

});