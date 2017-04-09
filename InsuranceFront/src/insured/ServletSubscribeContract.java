package insured;

import abstraction.AbstractServlet;
import insurance.model.contract.ContractKind;
import insurance.remote.*;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/souscrire")
//TODO @ServletSecurity(@HttpConstraint(rolesAllowed = {"INSURED"}))
public class ServletSubscribeContract extends AbstractServlet {
    protected static final long serialVersionUID = 1L;

    @EJB
    protected ContractKindRemote contractKindRemote;
    @EJB
    protected ContractRemote contractRemote;
    @EJB
    protected UserRemote userRemote;
    @EJB
    protected RoleRemote roleRemote;
    @EJB
    protected RequestRemote requestRemote;

    //TODO changer la redirection
    @Override
    public void initPostCommands(Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> map) {
        map.put("SubscribeHouseContract", (HttpServletRequest request, HttpServletResponse response) -> {
            Integer contractId = contractRemote.addSubscriptionHouseContract(request.getParameter("htitle"), request.getUserPrincipal().getName(), Integer.parseInt(request.getParameter("hamount")), Integer.parseInt(request.getParameter("hkindid")),
                    "HABITATION",  Integer.parseInt(request.getParameter("maxAmount")), request.getParameter("address"));
            requestRemote.addRequest(contractId, "subscription", true);
            try {
                this.getServletContext().getRequestDispatcher("/mes_contrats").forward(request, response);
            } catch (IOException | ServletException e) {
                e.printStackTrace();
            }
        });
        map.put("SubscribeLifeContract", (HttpServletRequest request, HttpServletResponse response) -> {
            Integer contractId = contractRemote.addSubscriptionLifeContract(request.getParameter("ltitle"), request.getUserPrincipal().getName(), Integer.parseInt(request.getParameter("lamount")), Integer.parseInt(request.getParameter("lkindid")),
                    "VIE", Integer.parseInt(request.getParameter("capital")), Integer.parseInt(request.getParameter("minYears")));
            requestRemote.addRequest(contractId, "subscription", true);
            try {
                this.getServletContext().getRequestDispatcher("/mes_contrats").forward(request, response);
            } catch (IOException | ServletException e) {
                e.printStackTrace();
            }
        });
        map.put("SubscribeCarContract", (HttpServletRequest request, HttpServletResponse response) -> {
            Integer contractId = contractRemote.addSubscriptionCarContract(request.getParameter("ctitle"), request.getUserPrincipal().getName(), Integer.parseInt(request.getParameter("camount")), Integer.parseInt(request.getParameter("ckindid")),
                    "AUTOMOBILE", request.getParameter("model"), request.getParameter("plate"));
            requestRemote.addRequest(contractId, "subscription", true);
            try {
                this.getServletContext().getRequestDispatcher("/mes_contrats").forward(request, response);
            } catch (IOException | ServletException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void launchPage(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("optionsHouseKinds", this.createOptionsHouseKinds());
        request.setAttribute("optionsLifeKinds", this.createOptionslifeKinds());
        request.setAttribute("optionsCarKinds", this.createOptionsCarKinds());
        try {
            this.getServletContext().getRequestDispatcher("/subscribeContract.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }


    public String createOptionsKinds(String kindName) {
        StringBuilder sb = new StringBuilder();
        for (ContractKind kind : contractKindRemote.listContractKinds().stream().filter(k -> k.getCategory().equals(kindName)).collect(Collectors.toList())){
            sb.append("<option value=\"");
            sb.append(kind.getId());
            sb.append("\">");
            sb.append(kind.getTitle());
            sb.append("</option>");
        }
        return sb.toString();
    }

    public String createOptionsHouseKinds() {
        return this.createOptionsKinds("HABITATION");
    }

    public String createOptionsCarKinds() {
        return this.createOptionsKinds("AUTOMOBILE");
    }

    public String createOptionslifeKinds() {
        return this.createOptionsKinds("VIE");
    }


}
