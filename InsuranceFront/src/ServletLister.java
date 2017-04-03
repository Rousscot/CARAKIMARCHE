import insurance.model.user.User;
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

@WebServlet("/ServletLister")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"ADMIN"}))
public class ServletLister extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private UserRemote userRemote;

    /**
     * Default constructor.
     */
    public ServletLister() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        List<User> users = userRemote.listUsers();
        out.println("<html><body>");
        out.println("<h1>Liste des utilisateurs</h1>");
        out.println("<ul>");
        for (User user : users) {
            out.println("<li>");
            out.println("<b>UserName</b> " + user.getUserName() + " "
                    + "<b>FirstName</b> " + user.getFirstName() + " "
                    + "<b>LastName</b> " + user.getLastName());
            out.println("</li>");
        }
        out.println("</ul></body></html>");
    }

}
