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
import java.util.stream.Collectors;

@Stateless
public class RoleBean implements RoleRemote {
    @PersistenceContext(unitName = "Insurance-ejb")
    protected EntityManager persistance;

    public List<Role> listRoles() {
        Query query = persistance.createNamedQuery("allRoles");
        return(List<Role>) query.getResultList();

    }

    public List<RoleUser> listRoleUsersForRole(String roleName){
        Query query = persistance.createNamedQuery("allRoleUsersForRole");
        query.setParameter("roleName", roleName);
        return (List<RoleUser>) query.getResultList();
    }

    public void addUserRole(String userName, String roleName) {
        RoleUser roleUser = new RoleUser(userName, roleName);
        persistance.persist(roleUser);
    }

    public void removeUserRole(String userName) {
        Query query = persistance.createNamedQuery("removeRoleUsersForUser");
        query.setParameter("userName", userName);
        query.executeUpdate();
    }

    public List<RoleUser> listRoleUsers() {
        Query query = persistance.createNamedQuery("allRoleUsers");
        List<RoleUser> roleUsers = new ArrayList<>();
        roleUsers.addAll((List<RoleUser>) query.getResultList());
        return roleUsers;
    }

    public List<String> listUsersForRole(String role) {
        List<RoleUser> roleUsers = listRoleUsersForRole(role);
        List<String> insuredUsers = roleUsers.stream().map(RoleUser::getUserName).collect(Collectors.toList());
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
