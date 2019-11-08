[#ftl]
[#import "/spring.ftl" as spring /]
<html lang="en">

<body>

<div class="container">

    [#if user??]
        <div style="float: right"><b>Hello: ${user}</b></div>
    [/#if]

    <ol class="breadcrumb">
        <li class="active"><a href="/">Home</a></li>

        [#if user??]
            <li><a href="/retete">Retete</a></li>
            <li><a href="/promovari">Promovari</a></li>
            <li><a href="/logout">Logout</a>
            </li>
        [#else]
            <li><a href="/login">Login</a></li>
        [/#if]
    </ol>


    <!--<img src="/ext-img/ValeaPoienii.jpg"/> -->

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


