package insurance.impl;

import insurance.model.user.Role;
import insurance.model.user.RoleUser;
import insurance.remote.RoleRemote;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class RoleBean implements RoleRemote {
    @PersistenceContext(unitName = "Insurance-ejb")
    protected EntityManager persistance;

    public List<Role> listRoles(){
        Query query = persistance.createNamedQuery("allRoles");
        List<Role> roles = new ArrayList<>();
        roles.addAll((List<Role>) query.getResultList());
        return roles;
    }

    public void addUserRole(String userName, String roleName) {
        RoleUser roleUser = new RoleUser(userName, roleName);
        persistance.persist(roleUser);
    }
}
