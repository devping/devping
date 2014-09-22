<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet"
	href="http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.css" />
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script
	src="http://code.jquery.com/mobile/1.4.2/jquery.mobile-1.4.2.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DevPing</title>
<style>
.my-page .ui-listview li .ui-btn p {
	color: #c0c0c0;
}

.my-page .ui-listview li .ui-btn .ui-li-aside {
	color: #eee;
}
/* First breakpoint is 48em (768px). 3 column layout. Tiles 250x250 pixels incl. margin at the breakpoint. */
@media ( min-width : 48em ) {
	.my-page .ui-content {
		padding: .5625em; /* 9px */
	}
	.my-page .ui-listview li {
		float: left;
		width: 30.9333%; /* 33.3333% incl. 2 x 1.2% margin */
		height: 14.5em; /* 232p */
		margin: .5625em 1.2%;
	}
	.my-page .ui-listview li>.ui-btn {
		-webkit-box-sizing: border-box;
		/* include padding and border in height so we can set it to 100% */
		-moz-box-sizing: border-box;
		-ms-box-sizing: border-box;
		box-sizing: border-box;
		height: 100%;
	}
	.my-page .ui-listview li.ui-li-has-thumb .ui-li-thumb {
		height: auto; /* To keep aspect ratio. */
		max-width: 100%;
		max-height: none;
	}
	/* Make all list items and anchors inherit the border-radius from the UL. */
	.my-page .ui-listview li,.my-page .ui-listview li .ui-btn,.my-page .ui-listview .ui-li-thumb
		{
		-webkit-border-radius: inherit;
		border-radius: inherit;
	}
	/* Hide the icon */
	.my-page .ui-listview .ui-btn-icon-right:after {
		display: none;
	}
	/* Make text wrap. */
	.my-page .ui-listview h2,.my-page .ui-listview p {
		white-space: normal;
		overflow: visible;
		position: absolute;
		left: 0;
		right: 0;
	}
	/* Text position */
	.my-page .ui-listview h2 {
		font-size: 1.25em;
		margin: 0;
		padding: .125em 1em;
		bottom: 50%;
	}
	.my-page .ui-listview p {
		font-size: 1em;
		margin: 0;
		padding: 0 1.25em;
		min-height: 50%;
		bottom: 0;
	}
	/* Semi transparent background and different position if there is a thumb. The button has overflow hidden so we don't need to set border-radius. */
	.ui-listview .ui-li-has-thumb h2,.ui-listview .ui-li-has-thumb p {
		background: #111;
		background: rgba(0, 0, 0, .8);
	}
	.ui-listview .ui-li-has-thumb h2 {
		bottom: 35%;
	}
	.ui-listview .ui-li-has-thumb p {
		min-height: 35%;
	}
	/* ui-li-aside has class .ui-li-desc as well so we have to override some things. */
	.my-page .ui-listview .ui-li-aside {
		padding: .125em .625em;
		width: auto;
		min-height: 0;
		top: 0;
		left: auto;
		bottom: auto;
		/* Custom styling. */
		background: #990099;
		background: rgba(153, 0, 153, .85);
		-webkit-border-top-right-radius: inherit;
		border-top-right-radius: inherit;
		-webkit-border-bottom-left-radius: inherit;
		border-bottom-left-radius: inherit;
		-webkit-border-bottom-right-radius: 0;
		border-bottom-right-radius: 0;
	}
	/* If you want to add shadow, don't kill the focus style. */
	.my-page .ui-listview li {
		-moz-box-shadow: 0px 0px 9px #111;
		-webkit-box-shadow: 0px 0px 9px #111;
		box-shadow: 0px 0px 9px #111;
	}
	/* Images mask the hover bg color so we give desktop users feedback by applying the focus style on hover as well. */
	.my-page .ui-listview li>.ui-btn:hover {
		-moz-box-shadow: 0px 0px 12px #33ccff;
		-webkit-box-shadow: 0px 0px 12px #33ccff;
		box-shadow: 0px 0px 12px #33ccff;
	}
	/* Animate focus and hover style, and resizing. */
	.my-page .ui-listview li,.my-page .ui-listview .ui-btn {
		-webkit-transition: all 500ms ease;
		-moz-transition: all 500ms ease;
		-o-transition: all 500ms ease;
		-ms-transition: all 500ms ease;
		transition: all 500ms ease;
	}
}
/* Second breakpoint is 63.75em (1020px). 4 column layout. Tiles will be 250x250 pixels incl. margin again at the breakpoint. */
@media ( min-width : 63.75em ) {
	.my-page .ui-content {
		padding: .625em; /* 10px */
	}
	/* Set a max-width for the last breakpoint to prevent too much stretching on large screens.
By setting the max-width equal to the breakpoint width minus padding we keep square tiles. */
	.my-page .ui-listview {
		max-width: 62.5em; /* 1000px */
		margin: 0 auto;
	}
	/* Because of the 1000px max-width the width will always be 230px (and margin left/right 10px),
but we stick to percentage values for demo purposes. */
	.my-page .ui-listview li {
		width: 23%;
		height: 230px;
		margin: .625em 1%;
	}
}
</style>
</head>
<body>
	<div data-role="page" data-theme="b" id="demo-page" class="my-page"
		data-url="demo-page">
		<div data-role="header">
			<!-- header -->
			<h1>DevPing</h1>
			<a href="./" data-shadow="false" data-iconshadow="false"
				data-icon="carat-l" data-iconpos="notext" data-rel="back"
				data-ajax="false">Back</a>
		</div>
		<!-- /header -->
		<div role="main" class="ui-content">
			<!-- content -->
			<ul data-role="listview" data-inset="true">
				<li><a href="http://m.google.com">
				<img src="https://encrypted-tbn1.gstatic.com/images?q=tbn%3AANd9GcSu5jsk9rXWN1qMCB7BQRDNSMqvgDDnTobZa6UsCiTpNDpFIaL-Jw" class="ui-li-thumb">
						<h2>Monitoring System</h2>
						<p>Monitoring System Developer</p>
						<p class="ui-li-aside">Monitoring</p>
				</a></li>
				<li><a href="http://m.google.com"> <img
						src="http://gracekellyyoga.files.wordpress.com/2011/04/palette12.jpg"
						class="ui-li-thumb">
						<h2>Web pallete</h2>
						<p>Code automation web tool, developed using Jquery Mobile</p>
						<p class="ui-li-aside">Web</p>
				</a></li>
				<li><a href="http://m.google.com"> 
				<img src="https://encrypted-tbn2.gstatic.com/images?q=tbn%3AANd9GcQ66ECCYoaxJPUlW8vavxUqyhsRmsO0xraHY7MryWfejqT_fqZW" class="ui-li-thumb">
						<h2>ERP</h2>
						<p>ERP Developer</p>
						<p class="ui-li-aside">ERP</p>
				</a></li>
				<li><a href="http://m.google.com"> 
				<img src="https://encrypted-tbn1.gstatic.com/images?q=tbn%3AANd9GcSron4XcMyNEbwJ4n_Jtg_ZFnsZPS1QlbVNzPytxnphX09IDlcF" class="ui-li-thumb">
						<h2>Mobile Application</h2>
						<p>Mobile Application Developer</p>
						<p class="ui-li-aside">Mobile</p>
				</a></li>
				<li><a href="http://m.google.com"> 
				<img src="http://beageek.biz/wp-content/uploads/backbone-js.gif" class="ui-li-thumb">
						<h2>Backbone.js</h2>
						<p>Javascript Developer</p>
						<p class="ui-li-aside">Javascript</p>
				</a></li>
				<li><a href="http://m.google.com"> 
				<img src="http://www.ddaily.co.kr/data/photos/20110623/20110623173423__SEMAL.jpg" class="ui-li-thumb">
						<h2>Touch Pad UI</h2>
						<p>UI Developer</p>
						<p class="ui-li-aside">UI</p>
				</a></li>
			</ul>
		</div>
		<!-- /content -->
	</div>
</body>
</html>
