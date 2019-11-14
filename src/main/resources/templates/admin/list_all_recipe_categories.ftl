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
						<h3 style="text-align:center;"> Lista CATEGORII RETETE </h3>
					</div>
					<table class="table table-bordered table-dark">
						<thead>
							<tr class="text-center" style="font-size:1.2em;">
								<th scope="col">Denumire Categorie</th>
								<th scope="col">&nbsp</th>
							</tr>
						</thead>
						<tbody>
						<#list categories as category>
						<tr>
							<th scope="row">${category.name!''}</th>
							<td><a href="" id="delete_recipe_category_${category.id!''}" onclick="adminDeleteCategory(${category.id!''})" class="btn btn-danger">Stergere</a></td>
						</tr>
						</#list>
						</tbody>
					</table>
					<a href="/" class="btn btn-info float-right">Inapoi la site</a>
					<a href="/recipe_atributes/add_recipe_category" class="btn btn-success float-right" style="margin-right:15px">Adaugare categorie noua</a>
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

  function adminDeleteCategory(cateogryId){
	if(!confirm("Atentie! Sunteti sigur ca doriti stergerea categoriei?")){
		return false;
	}else{
		document.getElementById("delete_recipe_category_" + cateogryId).href= "/recipe_atributes/delete_category?id="+cateogryId;
	}

  }

  </script>
<div style="display:none;"
  <#include '/bootstrap_footer.ftl'>
</div>
