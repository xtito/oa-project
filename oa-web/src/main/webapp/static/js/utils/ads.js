define("ads", ["jquery"], function ($) {
	var Ads = function ($) {
		var currId = 0;
		var slidInt;
		var picArr, lnkArr, ttlArr;
        var focElement;
        var focPic, focLnk, focTitle, imgArr;
		var time = 4500;
		this.setFoc = function (currId) {
			this.focPic.src = this.picArr[currId];
			this.focLnk.href = this.lnkArr[currId];
			this.focTitle.innerHTML = '<a href="' + this.lnkArr[currId] + '" target="_blank">' + this.ttlArr[currId] + "</a>";
			for (var i = 0; i < 3; i++)
				this.imgArr[i].className = "abs-thumb-off"

			this.imgArr[currId].className = "abs-thumb-on";
			this.focPic.style.visibility = "hidden";
			if (this.focPic.style.visibility == "visible") {
				this.focPic.style.visibility = "hidden"
			} else {
				this.focPic.style.visibility = "visible"
			}
			this.stopIn.call(this)
		};
		this.playNext = function () {
			if (currId >= 2) {
				currId = 0
			} else {
				currId++
			}
			this.setFoc.call(this, currId);
			this.playIn.call(this)
		};
		this.playIn = function () {
			var that = this;
			slidInt = setTimeout(function () {
				that.playNext.call(that)
			}, time)
		};
		this.stopIn = function () {
			clearTimeout(slidInt)
		}
	};
	return {
		init: function (focElement, picArr, lnkArr, ttlArr, time) {
			var _ads = new Ads($);
			_ads.picArr = picArr;
			_ads.lnkArr = lnkArr;
			_ads.ttlArr = ttlArr;
			_ads.focElement = focElement;
			if (time)_ads.time = time;
			var $focElement = $(_ads.focElement);
			_ads.focPic = $focElement.find(".ads-foc-pic")[0];
			_ads.focLnk = $focElement.find(".ads-foc-lnk")[0];
			_ads.focTitle = $focElement.find(".ads-foc-title")[0];
			_ads.imgArr = $focElement.find(".ads-slide-thumb>div");
			_ads.imgArr.mouseover(function () {
				_ads.currId = _ads.imgArr.index(this);
				_ads.setFoc.call(_ads, _ads.currId)
			}).mouseout(function () {
				_ads.currId = _ads.imgArr.index(this);
				_ads.playIn.call(_ads, _ads.currId)
			});
			_ads.playIn();
			return _ads
		}
	}
});
