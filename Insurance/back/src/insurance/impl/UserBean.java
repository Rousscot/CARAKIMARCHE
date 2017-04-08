package insurance.impl;

import insurance.model.user.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserBean implements insurance.remote.UserRemote {
    @PersistenceContext(unitName = "Insurance-ejb")
    protected EntityManager persistance;

    public List<User> listUsers() {
        Query query = persistance.createNamedQuery("allUsers");
        List<User> users = new ArrayList<>();
        users.addAll((List<User>) query.getResultList());
        return users;
    }

    @Override
    public boolean login(String lastName, String password) {
        //TODO
        return true;
    }

    public void addUser(String userName, String pswd, String firstName, String lastName) {
        User user = new User(userName, pswd, firstName, lastName);
        persistance.persist(user);
    }

    public void removeUser(String userName) {
        User user = persistance.find(User.class, userName);
        persistance.remove(user);
    }

    public User findUserByUsername(String userName) {
        return persistance.find(User.class, userName);
    }
}

