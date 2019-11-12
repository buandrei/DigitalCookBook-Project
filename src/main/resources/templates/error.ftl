<#ftl]
<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html lang="ro">
<head>
<#include "/bootstrap_header.ftl">
</head>
<body>
<div class="container">
	<header>
        <#include '/top_of_pages.ftl'>
        <nav style="margin-bottom:15px" class="navbar navbar-expand-sm navbar-dark bg-dark">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarsExample03">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="/">Home</a>
                    </li>
                    <li class="nav-item dropdown ">
                        <a class="nav-link dropdown-toggle " style="padding:0" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Retete</a>
                        <div class="dropdown-menu" style="top:30px" aria-labelledby="dropdown03">
                            <a class="dropdown-item" href="/retete/list_all">Cautare simpla</a>
                            <a class="dropdown-item" href="/retete/cauta_dupa_ingrediente">Cauta dupa ingrediente specifice</a>
                        </div>
                    </li>
                    <li class="nav-item"><a class="nav-link" href="/promotion">Promovare</a></li>
                    <li class="nav-item" ><a class="nav-link" href="/retete/tutoriale_incepatori">Tutoriale de gatit</a></li>
                    <li class="nav-item "><a  class="nav-link" href="/retete/upload_recipe">Incarca reteta</a></li>
                    <li class="nav-item"><a class="nav-link" href="/events">Evenimente</a></li>
                </ul>
            </div>
        </nav>
	</header>
	<main role="main">
		<div id="block_error">
			<div>
				<h2>Oops. &nbsp Ati intampinat o eroare :-( </h2>
				<p>
				Se pare ca ati intampinat o problema sau pagina cautata nu mai exista!<br />
				Incercati mai tarziu sau daca eroarea persista, va rugam sa ne contactati via e-mail!
				</p>
			</div>
		</div>
	</main>
<#include '/bootstrap_footer.ftl'>
</div>
</body>
</html>


