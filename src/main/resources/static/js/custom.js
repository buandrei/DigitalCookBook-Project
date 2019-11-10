/*
	Custom JS goes here
*/

$(document).ready(function() {

		$("#recipeForm").on("submit", function(){

			getSelectedIngredients();
			verifyTutorialCheckbox();
			document.getElementById('idCategoria').value = getSelectedRecipeCategory();

	    	return validateRecipeFormOnSubmit();

	   });

});

function getSelectedRecipeCategory(){
	var categoryInput = document.getElementById("categorie_reteta");
	var value = categoryInput.options[categoryInput.selectedIndex].value;

	return value;
}

function setTextAreaValue(savedValue){
	var descriptionTextArea = document.getElementById("description");
	descriptionTextArea.innerHTML = savedValue;
}

function validateRecipeFormOnSubmit() {
	var name = document.forms["saveForm"]["name"];
	var idCategoria = document.forms["saveForm"]["idCategoria"];
	var description = document.forms["saveForm"]["description"];
	var idIngrediente = document.forms["saveForm"]["ingredientsId"];
	var link = document.forms["saveForm"]["link"];
	var instructiuni = document.forms["saveForm"]["instructions"];
	var file = document.forms["saveForm"]["file"];
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

	if(idCategoria.value == 0){
		alert_popup.innerHTML += " Va rugam sa selectati o <strong>Categorie</strong> !<br>";
		idCategoria.classList.add("is-invalid");
		isOk = false;
	}else{
		idCategoria.classList.remove("is-invalid");
	}

	if(description.value == ""){
		alert_popup.innerHTML += " Va rugam sa completati  <strong>Descrierea</strong>  !<br>";
		description.classList.add("is-invalid");
		isOk = false;
	}else{
		description.classList.remove("is-invalid");
	}

	if (idIngrediente.value == "") {
		alert_popup.innerHTML += " Va rugam sa alegeti <strong>Ingrediente</strong>  !<br>";
		idIngrediente.classList.add("is-invalid");
		isOk = false;
	}else{
		idIngrediente.classList.remove("is-invalid");
	}

	if (link.value == "") {
		alert_popup.innerHTML += " Va rugam sa adaugati un <strong>Link</strong>  !<br>";
		link.classList.add("is-invalid");
		isOk = false;
	}else{
		link.classList.remove("is-invalid");
	}

		if (instructiuni.value == "") {
			alert_popup.innerHTML += " Va rugam sa completati <strong>Modul de Preparare</strong> !<br>";
			instructiuni.classList.add("is-invalid");
			isOk = false;
		}else{
			instructiuni.classList.remove("is-invalid");
		}

	if(!document.getElementById("recipe_picture_edit")){

		if (file.value == "") {
			alert_popup.innerHTML += " Va rugam atasati o <strong>Imagine</strong> !<br>";
			file.classList.add("is-invalid");
			isOk = false;
		}else{
			file.classList.remove("is-invalid");
		}
	}

	if(!isOk){
		window.scrollTo(0, 0);
		return false;
	}
	return true;
}

/* File input name change */
$("#recipe_picture").on("change", function() {
      var fileName = $(this).val().split("\\").pop();
      $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
});



$(function (){
		var nMaxLength = 150;
		$('.description_remaining').text(nMaxLength);
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
				$('.description_remaining').text(nRemaining);
			}
		}
});

//validate upload filesize
function ValidateRecipeImageSize(file) {
	document.getElementById("fileError").innerHTML  ="";
	var FileSize = file.files[0].size / 1024 / 1024; // in MB
	if (FileSize > 2) {
		document.getElementById("fileError").innerHTML = "Marimea fisierului depaseste 2 MB! Va rugam sa incercati alta imagine!"
	   $(file).val('');

	} else {

	}
}

$(function (){
		var nMaxLength1 = 60;
		$('.name_remaining').text(nMaxLength1);
		$("#name_input").keydown(function (event) {
			LimitCharacters($(this));
		});
		$("#name_input").keyup(function (event) {
			LimitCharacters($(this));
		});

		function LimitCharacters(name_input){
			if(name_input.val().length > nMaxLength1){
				name_input.val(name_input.val().substring(0, nMaxLength1));
			}else{
				var nRemaining1 = nMaxLength1 - name_input.val().length;
				$('.name_remaining').text(nRemaining1);
			}
		}
});


function getSelectedIngredients() {

	var selected = new Array();
	var tblIngredients = document.getElementById("ingredient_checkboxes");
	var chks = tblIngredients.getElementsByTagName("INPUT");

	for (var i = 0; i < chks.length; i++) {
		if (chks[i].checked) {
			selected.push(chks[i].value);
		}
	}

	if (selected.length > 0) {
		document.getElementById("ingredientsId").value = selected;
	}
};

function verifyTutorialCheckbox() {
    document.getElementById("istutorial").value = document.getElementById("istutorial").checked;
}


function doAjaxPost() {
	var id = $('#recipeId').val();
	var rvalue = $('#rvalue').val();
	var $div = $('#rating_grades');

	if(rvalue == ""){
		alert("Nu ati selectat nici o optiune!");
		return false;
	}

	$.ajax({
		type: "POST",
		url: "/retete/give_rating.htm",
		data: "id=" + id + "&rvalue=" + rvalue,
		success: function(response){
			// we have the response
			$('#rated_info').html(response);
		},
		error: function(e){
			alert('Error: ' + e);
		}
	});

	sleep(500);
	$div
		.addClass('hideBlock')
		.outerWidth();
	updateDiv();
}

function sleep(milliseconds) {
  var start = new Date().getTime();
  for (var i = 0; i < 1e7; i++) {
    if ((new Date().getTime() - start) > milliseconds){
      break;
    }
  }
}

function updateDiv()
{
  $("#starDiv").load(location.href + " #starDiv>*", "");
}

function setInputTypeValue(){
   var radios = document.getElementsByName('typeCheckboxes');

    for (var i = 0, length = radios.length; i < length; i++){
        if (radios[i].checked){
            document.getElementById("idTipPromovare").value = radios[i].value;

            break;
        }
    }
}

function getInputTypeValue(){
    if(document.getElementById("idTipPromovare").value == 0){
        alert("Nu ati selectat nici o optiune!");
        return false;
    }
    return true;
}



$(document).ready(function() {
		$("#promoForm").on("submit", function(){
			setInputTypeValue();
			return getInputTypeValue();
	   })
});
