<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${article == null ? 'Add New Article' : 'Edit Article'}</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2>${article == null ? 'Add New Article' : 'Edit Article'}</h2>
    <form action="articles.do?action=${article == null ? 'insert' : 'update'}" method="post">
        <c:if test="${article != null}">
            <input type="hidden" name="id" value="${article.id}" />
        </c:if>
        <div class="form-group">
            <label for="description">Description:</label>
            <input type="text" class="form-control" id="description" name="description" value="${article.description}" required>
        </div>
        <div class="form-group">
            <label for="quantity">Quantité:</label>
            <input type="number" class="form-control" id="quantity" name="quantity" value="${article.quantite}" required>
        </div>
        <div class="form-group">
            <label for="price">Prix:</label>
            <input type="number" step="0.01" class="form-control" id="price" name="price" value="${article.price}" required>
        </div>
        <button type="submit" class="btn btn-primary">${article == null ? 'Add Article' : 'Update Article'}</button>
    </form>
</div>
</body>
</html>
