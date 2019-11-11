[#ftl]
[#import "/spring.ftl" as spring /]
<!DOCTYPE html>
<html lang="en">
<head>
[#include "/bootstrap_header.ftl"]
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

                <li class="active"><a href="/events/save">Salvare events </a> </li>
				</ul>
		  </div>
		</nav>




	<!--
		<ol class="breadcrumb">

            <li class="active"><a href="/events/save">Salvare events </a> </li>
			<li><a href="/events/add">Adaugare events</a></li>
			<li><a href="/events/view">View events</a></li>

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