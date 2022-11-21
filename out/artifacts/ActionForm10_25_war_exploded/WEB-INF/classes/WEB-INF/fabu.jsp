<%@ page import="Com.cjj.web.form.SectorForm" %>
<%@ page import="java.util.List" %>
<%@ page import="Com.cjj.web.Service.Servce" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/11/13
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        #big{
            height: 500px;
            width:400px;
            background-color: #e4b9c0;
        }
        #center1{
            padding: 10px 10px;
            height: 60%;
            width: 50%;
            background-color: #5bc0de;
        }
    </style>
</head>
<body>
<%
    Servce servce=new Servce();
    List<SectorForm> list=servce.getsector();
%>
<div id="big">
    <div id="center1">
        <form method="post" action="fabu.do">
        <input type="text" placeholder="请输入标题" name="title"/>
        <input type="text" placeholder="请输入正文" name="content"/>
            技术类型：
            <select name="selectList" size="<%=list.size()%>">
                <%for(int i=0;i< list.size();i++){%>
                <option name="type"><%=list.get(i).getSectorName()%></option>
                <%}%>
            </select>
            <input type="submit" value="提交帖子"/>
        </form>
    </div>

</div>
</body>
</html>
