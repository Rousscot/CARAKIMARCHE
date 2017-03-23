package insurance.impl;

import insurance.model.user.User;

import java.util.ArrayList;
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
    public List<User> listUsers() {
        Query query = persistance.createNamedQuery("allUsers");
        List<User> users = new ArrayList<User>();
        users.addAll((List<User>) query.getResultList());
        return users;
    }

    @Override
    public boolean login(String lastName, String password) {
        //TODO
        return true;
    }

}

