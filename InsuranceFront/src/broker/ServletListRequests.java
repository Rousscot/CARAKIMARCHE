package broker;

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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.function.BiConsumer;

@WebServlet("/demandes")
//TODO @ServletSecurity(@HttpConstraint(rolesAllowed = {"BROKER"}))
public class ServletListRequests extends AbstractServlet {
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
        //TODO ?
    }

    @Override
    public void launchPage(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("tableHouse", this.createTableHouse());
        request.setAttribute("tableLife", this.createTableLife());
        request.setAttribute("tableCar", this.createTableCar());

        try {
            this.getServletContext().getRequestDispatcher("/listRequests.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    public String createTableHouse() {
        StringBuilder sb = new StringBuilder();
        for (Contract contract : contractRemote.listContractsRequestedForCategory("HABITATION")) {
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
            sb.append(contract.getMaxAmount()).append("</td>\n");

            sb.append(getRequestButtons(request, contract.getId()));

        }
        return sb.toString();
    }

    public String createTableLife() {
        StringBuilder sb = new StringBuilder();
        for (Contract contract : contractRemote.listContractsRequestedForCategory("VIE")) {
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
            sb.append(contract.getMinYears()).append("</td>\n");

            sb.append(getRequestButtons(request, contract.getId()));
        }
        return sb.toString();
    }

    public String createTableCar() {
        StringBuilder sb = new StringBuilder();
        for (Contract contract : contractRemote.listContractsRequestedForCategory("AUTOMOBILE")) {
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
            sb.append(contract.getPlate()).append("</td>\n");

            sb.append(getRequestButtons(request, contract.getId()));
        }
        return sb.toString();
    }

    public static String getRequestButtons(Request request, Integer contractId) {
        StringBuilder sb = new StringBuilder("");
        if (request != null) {
            String button1Name = (request.getRequestType().equals("removal")) ? "Suppr":"Ajout";
            String button2Name ;
            String button2Style;

            if(request.getBrokerAcceptance() == null) {
                button2Name = "En cours";
                button2Style = "";
            } else {
                button2Name = (request.getBrokerAcceptance()) ? "OK" : "KO";
                button2Style = (request.getBrokerAcceptance()) ? " style = \"color:green;\" " : " style = \"color:red;\" ";
            }


            sb.append("<td class=\"mdl-data-table__cell--non-numeric\"><button class=\"mdl-button mdl-js-button mdl-button--raised mdl-button--accent\" value=\"");
            sb.append(contractId);
            sb.append("\" >").append(button1Name).append("</button></td>");

            sb.append("<td class=\"mdl-data-table__cell--non-numeric\"><button ").append(button2Style).append(" class=\"mdl-button mdl-js-button mdl-button--raised \" value=\"");
            sb.append(contractId);
            sb.append("\" >").append(button2Name).append("</button></td></tr>");
        }
        return sb.toString();
    }
}
