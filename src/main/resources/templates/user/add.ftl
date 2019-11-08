[#ftl]
[#import "/spring.ftl" as spring /]
<html lang="en">

<div class="container">

    [#if user??]
        <div style="float: right"><b>Hello: ${user}</b></div>
    [/#if]

    <ol class="breadcrumb">
        <li><a href="/">Home</a></li>
        [#if user??]
            <li class="active"><a href="/retete">Retete</a></li>
            <li><a href="/promovari">Promovari</a></li>
            <li><a href="/logout">Logout</a>
            </li>
        [#else]
            <li><a href="/login">Login</a></li>
        [/#if]
    </ol>


    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">Add User</h3>
        </div>
        <div class="panel-body">

            [#if errors??]
                [#list errors as error]
                    <span style="color:red"> ${error}</span>
                    <br>
                [/#list]
            [/#if]
            <form method="post" action="/user/save">

                Username: <input name="userName" type="input" value="${user.userName!''}">
                <br>
                Email: <input name="email" type="input" value="${user.email!''}">
                <br>
                Nume: <input name="nume" type="input" value="${user.nume!''}">
                <br>
                Prenume: <input name="prenume" type="input" value="${user.prenume!''}">
                <br>

                [#if user.id??]
                    <input name="id" type="hidden" value="${user.id?c}"/>
                [/#if]
                <input value="save" type="submit"/>
            </form>

        </div>
    </div>
</div>