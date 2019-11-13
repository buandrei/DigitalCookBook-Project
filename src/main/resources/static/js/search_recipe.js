$(document).ready( function () {

	$("#recipeFilterForm").on("submit", function(){
		document.getElementById('category_search').value = getCategoryForSearch();
		return validateSearchForOnSubmit();
   });

   	$("#recipeIngredientsSearchForm").on("submit", function(){
		getSelectedIngredients();
		checkAndSetMoreIngredientCheckBox();
		return validateSearchByIngredientsForm();
   });

});

function getCategoryForSearch(){

	var categoryInput = document.getElementById("recipe_category");
	var selectedCategoryValue = categoryInput.options[categoryInput.selectedIndex].value;
	return selectedCategoryValue;
}

function validateSearchForOnSubmit(){
	var name_search = document.getElementById('name_search');
	var category_search = document.getElementById('category_search');
	if((name_search.value.trim() == "") && (category_search.value == "0")){
		var alert_popup = document.getElementById('form_validation');
		alert_popup.style.display = 'block';
		alert_popup.innerHTML = "Va rugam completati macar un camp pentru filtrare si incercati din nou!";
		return false;
	}
	return true;
}

function validateSearchByIngredientsForm(){
	var ingredientArray = document.getElementById('ingredientsId');
	if (ingredientArray.value == ""){
		alert("Nu ati selectat nici un ingredient!");
		return false;
	}
	return true;
}

function checkAndSetMoreIngredientCheckBox() {

	if(document.getElementById("moreIngredients").checked){
		document.getElementById("isMoreIngredientsChecked").value =  "yes";
	} else{
		document.getElementById("isMoreIngredientsChecked").value =  "no";
	}

}