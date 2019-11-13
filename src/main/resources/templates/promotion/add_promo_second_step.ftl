<#ftl>
<#import "/spring.ftl" as spring />
<html lang="en">
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
					<li class="nav-item dropdown active">
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

					<li class="nav-item" ><a class="nav-link" href="/retete/tutoriale_incepatori">Tutoriale de gatit</a></li>
					<li class="nav-item "><a  class="nav-link" href="/retete/upload_recipe">Incarca reteta</a></li>
					<li class="nav-item"><a class="nav-link" href="/events">Evenimente</a></li>
				</ul>
			</div>
		</nav>
	</header>
    <main role="main">

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
                    <h2 class="text-center">Selectati promovarea dorita!</h2>
                    <div id="type_checkboxes">
                    	<div class="card-deck">
                        <#list promotionTypes as type>
                         <div class="card">
						    <div class="card-body ${type.name!''}_card ">
						      	<h5 class="card-title">${type.name!''}</h5>
						      	<p class="card-text">${type.description!''}</p>
						      	<p class="card-text">${type.sumPromotion!''} RON</p>

						      	<div class="form-check form-check-inline" style="min-width:200px;">
									<div class="custom-control custom-radio">

										<input class="custom-control-input" type="radio" name="typeCheckboxes" id="promotype_${type.id!''}" value="${type.id!''}">
										<label class="custom-control-label" for="promotype_${type.id!''}">Selecteaza aceasta promotie</label>
									</div>
								</div>
						    </div>
						</div>
                            <!--TODO Afisam si campurile de suma -->
                        </#list>
                    </div>
                    <br/>
                    <input type="hidden" id="idPromotionType" name="idPromotionType" value="">
                    <input type="hidden" id="idRecipe" name="idRecipe" value="${recipe.id!''}">

                </div>
            </div>


            <input value="Salveaza promovarea" class="btn btn-primary btn-lg btn-block" type="submit"/>

        </form>
    </main>
<#include '/bootstrap_footer.ftl'>
</div>
</body>
</html>
