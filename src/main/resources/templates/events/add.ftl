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
      			<li><a href="/">Home</a></li>
      			[#if user??]
                          <li class="active"><a href="/events">Events</a></li>

                          <li><a href="/logout">Logout</a>
                          </li>
                      [#else]
                          <li><a href="/login">Login</a></li>
                      [/#if]
      		</ol>


<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">Edit/Add Events</h3>
    </div>
    <div class="panel-body">

[#if errors??]
    [#list errors as error]
       <span style="color:red"> ${error}</span>
    <br>
    [/#list]
[/#if]
<form method="post" action="/events/save">

    Name: <input name="denumire" type="input" value="${events.denumire!'Name'}">
    <br>
    Description: <input name="descriere" type="input"  value="${events.descriere!'Description'}">
    <br>
    Start date (dd/MM/yyyy): <input name="data_start" type="input" value="[#if events.data_start??]${events.data_start?string('dd/MM/yyyy')}[/#if]">
    <br>
     Final date (dd/MM/yyyy): <input name="data_final" type="input" value="[#if events.data_final??]${events.data_final?string('dd/MM/yyyy')}[/#if]">
    <br>
    Organizer: <input name="organizator" type="input"  value="${events.organizator!'Organizer'}">
    <br>
    Idpromo: <input name="idpromo" type="hidden"  value="${events.idpromo!''}">
    <br>
    Iduser: <input name="iduser" type="hidden"  value="${events.iduser!''}">
    <br>
    Inactive: <input name="inactiv" type="hidden"  value="${events.inactiv!''}">
    <br>


    [#if events.id??]
        <input name="id" type="hidden" value="${events.id?c}"/>
    [/#if]
    <input value="save" type="submit"/>
</form>

    </div>
</div>
</div>
[#include '/bootstrap_footer.ftl']
</body>
</html>