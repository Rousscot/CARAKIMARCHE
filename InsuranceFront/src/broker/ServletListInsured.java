package broker;

import abstraction.AbstractServlet;
import insurance.model.user.User;
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
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

@WebServlet("/assures")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"BROKER"}))
public class ServletListInsured extends AbstractServlet {
    protected static final long serialVersionUID = 1L;

    @EJB
    protected UserRemote userRemote;

    @EJB
    protected RoleRemote roleRemote;

    @Override
    public void initPostCommands(Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> map) {

    }

    @Override
    public void launchPage(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("tableInsured", this.createTableInsured());
        try {
            this.getServletContext().getRequestDispatcher("/listInsuredUsers.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    public String createTableInsured() {

        List<User> insuredUsers = roleRemote.listUsersForRole("INSURED").stream().map(userName -> userRemote.findUserByUsername(userName)).collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        for (User user : insuredUsers) {
            sb.append("<tr><td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(user.getUserName());
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(user.getFirstName());
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(user.getLastName());
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\"><form method=\"POST\" action=\"contrats_assure\"><button class=\"mdl-button mdl-js-button mdl-button--raised mdl-button--primary\" type =\"submit\" value=\"");
            sb.append(user.getUserName());
            sb.append("\" name=\"action:DisplayContracts\">Voir</button></form></td></tr>");
        }
        return sb.toString();
    }

}
