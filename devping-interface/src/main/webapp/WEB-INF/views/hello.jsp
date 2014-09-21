<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DevPing</title>
<style>
#topMenu {
	height: 30px;
	width: 850px;
}

#topMenu ul li {
	list-style: none;
	color: white;
	background-color: #2d2d2d;
	float: left;
	line-height: 30px;
	vertical-align: middle;
	text-align: center;
}

#topMenu .menuLink {
	text-decoration: none;
	color: white;
	display: block;
	width: 150px;
	font-size: 12px;
	font-weight: bold;
	font-family: "Trebuchet MS", Dotum, Arial;
}

#topMenu .menuLink:hover {
	color: red;
	background-color: #4d4d4d;
}

li a {
	display: inline-block;
}

li a {
	display: block;
}

body {
	font-family: cursive;
	font-size: 0.66em;
}

p {
	line-height: 1.5em;
}

ul#menu,ul#menu ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	width: 14.4em;
}

ul#menu a {
	display: block;
	text-decoration: none;
}

ul#menu li {
	margin-top: 1px;
}

ul#menu li a {
	background: blue;
	color: #fff;
	padding: 0.5em;
}

ul#menu li a:hover {
	background: green;
}

ul#menu li ul li a {
	background: pink;
	color: #000;
	padding-left: 20px;
}

ul#menu li ul li a:hover {
	background: yellow;
	border-left: 5px #ffffff solid;
	padding-left: 15px;
}
</style>
<script>
	$(document).ready(function() {
		$('#menu ul').hide();
		$('#menu li a').click(function() {
			var checkElement = $(this).next();
			if ((checkElement.is('ul')) && (checkElement.is(':visible'))) {
				$('#menu ul:visible').slideUp(300);
				return false;
			}
			if ((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
				$('#menu ul:visible').slideUp(300);
				checkElement.slideDown(300);
				return false;
			}
		});
	});
</script>
</head>
<body>
	<h2>Welcome DevPing</h2>
	<nav id="topMenu">
	<ul>
		<li><a class="menuLink" href="#">About us</a></li>
		<li><a class="menuLink" href="#">Tag Cloud</a></li>
		<li><a class="menuLink" href="#">Chatting</a></li>
	</ul>
	</nav>
	<div style="width: 160px;" id="div1">
		<tr>
			<td height="800" valign="top">
				<ul id="menu" width="160px">
					<li><a href="#">Chatting</a>
						<ul>
							<li><a href="#">Ping</a></li>
							<li><a href="#">Ping</a></li>
						</ul></li>
					<li><a href="#">Tag</a>
						<ul>
							<li><a href="#">Tag Search</a></li>
							<li><a href="#">Tag Choice</a></li>
						</ul></li>
				</ul>
			</td>
		</tr>
	</div>
</body>
</html>
