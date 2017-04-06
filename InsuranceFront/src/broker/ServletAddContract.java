package broker;

import abstraction.AbstractServlet;
import insurance.model.contract.ContractKind;
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
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

@WebServlet(name = "addContract", urlPatterns = "/ajouterContrat")
//TODO @ServletSecurity(@HttpConstraint(rolesAllowed = {"BROKER"}))
public class ServletAddContract extends AbstractServlet {
    protected static final long serialVersionUID = 1L;

    @EJB
    protected ContractKindRemote contractKindRemote;
    @EJB
    protected ContractRemote contractRemote;
    @EJB
    protected UserRemote userRemote;

    //TODO changer la redirection
    @Override
    public void initPostCommands(Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> map) {
        map.put("AddHouseContract", (HttpServletRequest request, HttpServletResponse response) -> {
            contractRemote.addHouseContract(request.getParameter("htitle"), request.getParameter("husername"), Integer.parseInt(request.getParameter("hamount")), Integer.parseInt(request.getParameter("hkindid")),
                    "HABITATION",  Integer.parseInt(request.getParameter("maxAmount")), request.getParameter("address"));
            try {
                this.getServletContext().getRequestDispatcher("/contrats").forward(request, response);
            } catch (IOException | ServletException e) {
                e.printStackTrace();
            }
        });
        map.put("AddLifeContract", (HttpServletRequest request, HttpServletResponse response) -> {
            contractRemote.addLifeContract(request.getParameter("ltitle"), request.getParameter("lusername"), Integer.parseInt(request.getParameter("lamount")), Integer.parseInt(request.getParameter("lkindid")),
                    "VIE", Integer.parseInt(request.getParameter("capital")), Integer.parseInt(request.getParameter("minYears")));
            try {
                this.getServletContext().getRequestDispatcher("/contrats").forward(request, response);
            } catch (IOException | ServletException e) {
                e.printStackTrace();
            }
        });
        map.put("AddCarContract", (HttpServletRequest request, HttpServletResponse response) -> {
            contractRemote.addCarContract(request.getParameter("ctitle"), request.getParameter("cusername"), Integer.parseInt(request.getParameter("camount")), Integer.parseInt(request.getParameter("ckindid")),
                    "AUTOMOBILE", request.getParameter("model"), request.getParameter("plate"));
            try {
                this.getServletContext().getRequestDispatcher("/contrats").forward(request, response);
            } catch (IOException | ServletException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void launchPage(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("optionsUser", this.createOptionsUser());
        request.setAttribute("optionsHouseKinds", this.createOptionsHouseKinds());
        request.setAttribute("optionsLifeKinds", this.createOptionslifeKinds());
        request.setAttribute("optionsCarKinds", this.createOptionsCarKinds());
        try {
            this.getServletContext().getRequestDispatcher("/addContract.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();
        }
    }

    public String createOptionsUser() {
        StringBuilder sb = new StringBuilder();
        for (User user : userRemote.listUsers()) {
            sb.append("<option value=\"");
            sb.append(user.getUserName());
            sb.append("\">");
            sb.append(user.getUserName());
            sb.append("</option>");
        }
        return sb.toString();
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
