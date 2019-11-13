<#ftl>
<#import "/spring.ftl" as spring />
<html lang="ro">
<head>
  <#include '/bootstrap_header.ftl'>
</head>

<body >
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
		</nav>
	</header>
	<main role="role">
		<form  name="saveForm"  method="post" action="/retete/salvare_reteta_editata" id="editForm" enctype="multipart/form-data">
			<div class="card">
				<h3 class="card-header text-center"><b>Editare reteta <br>${recipe.name!''}</b></h3>
				<small><p style="margin:0" class="font-weight-lighter text-center font-italic">campurile marcate cu * sunt obligatorii</p></small>
				<div class="card">
					<h4 class="text-center bg-info card-header">Informatii reteta</h3>
					<div class="card-body">

					<div class="bs-example">
						<div id="validateALert" style='display:none' class="alert alert-danger ">
						</div>
					</div>

					<#if errors??>
						<#list errors as error>
						<span style="color:red"> ${error}</span>
						<br>
						</#list>
					</#if>

						<div class="form-group">
							<label for="name_input">Denumire *</label>
							<input name="name" value="${recipe.name!''}" type="text" class="form-control" id="name_input" aria-describedby="Denumire" placeholder="Denumirea retetei" >
							<b><span class="name_remaining" style="color:#1d91d1;"></span></b> caractere ramase
							<br>
						</div>

						<div class="row">
							 <div class="col-lg-6">
								<div class="form-group">
									<label class="control-label" for="categorie_reteta">Categoria  *</label>
									<select name="category_selection" class="form-control" id="categorie_reteta">
										<#list categories as categorii>
										<option value="${categorii.id!''}"> ${categorii.name!''}</option>
										</#list>
									</select>
								</div>
							</div>
							<input id ="idCategoria" name="idCategoria" type="hidden" value="${recipe.idCategoria!''}"/>


						<div class="col-lg-6">
							<div class="form-group">
								<label class="control-label"  for="portions_input">Portii  *</label>
								<input name="portions" value="${recipe.portions!''}" type="number" class="form-control" id="portions_input" placeholder="Ex: 2" min="1">
								<b><span class="portii_remaining" style="color:#1d91d1;"></span></b> numere ramase
								<br>
							</div>
							</div>
						</div>

						<div class="row">
							 <div class="col-lg-6">
								<div class="form-group">
									<label class="control-label" for="cookingTime">Timp gatire (in minute)  *</label>
									<input name="cookingTime" value="${recipe.cookingTime!''}" type="number" class="form-control" id="cookingTime" placeholder="Ex: 120" min="1">
									<b><span class="cookingTime_remaining" style="color:#1d91d1;"></span></b> numere ramase
									<br>
								</div>
							</div>
						<div class="col-lg-6">
							<div class="form-group">
								<label class="control-label"  for="preparationTime ">Timp preparare (in minute)  *</label>
								<input name="preparationTime" value="${recipe.preparationTime !''}" type="number" class="form-control" id="preparationTime " placeholder="Ex: 30" min="1">
								<b><span class="preparationTime_remaining" style="color:#1d91d1;"></span></b> numere ramase
								<br>
							</div>
							</div>
						</div>

						<div class="form-group">
							<label for="description">Descriere scurta  *</label>
							<textarea value="${recipe.description!''}" name ="description" rows="2" class="form-control" id="description" placeholder="Descriere scurta a retetei. Maxim 150 de caractere" >${recipe.description!''}</textarea>
							<b><span class="description_remaining" style="color:#1d91d1;"></span></b> caractere ramase
							<br/>

						</div>
						<div class="form-check">
							<input  class="form-check-input" name="istutorial" value="${recipe.istutorial?c}" type="checkbox" id="istutorial" >
							<label id="istutorial_checkbox"   class="form-check-label" for="istutorial">
							Este tutorial de gatit pentru incepatori?
							</label>
						</div>
					</div>
					<div class="card">
						<h4 class="text-center bg-info card-header">Media</h3>
						<div class="card-body">
							<div class="form-group text-center col-xs-12 text-center">
								<p><b>Clip actual</b></p>
								<div class="w-50 embed-responsive embed-responsive-16by9 edit-clip" >
									<iframe class="embed-responsive-item" src="${recipe.link!''}" allowfullscreen></iframe>
								</div>
							</div>
							<div class="form-group text-center">
								<label for="link_input"><b>Schimba videoclip*</b></label>
								<input  name="link" value="${recipe.link!''}" type="text" class="form-control" id="link_input" aria-describedby="Denumire" placeholder="Link catre videoclip de pe o platforma video. Ex: https://www.youtube.com/watch?v=wX1nIIcUzgc">
							</div>

							<div class="form-group text-center col-xs-12 text-center">
								<p><b>Imagine actuala</b></p>
								<div class="block1 w-100">
									<img style="max-height:240px;" src="data:image/*;base64, ${recipePhoto}" class="img-fluid" alt="Responsive image">
								</div>
							</div>

							<div class="input-group mb-3">
								<div class="custom-file">
									<input onchange="ValidateRecipeImageSize(this)" name="file"  accept="image/*" type="file" class="custom-file-input" id="recipe_picture_edit">
									<label class="font-weight-bold custom-file-label" for="recipe_picture">Schimba imaginea de prezentare  *</label>
								</div>
							</div>
									<input type="hidden" value="${recipe.idPoza!''}" name="idPoza">
							<small><p style="margin:0" class="font-weight-lighter text-center font-italic">marimea imaginii nu trebuie sa depaseasca 2 MB</p></small>

							<font color="red"><b><span id="fileError"></span></b></font>
						</div>
					</div>

					<div class="card">
						<h4 class="text-center bg-info card-header">Ingrediente *</h3>
						<div class="card-body">
							<div id="ingredient_checkboxes" class="control-group">
								<#list ingredientsMap?keys as key>
								<h4 class="text-center bg-transparent card-header">${key}</h3>
								<hr style="margin:0;" class="hr_styled">
									<#list ingredientsMap[key] as ingredients>
									<div class="form-check form-check-inline" style="min-width:200px;">
										<div class="custom-control custom-checkbox">
											<input value="${ingredients.id!''}" type="checkbox" class="custom-control-input" id="checkbox_${ingredients.id!''}">
											<label class="custom-control-label" for="checkbox_${ingredients.id!''}">${ingredients.name!''}</label>
										</div>
									</div>

									</#list>
								</#list>

							</div>
							<input id ="ingredientsId" name="ingredientsId" type="hidden" value="${ingredientArrayValue}"/>
						</div>
					</div>
					<div class="card">
						<h4 class="text-center bg-info card-header">Mod de preparare</h3>
							<small><p style="margin:0" class="font-weight-lighter text-center font-italic">Va rugam sa furnizati instructiuni! </p></small>
						 <div class="card-body">
							<div class="form-group">
								<textarea  name ="instructions" rows="30" class="form-control" id="instructions" placeholder="Instructiunile retetei." >${recipeIngredients.instructions!''}</textarea>
								<br>
							</div>
						</div>

						<input value="save" class="btn btn-primary btn-lg btn-block" type="submit"/>
						<#if recipe.id??>
							<input name="id" type="hidden" value="${recipe.id?c}"/>
						</#if>
							<input name="photoId" type="hidden" value="${recipe.photoId!''}">
							<input name="recipeId" type="hidden" value="${recipe.recipeId!''}">
					</div>
				</div>
			</div>
		</form>
	</main>
	<#include '/bootstrap_footer.ftl'>
</div>

<script src="/js/edit_recipe.js"></script>
<script type="text/javascript">
function toggleArea1() {
    var instructiuniTextArea;
	if(!instructiuniTextArea) {
		instructiuniTextArea = new nicEditor({fullPanel : true}).panelInstance('instructions',{hasPanel : true});
	} else {
		instructiuniTextArea.removeInstance('myArea1');
		instructiuniTextArea = null;
	}
}
bkLib.onDomLoaded(function() { toggleArea1(); });
</script>
</body>
</html>




