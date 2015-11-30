<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.google.appengine.api.datastore.*" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>

<html>
    <head>
        <meta charset="utf-8" />
        <link rel="stylesheet" type="text/css" href="../css/style.css">
        <title>Handicacces</title>
    </head>

    <body>
        <h1>HANDICACCES</h1>
        <h2>moteur de recherche spécialisé pour le handicap</h2>
        
        <div>
        	<label><input type="text" name="name" style="width: 600px;height: 40px;"/></label>
        </div>
        
        <form action="/post" method="post">
        <h3> Vous pouvez rajouter des URLs </h3>
            <p>
                <label>Votre nom : <input type="text" name="name" placeholder="nom"/></label>
            </p>
            <p>
                <label>Votre url :
                <input type="text" name="url" style="width: 400px;" placeholder="http://exemple.com"/></label>
            </p>
            <p>
                <input type="submit" />
            </p>
        </form>

        <h3>ce qui est écrit dans le datastore :</h3>

        <%
            List<Entity> urls = (List<Entity>) request.getAttribute("urls");
            for (Entity url : urls) {
        %>
        <p>
            <strong><%= url.getProperty("name") %></strong> a écrit :
            <%= url.getProperty("url") %>
        </p>
        <%
            }
        %>
    </body>
</html>

