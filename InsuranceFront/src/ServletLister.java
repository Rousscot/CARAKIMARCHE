import insurance.remote.UserRemote;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletLister")
public class ServletLister extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private UserRemote myBean;

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
        // TODO Auto-generated method stub
        PrintWriter out = response.getWriter();
        // TODO au cas ou on arrive à choper des users List<User> users = myBean.listUsers();
        List<String> users = myBean.listUsers();
        out.println("<html><body>");
        out.println("<h1>Liste des utilisateurs</h1>");
        out.println("<ul>");
/*  TODO same pour les users
        for (User user : users) {
            out.println("<li>");
            out.println("<b>UserName</b> " + user.getUserName() + " "
                    + "<b>FirstName</b> " + user.getFirstName() + " "
                    + "<b>LastName</b> " + user.getLastName());
            out.println("</li>");
        }
*/
        for (String user : users) {
            out.println("<li>");
            out.println(user);
            out.println("</li>");
        }
        out.println("</ul></body></html>");
    }

}
