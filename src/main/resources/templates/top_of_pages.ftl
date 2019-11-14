	<div class="page-header">
		<div class="row">
			<div class="col-8" >
				<img src="<@spring.url '/images/logo.png' />"	width="400px" />
			</div>

			<div class="col-4" >
				<div class="container" style="height:100%;padding-top:10%">
				<#if loggedUser??>
				<a class="btn btn-primary" href="/logout" role="button">Log-out</a>
					<li class=" dropdown btn btn-primary">${loggedUser}

						<div class="dropdown-menu" style="top:30px" aria-labelledby="dropdown03">
							<a class="dropdown-item" href="/retete/my_recipes">Retetele mele</a>
							<a class="dropdown-item" href="/promovari/my_promotions/">Istoric promovari</a>
							<a class="dropdown-item" href="/events/yourlist">Istoric evenimentele mele</a>
							<a class="dropdown-item" href="/user/">Modifica detalii utilizator</a>
						</div>
					</li>
				<#else>
					<a class="btn btn-primary" href="/login" role="button">Log-in</a>
					<a class="btn btn-primary" href="/user/add" role="button">Creaza un cont nou</a>
				</#if>
				</div>
			</div>
		</div>
	</div>