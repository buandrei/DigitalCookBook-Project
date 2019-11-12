<#ftl>
<#import "/spring.ftl" as spring />
<html lang="en">
<head>
<#include '/bootstrap_header.ftl'>

</head>
<body>


<header>
<div class="container">
	<header>
	<#include '/top_of_pages.ftl'>
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
					<li class="nav-item" ><a class="nav-link" href="/retete/tutoriale_incepatori">Tutoriale de gatit</a></li>
					<li class="nav-item "><a  class="nav-link" href="/retete/upload_recipe">Incarca reteta</a></li>
					<li class="nav-item"><a class="nav-link" href="/events">Evenimente</a></li>
				</ul>
			</div>
		</nav>
	</header>
	<main role="main">
		<div class="panel panel-default">

			<div class="jumbotron text-dark p-3 mb-0" style="min-height: 240px;">
				<div class="row text-center">
					<div class="col-sm-4 block1 w-100" >
							<img style="max-height:240px;" src="data:image/*;base64, ${recipePhoto}" class="img-fluid" alt="Responsive image">
					</div>
					<div class="col-sm-8 block1 text-left">
						<div class="row">
							<div class="col align-self-start">
								<h2 class="">${recipe.name!''}</h3>

								<p class="lead my-3 ">
									${recipe.description!''}
								</p>
							</div>
						</div>
						<div class="row">
							<div class="col align-self-end">
								<div class="row">
								<div class="col-sm-4">
									<p class="text-muted font-weight-lighter font-italic text-left align-text-bottom">
										Categorie: ${recipeCategory.name!''}
									</p>
								</div>
								<div class="col-sm-8  ">
									<p class=" text-right align-text-bottom">
									Autor: --
									</p>
								</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="embed-responsive embed-responsive-16by9">
			  <iframe class="embed-responsive-item" src="${recipe.link!''}" allowfullscreen></iframe>
			</div>

			<div class="jumbotron">
				<div class="row">
					<div class="col-sm-4 block1 w-100 text-center">
						<div class="row">
							<p style="font-size:1.1em;">
								Reteta a fost accesata de :<b> ${recipe.accessCount!''} </b>ori
							</p>
						</div>

						<div class="row">
							<p style="font-size:1.1em;">
								Portii :<b> ${recipe.portions!''} </b>
							</p>
						</div>

						<div style="padding:5px 0" class="row text-left" style="font-size:1.1.1em;">
							<img style="max-width:25px;margin-right:10px"  src="/images/knife.png">Timp preparare
							<span class="col text-right">
								${preparationTime!''}
							</span>
						</div>
						<div style="padding:5px 0" class="row align-left" style="font-size:1.1.1em;">
							<img style="max-width:25px;margin-right:10px"  src="/images/cooking_pan.png">Timp gatire
							<span class="col text-right">
								${cookingTime!''}
							</span>
						</div>

						<div class="row  text-left" style="font-size:1.1em;" id="starDiv">
							<p style="padding:0" class="col text-left">Rating</p>
							<span style="padding:0" class="col text-right text-muted font-italic"> (${recipe.rating!''})

								<#assign x= recipe.rating?floor >
								<#assign y= 5 - x>
								<#if x == 0>
									<#list 1..5 as i>
										<span class="fa fa-star"></span>
									</#list>
								<#elseif (x > 0) >
									<#list 1..x as z>
										<span class="fa fa-star rating_checked"></span>
									</#list>
									<#list 0..<y as q>
										<span class="fa fa-star"></span>
									</#list>
								<#else>
								</#if>

								<!--<span class="fa fa-star rating_checked"></span>-->


							</span>
						</div>
					</div>
					<div class="col-sm-8 block1 text-left">
						<ul class="list-group list-group-flush">
							<#list ingredients as ingredients>
								<li class="list-group-item">${ingredients.name!''}</li>
							</#list>
						</ul>
					</div>
				</div>
				<div class="row  text-center display-div give-rating-div" id="rating_grades" style="font-size:1.1em;">
					<div class="col">

						<p><b>ACORDA UN RATING</b></p>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="rradiobuttons" id="grade1" value="1">
							<label class="form-check-label" for="grade1">1</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="rradiobuttons" id="grade2" value="2">
							<label class="form-check-label" for="grade2">2</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="rradiobuttons" id="grade3" value="3">
							<label class="form-check-label" for="grade3">3</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="rradiobuttons" id="grade4" value="4">
							<label class="form-check-label" for="grade4">4</label>
						</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="radio" name="rradiobuttons" id="grade5" value="5">
							<label class="form-check-label" for="grade5">5</label>
						</div>
						<input type="hidden" value="" id="rvalue">
						<script>
							$('input[type=radio][name=rradiobuttons]').change(function() {
								document.getElementById("rvalue").value = this.value;
							});
						</script>
						<div class="text-center">
							<button id="give_rating" onclick="doAjaxPost()" class="btn btn-info">Acorda	</button>
						</div>

					</div>
					<p id="rated_info"></p>
				</div>
			</div>
			<div class=" text-center text-white overflow-hidden" style="background-color:#e9ecef">
				<h2 class="text-dark display-5">Mod de preparare</h2>
				<a href="/retete/pdfview?id=${recipe.id?c}" class="btn btn-info"><b>Salveaza ca PDF</b></a>
				<div class="my-3 py-3" style="font-size:1.3em;">
					<div class="bg-dark box-shadow mx-auto" style="width: 95%; border-radius: 21px;">
						${recipeIngredient.instructions!''}
					</div>
				</div>

			</div>
			<input type="hidden" value=${recipe.id?c}	id="recipeId">
			<br>
			<a  class="btn btn-secondary btn-lg btn-block" href="/retete/list_all">Inapoi</a>
			<a  class="btn btn-secondary btn-lg btn-block" href="/retete/editare_reteta?id=${recipe.id?c}">Editare</a>
		</div>
	</main>
	<#include '/bootstrap_footer.ftl'>
</div>
</body>
</html>
