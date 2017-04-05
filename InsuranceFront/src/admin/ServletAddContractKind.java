package admin;

import insurance.model.user.Role;
import insurance.model.utils.ContractKindEnum;
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

@WebServlet(name = "addContractKind", urlPatterns = "/ajouterTypeDeContrat")
@ServletSecurity(@HttpConstraint(rolesAllowed = {"ADMIN"}))
public class ServletAddContractKind extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private ContractKindRemote contractKindRemote;

    public ServletAddContractKind() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>Ajouter un type de contrat</h1>");
        out.println("<FORM Method=\"POST\" Action=\"ajouterTypeDeContrat\"> \n" +
                "Titre : \t\t<INPUT type=\"text\" size=40 name=\"title\"><BR>\n \n" +
                "Description : \t<INPUT type=\"text\" size=255 name=\"description\"><BR>\n \n" +
                "Montant minimal : \t<INPUT type=\"number\" size=40 name=\"minAmount\"><BR>\n \n" +
                "Catégorie : \t\t<SELECT name=\"category\"><BR>\n");
        for (ContractKindEnum category : ContractKindEnum.values()) {
            out.println("<option value=\"" + category + "\">" + category + "</option>");
        }
        out.println("\t\t<INPUT type=submit value=Créer>\n </FORM>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        Integer minAmount = Integer.parseInt(request.getParameter("minAmount"));
        String category = request.getParameter("category");

        contractKindRemote.addContractKind(title, description, minAmount, category);
        getServletContext().getRequestDispatcher("/ajouterTypeDeContrat");
    }


}
