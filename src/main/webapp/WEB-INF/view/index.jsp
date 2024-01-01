<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp - Maven - Spring MVC - JSP Demo</title>
<link rel="stylesheet" href="<spring:url value='/css/index.css'/>"/>

<link rel="stylesheet" href="<spring:url value='/out-statics/out-css/style.css'/>"/> <!-- 可以去除【/out-statics】，見SpringWebConfig.java第66行 -->
<script src="<spring:url value='/out-statics/out-js/app.js'/>"></script>             <!-- 可以去除【/out-statics】，見SpringWebConfig.java第66行 -->
</head>
<body>

<div style="margin-left: auto; margin-right: auto; width:820px; ">
	<div>
		1. <h1 style="display:inline;">Hello, ${message} : Spring MVC + JSP  </h1>                        <br><br><br>
		2. <a href="<spring:url value='/emp/select_page'/>">Click here to Spring MVC + JSP Project</a>    <br><br><br>
		3. <a href="<spring:url value='/hello?name=peter1吳永志'/>">世界你好 (測試中文請求參數)</a>         <br><br>
		4. <button onclick="changeColor()" style="font-weight: bold; ">Change Color - 測試外部 C:\out-statics 資源:</button>
		   <span class="color1" id="title1">見</span> <b>：</b> <span class="color2" id="title2">SpringWebConfig.java第66行</span>
	</div>
	
	<br><br>
	
    5. <div class="hello-color" 
	        style="display:inline-block; background-color: black; border: 4px outset red; text-align: center; width:270px;">
	        這裡測試讀取靜態資源的順序
	   </div>  <br><br><br>

    6. <div style="border: 1px outset blue; text-align: left;">
	       <div style="border: 1px outset blue; text-align: center; font-weight: bold;">
	                  forward 是 Spring MVC 的預設行為，以下為請求參數與 Scope 的測試
	       </div>
		   
		   <ol><li>
		              <a href='<%=request.getContextPath()%>/A1.do?ename=peter1吳永志'>forward-1</a>  <br><br><br>
           
           </li><li>
				      <form method="post" action="<%=request.getContextPath()%>/A2.do">
					       請輸入您的名字: <input type="text" name="ename" value="peter2吳永志">
					       <input type="submit" value="forward-2">
				      </form>                                                                         <br><br>
       
           </li><li>
				      <form method="post" action="<%=request.getContextPath()%>/A3_Redirect.do">
					       請輸入您的名字: <input type="text" name="ename" value="peter3吳永志">
					       <input type="submit" value="redirect">
				      </form>

           </li></ol></div>
</div>
</body>
</html>