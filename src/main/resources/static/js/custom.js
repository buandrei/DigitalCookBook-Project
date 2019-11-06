//onPageLoad example Function-> attach to body of HTML PAGE ex: <body onload="bodyUploadRecipeOnLoad()">
function bodyOnLoad(){
    //what to do
}

function setTextAreaValue(savedValue){
	alert(savedValue);
	var descriptionTextArea = document.getElementById("description");
	descriptionTextArea.innerHTML = savedValue;
}
		
		
$(function (){
		var nMaxLength = 150;
		$('.descriere_remaining').text(nMaxLength);
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
				$('.descriere_remaining').text(nRemaining);
			}
		}
});

//validate upload filesize
function ValidateRecipeImageSize(file) {
	document.getElementById("fileError").innerHTML  ="";
	var FileSize = file.files[0].size / 1024 / 1024; // in MB
	if (FileSize > 2) {
		document.getElementById("fileError").innerHTML = "Marimea fisierului depaseste 1 MB! Va rugam sa incercati alta imagine!"
	   $(file).val('');

	} else {

	}
}

$(function (){
		var nMaxLength1 = 60;
		$('.denumire_remaining').text(nMaxLength1);
		$("#denumire_input").keydown(function (event) {
			LimitCharacters($(this));
		});
		$("#denumire_input").keyup(function (event) {
			LimitCharacters($(this));
		});

		function LimitCharacters(denumire_input){
			if(denumire_input.val().length > nMaxLength1){
				denumire_input.val(denumire_input.val().substring(0, nMaxLength1));
			}else{
				var nRemaining1 = nMaxLength1 - denumire_input.val().length;
				$('.denumire_remaining').text(nRemaining1);
			}
		}
});

$(document).ready(function() {
		var selectedCategory = 0;


		$('#categorie_reteta').on('change',function() {
						selectedCategory = $(this).find("option:selected").val();
					});

		$("#recipeForm").on("submit", function(){
			GetSelected();
			verifyTutorialCheckbox();
			var selectedCategoryWithoutCommas = selectedCategory.replace(/,/g, '');
			document.getElementById('idCategoria').value = selectedCategoryWithoutCommas;

			return true;
	   })
});

function GetSelected() {

	var selected = new Array();
	var tblFruits = document.getElementById("ingredient_checkboxes");
	var chks = tblFruits.getElementsByTagName("INPUT");

	for (var i = 0; i < chks.length; i++) {
		if (chks[i].checked) {
			selected.push(chks[i].value);
		}
	}

	if (selected.length > 0) {
		document.getElementById("idIngrediente").value = selected;
		alert(document.getElementById("idIngrediente").value);
	}
};

function verifyTutorialCheckbox() {

    document.getElementById("istutorial").value = document.getElementById("istutorial").checked;

}