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
    			<li class="active"><a href="/">Home</a></li>

    			[#if user??]
                        <li><a href="/events">Events</a></li>

                        <li><a href="/logout">Logout</a>
                        </li>
                    [#else]
                        <li><a href="/login">Login</a></li>
                    [/#if]
    		</ol>




<div class="panel panel-default">
    <!-- Default panel contents -->
    <div class="panel-heading">Events List:
        <div style="float:right"><a href="events/add"><span class="glyphicon glyphicon-plus" aria-hidden="true"></span></a></div>
    </div>

<table>
    <tr>
        <th style="min-width:150px">Name</th>
        <th style="min-width:150px">Description</th>
        <th style="min-width:150px">Start date</th>
        <th style="min-width:150px">Final date</th>
        <th style="min-width:150px">Organizer</th>
        <th style="min-width:150px"></th>

    </tr>

    [#list events as events]
    <tr>
        <td style="min-width:150px">${events.denumire}</td>
        <td style="min-width:150px">${events.descriere}</td>
        <td style="min-width:150px">${events.data_start?string('dd/MM/yyyy')}</td>
        <td style="min-width:150px">${events.data_final?string('dd/MM/yyyy')}</td>
        <td style="min-width:150px">${events.organizator}</td>
        <td style="min-width:150px"> <a href="/events/add">Add</a>
                                     <a href="/events/edit?id=${events.id?c}">Edit</a>
                                     <a href="/events/delete?id=${events.id?c}">Delete</a>
                                     <a href="/events/admin?id=${events.id?c}">Admin</a>


        </td>
    </tr>
    [/#list]

</table>

 </div>
</div>
</div>

</body>
</html>


