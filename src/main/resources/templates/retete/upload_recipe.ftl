<#ftl>
<#import "/spring.ftl" as spring />
<html lang="ro">
<head>
  <#include '/bootstrap_header.ftl'>
</head>

<body >
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
			<form class="form-inline my-2 my-md-0">
				<input class="form-control" type="text" placeholder="Search">
			</form>
		</div>
		</nav>
<!--	BODY -->

		<form onsubmit="return validateFormOnSubmit()" name="saveForm" onsubmit="validateFormOnSubmit()" method="post" action="/retete/salvare_reteta" id="recipeForm" enctype="multipart/form-data">
			<div class="card">
				<h3 class="card-header text-center"><b>Incarca o reteta! Este gratis!</b></h2>
				<small><p style="margin:0" class="font-weight-lighter text-center font-italic">campurile marcate cu * sunt obligatorii</p></small>
				<div class="card">
					<h4 class="text-center bg-info card-header">Informatii reteta</h3>
					<div class="card-body">

					<div class="bs-example">
						<div id="validateALert" style='display:none' class="alert alert-info alert-dismissible fade show">
						<br>

						<button type="button" class="close">&times;</button>
						</div>
					</div>

					<#if errors??>
						<#list errors as error>
						<span style="color:red"> ${error}</span>
						<br>
						</#list>
					</#if>

						<div class="form-group">
							<label for="denumire_input">Denumire *</label>
							<input name="denumire" value="${recipe.denumire!''}" type="text" class="form-control" id="denumire_input" aria-describedby="Denumire" placeholder="Denumirea retetei" >
							<b><span class="denumire_remaining" style="color:#1d91d1;"></span></b> caractere ramase
							<br>
						</div>


						<div class="row">
							 <div class="col-lg-6">
								<div class="form-group">
									<label class="control-label" for="categorie_reteta">Categoria  *</label>
									<select name="category_selection" class="form-control" id="categorie_reteta">
										<option><font color="red">Va rugam selectati din lista o categorie </font></option>
										<#list categories as categorii>
										<option value="${categorii.id!''}"> ${categorii.denumire!''}</option>
										</#list>
									</select>
								</div>
							</div>
							<input id ="idCategoria" name="idCategoria" type="hidden" value="${recipe.idCategoria!''}"/>


						<div class="col-lg-6">
							<div class="form-group">
								<label class="control-label"  for="portii_input">Portii  *</label>
								<input name="portii" value="${recipe.portii!''}" type="number" class="form-control" id="portii_input" placeholder="Ex: 2" min="1">
							</div>
							</div>
						</div>

						<div class="row">
							 <div class="col-lg-6">
								<div class="form-group">
									<label class="control-label" for="timp_gatire">Timp gatire (in minute)  *</label>
									<input name="timp_gatire" value="${recipe.timp_gatire!''}" type="number" class="form-control" id="timp_gatire" placeholder="Ex: 120" min="1">
								</div>
							</div>
						<div class="col-lg-6">
							<div class="form-group">
								<label class="control-label"  for="timp_preparare ">Timp preparare (in minute)  *</label>
								<input name="timp_preparare" value="${recipe.timp_preparare !''}" type="number" class="form-control" id="timp_preparare " placeholder="Ex: 30" min="1">
							</div>
							</div>
						</div>

						<div class="form-group">
							<label for="description">Descriere scurta  *</label>
							<textarea value="${recipe.descriere!''}" name ="descriere" rows="2" class="form-control" id="description" placeholder="Descriere scurta a retetei. Maxim 150 de caractere" >${recipe.descriere!''}</textarea>
							<b><span class="descriere_remaining" style="color:#1d91d1;"></span></b> caractere ramase
							<br/>

						</div>
						<div class="form-check">
							<input  class="form-check-input" name="istutorial" value="" type="checkbox" id="istutorial" >
							<label id="istutorial_checkbox"   class="form-check-label" for="istutorial">
							Este tutorial de gatit pentru incepatori?
							</label>
						</div>
					</div>
					<div class="card">
						<h4 class="text-center bg-info card-header">Media</h3>
						<div class="card-body">
							<div class="form-group">
								<label for="link_input">Link videoclip *</label>
								<input  name="link" value="${recipe.link!''}" type="text" class="form-control" id="link_input" aria-describedby="Denumire" placeholder="Link catre videoclip de pe o platforma video. Ex: https://www.youtube.com/watch?v=wX1nIIcUzgc">
							</div>


							<div class="input-group mb-3">
								<div class="custom-file">
									<input onchange="ValidateRecipeImageSize(this)" name="file"  type="file" class="custom-file-input" id="recipe_picture">
									<label class="custom-file-label" for="recipe_picture">Alege imagine de prezentare  *</label>
								</div>
								<script type="application/javascript">
									$('input[type="file"]').change(function(e){
										var fileName = e.target.files[0].name;
										$('.custom-file-label').html(fileName);
									});
								</script>

							</div>
								<font color="red"><b><span id="fileError"></span></b></font>
						</div>
					</div>

					<div class="card">
						<h4 class="text-center bg-info card-header">Ingrediente *</h3>
						<div class="card-body">
							<div id="ingredient_checkboxes" class="control-group">
								<#assign i = 0>
								<#list ingredients as ingredients>
									<#if i==3>
										<br>
										<#assign i =0>
									</#if>
									<div class="form-check form-check-inline">
										<label class="checbox" for="checkbox_${ingredients.id!''}">${ingredients.denumire!''}
											<input type="checkbox"  id="checkbox_${ingredients.id!''}" value="${ingredients.id!''}">
										</label>
									</div>
									<#assign i++>
								</#list>

							</div>
							<input id ="idIngrediente" name="idIngrediente" type="hidden" value="${recipeIngredients.idingrediente!''}"/>
						</div>

					<div class="card">
						<h4 class="text-center bg-info card-header">Mod de preparare</h3>
							<small><p style="margin:0" class="font-weight-lighter text-center font-italic">Va rugam sa furnizati instructiuni! </p></small>
						 <div class="card-body">
							<div class="form-group">
								<textarea value="${recipeIngredients.instructiuni!''}" name ="instructiuni" rows="30" class="form-control" id="instructiuni" placeholder="Instructiunile retetei." >${recipeIngredients.instructiuni!''}</textarea>
								<br>
							</div>
						</div>

						<input value="save" class="btn btn-primary btn-lg btn-block" type="submit"/>
						<#if recipe.id??>
							<input name="id" type="hidden" value="${recipe.id?c}"/>
						</#if>
					</div>
				</div>
			</div>
		</form>

	</div>
</div>

<#include '/bootstrap_footer.ftl'>
<script type="text/javascript">

function toggleArea1() {
    var instructiuniTextArea;
	if(!instructiuniTextArea) {
		instructiuniTextArea = new nicEditor({fullPanel : true}).panelInstance('instructiuni',{hasPanel : true});
	} else {
		instructiuniTextArea.removeInstance('myArea1');
		instructiuniTextArea = null;
	}
}

bkLib.onDomLoaded(function() { toggleArea1(); });
</script>
</body>
</html>




