<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Hello World Example</title>
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
                text-decoration:none;                     
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
	</style>
</head>
<body>
<h2>Hello DevPing</h2>
<nav id="topMenu" >
        <ul>
                <li><a class="menuLink" href="#">About us</a></li>
                <li><a class="menuLink" href="#">Tag Cloud</a></li>
                <li><a class="menuLink" href="#">Chatting</a></li>
        </ul>
</nav>
</body>
</html>
