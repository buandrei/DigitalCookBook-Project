[#ftl]
[#import "/spring.ftl" as spring /]
<html lang="ro">
<head>
[#include '/bootstrap_header.ftl']
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
		<!--
		<div class="">
			<a href="/"><img src="[@spring.url '/images/logo.png' /]" width="100"/></a>
		</div>
		-->

		<!--navbar -->
		<nav class="navbar navbar-inverse .navbar-fixed-top">
		  <div class="container-fluid">
			<ul class="nav navbar-nav">
				<li class="active"><a href="/">Home</a></li>
				<li class="dropdown">
					<a class="dropdown-toggle"  href="/retete">Cautare retete
					<span class="caret"></span></a>
						<ul class="dropdown-menu list-inline dropdown-menu-modified">

								<li>
									<a href="/retete">Cautare simpla</a>
								</li>
								<li>
									<a href="/specific_ingredients">Cauta dupa ingrediente specifice</a>
								</li>

						</ul>
					</li>
				<li class="dropdown">
                   	<a class="dropdown-toggle"  href="/promovari">Promovari retete
                   	<span class="caret"></span></a>
                   		<ul class="dropdown-menu list-inline dropdown-menu-modified">

                   				<li>
                   					<a href="/promovari/add">Adaugare promovare</a>
                   				</li>
                   				<li>
                   					<a href="/promovari/list">Afisare promovare/promovari</a>
                   				</li>
                   				<li>
                   					<a href="/promovari/modificare promovari">Modificare date promovare</a>
                   				</li>
                   				<li>
                   					<a href="/promovari/sterge promovare">Stergere promovare (admin)</a>
                   				</li>


                   		</ul>
				<li><a href="/tutorials">Tutoriale de gatit</a></li>
				<li><a href="/upload_recipe">Incarca reteta</a></li>
				<li><a href="/events">Evenimente</a></li>
			</ul>
		  </div>
		</nav>
<!--
[#if errors??]
    [#list errors as error]
       <span style="color:red"> ${error}</span>
    <br>
    [/#list]
[/#if]
-->

<form method="post" action="/promovari/getAll">
    Tip promovare: <input name="idUser" type="input" value="${promovareleasa.denumire!''}">
    <br>

    <input value="save" type="submit"/>
</form>

<table>
    <tr>
        <th style="min-width:150px">Id user</th>
        <th style="min-width:150px">Id promovare</th>
        <th style="min-width:150px">Id tip promovare</th>
        <th style="min-width:150px">Data adaugare promovare</th>
        <th style="min-width:150px">Data terminare promovare</th>
        <th style="min-width:150px">Stare promovare</th>

    </tr>

    [#list promovari]
    <tr>
        <td style="min-width:150px">${promovare.idUser}</td>
        <td style="min-width:150px">${promovare.id}</td>
        <td style="min-width:150px">${promovare.tipPromovare}</td>
        <td style="min-width:150px">${promovare.dataAdaugare?string('dd/MM/yyyy')}</td>
        <td style="min-width:150px">${promovare.dataFinal?string('dd/MM/yyyy')}</td>
        <td style="min-width:150px">${promovare.starePromovare}</td>
        </td>
    </tr>
    [/#list]
</table>

 </div>
</div>
</div>

</body>
</html>
