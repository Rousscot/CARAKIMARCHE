package broker;

import abstraction.AbstractServlet;
import insurance.model.contract.Contract;
import insurance.model.user.User;
import insurance.remote.ContractKindRemote;
import insurance.remote.ContractRemote;
import insurance.remote.UserRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

@WebServlet("/contrats_assure")
//TODO @ServletSecurity(@HttpConstraint(rolesAllowed = {"ADMIN"}))
public class ServletListInsuredContracts extends AbstractServlet {
    protected static final long serialVersionUID = 1L;

    @EJB
    protected UserRemote userRemote;

    @EJB
    protected ContractKindRemote contractKindRemote;

    @EJB
    protected ContractRemote contractRemote;

    protected User insuredUser;

    @Override
    public void initPostCommands(Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> map) {
        map.put("DisplayContracts", (HttpServletRequest request, HttpServletResponse response) -> {
            String userName = request.getParameter("action:DisplayContracts");
            insuredUser = userRemote.findUserByUsername(userName);
            this.launchPage(request, response);
        });
    }

    @Override
    public void launchPage(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("insuredUserName", this.insuredUser.getUserName());

        request.setAttribute("tableHouse", this.createTableHouse());
        request.setAttribute("tableLife", this.createTableLife());
        request.setAttribute("tableCar", this.createTableCar());

        try {
            this.getServletContext().getRequestDispatcher("/listInsuredContracts.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    public String createTableHouse() {
        StringBuilder sb = new StringBuilder();
        for (Contract contract : contractRemote.listContractsForUserForCategory(insuredUser.getUserName(), "HABITATION")) {
            sb.append("<tr><td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(contract.getTitle());
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(contract.getUsername());
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(contract.getAddress());
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(contract.getKindId());
            sb.append("</td>\n<td>");
            sb.append(contract.getAmount());
            sb.append("</td>\n<td>");
            sb.append(contract.getMaxAmount());
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\"><form method=\"POST\" action=\"contrats\"><button class=\"mdl-button mdl-js-button mdl-button--raised mdl-button--primary\" type =\"submit\" value=\"");
            sb.append(contract.getId());
            sb.append("\" name=\"action:DeleteHouse\">Supprimer</button></form></td></tr>");
        }
        return sb.toString();
    }

    public String createTableLife() {
        StringBuilder sb = new StringBuilder();
        for (Contract contract : contractRemote.listContractsForUserForCategory(insuredUser.getUserName(), "VIE")) {
            sb.append("<tr><td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(contract.getTitle());
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(contract.getUsername());
            sb.append("</td>\n<td>");
            sb.append(contract.getCapitalAmount());
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(contract.getKindId());
            sb.append("</td>\n<td>");
            sb.append(contract.getAmount());
            sb.append("</td>\n<td>");
            sb.append(contract.getMinYears());
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\"><form method=\"POST\" action=\"contrats\"><button class=\"mdl-button mdl-js-button mdl-button--raised mdl-button--primary\" type =\"submit\" value=\"");
            sb.append(contract.getId());
            sb.append("\" name=\"action:DeleteLife\">Supprimer</button></form></td></tr>");
        }
        return sb.toString();
    }

    public String createTableCar() {
        StringBuilder sb = new StringBuilder();
        for (Contract contract : contractRemote.listContractsForUserForCategory(insuredUser.getUserName(), "AUTOMOBILE")) {
            sb.append("<tr><td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(contract.getTitle());
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(contract.getUsername());
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(contract.getModel());
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(contract.getKindId());
            sb.append("</td>\n<td>");
            sb.append(contract.getAmount());
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(contract.getPlate());
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\"><form method=\"POST\" action=\"contrats\"><button class=\"mdl-button mdl-js-button mdl-button--raised mdl-button--primary\" type =\"submit\" value=\"");
            sb.append(contract.getId());
            sb.append("\" name=\"action:DeleteCar\">Supprimer</button></form></td></tr>");
        }
        return sb.toString();
    }
}
