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

    public List<Role> listRoles() {
        Query query = persistance.createNamedQuery("allRoles");
        List<Role> roles = new ArrayList<>();
        roles.addAll((List<Role>) query.getResultList());
        return roles;
    }

    public void addUserRole(String userName, String roleName) {
        RoleUser roleUser = new RoleUser(userName, roleName);
        persistance.persist(roleUser);
    }

    public void removeUserRole(String userName) {
        List<RoleUser> roleUsers = listRoleUsers();
        for (RoleUser roleUser : roleUsers) {
            if (roleUser.getUserName().equals(userName)) {
                persistance.remove(roleUser);
            }
        }
    }

    public List<RoleUser> listRoleUsers() {
        Query query = persistance.createNamedQuery("allRoleUsers");
        List<RoleUser> roleUsers = new ArrayList<>();
        roleUsers.addAll((List<RoleUser>) query.getResultList());
        return roleUsers;
    }

    public List<String> listInsuredUsers() {
        List<RoleUser> roleUsers = listRoleUsers();
        List<String> insuredUsers = new ArrayList<>();
        for (RoleUser roleUser : roleUsers) {
            if (roleUser.getRoleName().equals("INSURED")) {
                insuredUsers.add(roleUser.getUserName());
            }
        }
        return insuredUsers;
    }

    public RoleUser getRoleUserFromUsername(String userName) {
        List<RoleUser> roleUsers = listRoleUsers();
        for (RoleUser roleUser : roleUsers) {
            if (roleUser.getUserName().equals(userName)) {
                return roleUser;
            }
        }
        return null;
    }
}
