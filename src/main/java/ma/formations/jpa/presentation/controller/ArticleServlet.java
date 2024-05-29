package ma.formations.jpa.presentation.controller;

import java.io.IOException;
import java.util.List;

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
    private IService service = new ServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Article> articles = service.getAllArticle();
        request.setAttribute("articles", articles);
        String successMessage = request.getParameter("successMessage");
        if (successMessage != null) {
            request.setAttribute("successMessage", successMessage);
        }
        request.getRequestDispatcher("/view/articles.jsp").forward(request, response);
    }
}
