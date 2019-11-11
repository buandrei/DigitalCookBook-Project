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

	<nav style="margin-bottom:15px" class="navbar navbar-expand-sm navbar-dark bg-dark">
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarsExample03">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item">
					<a class="nav-link" href="/">Home</a>
				</li>
				<li class="nav-item dropdown active">
					<a class="nav-link dropdown-toggle " style="padding:0" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Retete</a>
					<div class="dropdown-menu" style="top:30px" aria-labelledby="dropdown03">
						<a class="dropdown-item" href="/retete/list_all">Cautare simpla</a>
						<a class="dropdown-item" href="/retete/cauta_dupa_ingrediente">Cauta dupa ingrediente specifice</a>
					</div>
				</li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle " style="padding:0" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Promovari</a>
                    <div class="dropdown-menu" style="top:30px" aria-labelledby="dropdown03">
                        <a class="dropdown-item" href="/promotion/add_promo_first_step">Adaugare promovare</a>
                        <a class="dropdown-item" href="/promotion/list_promotion_first_step">Listare promovare retete</a>
                        <a class="dropdown-item" href="/promotion/delete_promotion">Stergere promovare</a>
                    </div>
                </li>
				<li class="nav-item" ><a class="nav-link" href="/retete/tutorials">Tutoriale de gatit</a></li>
				<li class="nav-item "><a  class="nav-link" href="/retete/upload_recipe">Incarca reteta</a></li>
				<li class="nav-item"><a class="nav-link" href="/events">Evenimente</a></li>
			</ul>
		</div>
	</nav>

</header>

<main role="main">
<div class="container">

<!-- BODY -->
		<div class="bd-example">
			<div class="list-group">
				<#list recipe_list as recipe>
				<a href="/promotion/delete_promotion_second_step?id=${recipe.id!''}" class="list-group-item list-group-item-action flex-column align-items-start">
					<div class="d-flex w-100 justify-content-between">
						<h5 class="mb-1">${recipe.name!''}</h5>
						<small>${recipe.addDate?date}</small>
					</div>
					<p class="mb-1">${recipe.description!''}</p>
				</a>
				</#list>
			</div>
		</div>

</div>
</main>

<#include '/bootstrap_footer.ftl'>
</body>
</html>