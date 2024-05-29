<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Articles List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script>
        function confirmDelete(articleId) {
            if (confirm("Are you sure you want to delete this article?")) {
                deleteArticle(articleId);
            }
        }

        function deleteArticle(articleId) {
            const xhr = new XMLHttpRequest();
            xhr.open("POST", "deleteArticle", true);
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    const response = JSON.parse(xhr.responseText);
                    if (response.status === "success") {
                        document.getElementById("articleRow-" + articleId).remove();
                        document.getElementById("successMessage").style.display = "block";
                    }
                }
            };
            xhr.send("id=" + articleId);
        }
    </script>
</head>
<body>
<div class="container">
    <h2>Articles List</h2>
    <a href="addArticle.jsp" class="btn btn-primary mb-2">Add New Article</a>
    <div id="successMessage" class="alert alert-success" style="display:none;">
        Article deleted successfully!
    </div>
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
                    <a href="editArticle?id=${article.id}" class="btn btn-warning">Edit</a>
                    <button type="button" class="btn btn-danger" onclick="confirmDelete(${article.id})">Delete</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
