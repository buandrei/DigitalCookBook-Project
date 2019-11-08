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

		<nav class="navbar navbar-inverse .navbar-fixed-top">
		  <div class="container-fluid">
			<ul class="nav navbar-nav">
				<li><a href="/">Home</a></li>
				<li class="active dropdown">
					<a class="dropdown-toggle"  href="/retete">Cautare retete
					<span class="caret"></span></a>
					<ul class="dropdown-menu list-inline dropdown-menu-modified">

						<li>
							<a href="/retete">Cautare simpla</a>
						</li>
						<li>
							<a href="/specific_ingredients">Cauta dupa idIngrediente specifice</a>
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


		<div class="panel panel-default">
			<!-- Default panel contents -->
			<div class="panel-heading">Recipe List:
			<div style="float:right"><a href="employee/add"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></a></div>
			</div>
			<div class="card-group">
				[#list recipes as recipe]

				<div class="card mb-3" style="max-width: 540px;">
					<div class="row no-gutters">
						<div class="col-md-4">
							<img src="..." class="card-img" alt="...">
						</div>
						<div class="col-md-8">
							<div class="card-body">
								<a href="/retete/vizualizare_reteta?id=${recipe.id?c}">
									<h5 class="card-title">${recipe.denumire}</h5>
									<p class="card-text">${recipe.descriere}</p>
									<p class="card-text"><small class="text-muted">Author: cineva</small></p>
								</a>
							</div>
						</div>
					</div>
				</div>
				[/#list]
			</div>
		</div>
	</div>

</body>
</html>


