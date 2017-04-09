import insurance.remote.UserRemote;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/logout")
public class ServletLogout extends HttpServlet {
    private static final long serialVersionUID = 1L;

    //TODO LOGOUT FONCTIONNE PAS DU TOUT
    @EJB
    private UserRemote myBean ;

    public ServletLogout() {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.logout();
        response.sendRedirect("/insuranceFront");
    }

}
