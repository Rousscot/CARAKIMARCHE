package admin;

import insurance.model.user.User;
import insurance.remote.RoleRemote;
import insurance.remote.UserRemote;

import abstraction.AbstractServlet;

import java.io.IOException;
import java.util.Map;
import java.util.function.BiConsumer;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/utilisateurs")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"ADMIN"}))
public class ServletListUsers extends AbstractServlet {

    protected static final long serialVersionUID = 1L;

    @EJB
    protected UserRemote userRemote;

    @EJB
    protected RoleRemote roleRemote;

    @Override
    public void initPostCommands(Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> map) {
        map.put("Delete", (HttpServletRequest request, HttpServletResponse response) -> {
            String userName = request.getParameter("action:Delete");
            roleRemote.removeUserRole(userName);
            userRemote.removeUser(userName);
            this.launchPage(request, response);
        });
    }

    @Override
    public void launchPage(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("usersTable", this.createUsersTable());
        try {
            this.getServletContext().getRequestDispatcher("/listUsers.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    public String createUsersTable() {
        StringBuilder sb = new StringBuilder();
        for (User user : userRemote.listUsers()) {
            sb.append("<tr><td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(user.getUserName());
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(user.getFirstName());
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(user.getLastName());
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(getRoleTranslation(roleRemote.getRoleUserFromUsername(user.getUserName()).getRoleName()));
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\"><form method=\"POST\" action=\"utilisateurs\"><button  class=\"mdl-button mdl-js-button mdl-button--raised mdl-button--primary\" type =\"submit\" value=\"");
            sb.append(user.getUserName());
            sb.append("\" name=\"action:Delete\">Supprimer</button></form></td></tr>");
        }
        return sb.toString();
    }

    public static String getRoleTranslation(String role){
        switch (role) {
            case "ADMIN":
                return "Administrateur";
            case "BROKER":
                return "Courtier";
            default:
                return "Assuré";
        }
    }
}
