package admin;

import insurance.model.contract.ContractKind;
import insurance.remote.ContractKindRemote;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/types_de_contrat")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"ADMIN"}))
public class ServletListContractKind extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private ContractKindRemote contractKindRemote;

    public ServletListContractKind() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        List<ContractKind> contractKinds = contractKindRemote.listContractKinds();

        out.println("<html><body>");
        out.println("<h1>Liste des contrats</h1>");
        out.println("<ul>");
        for (ContractKind contractKind : contractKinds) {
            out.println("<form method=\"POST\" action=\"types_de_contrat\"><li>");
            out.println("<b>Titre</b> " + contractKind.getTitle() + " "
                    + "<b>Description</b> " + contractKind.getDescription() + " "
                    + "<b>Catégorie</b> " + contractKind.getCategory() + " "
                    + "<b>Montant mini</b> " + contractKind.getMinAmount() + " "
                    + "<input type =\"submit\" value=\"" + contractKind.getId() + "\" name=\"idContractKind\"/> ");
            out.println("</li></form>");
        }
        out.println("</ul></body></html>");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("idContractKind"));
        contractKindRemote.removeContractKind(id);
        doGet(request, response);
    }
}
