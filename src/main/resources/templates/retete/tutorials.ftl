<#ftl>
<#import "/spring.ftl" as spring />
<html lang="ro">
<head>
<#include '/bootstrap_header.ftl'>
</head>
<body>
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
					<li class="nav-item dropdown">
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

					<li class="nav-item active" ><a class="nav-link" href="/retete/tutoriale_incepatori">Tutoriale de gatit</a></li>
					<li class="nav-item "><a  class="nav-link" href="/retete/upload_recipe">Incarca reteta</a></li>
					<li class="nav-item"><a class="nav-link" href="/events">Evenimente</a></li>
				</ul>
			</div>
		</nav>
	</header>
	<main role="main">
		<#if nothingFound?? >
		<p class="text-center">${nothingFound}</p>
		</#if>

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
							<a class="text-dark " href="/retete/vizualizare_reteta?id=${recipe.id?c}">
								<img style="max-height:195px;" src="data:image/*;base64, ${recipe.photo.getEncodedContent()!''}" class="card-img-top zoom-image" >
							</a>
						</div>
						<a class="text-dark " href="/retete/vizualizare_reteta?id=${recipe.id?c}">
							<div class="card-body">
								<h5 class="card-title" style="height:40px;">${recipe.name!''}</h5>
								<hr class=".hr_styled"/>
								<h6 class="card-title text-muted font-italic" style="height:30px;">Categoria: ${recipe.recipeCategory.getName()!''}</h6>
								<p class="card-text" style="height:120px;">${recipe.description!''}</p>
								<div class="row  text-center" style="font-size:1.1em;height:30px" id="starDiv">
									<span style="padding:0" class="col text-right text-muted font-italic">
										<#assign i= recipe.rating?floor >
										<#assign y= 5 - i>
										<#if i == 0>
											<#list 1..5 as i>
												<span class="fa fa-star"></span>
											</#list>
										<#elseif (i > 0) >
											<#list 1..i as z>
												<span class="fa fa-star rating_checked"></span>
											</#list>
											<#list 0..<y as q>
												<span class="fa fa-star"></span>
											</#list>

										</#if>
									</span>
								</div>
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
	</main>
	<#include '/bootstrap_footer.ftl'>
</div>
<script src="/js/search_recipe.js" ></script>
</body>
</html>



