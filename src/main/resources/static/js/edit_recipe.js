$(document).ready( function () {
  setCategoryOption();
  checkIngredientsIfFound();
  getTutorialCheckbox();

	$("#editForm").on("submit", function(){
		getRecipeCategory();
		getSelectedIngredients();
		verifyTutorialCheckbox();
		document.getElementById('idCategoria').value = getRecipeCategory();

		return validateRecipeFormOnSubmit();
   });

});

function getRecipeCategory(){
	var categoryInput = document.getElementById("categorie_reteta");
	var value = categoryInput.options[categoryInput.selectedIndex].value;

	return value;
}

function getTutorialCheckbox(){
	var checkbox = document.getElementById("istutorial");
	if(checkbox.value == true){
		checkbox.click();
	}
}

function setCategoryOption(){
	var categoryValue = document.getElementById("idCategoria").value;
	$("#categorie_reteta").val(categoryValue);
}

function checkIngredientsIfFound(){
	var ingredientValue = document.getElementById("ingredientsId").value;
	var idarray = ingredientValue.split(",");

	for(var i=0; i<idarray.length; i++){

		var ingredientCheckbox = document.getElementById("checkbox_" + idarray[i]);

		if(ingredientCheckbox.value == idarray[i]){
			ingredientCheckbox.click();
		}
	}

}

