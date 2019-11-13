<#ftl>
<#import "/spring.ftl" as spring />
<html lang="ro">
<head>
    <#include '/bootstrap_header.ftl'>
</head>

<body >
<div class="container">
    <div class="page-header">
        <!-- TOP (top part, navbar)-->

        <div class="row">
            <!--logo-->
            <div class="col-8" >
                <h1>Aici o sa avem logo</h1>
            </div>

            <div class="col-4" >
                <p><button>SIGN UP</button> <BUTTON>LOG IN</BUTTON></p>
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
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle " style="padding:0" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Retete</a>
                    <div class="dropdown-menu" style="top:30px" aria-labelledby="dropdown03">

                        <a class="dropdown-item" href="/retete/list_all">Cautare simpla</a>


                        <a class="dropdown-item" href="/retete/search_ingredients">Cauta dupa ingrediente specifice</a>


                    </div>
                </li>
                <li class="nav-item"><a class="nav-link" href="/promotion">Promovare</a></li>
                <li class="nav-item" ><a class="nav-link" href="/retete/tutorials">Tutoriale de gatit</a></li>
                <li class="nav-item active"><a  class="nav-link" href="/retete/upload_recipe">Incarca reteta</a></li>
                <li class="nav-item"><a class="nav-link" href="/events">Evenimente</a></li>
            </ul>
            <form class="form-inline my-2 my-md-0">
                <input class="form-control" type="text" placeholder="Search">
            </form>
        </div>
    </nav>

    <form name="saveForm" method="post" action="/user/save" enctype="multipart/form-data">
        <div class="card">
            <h3 class="card-header text-center"><b>Creare cont</b></h3>
            <small><p style="margin:0" class="font-weight-lighter text-center font-italic">campurile marcate cu * sunt obligatorii</p></small>
            <div class="card">
                <h4 class="text-center bg-info card-header">Informatii utilizator</h4>
                <div class="card-body">

                    <#if errors??>
                        <#list errors as error>
                            <span style="color:red"> ${error}</span>
                            <br>
                        </#list>
                    </#if>

                    <div class="form-group">
                        <label for="userName">Username *</label>
                        <input name="userName" value="${user.userName!''}" type="input" class="form-control" id="userName" aria-describedby="Username" placeholder="Username" >
                    </div>

                    <div class="form-group">
                        <label for="email">Email address *</label>
                        <input name="email" value="${user.email!''}" type="input" class="form-control" id="email" aria-describedby="emailHelp" placeholder="Email">
                        <small id="email" class="form-text text-muted">Nu va vom publica e-mailul unor terte parti niciodata</small>
                    </div>

                    <div class="form-group">
                        <label for="nume">Nume *</label>
                        <input name="nume" value="${user.nume!''}" type="input" class="form-control" id="nume" aria-describedby="Nume" placeholder="Nume" >
                    </div>

                    <div class="form-group">
                        <label for="prenume">Prenume *</label>
                        <input name="prenume" value="${user.prenume!''}" type="input" class="form-control" id="prenume" aria-describedby="Prenume" placeholder="Prenume" >
                    </div>

                    <div class="form-group">
                        <label for="isbucatar">Esti bucatar ? d sau n *</label>
                        <input name="isbucatar" value="${user.isbucatar!''}" type="input" class="form-control" id="isbucatar" aria-describedby="IsBucatar" placeholder="IsBucatar" >
                    </div>

                    <div class="form-group">
                        <label for="parola">Parola *</label>
                        <input name="parola" value="${user.parola!''}" type="password" class="form-control" id="parola" aria-describedby="Parola" placeholder="Parola" >
                    </div>

                    <#--picture-->

                    <#--<div class="input-group mb-3">

                        <div class="custom-file">
                            <input onchange="ValidateUserImageSize(this)" name="file"  accept="image/*" type="file" class="custom-file-input" id="recipe_picture">
                            <label class="custom-file-label" for="recipe_picture">Alege imagine de utilizator  *</label>
                        </div>

                    </div>-->

                    <small><p style="margin:0" class="font-weight-lighter text-center font-italic">marimea imaginii nu trebuie sa depaseasca 2 MB</p></small>


                </div>
                <input value="save" class="btn btn-primary btn-lg btn-block" type="submit"/>
            </div>
        </div>
    </form>
</div>

<#include '/bootstrap_footer.ftl'>

</body>
</html>

