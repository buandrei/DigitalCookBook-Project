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

    <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
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
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <div class="panel-heading">Employee List:
            <div style="float:right"><a href="user/add"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></a></div>
        </div>

        <table>
            <tr>
                <th style="min-width:150px">Username</th>
                <th style="min-width:150px">Email</th>
                <th style="min-width:150px">Nume</th>
                <th style="min-width:150px">Prenume </th>

                <th style="min-width:150px"></th>

            </tr>

            [#list users as user]
                <tr>
                    <td style="min-width:150px">${user.userName}</td>
                    <td style="min-width:150px">${user.email}</td>
                    <td style="min-width:150px">${user.nume}</td>
                    <td style="min-width:150px">${user.prenume}</td>
                    <td style="min-width:150px"><a href="/user/edit?id=${user.id?c}">Edit</a>
                        <a href="/user/delete?id=${user.id?c}">Delete</a>
                    </td>
                </tr>
            [/#list]
        </table>

    </div>
</div>
</div>

</body>
</html>


