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
        <!-- Default panel contents -->


        <table>
            <tr>
                <th style="min-width:150px">email</th>

                <th style="min-width:150px">username</th>
                <th style="min-width:150px">email</th>
                <th style="min-width:150px">lastName</th>
                <th style="min-width:150px">firstName</th>
                <th style="min-width:150px">isBucatar</th>
                <th style="min-width:150px">active</th>
                <th style="min-width:150px">role</th>

            </tr>

            [#list user as user]
                <tr>
                    <td style="min-width:150px">${user.email}</td>
                    <td style="min-width:150px">${user.userName}</td>
                    <td style="min-width:150px">${user.email}</td>
                    <td style="min-width:150px">${user.lastName}</td>
                    <td style="min-width:150px">${user.firstName}</td>
                    <td style="min-width:150px">${user.isBucatar}</td>
                    <td style="min-width:150px">${user.active}</td>
                    <td style="min-width:150px">${user.role}</td>
                    <td style="min-width:150px">
                        <a href="/user/edit?id=${user.id?c}"> Edit </a>

                        <a href="/user/admin?id=${user.id?c}"> Admin </a>



                    </td>
                </tr>
            [/#list]

        </table>

    </div>
</div>
</div>

</body>
</html>


