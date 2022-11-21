<%@ page import="Com.cjj.web.form.SectorForm" %>
<%@ page import="Com.cjj.web.Dao.SectorDao" %>
<%@ page import="Com.cjj.web.Service.Servce" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.List" %>
<%@ page import="Com.cjj.web.form.TopicForm" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/11/3
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>欢迎你的光临!!!</title>
  <style>
    #ImgDiv{

      text-align: center;
      margin:0;
      padding: 0;
      height: 150px;
    }
    ul li{padding:0;margin:0}
    a{
      text-decoration: none;
    }
    a:hover{
      color: red;
      font-size: 20px;
    }
    #d2{
      text-align: right;
      font-family: "微软雅黑";
      font-size: 20px;
      height: 100px;
    }
    .btn{
      margin: 20px 120px;
      width: 80px;
    }
    .content{
      margin: 10px auto;
      display: none;
    }
    .btn.active{
      background-color: pink;
    }
    .content.show{
      display:block;

    }

    button:hover{
      color: green;
    }
  </style>
</head>
<body bgcolor="#f5deb3">
<div id="ImgDiv">
  <img src="#" alt="i1">
</div>
<div id="d2">
  <a href="login.jsp">登录|</a>&nbsp;&nbsp;&nbsp;
  <a href="register.jsp">注册|</a>&nbsp;&nbsp;&nbsp;
</div>
<hr style="border: #4cae4c  3px solid ;">
<p class="btn1">
  <%
    Servce servce=new Servce();
    List<SectorForm> list=servce.getsector();
    List<TopicForm> list1=servce.gettopic();
  %>
<%for(int i=0;i< list.size();i++){%>

  <button class="btn"><%=list.get(i).getSertorName()%></button>

  <%}%>

</p>

<div>
    <%for(int i=0;i< list1.size();i++){%>
    <p><%=list1.get(i).getTitle()%></p>
    <p><%=list1.get(i).getContent()%></p>
    <p><%=list1.get(i).getCreattime()%></p>
    <p><%=list1.get(i).getAuthuser()%></p>
    <p><%="点击数："+list1.get(i).getHits()%></p>
    <p><%="类型："+list1.get(i).getType()%></p>
    <%}%>
</div>
<div class="content show">
  <p>页面一要显示的内容</p>
  <table border="1px">
    <tr>
      <td>aaa</td>
      <td>aaa</td>
      <td>aaa</td>
    </tr>
    <tr>
      <td>aaa</td>
      <td>aaa</td>
      <td>aaa</td>
    </tr>
    <tr>
      <td>aaa</td>
      <td>aaa</td>
      <td>aaa</td>
    </tr>
  </table>
</div>

<div class="content">
  <p>页面二要显示的内容</p>
  <table border="1px">
    <tr>
      <td>bbb</td>
      <td>bbb</td>
      <td>bbb</td>
    </tr>
    <tr>
      <td>bbb</td>
      <td>bbb</td>
      <td>bbb</td>
    </tr>
    <tr>
      <td>bbb</td>
      <td>bbb</td>
      <td>bbb</td>
    </tr>
  </table>
</div>

<div class="content" >
  <p>页面三要显示的内容</p>
  <table border="1px">
    <tr>
      <td>ccc</td>
      <td>ccc</td>
      <td>ccc</td>
    </tr>
    <tr>
      <td>ccc</td>
      <td>ccc</td>
      <td>ccc</td>
    </tr>
    <tr>
      <td>ccc</td>
      <td>ccc</td>
      <td>ccc</td>
    </tr>
  </table>
</div>

<script>
  //获取页面的每个按钮
  var btns = document.getElementsByClassName("btn")
  //获取内容盒子
  var contents = document.getElementsByClassName("content")

  //遍历每个按钮为其添加点击事件
  for(var i=0;i<btns.length;i++){
    btns[i].index = i;
    btns[i].onclick = function(){
      //对当前点击的按钮赋予active类名及显示当前按钮对应的内容
      for(var j=0;j<btns.length;j++){
        btns[j].className = btns[j].className.replace(' active', '').trim();
        contents[j].className = contents[j].className.replace(' show', '').trim();
      }
      this.className = this.className + ' active';
      contents[this.index].className = contents[this.index].className + ' show';
    };

  }


</script>
<button type="button">发布帖子</button>
</body>
</html>
