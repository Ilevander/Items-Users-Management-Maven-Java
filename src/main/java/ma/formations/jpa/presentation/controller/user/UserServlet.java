package ma.formations.jpa.presentation.controller.user;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.formations.jpa.model.User;
import ma.formations.jpa.service.IService;
import ma.formations.jpa.service.ServiceImpl;

@WebServlet("/users.do")
public class UserServlet extends HttpServlet {
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
                listUsers(request, response);
            } else {
                switch (action) {
                    case "new":
                        showNewForm(request, response);
                        break;
                    case "insert":
                        insertUser(request, response);
                        break;
                    case "delete":
                        deleteUser(request, response);
                        break;
                    case "edit":
                        showEditForm(request, response);
                        break;
                    case "update":
                        updateUser(request, response);
                        break;
                    default:
                        listUsers(request, response);
                        break;
                }
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> users = service.getAllUsers();
        request.setAttribute("users", users);
        String successMessage = request.getParameter("successMessage");
        if (successMessage != null) {
            request.setAttribute("successMessage", successMessage);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/users.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/addUser.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = service.getUserById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/view/addUser.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);
    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(password);

        service.addUser(newUser);
        response.sendRedirect("users.do?action=list&successMessage=User added successfully");
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        User user = new User();
        user.setId((long) id);
        user.setUsername(username);
        user.setPassword(password);

        service.updateUser(user);
        response.sendRedirect("users.do?action=list&successMessage=User updated successfully");
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        service.deleteUser((long) id);
        response.sendRedirect("users.do?action=list&successMessage=User deleted successfully");
    }
}
