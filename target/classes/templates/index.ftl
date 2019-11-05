[#ftl]
[#import "/spring.ftl" as spring /]
<html lang="ro">
<head>
[#include '/bootstrap_header.ftl']
</head>
<body>
	<div class="container">
		<div class="page-header">
		<!-- TOP (top part, navbar)-->

			<div class="row">
			<!--logo-->
			  <div class="col-8" >
				<h1>Aici o sa avem logo</h1>
			  </div>

			   <div class="col-4" >
				<p>Aici o sa punem buton sign up si login .</p>
			  </div>
			</div>
		</div>
		<!--
		<div class="">
			<a href="/"><img src="[@spring.url '/images/logo.png' /]" width="100"/></a>
		</div>
		-->

		<!--navbar -->
		<nav class="navbar navbar-inverse .navbar-fixed-top">
		  <div class="container-fluid">
			<ul class="nav navbar-nav">
				<li class="active"><a href="/">Home</a></li>
				<li class="dropdown">
					<a class="dropdown-toggle"  href="/retete">Cautare retete
					<span class="caret"></span></a>
						<ul class="dropdown-menu list-inline dropdown-menu-modified">
							
								<li>
									<a href="/retete">Cautare simpla</a>
								</li>
								<li>
									<a href="/specific_ingredients">Cauta dupa ingrediente specifice</a>
								</li>
							
						</ul>
					</li>
				<li><a href="/promotion">Promovare</a></li>
				<li><a href="/tutorials">Tutoriale de gatit</a></li>
				<li><a href="/upload_recipe">Incarca reteta</a></li>
				<li><a href="/events">Evenimente</a></li>
			</ul>
		  </div>
		</nav>




	<!--
		<ol class="breadcrumb">
			<li class="active"><a href="/">Home</a></li>

			  <li><a href="/employee">Employees</a></li>
			  <li><a href="/projects">Projects</a></li>
			  <li><a href="/logout">Logout</a>
					  </li>
				[#if user??]
				[#else]
					  <li><a href="/login">Login</a></li>
				[/#if]
		</ol>
	-->

<!-- PAGE BODY-->
	

<!-- FOOTER -->

	</div>
</body>
</html>


