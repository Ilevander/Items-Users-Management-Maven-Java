<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Article</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <h2>Edit Article</h2>
    <form action="editArticle" method="post">
        <input type="hidden" name="id" value="${article.id}" />
        <div class="form-group">
            <label for="description">Description</label>
            <input type="text" class="form-control" id="description" name="description" value="${article.description}" required>
        </div>
        <div class="form-group">
            <label for="quantite">Quantité</label>
            <input type="number" step="0.01" class="form-control" id="quantite" name="quantite" value="${article.quantite}" required>
        </div>
        <div class="form-group">
            <label for="price">Prix</label>
            <input type="number" step="0.01" class="form-control" id="price" name="price" value="${article.price}" required>
        </div>
        <button type="submit" class="btn btn-primary">Save Changes</button>
        <a href="articles.do" class="btn btn-secondary">Cancel</a>
    </form>
</div>
</body>
</html>
