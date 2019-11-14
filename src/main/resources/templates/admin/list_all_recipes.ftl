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
                <a href="/admin/view_ingredients">
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
						<h3 style="text-align:center;"> Lista RETETE </h3>
					</div>
					<table class="table table-bordered table-dark">
						<thead>
							<tr class="text-center" style="font-size:1.2em;">
								<th scope="col">Denumire Reteta</th>
								<th scope="col">Categoria</th>
								<th scope="col">Descriere</th>
								<th scope="col">Autor</th>
								<th scope="col">&nbsp</th>
								<th scope="col">&nbsp</th>
								<th scope="col">&nbsp</th>
								<th scope="col">&nbsp</th>
							</tr>
						</thead>
						<tbody>
						<#assign recipePageList = recipeList>
						<#list recipeList.pageList as recipe>
						<tr>
							<th scope="row">${recipe.name!''}</th>
							<td>${recipe.recipeCategory.getName()!''}</td>
							<td>${recipe.description!''}</td>
							<td>${recipe.user.getNume()!''}</td>
							<td><a href="/retete/vizualizare_reteta?id=${recipe.id!''}" class="btn btn-info">Vizualizare</a></td>
							<td><a href="/retete/editare_reteta?id=${recipe.id!''}" class="btn btn-warning">Editare</a></td>
							<#if recipe.inactiv == true>
								<td><a href="/admin/activate_recipe?id=${recipe.id!''}" class="btn btn-success">Activare</a></td>
							<#else>
								<td><a href="/admin/inactivate_recipe?id=${recipe.id!''}" class="btn btn-warning">Inactivare</a></td>
							</#if>
							<td><a href="" id="delete_recipe_ref_${recipe.id!''}" onclick="adminDeleteRecipe(${recipe.id!''})" class="btn btn-danger">Stergere</a></td>
						</tr>
						</#list>
						</tbody>
					</table>
					<nav>
						<ul class="pagination">

							<#if recipePageList.firstPage>
								<li class="page-item disabled">
									<span class="page-link">Prev</span>
								</li>
							<#else>
								<li class="page-item">
									<span class="page-link"><a href="/admin/view_recipes/prev">Prev</a></span>
								</li>
							</#if>
							<#list 1..recipePageList.pageCount as tagStatus>
								<#if (recipePageList.page + 1) == tagStatus?counter>

								<li class="page-item active">
									<span class="page-link">
										${tagStatus?counter}
										<span class="sr-only">(current)</span>
									</span>
								</li>
								<#else >
									 <li class="page-item"><a class="page-link"  href="/admin/view_recipes/${tagStatus?counter}">${tagStatus?counter}</a></li>
								</#if>
							</#list>
							<#if recipePageList.lastPage>
								<li class="page-item disabled">
									<a class="page-link">Next</a>
								</li>
							<#else>
								<li class="page-item">
									<a class="page-link" href="/admin/view_recipes/next">Next</a>
								</li>

							</#if>
						</ul>
					</nav>
					<a href="/" class="btn btn-info float-right">Inapoi la site</a>
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

  function adminDeleteRecipe(recipeId){
	if(!confirm("Atentie! Sunteti sigur ca doriti stergerea retetei?")){
		return false;
	}else{
		document.getElementById("delete_recipe_ref_"+recipeId).href= "/admin/delete_recipe?id="+recipeId;
	}

  }

  </script>
<div style="display:none;"
  <#include '/bootstrap_footer.ftl'>
</div>
