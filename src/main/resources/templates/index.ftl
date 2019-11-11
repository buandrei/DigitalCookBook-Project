[#ftl]
[#import "/spring.ftl" as spring /]
<!DOCTYPE html>
<html lang="ro">
<head>
[#include "/bootstrap_header.ftl"]
</head>
<body>
	<div class="container">
		<div class="page-header">
        		<!-- TOP (top part, navbar)-->

			<div class="row">
			<!--logo-->
			  <div class="col-8" >
				<h1>Aici o sa avem logo</h1>
			  </div>

			   <div class="col-4" >
				<p>Aici o sa punem buton sign up si login .</p>
			  </div>
			</div>
		</div>

        <nav style="margin-bottom:15px" class="navbar navbar-expand-sm navbar-dark bg-dark">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample03" aria-controls="navbarsExample03" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarsExample03">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="/">Home</a>
                    </li>
                    <li class="nav-item dropdown active">
                        <a class="nav-link dropdown-toggle " style="padding:0" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Retete</a>
                        <div class="dropdown-menu" style="top:30px" aria-labelledby="dropdown03">
                            <a class="dropdown-item" href="/retete/list_all">Cautare simpla</a>
                            <a class="dropdown-item" href="/retete/cauta_dupa_ingrediente">Cauta dupa ingrediente specifice</a>
                        </div>
                    </li>
                    <li class="nav-item"><a class="nav-link" href="/promotion">Promovare</a></li>
                    <li class="nav-item" ><a class="nav-link" href="/retete/tutorials">Tutoriale de gatit</a></li>
                    <li class="nav-item "><a  class="nav-link" href="/retete/upload_recipe">Incarca reteta</a></li>
                    <li class="nav-item"><a class="nav-link" href="/events">Evenimente</a></li>
                </ul>
            </div>
        </nav>


	<!--
		<ol class="breadcrumb">
			<li class="active"><a href="/">Home</a></li>

			  <li><a href="/employee">Employees</a></li>
			  <li><a href="/projects">Projects</a></li>
			  <li><a href="/logout">Logout</a>
					  </li>
				[#if user??]
				[#else]
					  <li><a href="/login">Login</a></li>
				[/#if]
		</ol>
	-->

<!-- PAGE BODY-->


<!-- FOOTER -->

	</div>
</body>
</html>


