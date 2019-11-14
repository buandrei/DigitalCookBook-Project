<#ftl]
<#import "/spring.ftl" as spring />
<#include "/bootstrap_header.ftl">
<div id="admin_wrapper">
    <!-- Sidebar -->
    <div id="admin_sidebar_wrapper">
    <ul class="admin_sidebar_nav"><b>
			<li>
				<a href="/admin/view_recipes">
					Listare/Editare/Stergere<br>RETETE
				</a>
			</li>
			<hr class="hr_styled">
			<li>
				<a href="/admin/view_promotions">
					Listare/Editare/Stergere<br>PROMOTII
				</a>
			</li>
			<hr class="hr_styled">

			<li>
				<a href="/admin/view_promotions">
					Listare/Editare/Stergere<br>EVENIMENTE
				</a>
			</li>
			<hr class="hr_styled">

			<li>
				<a href="/admin/view_promotions">
					Listare/Editare/Stergere<br>USERI
				</a>
			</li>
            <li>
                <a href="/admin/view_ingredients">
                    Adaugare/Listare/Stergere<br>INGREDIENTE
                </a>
            </li>
            <hr class="hr_styled">
            <li>
                <a href="/admin/view_categories">
                   Adaugare/Listare/Stergere<br>CATEGORII RETETE
                </a>
            </li>

      </ul>
    </div>
	<div id="page-content-wrapper">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
					<div style="display:block;width:100%;">
						<button  type="button" id="menu-toggle" class="btn btn-secondary btn-sm">Open/Close SideMenu</button>
						<h3 style="text-align:center;"> Lista INGREDIENTE </h3>
					</div>
					<table class="table table-bordered table-dark">
						<thead>
							<tr class="text-center" style="font-size:1.2em;">
								<th scope="col">Denumire Reteta</th>
								<th scope="col">UM</th>
								<th scope="col">&nbsp</th>
							</tr>
						</thead>
						<tbody>
						<#assign ingredientList = ingredientList>
						<#list ingredientList.pageList as ingredient>
						<tr>
							<th scope="row">${ingredient.name!''}</th>
							<td>${ingredient.um!''}</td>
							<td><a href="" id="delete_ingredient_${ingredient.id!''}" onclick="adminDeleteIngredient(${ingredient.id!''})" class="btn btn-danger">Stergere</a></td>
						</tr>
						</#list>
						</tbody>
					</table>
					<nav>
						<ul class="pagination">

							<#if ingredientList.firstPage>
								<li class="page-item disabled">
									<span class="page-link">Prev</span>
								</li>
							<#else>
								<li class="page-item">
									<span class="page-link"><a href="/admin/view_ingredients/prev">Prev</a></span>
								</li>
							</#if>
							<#list 1..ingredientList.pageCount as tagStatus>
								<#if (ingredientList.page + 1) == tagStatus?counter>

								<li class="page-item active">
									<span class="page-link">
										${tagStatus?counter}
										<span class="sr-only">(current)</span>
									</span>
								</li>
								<#else >
									 <li class="page-item"><a class="page-link"  href="/admin/view_ingredients/${tagStatus?counter}">${tagStatus?counter}</a></li>
								</#if>
							</#list>
							<#if ingredientList.lastPage>
								<li class="page-item disabled">
									<a class="page-link">Next</a>
								</li>
							<#else>
								<li class="page-item">
									<a class="page-link" href="/admin/view_ingredients/next">Next</a>
								</li>

							</#if>
						</ul>
					</nav>
					<a href="/" class="btn btn-info float-right">Inapoi la site</a>
					<a href="/recipe_atributes/add_ingredient" class="btn btn-success float-right" style="margin-right:15px">Adaugare ingredient nou</a>
				</div>
			</div>
		</div>
	</div>
</div>
  <script>
  $(document).ready(function(){
    $("#menu-toggle").click(function(e){
      e.preventDefault();
      $("#admin_wrapper").toggleClass("menuDisplayed");
    });
  });

  function adminDeleteIngredient(ingredientId){
	if(!confirm("Atentie! Sunteti sigur ca doriti stergerea ingredientului?")){
		return false;
	}else{
		document.getElementById("delete_ingredient_" + ingredientId).href= "/recipe_atributes/delete_ingredient?id="+ingredientId;
	}

  }

  </script>
<div style="display:none;"
  <#include '/bootstrap_footer.ftl'>
</div>
