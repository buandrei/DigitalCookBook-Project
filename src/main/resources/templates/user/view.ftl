[#ftl]
[#import "/spring.ftl" as spring /]
<html lang="en">

<body>

<div class="container">
    <a href="/"> <img src="[@spring.url '/images/logo.png' /]" width="100"/>
    </a>

    <ol class="breadcrumb">
        <li class="active"><a href="/user/view">Home</a></li>
        <li><a href="/timecards">Timecards</a></li>
    </ol>

    <div class="panel panel-default">
        <table>
            <tr>
                <td style="min-width:150px">${user.userName}</td>
                <td style="min-width:150px">${user.email}</td>
                <td style="min-width:150px">${user.nume}</td>
                <td style="min-width:150px">${user.prenume}</td>

            </tr>

            <tr>
                <td style="min-width:150px">${user.userName}</td>
                <td style="min-width:150px">${user.email}</td>
                <td style="min-width:150px">${user.nume}</td>
                <td style="min-width:150px">${user.prenume}</td>
                </td>
            </tr>
        </table>

    </div>
</div>

</body>
</html>