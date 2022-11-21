<%@ page import="Com.cjj.web.Service.Servce" %>
<%@ page import="java.util.List" %>
<%@ page import="Com.cjj.web.Sevlet.RegisterAction" %>
<%@ page import="Com.cjj.web.form.RegisterForm" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/11/20
  Time: 16:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<style>
    #image{
        width: 200px;
        height: 120px;
        margin-left: 0px;
    }
</style>
<body>
<%Servce servce=new Servce();
    List<RegisterForm> list=servce.getuserallson((String) request.getSession().getAttribute("userdename"));
%>
<%for (int i = 0; i < list.size(); i++) {%>
<p>用户名:<%=list.get(i).getName()%></p>
头像：<img id="image" src="<%=list.get(i).getImage()%>"/>
<br/>
<p>性别:<%=list.get(i).getSex()%></p>
<p>手机号:<%=list.get(i).getMobile()%></p>
<p>邮箱:<%=list.get(i).getEmail()%></p>
<p>创建日期:<%=list.get(i).getDatetime()%></p>
    <%}%>
</body>
</html>
