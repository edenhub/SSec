<%@ page import="DBPersistence.FileCompile.CompileAction" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.UUID" %>
<%@ page import="java.util.Random" %>
<%--
  Created by IntelliJ IDEA.
  User: lab
  Date: 2014/12/13
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <meta charset="utf-8">
    <script src="js/jquery-1.11.1.min.js"></script>
    <script src="//cdn.jsdelivr.net/ace/1.1.8/min/ace.js" type="text/javascript" charset="utf-8"></script>
    <style type="text/css" media="screen">
        .editor {
            position: absolute;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
            width: 580px;
            height: 500px;
            position: relative;
        }


    </style>


</head>
<body>

<%--<div id="editorHead" class="editor" style="height: 100px;">--%>
<%--public class PesistenceLogi_12345 implement ActionLogi{--%>

<%--@Override--%>
<%--public void doHandle(Connection conn,DataBean data){--%>
<%--//TODO:在方法中实现数据库写入逻辑--%>
<%--</div>--%>
<div id="editorLogi" class="editor">
    <%
        CompileAction action = new CompileAction();
//    UUID uuid = UUID.randomUUID();
//        String actionName = "UserId" + System.currentTimeMillis();
        String actionName = "UserId_1";
        String template = action.clazzTemplate(actionName, "\n\n");

    %>

    <%=template%>
</div>
<%--<div id="editorTail" class="editor" style="height: 100px;">--%>
<%--}--%>
<%--}--%>
<%--</div>--%>
<button id="btnSubmit">Submit</button>
<script>
    $(document).ready(function () {

        $("#btnSubmit").click(function () {
            var editor = ace.edit("editorLogi");
//                alert(editor.getSession().getValue());
            var action = editor.getValue();
            <%="var actionName = '"+actionName+"'"%>
//            alert(actionName);
//                var action = $("#editorLogi").text();
            console.log(action);
            $.ajax({
                url: "/ssc/compile?actionName=" + actionName+"&action="+action
            })
        });

    });
</script>
<script>
    //    var editorHead = ace.edit("editorHead");
    //    editorHead.setTheme("ace/theme/monokai");
    //    editorHead.getSession().setMode("ace/mode/java");

    //    var editor = ace.edit("editorTail");
    //    editor.setTheme("ace/theme/monokai");
    //    editor.getSession().setMode("ace/mode/java");

    var editorLogi = ace.edit("editorLogi");
    editorLogi.setTheme("ace/theme/monokai");
    editorLogi.getSession().setMode("ace/mode/java");
</script>
</body>
</html>
