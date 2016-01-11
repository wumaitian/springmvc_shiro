<%--
  Created by IntelliJ IDEA.
  User: dingxin
  Date: 2016/1/3
  Time: 15:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<head>
    <title></title>
</head>
<body>
<h1>${message}</h1>
<shiro:hasRole name="admin">
  欢迎admin角色用户登录.<shiro:principal/>
</shiro:hasRole>
<shiro:hasPermission name="student:add">
  欢迎有student add权限用户<shiro:principal/>
</shiro:hasPermission>

</body>
</html>
