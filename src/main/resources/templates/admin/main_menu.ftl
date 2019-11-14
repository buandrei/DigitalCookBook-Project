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
					<button  type="button" id="menu-toggle" class="btn btn-secondary btn-sm">Open/Close SideMenu</button>
					<h1 class="text-center">DigitalCookBook Admininstration Page</h1>
					<p class="text-center">Bine ati venit pe pagina de administrator! Aici puteti sa editati/stergeti/lista entitatille aplicatiei!</p>
				</div>
			</div>
			<a href="/" class="btn btn-info float-right">Inapoi la site</a>
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

  </script>
<div style="display:none;"
  <#include '/bootstrap_footer.ftl'>
</div>
