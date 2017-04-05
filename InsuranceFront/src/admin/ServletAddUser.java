package admin;

import insurance.model.user.Role;
import insurance.remote.RoleRemote;
import insurance.remote.UserRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;

@WebServlet(name = "addUser", urlPatterns = "/ajouterUtilisateur")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"ADMIN"}))
public class ServletAddUser extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private RoleRemote roleRemote;

    @EJB
    private UserRemote userRemote;

    public ServletAddUser() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>Ajouter un utilisateur</h1>");
        out.println("<FORM Method=\"POST\" Action=\"ajouterUtilisateur\"> \n" +
                "Nom : \t\t<INPUT type=text size=40 name=lastName><BR>\n \n" +
                "Prénom : \t<INPUT type=text size=40 name=firstName><BR>\n \n" +
                "Pseudo : \t<INPUT type=text size=40 name=userName><BR>\n \n" +
                "Mot de passe : \t\t<INPUT type=password size=255 name=pswd><BR>\n \n" +
                "Role : \t\t<SELECT name=role><BR>\n");
        for (Role role : roleRemote.listRoles()
                ) {
            out.println("<option value=\"" + role.getRoleName() + "\">" + role.getRoleName() + "</option>");
        }
        out.println("\t\t<INPUT type=submit value=Créer>\n </FORM>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String pswd = request.getParameter("pswd");
        pswd = sha256(pswd);
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        userRemote.addUser(userName, pswd, firstName, lastName);
        roleRemote.addUserRole(userName, request.getParameter("role"));
        getServletContext().getRequestDispatcher("/utilisateurs");
    }

    public static String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (byte aHash : hash) {
                String hex = Integer.toHexString(0xff & aHash);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
