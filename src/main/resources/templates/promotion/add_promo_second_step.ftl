<#ftl>
<#import "/spring.ftl" as spring />
<html lang="en">
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
			<li><a href="/promotion/add_promo_first_step">Promovare</a></li>
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
	<form  name="promotionSaveForm" method="POST" action="/promotion/savePromotion" id="promoForm" enctype="multipart/form-data">

		<div class="panel panel-default">
			<div class="jumbotron text-dark p-3 mb-0" style="min-height: 240px;">
				<div class="row text-center">
					<div class="col-sm-4 block1 w-100">
							<img src="" class="img-fluid" alt="Responsive image">
					</div>
					<div class="col-sm-8 block1 text-left">
						<div class="row">
							<div class="col align-self-start">
								<h2 class="">${recipe.denumire!''}</h2>

								<p class="lead my-3 ">
									${recipe.descriere!''}
								</p>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="container">
				<h2>Selectati promovarea dorita!</h2>
				<p>Avem o gama variatat a ......</p>
				<div id="type_checkboxes">
   				    <#list promotionTypes as type>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="typeCheckboxes" id="promotype_${type.id!''}" value="${type.id!''}">
						  <label class="form-check-label" for="promotype_${type.id!''}">${type.denumire!''}</label>
						</div>
						<!--TODO Afisam si campurile de suma -->
    				</#list>
				</div>
				<input type="hidden" id="idTipPromovare" name="idTipPromovare" value="">

			</div>
		</div>


		<input value="Salveaza promovarea" class="btn btn-primary btn-lg btn-block" type="submit"/>

	</form>
</div>
</main>

<#include '/bootstrap_footer.ftl'>

</body>
</html>
