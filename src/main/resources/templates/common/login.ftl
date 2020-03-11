<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>微信点餐后台管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=0.5,
          maximum-scale=2.0, user-scalable=yes"/>
    <link rel="stylesheet" href="/sell/css/font.css">
    <link rel="stylesheet" href="/sell/css/weadmin.css">
</head>
<body class="login-bg">
<div class="login">
    <div class="message">微信点餐后台管理</div>
    <div id="darkbannerwrap"></div>
    <form method="post" action="/sell/seller/login" class="layui-form">
        <input name="username" placeholder="用户名" type="text" lay-verify="required" class="layui-input">
        <hr class="hr15">
        <input name="password" lay-verify="required" placeholder="密码" type="password" class="layui-input">
        <hr class="hr15">
        <input class="loginin" value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
        <hr class="hr20">
    </form>
</div>
<script src="/sell/js/layui.js" charset="utf-8"></script>
</body>
</html>