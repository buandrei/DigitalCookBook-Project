<#ftl>
<#import "/spring.ftl" as spring />
<html lang="ro">
<head>
	<link href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
	<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
	<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
	<div class="container">
        <div id="loginbox" style="margin-top:50px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
            <div class="panel panel-info" >
				<div class="panel-heading">
					<div class="panel-title">Adaugare categorie reteta noua</div>
				</div>
				<div style="padding-top:30px" class="panel-body" >
					<form  name="categoryForm"  method="post" action="/recipe_atributes/save_recipe_category" id="categoryForm" enctype="multipart/form-data">
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
						<div class="controls">
							<div class="row">
								<div class="col-lg-12">
									<div class="form-group">
										<label for="name_input">Denumire categorie *</label>
										<input name="name" value="" type="text" class="form-control" id="name_input" aria-describedby="Denumire" placeholder="Denumirea retetei" >
										<br>
									</div>
								</div>
							</div>
						</div>
						<input value="Salveaza categoria" class="btn btn-primary btn-lg btn-block" type="submit"/>
						<a href="/admin/view_categories" class="btn btn-danger btn-lg btn-block">Cancel</a>
					</form>
				</div>
			</div>
		</div>
		<!-- -->
    </div>
	<script>
		$(document).ready(function() {

			$("#categoryForm").on("submit", function(){

				return validateCategoryFormOnSubmit();
		   });

		})
		function validateCategoryFormOnSubmit() {
			var name = document.forms["categoryForm"]["name"];
			var alert_popup = document.getElementById('validateALert');
			alert_popup.style.display = 'block';
			alert_popup.innerHTML = "";

			var isOk = true;

			if (name.value == "") {
				alert_popup.innerHTML += "Va rugam sa completati campul <strong>Denumire</strong> !<br>";
				name.classList.add("is-invalid");
				isOk = false;
			}else{
				name.classList.remove("is-invalid");
			}
			if(!isOk){
				return false;
			}
			return true;
		}

	</script>
</body>
</html>