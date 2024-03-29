<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1.0" />
    <title>注册</title>

    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />

    <style type="text/css">
        html,body {
            height: 100%;
        }
        .box {
            filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#6699FF', endColorstr='#6699FF'); /*  IE */
            background-image:linear-gradient(bottom, #6699FF 0%, #6699FF 100%);
            background-image:-o-linear-gradient(bottom, #6699FF 0%, #6699FF 100%);
            background-image:-moz-linear-gradient(bottom, #6699FF 0%, #6699FF 100%);
            background-image:-webkit-linear-gradient(bottom, #6699FF 0%, #6699FF 100%);
            background-image:-ms-linear-gradient(bottom, #6699FF 0%, #6699FF 100%);

            margin: 0 auto;
            position: relative;
            width: 100%;
            height: 100%;
        }
        .login-box {
            width: 100%;
            max-width:500px;
            height: 400px;
            position: absolute;
            top: 20%;

            margin-top: -150px;
            /*设置负值，为要定位子盒子的一半高度*/

        }
        @media screen and (min-width:500px){
            .login-box {
                left: 50%;
                /*设置负值，为要定位子盒子的一半宽度*/
                margin-left: -250px;
            }
        }

        .form {
            width: 100%;
            max-width:500px;
            height: 275px;
            margin: 25px auto 0px auto;
            padding-top: 25px;
        }
        .login-content {
            height: 650px;
            width: 100%;
            max-width:500px;
            background-color: rgba(255, 250, 2550, .6);
            float: left;
        }


        .input-group {
            margin: 0px 0px 30px 0px !important;
        }
        .form-control,
        .input-group {
            height: 40px;
        }

        .form-group {
            margin-bottom: 0px !important;
        }
        .login-title {
            padding: 20px 10px;
            background-color: rgba(0, 0, 0, .6);
        }
        .login-title h1 {
            margin-top: 10px !important;
        }
        .login-title small {
            color: #fff;
        }

        .link p {
            line-height: 20px;
            margin-top: 20px;
        }
        .btn-sm {
            padding: 8px 24px !important;
            font-size: 16px !important;
        }
    </style>
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
    <script>

        $(function(){
            $("#btn").click(function(){
                if($(".email").val()){
                    $.ajax({
                        type:"POST",
                        url :"email.action?random"+Math.random(),
                        data:{
                            email:$(".email").val(),
                        },
                        success:function(data){
                            alert("success");
                        },
                    })
                }else{
                    alert("fail");
                    $("#notice").html("请填写邮箱");
                    setTimeout(function(){
                        $("#notice").hide();
                    },1000);
                }
            });

            //  判断用户是否可以注册
            $("#submit").click(function(){
                if($(".email").val()){
                    $("#RegistForm").submit();
                }else{   //  如果没有值
                    $("#notice").html("请完整信息");
                    setTimeout(function(){
                        $("#notice").hide();
                    },1000);
                }
            });
        });

    </script>

</head>

<body>
<div class="box">
    <div class="login-box">
        <div class="login-title text-center">
            <h1><small>注册</small></h1>
        </div>
        <div class="login-content ">
            <div class="form">
                <form id="RegistForm" action="register.do" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <div class="col-xs-12  ">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                                <input type="text" id="username" name="username" class="form-control" placeholder="用户名">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12  ">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                <input type="password" id="password" name="password" class="form-control" placeholder="密码">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12  ">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                <input type="text" id="moblie" name="moblie" class="form-control" placeholder="手机号码">
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-12  ">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                    <input type="email" id="email" name="email" class="email" placeholder="邮箱">
                                <p id="btn" class="btn">发送邮箱验证码</p>

                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-12  ">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                <input type="text" name="code" class="code" placeholder="请输入验证码"/>
                                <span id="notice" class="hide">请先完成邮箱验证</span>
                            </div>
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-xs-12  ">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                <input type="file" name="image" placeholder="选择头像"/>

                            </div>
                        </div>

                    </div>
                    <div class="form-group">
                        <div class="col-xs-12  ">
                            <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                                性别：   <input type="radio" name="sex" value="女">女
                                <input type="radio" name="sex" value="男">男
                            </div>
                        </div>

                    </div>



                    <div class="form-group form-actions">
                        <div class="col-xs-4 col-xs-offset-4 ">
                            <button id="submit" class="btn btn-sm btn-info"><span class="glyphicon glyphicon-off"></span> 注册</button>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-6 link">
                            <p class="text-center remove-margin"><small>有账号了？</small> <a href="login.jsp" ><small>去登录</small></a>
                            </p>
                        </div>
<%--                        <div class="col-xs-6 link">--%>
<%--                            <p class="text-center remove-margin"><small>还没注册?</small> <a href="javascript:void(0)" ><small>注册</small></a>--%>
<%--                            </p>--%>
<%--                        </div>--%>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


</body>

</html>