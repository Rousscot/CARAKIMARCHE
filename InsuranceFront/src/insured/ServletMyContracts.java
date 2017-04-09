package insured;

import abstraction.AbstractServlet;
import insurance.model.contract.Contract;
import insurance.model.contract.Request;
import insurance.model.user.User;
import insurance.remote.ContractKindRemote;
import insurance.remote.ContractRemote;
import insurance.remote.RequestRemote;
import insurance.remote.UserRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.function.BiConsumer;

@WebServlet("/mes_contrats")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"INSURED"}))
public class ServletMyContracts extends AbstractServlet {
    protected static final long serialVersionUID = 1L;

    @EJB
    protected UserRemote userRemote;

    @EJB
    protected ContractKindRemote contractKindRemote;

    @EJB
    protected ContractRemote contractRemote;

    @EJB
    protected RequestRemote requestRemote;

    @Override
    public void initPostCommands(Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> map) {
        map.put("AskRemoveContract", (HttpServletRequest request, HttpServletResponse response) -> {
            requestRemote.addRequest(Integer.parseInt(request.getParameter("action:AskRemoveContract")), "removal", true);
            this.launchPage(request, response);
        });
    }

    @Override
    public void launchPage(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getUserPrincipal().getName();
        User insuredUser = userRemote.findUserByUsername(userName);
        request.setAttribute("insuredUserName", userName);

        request.setAttribute("tableHouse", this.createTableHouse(insuredUser));
        request.setAttribute("tableLife", this.createTableLife(insuredUser));
        request.setAttribute("tableCar", this.createTableCar(insuredUser));

        try {
            this.getServletContext().getRequestDispatcher("/listMyContracts.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    public String createTableHouse(User insuredUser) {
        StringBuilder sb = new StringBuilder();
        for (Contract contract : contractRemote.listContractsForUserForCategory(insuredUser.getUserName(), "HABITATION")) {
            Request request = requestRemote.getRequestFromContractId(contract.getId());
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

            sb.append(getRemovalButton(request, contract.getId()));

        }
        return sb.toString();
    }

    public String createTableLife(User insuredUser) {
        StringBuilder sb = new StringBuilder();
        for (Contract contract : contractRemote.listContractsForUserForCategory(insuredUser.getUserName(), "VIE")) {
            Request request = requestRemote.getRequestFromContractId(contract.getId());
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

            sb.append(getRemovalButton(request, contract.getId()));
        }
        return sb.toString();
    }

    public String createTableCar(User insuredUser) {
        StringBuilder sb = new StringBuilder();
        for (Contract contract : contractRemote.listContractsForUserForCategory(insuredUser.getUserName(), "AUTOMOBILE")) {
            Request request = requestRemote.getRequestFromContractId(contract.getId());
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

            sb.append(getRemovalButton(request, contract.getId()));
        }
        return sb.toString();
    }

    public static String getRemovalButton(Request request, Integer contractId){
        StringBuilder sb = new StringBuilder("");
        if(request != null && request.getRequestType().equals("removal")){
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\"><button class=\"mdl-button mdl-js-button mdl-button--raised mdl-button--accent\" value=\"");
            sb.append(contractId);
            sb.append("\" >Rés. en cours</button></td></tr>");
        } else {
            sb.append("</td>\n<td class=\"mdl-data-table__cell--non-numeric\"><form method=\"POST\" action=\"mes_contrats\"><button class=\"mdl-button mdl-js-button mdl-button--raised mdl-button--primary\" type =\"submit\" value=\"");
            sb.append(contractId);
            sb.append("\" name=\"action:AskRemoveContract\">Dem. résiliation</button></form></td></tr>");
        }
        return sb.toString();
    }
}
