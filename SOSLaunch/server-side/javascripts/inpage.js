$(document).ready(
    function()
    {
    }
)

function viewMap(latitude, longitude, msg)
{
	$("#map_canvas").show();
	$("#contents").fadeOut(
		"normal",
		function()
		{
			$("#map_container").animate(
				{
					height: "80%",
					opacity: "100%"
				},
				1000,
				function()
				{
					var latlng = new google.maps.LatLng(latitude, longitude);
					var myOptions =
					{
						zoom: 14,
						center: latlng,
						mapTypeId: google.maps.MapTypeId.ROADMAP
					};
					
					var infoWindow = new google.maps.InfoWindow(
						{
							content: "<strong>" + msg + "</strong>"
						}
					);
					
					var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
					
					var marker = new google.maps.Marker(
						{
							position: latlng,
							title: msg
						}
					);
					
					marker.setMap(map);
					
					google.maps.event.addListener(
						marker,
						"click",
						function()
						{
							infoWindow.open(map, marker);
						}
					);
				}
			);
		}
	)
}

function closeMap()
{
	$("#map_canvas").fadeOut(
		"normal",
		function()
		{
			$("#map_container").animate(
				{
					height: "10%",
					opacity: "0%"
				},
				500,
				function()
				{
					$("#contents").fadeIn("normal");
				}
			);
		}
	);
}