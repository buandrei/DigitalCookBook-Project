<#ftl>
<#import "/spring.ftl" as spring />
<html lang="ro">
<head>
<#include '/bootstrap_header.ftl'>
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
				<li class="nav-item"><a class="nav-link" href="/promotion">Promovare</a></li>
				<li class="nav-item" ><a class="nav-link" href="/retete/tutorials">Tutoriale de gatit</a></li>
				<li class="nav-item "><a  class="nav-link" href="/retete/upload_recipe">Incarca reteta</a></li>
				<li class="nav-item"><a class="nav-link" href="/events">Evenimente</a></li>
			</ul>
		</div>
	</nav>

	<form name="recipeIngredientsSearchForm"  method="post" action="/retete/retete_rezultate" id="recipeIngredientsSearchForm" enctype="multipart/form-data" >
		<div class="card">
			<h4 class="text-center bg-info card-header">Ingrediente</h3>
			<div class="card-body">
				<div id="ingredient_checkboxes" class="control-group">
					<#list ingredientsMap?keys as key>
						<h4 class="text-center bg-transparent card-header">${key}</h3>

						<hr style="margin:0;" class="hr_styled">
						<#list ingredientsMap[key] as ingredients>
						<div class="form-check form-check-inline" style="min-width:210px;">
							<div class="custom-control custom-checkbox">
								<input value="${ingredients.id!''}" type="checkbox" class="custom-control-input" id="checkbox_${ingredients.id!''}">
								<label class="custom-control-label" for="checkbox_${ingredients.id!''}">${ingredients.name!''}</label>
							</div>
						</div>
						</#list>
					</#list>

				</div>
				<input id ="ingredientsId" name="ingredientsId" type="hidden" value=""/>
				<input id ="isMoreIngredientsChecked" name="isMoreIngredientsChecked" type="hidden" value=""/>
			</div>
		</div>

		<div class="jumbotron text-center">
			<h4 class="text-center bg-transparent">Atentie</h4>
			<p class="lead">Aceasta functionalitate v-a returna retetele care au doar ingredientele alese!</p>
			<p class="lead">Daca doriti sa cautati retete care contin ingredientele alese plus altele , atunci bifati optiunea de mai jos</p>
			<p class="lead">Pentru rapiditatea si acuratetea cautarii, alegeti cat mai multe ingrediente</p>

			<div class="custom-control custom-checkbox">
				<input  class="custom-control-input" name="moreIngredients" value="false" type="checkbox" id="moreIngredients" >
				<label id="moreIngredients_checkbox"  class="custom-control-label" for="moreIngredients">
					<b>Cauta retete care contin alte ingrediente fata de cele selectate!</b>
				</label>
			</div>

		</div>
		<input value="Cauta dupa ingredientele selectate" class="btn btn-primary btn-lg btn-block" type="submit"/>
	</form>

<script src="/js/search_recipe.js" ></script>
<#include '/bootstrap_footer.ftl'>
</body>
</html>



