<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Articles List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2>Articles List</h2>
    <a href="<c:url value='/articles.do?action=new' />" class="btn btn-primary mb-2">Add New Article</a>

    <c:if test="${not empty successMessage}">
        <div class="alert alert-success">${successMessage}</div>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">${errorMessage}</div>
    </c:if>

    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Description</th>
            <th scope="col">Quantité</th>
            <th scope="col">Prix</th>
            <th scope="col">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${articles}" var="article">
            <tr id="articleRow-${article.id}">
                <th scope="row">${article.id}</th>
                <td>${article.description}</td>
                <td>${article.quantite}</td>
                <td>${article.price}</td>
                <td>
                    <a href="<c:url value='/articles.do?action=edit&id=${article.id}' />" class="btn btn-warning">Edit</a>
                    <a href="<c:url value='/articles.do?action=delete&id=${article.id}' />" class="btn btn-danger">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
