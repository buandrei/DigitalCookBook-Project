<#ftl>
<#import "/spring.ftl" as spring />
<html lang="ro">
<head>
<#include '/bootstrap_header.ftl'>
</head>
<body>

<header>
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
								<a href="../retete/list_all">Cautare simpla</a>
							</li>
							<li>
								<a href="/specific_ingredients">Cauta dupa ingrediente specifice</a>
							</li>

					</ul>
				</li>
			<li><a href="/promotion">Promovare</a></li>
			<li><a href="/tutorials">Tutoriale de gatit</a></li>
			<li><a href="/retete/upload_recipe">Incarca reteta</a></li>
			<li><a href="/events">Evenimente</a></li>
		</ul>
	  </div>
	</nav>
</div>
</header>

<main role="main">
<div class="container">

<!-- BODY -->
		<div class="bd-example">

			<div class="list-group">
				<#list recipe_list as recipe>
				<a href="/promotion/add_promo_second_step?id=${recipe.id!''}" class="list-group-item list-group-item-action flex-column align-items-start">
					<div class="d-flex w-100 justify-content-between">
						<h5 class="mb-1">${recipe.denumire!''}</h5>
						<small>${recipe.data_adaugarii?date}</small>
					</div>
					<p class="mb-1">${recipe.descriere!''}</p>
				</a>
				</#list>
			</div>
		</div>

</div>
</main>

<#include '/bootstrap_footer.ftl'>
</body>
</html>


