package ma.formations.jpa.presentation.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.formations.jpa.model.Article;
import ma.formations.jpa.service.IService;
import ma.formations.jpa.service.ServiceImpl;

@WebServlet("/editArticle")
public class EditArticleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IService service = new ServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Article article = service.findArticleById(id);
        request.setAttribute("article", article);
        request.getRequestDispatcher("/view/editArticle.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String description = request.getParameter("description");
        Double quantite = Double.parseDouble(request.getParameter("quantite"));
        Double price = Double.parseDouble(request.getParameter("price"));

        Article article = new Article(id, description, quantite, price);
        service.updateArticle(article);

        response.sendRedirect("articles.do");
    }
}
