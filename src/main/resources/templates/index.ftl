<#ftl]
<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="ro">
<head>
<#include "/bootstrap_header.ftl">
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
                    <li class="nav-item active">
                        <a class="nav-link" href="/">Home</a>
                    </li>
                    <li class="nav-item dropdown ">
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

		<section class="jumbotron text-center">
			<div class="container">
				<h1 class="jumbotron-heading">Bine ati venit!</h1>
				<p class="lead text-muted">Suntem bucurosi sa va prezentam site-ul de retete DigitalCookBook. Un site in care dvs puteti sa impartasiti retetele proprii cu alti utilizatori, sa le promovati sau sa promovati evenimente!</p>
			</div>
		</section>
		<section class="jumbotron text-center">
			<div class="container">
				<h3 class="jumbotron-heading">Avem o selectie de retete promovate!</h3>
			</div>
			<hr style="hr_styled">
			<#if recipeMap?size != 0>
			<#list recipeMap?keys as key>

				<h4 class="text-center custom_header_for_${key}"></span><b>${key}</b></h4>
				<div class="card-deck" style="margin-bottom:15px;">
				<#assign x = 0>
				<#list recipeMap[key] as recipe>

					<#if x==3>
						</div>
						<div class="card-deck" style="margin-bottom:15px;">
						<#assign x = 0>
					</#if>
						<div class="card
							<#if recipe.idTipPromotie == 1>
								platinum_card
							<#elseif recipe.idTipPromotie == 2>
								golden_card
							<#elseif recipe.idTipPromotie == 3>
								bronze_card
							<#else>
							</#if>
						" style="width: 16rem; max-width:350px;">
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
			</#list>
			</#if>
		</section>
	</main>
	<#include '/bootstrap_footer.ftl'>
</div>
</body>
</html>


