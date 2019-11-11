[#ftl]
[#import "/spring.ftl" as spring /]
<html lang="en">
<head>
  [#include '/bootstrap_header.ftl']
  <script type="text/javascript">

$(function (){
		var nMaxLength = 250;
		$('.remaining').text(nMaxLength);
		$("#description").keydown(function (event) {
			LimitCharacters($(this));
		});
		$("#description").keyup(function (event) {
			LimitCharacters($(this));
		});

		function LimitCharacters(description){
			if(description.val().length > nMaxLength){
				description.val(description.val().substring(0, nMaxLength));
			}else{
				var nRemaining = nMaxLength - description.val().length;
				$('.remaining').text(nRemaining);
			}
		}
});
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
									<a href="/specific_ingredients">Cauta dupa ingrediente specifice</a>
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
			<div class="panel-heading">
				<h3 class="panel-title">Incarcare reteta</h3>
			</div>
			<div class="panel-body">

				[#if errors??]
					[#list errors as error]
					<span style="color:red"> ${error}</span>
					<br>
					[/#list]
				[/#if]


				<form method="post" action="/retete/save">

					<div class="form-group">
						<label for="denumire_input">Denumire</label>
						<input  type="text" class="form-control" id="denumire_input" aria-describedby="Denumire" placeholder="Denumirea retetei" >

					</div>

					<div class="form-group">
						<label for="description">Descriere</label>
						<textarea  name ="description" onload="checkRemainingChars(); rows="3" onDrop="return false" class="form-control" id="description" aria-describedby="Descriere" placeholder="Descriere scurta a retetei. Maxim 250 de caractere"></textarea>
						<b><span class="remaining" style="color:#1d91d1;"></span></b> caractere ramase

						<label for="portii_input">Portii</label>
						<input  type="number" class="form-control" id="portii_input" placeholder="Ex: 2" >

						<label for="link_input">Link videoclip</label>
						<input type="text" class="form-control" id="link_input" aria-describedby="Denumire" placeholder="Link catre videoclip de pe platforma o platforma video." value="${recipe.denumire!''}">
					</div>
					<div class="form-check">
						<input  class="form-check-input" type="checkbox" id="istutorial" >
						<label class="form-check-label" for="istutorial">
						Este tutorial de gatit pentru incepatori?
						</label>
					</div>
				<input value="save" type="submit"/>
				</form>
			</div>
		</div>
</div>
[#include '/bootstrap_footer.ftl']
</body>
</html>




