<%@ page import="Com.cjj.web.Service.Servce" %>
<%@ page import="Com.cjj.web.form.SectorForm" %>
<%@ page import="Com.cjj.web.form.TopicForm" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %><%--
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
        body{
            margin: 0;
            padding: 0;
        }
        #nav ul {
            list-style: none;
            margin-left: 50px;
        }
        #nav li {
            display: inline;
        }
        #nav a {
            line-height: 40px;
            color: #fff;
            text-decoration: none;
            padding: 20px 20px;
        }
        #nav a:hover {
            background-color: #060;
        }
        #d3{
            display:inline-block;
            margin-top: 0px;
            height: 100px;
            width: 100%;
            float:left;
            display:block;
        }
        #d5 a{
            margin-right:120px;
            margin-bottom: 120px;
            margin-top: 120px;
        }
        #fabu{
            margin-left: 1450px;
            border:2px solid #a1a1a1;
            width:70px;
            height: 50%;
            border-radius:25px;
            background-color: turquoise;
        }
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
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
<%--    <script>--%>
<%--        $(document).ready(function() {--%>
<%--            $("#image1").click(function() {--%>
<%--                $.ajax({--%>
<%--                    type:"POST",--%>
<%--                    url :"showtest.action",--%>
<%--                })--%>
<%--               // window.location.href = "showtest.jsp";--%>
<%--            });--%>

<%--            $("#fabu").click(function() {--%>
<%--                $.ajax({--%>
<%--                    type:"POST",--%>
<%--                    url :"fabutest.action",--%>
<%--                })--%>
<%--            });--%>
<%--        });--%>
<%--    </script>--%>

</head>
<body>
<div id="d3">
    <a href="test">
        <img id="image1" class="image1" style="width: 100px;height: 100%;margin-left: 1250px; border-radius:100%; overflow:hidden;background-size: cover;" src="<%=request.getSession().getAttribute("image")%>" alt="个人中心"/>
    </a>
    <a href="test1" id="fabu">发布帖子</a>
</div>
<hr style="border: #4cae4c  3px solid ;">
<p class="btn1">
        <%
    Servce servce=new Servce();
    List<SectorForm> list=servce.getsector();
    List<TopicForm> list1=servce.gettopic();

    List<Map<Integer,TopicForm>> list3=servce.gettopic111();

      List<TopicForm> list4=new ArrayList<>();
  %>

        <%for(int i=0;i<list.size();i++){%>
    <form action="topic.do" method="post">
    <input class="btn" type="submit" name="type" placeholder="<%=list.get(i).getSectorName()%>" value="<%=list.get(i).getId()%>">
</form>
    <%
        for (Map<Integer, TopicForm> map : list3) {
            for (Integer s : map.keySet()) {
                if (s.equals(list.get(i).getId()))
                {
                    list4.add(map.get(s));
//          request.getSession().setAttribute("list",list4);
                }
            }
        }
    %>
<%}%>


</p>

<div class="content show">
    <p>页面一要显示的内容</p>
</div>

<div class="content">
    <p>页面二要显示的内容</p>
</div>

<div class="content" >
    <p>页面三要显示的内容</p>
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
