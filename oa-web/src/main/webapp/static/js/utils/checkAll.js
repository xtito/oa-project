define('checkAll', ['jquery'], function ($) {
	return {
		init: function () {
			var $table = $(".table-check-all");

			//全选按钮
			$table.find("th>input[type=checkbox]").change(function () {
				var $this = $(this);
				var $table = $this.parents(".table-check-all");
				$table.find("td>input[type=checkbox]").each(function () {
					this.checked = $this[0].checked;
				})
			});

			//子按钮级联全选按钮状态
			$table.find("td>input[type=checkbox]").change(function () {
				var $this = $(this);

				var $table = $this.parents(".table-check-all");

				if (!this.checked) {
					$table.find("th>input[type=checkbox]").each(function () {
						this.checked = false;
					});
				} else {
					var $checkbox = $table.find("td>input[type=checkbox]");
					var checked = true;
					for (var i = 0; i < $checkbox.length; i++) {
						var checkbox = $checkbox[i];
						if (!checkbox.checked) {
							checked = false;
							break;
						}
					}
					$table.find("th>input[type=checkbox]").each(function () {
						this.checked = checked;
					});
				}
			});
		}
	}
});