package insurance.impl;

import insurance.model.user.User;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UserBean implements insurance.remote.UserRemote {
    @PersistenceContext(unitName = "Insurance-ejb")
    protected EntityManager persistance;

    //TODO le permitAll est censé laisser les roles déclarés en haut de classe utiliser cette méthode, mais c'est la théorie lol
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

