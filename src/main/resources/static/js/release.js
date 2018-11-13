$(function() {
	$(".release").click(function() {
		$.ajax({
			url: 'http://111.230.220.64:8080/api/getLoginUser',
			type: "GET",
			dataType: "json",
			xhrFields: {
				withCredentials: true
			},
			crossDomain: true,
			success: function(data) {
				if(data.code == 0) {
					var ownerId = data.data.userId;
					var name = $(".name").val();
					var describe = $(".describe").val();
					var address = $(".address").val();
					var category = $(".category").val();
					var date = $(".date").val().replace(/T/g, " ");
					var startbook = $(".startbook").val().replace(/T/g, " ");
					var endbook = $(".endbook").val().replace(/T/g, " ");
					$.ajax({
						url: "http://111.230.220.64:8080/api/activity",
						type: "POST",
						async: false,
						dataType: "json",
						xhrFields: {
							withCredentials: true
						},
						crossDomain: true,
						data: {
							"activityName": name,
							"activityDate": date,
							"activityDescribe": describe,
							"activityIcon": "",
							"categoryId": category,
							"ownerId": ownerId,
							"activityAddress": address,
							"startBookTime": startbook,
							"endBookTime": endbook
						},
						success: function(response) {
							if(response.code == "0") {
								location.href = "introducePage.html?activityId=" + response.data.activityId;
							}
						},
						error: function() {
							alert("系统繁忙，请稍后再试...");
						}
					});
				};
				if(data.code == 10) {
					alert("请先登录");
				};
			}
		});

	});
});
$(document).ready(function() {
	$.ajax({
		url: 'http://111.230.220.64:8080/api/category',
		type: "GET",
		success: function(data) {
			var list = "";
			for(var i = 0; i < data.data.length; i++) {
				$(".category").append("<option value='" + data.data[i].categoryId + "'>" + data.data[i].categoryName + "</option>");
			};
		}
	});
});