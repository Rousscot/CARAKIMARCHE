package admin;

import abstraction.AbstractServlet;
import insurance.model.utils.ContractKindEnum;
import insurance.remote.ContractKindRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.function.BiConsumer;

@WebServlet(name = "addContractKind", urlPatterns = "/ajouterTypeDeContrat")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"ADMIN"}))
public class ServletAddContractKind extends AbstractServlet {
    protected static final long serialVersionUID = 1L;

    @EJB
    protected ContractKindRemote contractKindRemote;

    @Override
    public void initPostCommands(Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> map) {
        map.put("Add", (HttpServletRequest request, HttpServletResponse response) -> {
            contractKindRemote.addContractKind(request.getParameter("title"),  request.getParameter("description"), Integer.parseInt(request.getParameter("minAmount")), request.getParameter("category"));
            try {
                this.getServletContext().getRequestDispatcher("/types_de_contrat").forward(request, response);
            } catch (IOException | ServletException e) {
                e.printStackTrace(); //TODO
            }
        });
    }

    @Override
    public void launchPage(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("options", this.createOptions());
        try {
            this.getServletContext().getRequestDispatcher("/addContractKind.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            e.printStackTrace();//TODO write in response that the request did not succeeded and why. Change the status
        }
    }

    public String createOptions() {
        StringBuilder sb = new StringBuilder();
        for (ContractKindEnum category : ContractKindEnum.values()) {
            sb.append("<option value=\"");
            sb.append(category);
            sb.append("\">");
            sb.append(category);
            sb.append("</option>");
        }
        return sb.toString();
    }


}
