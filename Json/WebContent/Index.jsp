<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs
/jquery/1.4.0/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	 $("#mybutton").click(function(){
		$.ajax({
			url : "${pageContext.request.contextPath}/ClassServlet",
			type : "GET",
			async : true,
			dataType : "json",
			success : function(classes) {
				if(classes.length>0){
					$("#classes").empty();
				}
				for(var i=0;i<classes.length;i++){
					$("#classes").append("<option value="+classes[i]["id"]+">"+classes[i]["name"]+"</option>");
				}
			},
			error : function(xhr) {
				alert("错误提示： " + xhr.status + " " + xhr.statusText);
			}
		});
	 });
	 $("#classes").change(function(){
		 var class_id=$("#classes").val();
		 $.ajax({
				url : "${pageContext.request.contextPath}/StudentsServlet",
				type : "POST",
				async : true,
				data :"class_id="+class_id,
				dataType : "json",
				success : function(students) {
					if(students.length>0){
						$("#students").empty();
					}
					for(var i=0;i<classes.length;i++){
						$("#students").append("<option value="+students[i]["id"]+">"+students[i]["name"]+"</option>");
					}
				},
				error : function(xhr) {
					alert("错误提示： " + xhr.status + " " + xhr.statusText);
			}
		});
	});
});

</script>
</head>
<body>
	<button type="button" id="mybutton">Click
		Me!</button>
	<div id="mydiv"></div>
	<select id="classes">
	<option value="">---请选择班级---</option>
	</select>
	<select id="students">
	<option value="">---请选择学生---</option>
	</select>
</body>
</html>