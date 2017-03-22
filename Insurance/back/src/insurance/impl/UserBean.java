package insurance.impl;

import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@DeclareRoles({"ADMIN", "BROKER", "INSURED"})
@Stateless
public class UserBean implements insurance.remote.UserRemote {
    @PersistenceContext(unitName = "Insurance-ejb")
    EntityManager persistance;

    //TODO le permitAll est censé laisser les roles déclarés en haut de classe utiliser cette méthode, mais c'est la théorie lol
    @PermitAll
    public List<String> listUsers() {
        Query q = persistance.createNamedQuery("allUsers");
        try {
            List<String> resultList = (List<String>) q.getResultList();
            return resultList;
        } catch (ClassCastException e) {
            return null;
        }
    }

    @Override
    public boolean login(String lastName, String password) {
        //TODO
        return true;
    }

}

