package ma.formations.jpa.presentation.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.formations.jpa.model.Article;
import ma.formations.jpa.service.IService;
import ma.formations.jpa.service.ServiceImpl;

@WebServlet("/articles.do")
public class ArticleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IService service;

    public void init() {
        service = new ServiceImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if (action == null) {
                listArticles(request, response);
            } else {
                switch (action) {
                    case "new":
                        showNewForm(request, response);
                        break;
                    case "insert":
                        insertArticle(request, response);
                        break;
                    case "delete":
                        deleteArticle(request, response);
                        break;
                    case "edit":
                        showEditForm(request, response);
                        break;
                    case "update":
                        updateArticle(request, response);
                        break;
                    default:
                        listArticles(request, response);
                        break;
                }
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listArticles(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Article> articles = service.getAllArticle();
        request.setAttribute("articles", articles);
        String successMessage = request.getParameter("successMessage");
        if (successMessage != null) {
            request.setAttribute("successMessage", successMessage);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/articles.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/addArticle.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Article existingArticle = service.getArticleById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/addArticle.jsp");
        request.setAttribute("article", existingArticle);
        dispatcher.forward(request, response);
    }

    private void insertArticle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String description = request.getParameter("description");
        double quantity = Double.parseDouble(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));

        Article newArticle = new Article();
        newArticle.setDescription(description);
        newArticle.setQuantite(quantity);
        newArticle.setPrice(price);

        service.addArticle(newArticle);
        response.sendRedirect("articles.do?action=list&successMessage=Article added successfully");
    }

    private void updateArticle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String description = request.getParameter("description");
        double quantity = Double.parseDouble(request.getParameter("quantity"));
        double price = Double.parseDouble(request.getParameter("price"));

        Article article = new Article();
        article.setId((long) id);
        article.setDescription(description);
        article.setQuantite(quantity);
        article.setPrice(price);

        service.updateArticle(article);
        response.sendRedirect("articles.do?action=list&successMessage=Article updated successfully");
    }

    private void deleteArticle(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        service.deleteArticle((long) id);
        response.sendRedirect("articles.do?action=list&successMessage=Article deleted successfully");
    }
}
