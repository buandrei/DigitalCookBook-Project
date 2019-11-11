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
			<li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle " style="padding:0" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Promovari</a>
                <div class="dropdown-menu" style="top:30px" aria-labelledby="dropdown03">
							<a class="dropdown-item" href="/promotion/add_promo_first_step">Adaugare promovare</a>
    						<a class="dropdown-item" href="/promotion/list_promotion_first_step">Listare promovare retete</a>
							<a class="dropdown-item" href="/promotion/delete_promotion">Stergere promovare</a>
                </div>
            </li>
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
			<div class="d-flex w-100 justify-content-between">
					<h5 class="mb-1">${recipe.name!''}</h5>
					<small>${recipe.addDate?date}</small>
					<p class="mb-1">${recipe.description!''}</p>
                    <p class="mb-1">${promotion.id!''}</p>
                    <p class="mb-1">${promotionType.name!''}</p>
                    <p class="mb-1">${promotion.startDate!''}</p>
                    <p class="mb-1">${promotion.endDate!''}</p>
                    <p class="mb-1">${promotionType.description!''}</p>
			</div>
		</div>

</div>
</main>

<#include '/bootstrap_footer.ftl'>
</body>
</html>