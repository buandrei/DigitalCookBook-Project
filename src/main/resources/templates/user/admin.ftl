[#ftl]
[#import "/spring.ftl" as spring /]
<html lang="en">
<head>
    [#include '/bootstrap_header.ftl']
</head>
<body>

<div class="container">
    <a href="/"> <img src="[@spring.url '/images/logo.png' /]" width="100"/>
    </a>

    [#if user??] <div style="float: right"><b>Hello: ${user}</b></div>

    [/#if]

    <ol class="breadcrumb">

        [#if user??]


            <li><a href="/logout">Logout</a>
            </li>
        [#else]
            <li><a href="/login">Login</a></li>
        [/#if]
    </ol>


    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">Edit/Admin user</h3>
        </div>
        <div class="panel-body">

            [#if errors??]
                [#list errors as error]
                    <span style="color:red"> ${error}</span>
                    <br>
                [/#list]
            [/#if]
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
                        <label for="lastName">Nume *</label>
                        <input name="lastName" value="${user.lastName!''}" type="input" class="form-control" id="lastName" aria-describedby="Nume" placeholder="Nume" >
                    </div>

                    <div class="form-group">
                        <label for="firstName">Prenume *</label>
                        <input name="firstName" value="${user.firstName!''}" type="input" class="form-control" id="firstName" aria-describedby="Prenume" placeholder="Prenume" >
                    </div>

                    <div class="form-group">
                        <label for="isBucatar">Esti bucatar ? d sau n *</label>
                        <input name="isBucatar" value="${user.isBucatar!''}" type="input" class="form-control" id="isBucatar" aria-describedby="IsBucatar" placeholder="IsBucatar" >
                    </div>

                    <div class="form-group">
                        <label for="password">Parola *</label>
                        <input name="password" value="${user.password!''}" type="password" class="form-control" id="password" aria-describedby="Parola" placeholder="Parola" >
                    </div>

                    <div class="form-group">
                        <label for="active">Esti activ ? t sau f *</label>
                        <input name="active" value="${user.active!''}" type="input" class="form-control" id="active" aria-describedby="IsActive" placeholder="IsActive" >
                    </div>

                    <div class="form-group">
                        <label for="role">Rol * (USER)</label>
                        <input name="role" value="${user.role!''}" type="input" class="form-control" id="role" aria-describedby="Role" placeholder="Role" >
                    </div>


                    <small><p style="margin:0" class="font-weight-lighter text-center font-italic">marimea imaginii nu trebuie sa depaseasca 2 MB</p></small>


                </div>
                <input value="save" class="btn btn-primary btn-lg btn-block" type="submit"/>
        </div>
    </div>
    </form>

</div>
</div>
</div>
[#include '/bootstrap_footer.ftl']
</body>
</html>