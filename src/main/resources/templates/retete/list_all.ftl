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
			<li class="nav-item dropdown">
				<a class="nav-link dropdown-toggle " style="padding:0" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Retete</a>
				<div class="dropdown-menu" style="top:30px" aria-labelledby="dropdown03">

							<a class="dropdown-item" href="/retete/list_all">Cautare simpla</a>


							<a class="dropdown-item" href="/retete/search_ingredients">Cauta dupa ingrediente specifice</a>


				</div>
			</li>
			<li class="nav-item"><a class="nav-link" href="/promotion">Promovare</a></li>
			<li class="nav-item" ><a class="nav-link" href="/retete/tutorials">Tutoriale de gatit</a></li>
			<li class="nav-item active"><a  class="nav-link" href="/retete/upload_recipe">Incarca reteta</a></li>
			<li class="nav-item"><a class="nav-link" href="/events">Evenimente</a></li>
		</ul>
	</div>
	</nav>
	<!--BODY-->
	<#assign recipePageList = recipeList>
	<#assign x = 0>

	<div class="card-deck" style="margin-bottom:15px;">
		<#list recipeList.pageList as recipe>
		<#if x==3>
			</div>
			<div class="card-deck" style="margin-bottom:15px;">
				<#assign x = 0>
			</#if>
				<div class="card" style="width: 18rem; max-width:350px;">
					<div style="height:195px;">
						<a class="text-dark" href="/retete/vizualizare_reteta?id=${recipe.id?c}">
							<img src="data:image/*;base64, ${recipe.photo.getEncodedContent()!''}" class="card-img-top zoom-image" >
						</a>
					</div>
					<a class="text-dark" href="/retete/vizualizare_reteta?id=${recipe.id?c}">
						<div class="card-body">
							<h5 class="card-title" style="height:70px;">${recipe.name!''}</h5>
							<p class="card-text" style="height:140px;">${recipe.description!''}</p>
						</div>
					</a>
					<div class="card-footer">
						 <small class="text-muted">Author: ${recipe.user.getNume()!''} </small>
					</div>
				</div>
		<#assign x++>
	</#list>
	</div>
	<nav>
		<ul class="pagination">

			<#if recipePageList.firstPage>
				<li class="page-item disabled">
					<span class="page-link">Prev</span>
				</li>
			<#else>
				<li class="page-item">
					<span class="page-link"><a href="/retete/list_all/prev">Prev</a></span>
				</li>
			</#if>
			<#list 1..recipePageList.pageCount as tagStatus>
				<#if (recipePageList.page + 1) == tagStatus?counter>

				<li class="page-item active">
					<span class="page-link">
						${tagStatus?counter}
						<span class="sr-only">(current)</span>
					</span>
				</li>
				<#else >
					 <li class="page-item"><a class="page-link"  href="/retete/list_all/${tagStatus?counter}">${tagStatus?counter}</a></li>
				</#if>
			</#list>
			<#if recipePageList.lastPage>
				<li class="page-item disabled">
					<a class="page-link">Next</a>
				</li>
			<#else>
				<li class="page-item">
					<a class="page-link" href="/retete/list_all/next">Next</a>
				</li>

			</#if>

		</ul>
	</nav>

<#include '/bootstrap_footer.ftl'>
</body>
</html>



