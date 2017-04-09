package admin;

import abstraction.AbstractServlet;
import insurance.model.user.Role;
import insurance.remote.RoleRemote;
import insurance.remote.UserRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.Map;
import java.util.function.BiConsumer;

@WebServlet(name = "addUser", urlPatterns = "/ajouterUtilisateur")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"ADMIN"}))
public class ServletAddUser extends AbstractServlet {

    protected static final long serialVersionUID = 1L;

    @EJB
    protected RoleRemote roleRemote;

    @EJB
    protected UserRemote userRemote;

    public String createOptions() {
        StringBuilder sb = new StringBuilder();
        for (Role role : roleRemote.listRoles()) {
            sb.append("<option value=\"");
            sb.append(role.getRoleName());
            sb.append("\">");
            sb.append(role.getRoleName());
            sb.append("</option>");
        }
        return sb.toString();
    }

    @Override
    public void initPostCommands(Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> map) {
        map.put("Add", (HttpServletRequest request, HttpServletResponse response) -> {
            String userName = request.getParameter("userName");
            String pswd = sha256(request.getParameter("pswd"));
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            userRemote.addUser(userName, pswd, firstName, lastName);
            roleRemote.addUserRole(userName, request.getParameter("role"));
            try {
                this.getServletContext().getRequestDispatcher("/utilisateurs").forward(request, response);
            } catch (IOException | ServletException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void launchPage(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("options", this.createOptions());
        try {
            this.getServletContext().getRequestDispatcher("/addUser.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
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
