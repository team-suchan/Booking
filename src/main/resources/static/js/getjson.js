$(document).ready(function() {
	$.ajax({
		url: 'http://111.230.220.64:8080/api/activity',
		type: "GET",
		success: function(data) {
			var list = "";
			for(var i = 0; i < data.data.length; i++) {
				list += 
					"<div class='project-list-item'>" +
					"<a href='http://www.baidu.com'>"+
					"<div class='project-list-item-img' style='background-image: url(" + data.data[i].activityIcon + ");'></div>" +
					"<div class='project-list-item-detail'>" +
					"<div class='project-list-item-title'>" + data.data[i].activityName + "</div>" +
					"<div class='project-list-item-time'><span class='icon time-icon'></span>" + data.data[i].activityDate + "</div>" +
					"<div class='project-list-item-address'><span class='icon address-icon'></span>" +
					"<span class='venue-name-and-address'>" + data.data[i].activityDescribe + "</span></div>" +
					"<div class='project-list-item-price'>" +
					"<div class='not-free'><span class='price-symbol'></span> <span class='price'>" + data.data[i].ownerId + "</span></div>" +
					"</div>" +
					"</div>" +
					"</a>"+
					"</div>"
					
			};
			$(".project-list").append(list);
		}
	});

});