<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Liste des Utilisateurs</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h1 class="my-4">Ajouter un article</h1>
    <form action="user.do" method="post" class="mb-4">
        <input type="hidden" name="action" value="add">
        <div class="form-group">
            <label for="username">Nom d'utilisateur:</label>
            <input type="text" class="form-control" id="username" name="username">
        </div>
        <div class="form-group">
            <label for="password">Mot de passe:</label>
            <input type="text" class="form-control" id="password" name="password">
        </div>
        <button type="submit" class="btn btn-primary">Ajouter</button>
    </form>

    <h1 class="my-4">Liste des Utilisateurs</h1>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Nom d'utilisateur</th>
            <th>Mot de passe</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>
                    <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#updatePasswordModal${user.id}">
                        Modifier mot de passe
                    </button>

                    <div class="modal fade" id="updatePasswordModal${user.id}" tabindex="-1" role="dialog" aria-labelledby="updatePasswordModalLabel${user.id}" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="updatePasswordModalLabel${user.id}">Modifier le mot de passe</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form action="user.do" method="post">
                                        <input type="hidden" name="action" value="updatePassword">
                                        <input type="hidden" name="id" value="${user.id}">
                                        <div class="form-group">
                                            <label for="newPassword">Nouveau mot de passe:</label>
                                            <input type="password" class="form-control" id="newPassword" name="newPassword">
                                        </div>
                                        <button type="submit" class="btn btn-primary">Enregistrer</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
