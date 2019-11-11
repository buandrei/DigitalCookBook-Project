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

	<nav style="margin-bottom:15px" class="navbar navbar-expand-sm navbar-dark bg-dark">
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarsExample03">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item">
					<a class="nav-link" href="/">Home</a>
				</li>
				<li class="nav-item dropdown ">
					<a class="nav-link dropdown-toggle " style="padding:0" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Retete</a>
					<div class="dropdown-menu" style="top:30px" aria-labelledby="dropdown03">
						<a class="dropdown-item" href="/retete/list_all">Cautare simpla</a>
						<a class="dropdown-item" href="/retete/cauta_dupa_ingrediente">Cauta dupa ingrediente specifice</a>
					</div>
				</li>
                <li class="nav-item dropdown active">
                    <a class="nav-link dropdown-toggle " style="padding:0" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Promovari</a>
                    <div class="dropdown-menu" style="top:30px" aria-labelledby="dropdown03">
                        <a class="dropdown-item" href="/promotion/add_promo_first_step">Adaugare promovare</a>
                        <a class="dropdown-item" href="/promotion/list_all_promotions">Listare toate promovarile de retete</a>
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
	<form  name="promotionSaveForm" method="POST" action="/promotion/savePromotion" id="promoForm" enctype="multipart/form-data">

		<div class="panel panel-default">
			<div class="jumbotron text-dark p-3 mb-0" style="min-height: 240px;">
				<div class="row text-center">
					<div class="col-sm-4 block1 w-100">
							<img src="data:image/*;base64, ${recipe.photo.getEncodedContent()!''}" class="card-img-top zoom-image" >
					</div>
					<div class="col-sm-8 block1 text-left">
						<div class="row">
							<div class="col align-self-start">
								<h2 class="">${recipe.name!''}</h2>

								<p class="lead my-3 ">
									${recipe.description!''}
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
				<input type="hidden" id="idRecipe" name="idRecipe" value="${recipe.id!''}">

			</div>
		</div>


		<input value="Salveaza promovarea" class="btn btn-primary btn-lg btn-block" type="submit"/>

	</form>
</div>
</main>

<#include '/bootstrap_footer.ftl'>

</body>
</html>
