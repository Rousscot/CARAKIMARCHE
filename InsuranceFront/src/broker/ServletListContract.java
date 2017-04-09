package broker;

import abstraction.AbstractServlet;
import insurance.model.contract.Contract;
import insurance.model.contract.Request;
import insurance.remote.ContractKindRemote;
import insurance.remote.ContractRemote;
import insurance.remote.RequestRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.function.BiConsumer;

@WebServlet("/contrats")
//TODO @ServletSecurity(@HttpConstraint(rolesAllowed = {"ADMIN"}))
public class ServletListContract extends AbstractServlet {
    protected static final long serialVersionUID = 1L;

    @EJB
    protected ContractKindRemote contractKindRemote;

    @EJB
    protected ContractRemote contractRemote;

    //TODO ajouter la remote request pour supprimer la requete si elle existe pour le contrat à supprimer)

    @Override
    public void initPostCommands(Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> map) {
        map.put("DeleteHouse", (HttpServletRequest request, HttpServletResponse response) -> {
            Integer id = Integer.parseInt(request.getParameter("action:DeleteHouse"));
            contractRemote.removeContract(id);

            this.launchPage(request, response);
        });
        map.put("DeleteLife", (HttpServletRequest request, HttpServletResponse response) -> {
            Integer id = Integer.parseInt(request.getParameter("action:DeleteLife"));
            contractRemote.removeContract(id);
            this.launchPage(request, response);
        });
        map.put("DeleteCar", (HttpServletRequest request, HttpServletResponse response) -> {
            Integer id = Integer.parseInt(request.getParameter("action:DeleteCar"));
            contractRemote.removeContract(id);
            this.launchPage(request, response);
        });
    }

    @Override
    public void launchPage(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("tableHouse", this.createTableHouse());
        request.setAttribute("tableLife", this.createTableLife());
        request.setAttribute("tableCar", this.createTableCar());
        try {
            this.getServletContext().getRequestDispatcher("/listContracts.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    public String createTableHouse() {
        StringBuilder sb = new StringBuilder();
        for (Contract contract : contractRemote.listContractsForCategory("HABITATION")) {
            sb.append("<tr><td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(contract.getTitle());
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(contract.getUsername());
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(contract.getAddress());
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(contractKindRemote.getContractKindFromId(contract.getKindId()).getTitle());
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
        for (Contract contract : contractRemote.listContractsForCategory("VIE")) {
            sb.append("<tr><td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(contract.getTitle());
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(contract.getUsername());
            sb.append("</td>\n<td>");
            sb.append(contract.getCapitalAmount());
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(contractKindRemote.getContractKindFromId(contract.getKindId()).getTitle());
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
        for (Contract contract : contractRemote.listContractsForCategory("AUTOMOBILE")) {
            sb.append("<tr><td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(contract.getTitle());
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(contract.getUsername());
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(contract.getModel());
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\">");
            sb.append(contractKindRemote.getContractKindFromId(contract.getKindId()).getTitle());
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
