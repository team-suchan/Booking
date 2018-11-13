function getdata(data) {
	var list = "";
	for(var i = 0; i < data.data.activityList.length; i++) {
		//				var time = new Date(data.data.activityList[i].activityDate * 1000);
		//				var timeType = time.getFullYear() + "." + time.getMonth() + "." + time.getDate() + " " + time.getHours() + ":" + time.getMinutes()

		list +=
			"<div class='project-list-item'>" +
			"<a href='introducePage.html?activityId=" + encodeURI(data.data.activityList[i].activityId) + "'>" +
			"<div class='project-list-item-img' style='background-image: url(img/zero2.gif);'></div>" +
			"<div class='project-list-item-detail'>" +
			"<div class='project-list-item-title'>" + data.data.activityList[i].activityName + " (" + data.data.activityList[i].category.categoryName + ")</div>" +
			"<div class='project-list-item-time'><span class='icon time-icon'></span>" + data.data.activityList[i].activityDate + "</div>" +
			"<div class='project-list-item-address'><span class='icon address-icon'></span>" +
			"<span class='venue-name-and-address'>" + data.data.activityList[i].activityAddress + "</span></div>" +
			"<div class='project-list-item-price'>" +
			"<div class='not-free'><span class='price-symbol'></span> <span class='price'>" + data.data.activityList[i].bookingStatus + "</span></div>" +
			"</div>" +
			"</div>" +
			"</a>" +
			"</div>"
	};
	$(".project-list").append(list);
};

function getclicked(number) {
	$.ajax({
		url: 'http://111.230.220.64:8080/api/activity?categoryId=00' + number,
		type: "GET",
		success: function(data) {
			$(".project-list").empty();
			getdata(data);
		}
	});
};

function getname() {
	$.ajax({
		url: 'http://111.230.220.64:8080/api/getLoginUser',
		type: "GET",
		success: function(data) {
			if(data.code == 0) {
				$(".order-center").html(data.data.username);
				$(".order-and-icon-wrapper").click(function() {
					location.href = "information.html";
				});
			};
			if(data.code == 10) {
				$(".order-and-icon-wrapper").click(function() {
					location.href = "login.html";
				});
			}
		}
	});
};

function getall() {
	$.ajax({
		url: 'http://111.230.220.64:8080/api/activity',
		type: "GET",
		success: function(data) {
			$(".project-list").empty();
			$(".pageNum.active").html("1");
			getdata(data);
		}
	});
};

function getback() {
	var value = parseInt($("#pageNumber").text()) - 1;
	$.ajax({
		url: 'http://111.230.220.64:8080/api/activity?page=' + value,
		type: "GET",
		success: function(data) {
			if(data.data.totalPages >= value) {
				$(".project-list").empty();
				$(".pageNum.active").html(value);
				getdata(data);
			}
		}
	});
};

function getnext() {
	var value = parseInt($("#pageNumber").text()) + 1;
	$.ajax({
		url: 'http://111.230.220.64:8080/api/activity?page=' + value,
		type: "GET",
		success: function(data) {
			if(data.data.totalPages >= value) {
				$(".project-list").empty();
				$(".pageNum.active").html(value);
				getdata(data);
			}
		}
	});
};
$(document).ready(function() {
	getall();
	getname();
	$.ajax({
		url: 'http://111.230.220.64:8080/api/category',
		type: "GET",
		success: function(data) {
			var list = "";
			for(var i = 0; i < data.data.length; i++) {
				list +=
					'<li class="current-type" onclick="getclicked(' + i + ')"><span class="">' + data.data[i].categoryName + '</span></li>'
			};
			$(".type-list").append(list);
		}
	});
});