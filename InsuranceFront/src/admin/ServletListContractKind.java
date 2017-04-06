package admin;

import abstraction.AbstractServlet;
import insurance.model.contract.ContractKind;
import insurance.remote.ContractKindRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.function.BiConsumer;

@WebServlet("/types_de_contrat")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"ADMIN"}))
public class ServletListContractKind extends AbstractServlet {
    protected static final long serialVersionUID = 1L;

    @EJB
    protected ContractKindRemote contractKindRemote;

    @Override
    public void initPostCommands(Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> map) {
        map.put("Delete", (HttpServletRequest request, HttpServletResponse response) -> {
            Integer id = Integer.parseInt(request.getParameter("action:Delete"));
            contractKindRemote.removeContractKind(id);
            this.launchPage(request, response);
        });
    }

    @Override
    public void launchPage(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("table", this.createTable());
        try {
            this.getServletContext().getRequestDispatcher("/listContractsKind.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();//TODO write in response that the request did not succeeded and why. Change the status
        }
    }

    public String createTable() {
        StringBuilder sb = new StringBuilder();
        for (ContractKind contractKind : contractKindRemote.listContractKinds()) {
            sb.append("<tr><td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(contractKind.getTitle());
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(contractKind.getDescription());
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(contractKind.getCategory());
            sb.append("</td>\n<td>");
            sb.append(contractKind.getMinAmount());
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\"><form method=\"POST\" action=\"types_de_contrat\"><button class=\"mdl-button mdl-js-button mdl-button--raised mdl-button--primary\" type =\"submit\" value=\"");
            sb.append(contractKind.getId());
            sb.append("\" name=\"action:Delete\">Supprimer</button></form></td></tr>");
        }
        return sb.toString();
    }
}
