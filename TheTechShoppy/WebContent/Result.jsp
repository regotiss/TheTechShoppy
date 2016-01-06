<!doctype html>
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Result</title>
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link rel="stylesheet" href="css/style.css">
		<link rel="stylesheet" href="css/branchstyle.css">
		<link rel="stylesheet" href="css/result.css">
		
		<script>
			function clearText(field) {
			if (field.defaultValue == field.value) field.value = '';
			else if (field.value == '') field.value = field.defaultValue;
			}
		</script>
		<style type="text/css">
		control{
	
			display:-webkit-box;
			-webkit-box-orient:horizontal;
		}
		.carousel-control {
			opacity:1;
			background:white;
			border:1px solid gray;
			width:40px;
			height:40px;
			border-radius:10px;
        }
		
		</style>
	</head>
	<body >
		<%@ page import = "model.ProductBean" %>
		<div id="big_wrapper">
			<div id="wrapper">	
				<header id="header">
					<div id="logo"><a href="index.html"><img src="images/logo.png" /></div></a>
					<div id="search_box">
					  <form action="Result" method="get">
						<input type="text" value="Enter keyword here..." name="book" size="10" id="searchfield"  onfocus="clearText(this)" onblur="clearText(this)"/>
						<input type="submit" name="Search" value="" alt="Search" id="searchbutton" />
					  </form>
					</div>
				</header>
				
				<nav id="nav">
					<ol id="menu">
					
						<li><a href="#">Categories</a>
							<ol>
								<li><a href="cseitAlgo.html">CSE and IT</a></li>
								<li><a href="electronics.html">Electronics</a></li>
								<li><a href="electrical.html">Electrical</a></li>
								<li><a href="civil.html">Civil</a></li>
								<li><a href="mechanical.html">Mechanical</a></li>
							</ol>
						</li>
						<li><a href="contact.html">Contact</a></li>
						<li><a href="about.html">About</a></li>
					</ol>
				</nav>	
			</div>
			<br>
			<br>

			<!-- best result start -->
			<div id="best" class="container-fluid">
			
					<div id="best-book-upper" class="row">
					<h2>Best Result</h2>
					<div id="best-book-left" class="col-xs-6">
						<% ProductBean[] res=(ProductBean [])request.getAttribute("bestresult");
							String seq[] =(String [])request.getAttribute("seq");
						%>
						<div class="col-xs-4">
							<img src=<%= res[0].getImgPath() %> alt="Image not available" class="img-responsive" width="200px" height="200px">
						</div>
						<div class="col-xs-8">
							<div id="best-title"><%= res[0].getTitle() %></div>
							<hr><h4><span class="label label-primary">Best Price:Rs.<%= res[0].getPrice() %></span></h4>
						</div>
					</div>
					<div  id="best-book-right" class="col-xs-6">
						<table class="table table-bordered">
							<thead>
							<tr>
								<th>Store</th>
								<th>Price</th>
								<th>Details</th>
							</tr>
							</thead>
							<tbody>
							<tr>
								<td><img src=<%= "images/"+seq[0] %> class="img-responsive" width="100px" height="50px"/></td>
								<td>Rs.<%= res[0].getPrice() %></td>
								<td><a href=<%= "Redirect.jsp?tostore="+res[0].getStoreLink()+"&store=images/"+seq[0] %> target="_blank"><div class="go_to_store">To Store</div></a></td>
							</tr>
							<tr>
								<td><img src=<%= "images/"+seq[1] %> class="img-responsive" width="100px" height="50px"/></td>
								<td>Rs.<%= res[1].getPrice() %></td>
								<td><a href=<%= "Redirect.jsp?tostore="+res[1].getStoreLink()+"&store=images/"+seq[1] %> target="_blank"><div class="go_to_store">To Store</div></a></td>
							</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<hr>
			
			<div class="container-fluid">
			<div id="resultwrapper">
				<h1>All Amazon Results</h1>

       <!-- <div class="well"> -->
       	
            <div id="myCarousel" class="carousel slide">
                
                <!-- Carousel items -->
				
                <div class="carousel-inner ">
                	<%
                		java.util.List<ProductBean> list= (java.util.List<ProductBean>)request.getAttribute("ProductAmazon");
						int i=0;
						for(ProductBean product:list){
							
							if(i%4==0&&i==0){%>
							<div class="item active">
							<div class="row">
							<%}else if(i%4==0){%>
				        		<div class="item">
	                        	<div class="row">
	                		<% }%>
                
                            <div class="col-xs-3">
								<div class="result-panel">
									<div class="result-body">
                                        <a href=<%= "Redirect.jsp?tostore="+product.getStoreLink()+"&store=images/amazon.png"%> target="_blank"><img src=<%= product.getImgPath()%> width="100px" height="200px" alt="Image" class="img-responsive" title="who will cry when you die? by robin sharma"></a>
                                    </div>
                                    <div class="result-heading">
                                        <h4 class="panel-title"><%= product.getTitle()%></h4>
                                        <div class="price">Price:Rs <%= product.getPrice()%></div>
										<a href=<%= product.getStoreLink()%> target="_blank"><div class="go_to_store">To Store</div></a>
                                    </div>
                                    
								</div>
                            </div>
                         <% if(i%4==3){%>
                            	</div>
                            	</div>
                            	<% }
							i++; } 
							if((list.size()%4)!=0)
							{
						%>
								</div>
								</div>
						<% }%>    
                                               
					</div>						 
					<a class="left carousel-control" href="#myCarousel" data-slide="prev"><img src="images/back.png"></a>
					<a class="right carousel-control" href="#myCarousel" data-slide="next"><img src="images/forward.png"></a>
					
				</div>
				<h1>All Flipkart Results</h1>

       <!-- <div class="well"> -->
       	
            <div id="myCarousel1" class="carousel slide">
                
                <!-- Carousel items -->
				
                <div class="carousel-inner ">
                	<%
                		list= (java.util.List<ProductBean>)request.getAttribute("ProductFlipkart");
						i=0;
						for(ProductBean product:list){
							
							if(i%4==0&&i==0){%>
							<div class="item active">
							<div class="row">
							<%}else if(i%4==0){%>
				        		<div class="item">
	                        	<div class="row">
	                		<% }%>
                
                            <div class="col-xs-3">
								<div class="result-panel">
									<div class="result-body">
                                        <a href=<%= "Redirect.jsp?tostore="+product.getStoreLink()+"&store=images/flipkart.jpg" %> target="_blank"><img src=<%= product.getImgPath()%> width="100px" height="200px" alt="Image" class="img-responsive" title="who will cry when you die? by robin sharma"></a>
                                    </div>
                                    <div class="result-heading">
                                        <h4 class="panel-title"><%= product.getTitle()%></h4>
                                        <div class="price">Price:Rs <%= product.getPrice()%></div>
										<a href=<%= product.getStoreLink()%> target="_blank"><div class="go_to_store">To Store</div></a>
                                    </div>
                                    
								</div>
                            </div>
                         <% if(i%4==3){%>
                            	</div>
                            	</div>
                            	<% }
							i++; } 
							if((list.size()%4)!=0)
							{
						%>
								</div>
								</div>
						<% }%>    
                                               
					</div>						 
					<a class="left carousel-control" href="#myCarousel1" data-slide="prev"><img src="images/back.png"></a>
					<a class="right carousel-control" href="#myCarousel1" data-slide="next"><img src="images/forward.png"></a>
					
				</div>
			</div>
			</div>
			<footer id="the_footer">
				Copyright &copy; 2015 <a href="#">WCE</a> | Designed by <a target="_blank"  href="#">GrNo:* Batch</a></div>
			</footer>
		</div>
		<script src="js/jquery.js"></script>
		<script src="js/bootstrap.min.js"></script>
	</body>
</html>