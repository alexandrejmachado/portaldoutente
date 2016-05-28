<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset='utf-8' />
<link href="<c:url value="/resources/res-cal/fullcalendar.css" ></c:url>" rel='stylesheet' />
<link href="<c:url value="/resources/res-cal/fullcalendar.print.css"> </c:url>" rel='stylesheet' media='print' />
<script src='<c:url value="/resources/js/jquery.js"></c:url>'></script>
<script src='<c:url value="/resources/res-cal/lib/moment.min.js"></c:url>'></script>
<script src='<c:url value="/resources/res-cal/fullcalendar.js"></c:url>'></script>
<script src='<c:url value="/resources/res-cal/lib/jquery.fancybox.pack.js"></c:url>'></script>
<link href="<c:url value="/resources/res-cal/jquery.fancybox.css"> </c:url>" rel='stylesheet' media='screen' />
<script>

$(document).ready(function() {
	$.get("https://"+ window.location.host+"/testCalendar/getEventos", function(data){
			console.log(typeof data[0]["title"]);
			var1 = data[0]["title"];
			var2 = data[0]["start"];
			console.log(var1);
			console.log(var2);
			$('#calendar').fullCalendar({
					defaultDate: '2016-05-12',
					editable: false,
					disableDragging:true,
					eventLimit: true, // allow "more" link when too many events
					events: data,
					selectable: true,
					selectHelper: true,
					select: function(start, end) {
						$.fancybox.open({
							title :  "Marque uma Consulta pls",
							href : 'https://' + window.location.host,
							type : 'iframe',
							padding : 5
									});
						$('#calendar').fullCalendar('unselect');
					},
			});
	});
});

</script>
<style>

	body {
		margin: 40px 10px;
		padding: 0;
		font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
		font-size: 14px;
	}

	#calendar {
		max-width: 900px;
		margin: 0 auto;
	}

</style>
</head>
<body>

	<div id='calendar'></div>

</body>
</html>
