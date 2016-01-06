<html>
<head>
<title>A web page that points a browser to a different page after 2 seconds</title>
<meta http-equiv="refresh" content=<%= "\"2; URL="+request.getParameter("tostore")+"\"" %>>
<meta name="keywords" content="automatic redirection">
</head>
<body>
	
	<p><center><img src="images/logo.png"></p></center>
	<p><center><h3>On your way to </h3><img src=<%= request.getParameter("store") %> alt="<%= request.getParameter("store") %>" class="img-responsive" width="130px" height="50px"/></center></p>
	<p><center><img src="images/loading.gif"></center>
	<p ><h3 style="text-align:center">If your browser doesn't automatically go there within a few seconds, </h3></p>
	<p><center><a href=<%= request.getParameter("tostore") %>><h2>click here</h2></center></a></p>
	<p > <h3 style="text-align:center">to go there manually.</h3></p>
</body>
</html>