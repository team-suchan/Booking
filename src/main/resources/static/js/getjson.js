$(document).ready(function() {
	$.ajax({
		url: 'http://111.230.220.64:8080/api/activity',
		type: "GET",
		success: function(data) {
			var list = "";
			for(var i = 0; i < data.data.activityList.length; i++) {
				var time = new Date(data.data.activityList[i].activityDate * 1000);
				var timeType = time.getFullYear() + "." + time.getMonth() + "." + time.getDate() + " " + time.getHours() + ":" + time.getMinutes()
				list +=
					"<div class='project-list-item'>" +
					"<a href='http://www.baidu.com'>" +
					"<div class='project-list-item-img' style='background-image: url(" + data.data.activityList[i].activityIcon + ");'></div>" +
					"<div class='project-list-item-detail'>" +
					"<div class='project-list-item-title'>" + data.data.activityList[i].activityName + "</div>" +
					"<div class='project-list-item-time'><span class='icon time-icon'></span>" + data.data.activityList[i].activityDate + "</div>" +
					"<div class='project-list-item-address'><span class='icon address-icon'></span>" +
					"<span class='venue-name-and-address'>" + data.data.activityList[i].activityAddress + "</span></div>" +
					"<div class='project-list-item-price'>" +
					"<div class='not-free'><span class='price-symbol'></span> <span class='price'>可预订</span></div>" +
					"</div>" +
					"</div>" +
					"</a>" +
					"</div>"
			};
			$(".project-list").append(list);
		}
	});

});