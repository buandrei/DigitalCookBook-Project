[#ftl]
[#import "/spring.ftl" as spring /]
<!DOCTYPE html>
<html lang="ro">
<head>
  [#include '/bootstrap_header.ftl']

 <script type="text/javascript">

</script>
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
									<a href="/retete">Cautare simpla</a>
								</li>
								<li>
									<a href="/specific_ingredients">Cauta dupa idIngrediente specifice</a>
								</li>

						</ul>
					</li>
				<li><a href="/promotion">Promovare</a></li>
				<li><a href="/tutorials">Tutoriale de gatit</a></li>
				<li><a href="/upload_recipe">Incarca reteta</a></li>
				<li><a href="/events">Evenimente</a></li>
			</ul>
		  </div>
		</nav>

<!--	BODY -->

		<div class="panel panel-default">
			<div class="panel-heading text-center panel-relative">
				<h2 class="panel-title"><b>Incarca o reteta! Este gratis!</b></h2>
			</div>
			<div class="panel panel-info">
				<div class="panel-heading panel-relative">
					<h3 class="panel-title">Informatii reteta</h3>
				</div>
				<div class="panel-body">

					[#if errors??]
						[#list errors as error]
						<span style="color:red"> ${error}</span>
						<br>
						[/#list]
					[/#if]


					<form method="post" action="/retete/salvare_reteta" id="recipeForm">

						<div class="form-group">
							<label for="denumire_input">Denumire</label>
							<input name="denumire" value="${recipe.denumire!''}" type="text" class="form-control" id="denumire_input" aria-describedby="Denumire" placeholder="Denumirea retetei" >
							<b><span class="denumire_remaining" style="color:#1d91d1;"></span></b> caractere ramase
							<br>
							<label for="categorie_reteta">Categorie</label>
							<select  class="form-control" id="categorie_reteta">
								<option><font color=red>Va rugam selectati din lista o categorie </font></option>
								[#list categories as categorii]
								<option value="${categorii.id!''}"> ${categorii.denumire!''}</option>
								[/#list]
							</select>
							<input id ="idCategoria" name="idCategoria" type="hidden" value="${recipe.idCategoria!''}"/>

						</div>

						<div class="form-group">
							<label for="description">Descriere</label>
							<textarea value="${recipe.descriere!''}" name ="descriere" rows="3" class="form-control" id="description" placeholder="Descriere scurta a retetei. Maxim 150 de caractere"></textarea>
							<b><span class="descriere_remaining" style="color:#1d91d1;"></span></b> caractere ramase
							<br/>

							<label for="portii_input">Portii</label>
							<input name="portii" value="${recipe.portii!''}" type="number" class="form-control" id="portii_input" placeholder="Ex: 2" min="0">

							<label for="link_input">Link videoclip</label>
							<input name="link" value="${recipe.link!''}" type="text" class="form-control" id="link_input" aria-describedby="Denumire" placeholder="Link catre videoclip de pe o platforma video. Ex: https://www.youtube.com/watch?v=wX1nIIcUzgc">
						</div>
						<div class="form-check">
							<input  class="form-check-input" type="checkbox" id="istutorial" >
							<label name="istutorial" class="form-check-label" for="istutorial">
							Este tutorial de gatit pentru incepatori?
							</label>
						</div>
						<div class="input-group">
							<!--<div class="input-group-prepend">
								<span class="input-group-text" id="inputGroupFileAddon01">Upload</span>
							</div>-->
							<div class="custom-file">
								<input  name="cale_fisier" type="file" class="custom-file-input" id="inputGroupFile01"
								aria-describedby="inputGroupFileAddon01">
								<label class="custom-file-label" for="inputGroupFile01">Choose file</label>
							</div>
						</div>
						[#if recipe.id??]
							<input name="id" type="hidden" value="${recipe.id?c}"/>
						[/#if]
						<input value="save" type="submit"/>
					</form>
				</div>
			</div>
		</div>
</div>
[#include '/bootstrap_footer.ftl']
</body>
</html>




