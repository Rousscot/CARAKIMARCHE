package admin;

import insurance.model.user.User;
import insurance.remote.RoleRemote;
import insurance.remote.UserRemote;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/utilisateurs")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"ADMIN"}))
public class ServletListUsers extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private UserRemote userRemote;
    @EJB
    private RoleRemote roleRemote;

    public ServletListUsers() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        List<User> users = userRemote.listUsers();
        out.println("<html><body>");
        out.println("<h1>Liste des utilisateurs</h1>");
        out.println("<ul>");
        for (User user : users) {
            out.println("<form method=\"POST\" action=\"utilisateurs\"><li>");
            out.println("<b>UserName</b> " + user.getUserName() + " "
                    + "<b>FirstName</b> " + user.getFirstName() + " "
                    + "<b>LastName</b> " + user.getLastName() + " "
                    + "<input type =\"submit\" value=\"" + user.getUserName() + "\" name=\"userName\"/> ");
            out.println("</li></form>");
        }
        out.println("</ul></body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        roleRemote.removeUserRole(userName);
        userRemote.removeUser(userName);
        doGet(request, response);
    }

}