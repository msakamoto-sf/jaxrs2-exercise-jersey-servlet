<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="UTF-8">
<title>jaxrs2-exercise-jersey-servlet</title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<script>
function call_api(method, path) {
    $.ajax({
        url: 'webapi/ex1/' + path,
        type: method,
        dataType: 'text'
    }).done(function(responseText) {
        $("#restext").val(responseText);
    });
};
function call_RequestParametersDemoResource(method) {
    $.ajax({
        url: 'webapi/ex1/typical-request-parameters-demo/item/' + $('input:text[name="id"]').val(),
        type: method,
        data: {
            age: $('input:text[name="age"]').val(),
            is_admin: $('input:text[name="is_admin"]').val(),
            name: $('input:text[name="name"]').val()
        },
        dataType: 'text'
    }).done(function(responseText) {
        $("#restext").val(responseText);
    });
};
</script>
</head>
<body>
<h2>jaxrs2-exercise-jersey-servlet</h2>
<a href="webapi/myresource" target="_blank">Hello, World</a>
<hr>
<input type="button" value="ContextDemoResource" onclick="call_api('GET', 'context-demo/pp1a/pp1b/pp2a/pp2b/pp3?q1=q1va&q1=q1vb&q1=q1vc&q2=q2va&q2=q2vb&q3=q3v');">
<hr>
HttpMethodAnnotationsDemoResource<br>
<input type="button" value="GET" onclick="call_api('GET', 'http-method-annotations-demo');">&nbsp;
<input type="button" value="POST" onclick="call_api('POST', 'http-method-annotations-demo');">&nbsp;
<input type="button" value="PUT" onclick="call_api('PUT', 'http-method-annotations-demo');">&nbsp;
<input type="button" value="DELETE" onclick="call_api('DELETE', 'http-method-annotations-demo');">
<hr>
PathDemoResource<br>
<input type="button" value="/" onclick="call_api('GET', 'resource-paths-demo');">&nbsp;
<input type="button" value="/sub1" onclick="call_api('GET', 'resource-paths-demo/sub1');">&nbsp;
<input type="button" value="/sub/sub2" onclick="call_api('GET', 'resource-paths-demo/sub/sub2');">
<hr>
RequestParametersDemoResource<br>
<a href="webapi/ex1/typical-request-parameters-demo/item/10" target="_blank">/item/10</a>&nbsp;
<a href="webapi/ex1/typical-request-parameters-demo/item/10/admin" target="_blank">/item/10/admin</a>
<br>
<form name="RequestParametersDemoResource">
id:<input type="text" name="id" value="10">&nbsp;
age:<input type="text" name="age" value="100">&nbsp;
is_admin:<input type="text" name="is_admin" value="false">&nbsp;
name:<input type="text" name="name" value="bob">&nbsp;
<br>
<input type="button" value="GET" onclick="call_RequestParametersDemoResource('GET');">&nbsp;
<input type="button" value="POST" onclick="call_RequestParametersDemoResource('POST');">
</form>

<hr>
<textarea name="restext" id="restext" cols="100" rows="10"></textarea>
<input type="button" value="clear" onclick="$('#restext').val('');">
<hr>
ResponseDemoResource<br>
<a href="webapi/ex1/typical-response-demo/created201" target="_blank">201</a>&nbsp;
<a href="webapi/ex1/typical-response-demo/seeother303" target="_blank">303</a>&nbsp;
<a href="webapi/ex1/typical-response-demo/tempredirect307" target="_blank">307</a>&nbsp;
<a href="webapi/ex1/typical-response-demo/custom500" target="_blank">500</a>&nbsp;
<a href="webapi/ex1/typical-response-demo/custom999" target="_blank">999</a><br>
<a href="webapi/ex1/typical-response-demo/binarydl" target="_blank">binarydl</a>&nbsp;
<a href="webapi/ex1/typical-response-demo/as_sjis" target="_blank">as_sjis</a>
<hr>
ResponseDemoResource<br>
<form action="webapi/ex1/validation-demo/demo1" method="GET" target="_blank">
intnum(10 - 20):<input type="text" name="intnum" value="10">&nbsp;
name2to5(2 - 5 chars):<input type="text" name="name2to5" value="abc">&nbsp;
<input type="submit" value="GET">
</form>
<hr>
Lifecycle and Filter Demo<br>
<a href="webapi/ex1/lifecycle-demo/sub1" target="_blank">/sub1 (NameBoundFilterA)</a><br>
<a href="webapi/ex1/lifecycle-demo/sub2" target="_blank">/sub2 (NameBoundFilterB)</a><br>
<a href="webapi/ex1/lifecycle-demo/sub3" target="_blank">/sub3 (No NameBoundFilters)</a><br>
<a href="webapi/ex1/lifecycle-demo/sub4" target="_blank">/sub4 (NameBoundFilterA + AlwaysUnauthorized + NameBoundFilterB)</a><br>
<a href="webapi/ex1/lifecycle-demo/sub5" target="_blank">/sub5 (NameBoundFilterA + AlwaysThrowException + NameBoundFilterB)</a><br>
<hr>
ExceptionDemoResource<br>
<a href="webapi/ex1/exception-demo/demo1" target="_blank">/demo1 (throws CustomNotFoundException)</a><br>
<a href="webapi/ex1/exception-demo/demo2" target="_blank">/demo2 (throws CustomException)</a><br>
<a href="webapi/ex1/exception-demo/demo3" target="_blank">/demo3 (throws CustomException2)</a><br>

</body>
</html>
