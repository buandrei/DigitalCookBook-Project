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
	<div class="panel panel-default">

		<div class="jumbotron text-dark p-3 mb-0" style="min-height: 240px;">
			<div class="row text-center">
				<div class="col-sm-4 block1 w-100">
						<img src="<c:url value="recipe_images/${recipePhoto.cale_fisier}" />" class="img-fluid" alt="Responsive image">
				</div>
				<div class="col-sm-8 block1 text-left">
					<div class="row">
						<div class="col align-self-start">
							<h2 class="">${recipe.denumire!''}</h3>

							<p class="lead my-3 ">
								${recipe.descriere!''}
							</p>
						</div>
					</div>
					<div class="row">
						<div class="col align-self-end">
							<div class="row">
							<div class="col-sm-4">
								<p class="text-muted font-weight-lighter font-italic text-left align-text-bottom">
									Categorie: ${recipeCategory.denumire!''}
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
							Reteta a fost accesata de : ${recipe.cautari!''} ori
						</p>
					</div>
					<div class="row  text-left" style="font-size:1.1em;">
						<p style="padding:0" class="col text-left">Rating</p>
						<span class="col text-right text-muted font-italic"> (${recipe.rating!''})

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
								<#list 1..y as q>
									<span class="fa fa-star"></span>
								</#list>
							<#else>
							</#if>

							<!--<span class="fa fa-star rating_checked"></span>-->


						</span>
					</div>
					<div class="row text-left" style="font-size:1.1.1em;">
						<img style="max-width:25px;margin-right:10px"  src="/images/knife.png">Timp preparare
						<span class="col text-right">
							${preparationTime!''}
						</span>
					</div>
					<div class="row align-left" style="font-size:1.1.1em;">
						<img style="max-width:25px;margin-right:10px"  src="/images/cooking_pan.png">Timp gatire
						<span class="col text-right">
							${cookingTime!''}
						</span>
					</div>
				</div>
				<div class="col-sm-8 block1 text-left">
				<ul class="list-group list-group-flush">
					<#list ingredients as ingredients>
						<li class="list-group-item">${ingredients.denumire!''}</li>
					</#list>
				</ul>

				</div>
			</div>
		</div>
		<div class=" text-center text-white overflow-hidden" style="background-color:#e9ecef">
			<h2 class="text-dark display-5">Mod de preparare</h2>
			<a href="/retete/pdfview?id=${recipe.id?c}" class="btn btn-info"><b>Salveaza ca PDF</b></a>
			<div class="my-3 py-3" style="font-size:1.3em;">
				<div class="bg-dark box-shadow mx-auto" style="width: 95%; border-radius: 21px;">
			    	${recipeIngredient.instructiuni!''}
				</div>
			</div>

		</div>
		<br>
		<a  class="btn btn-secondary btn-lg btn-block" href="/retete/list_all">Inapoi</a>
	</div>
</div>
</main>

<#include '/bootstrap_footer.ftl'>

</body>
</html>
