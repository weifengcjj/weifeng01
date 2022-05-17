
<%@ page import="java.util.Map" %>
<%@ page import="Com.cjj.web.Sevlet.ActionErrors" %>
<%@ page import="Com.cjj.web.form.TestundileForm" %>
Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/10/25
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="css/styles.css">
</head>
<body>
<div class="wrapper">
    <div class="container">
        <h1>Welcome</h1>
        <%  ActionErrors errors=(ActionErrors) request.getAttribute("errors");
            TestundileForm tf=(TestundileForm) request.getAttribute("form");
        %>
        <form  action="login.do" method="post">
            <input type="text" placeholder="admin" name="username">

            <input type="password" placeholder="password" name="password">

            <button type="submit">Login</button>
            <button type="button"  onclick="abc()">Logon</button>

            <h1 style="font-family: 华文琥珀;font-size: 20px; color: black;text-align: -moz-right;">
                <%
                    if(tf!=null){
                        Map map= errors.getMap();
                        String value=(String)map.get("pwError");
                        out.println("<font color=red size=10>"+value+"</font>");
                    }
                %>
            </h1>
            <%--      //<a href="register.jsp">注册</a>--%>
            <div style="color:black;font-family: '微软雅黑 Light';text-align: right;" class="aa">
                <p>© 2022-2024 chengjiejia @</p>
                <p>渝C1-20040309-1</p>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    $('#login-button').click(function(event){
        event.preventDefault();
        $('form').fadeOut(500);
        $('.wrapper').addClass('form-success');
    });
    function  abc()
    {
        window.location = "register.jsp";
    }

</script>
</body>
</html>
