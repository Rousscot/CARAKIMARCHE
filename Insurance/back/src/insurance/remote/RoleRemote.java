package insurance.remote;

import insurance.model.user.Role;
import insurance.model.user.RoleUser;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface RoleRemote {
    void addUserRole(String userName, String roleName);
    List<Role> listRoles();
    List<RoleUser> listRoleUsers();
    List<RoleUser> listRoleUsersForRole(String roleName);
    List<String> listUsersForRole(String role);
    void removeUserRole(String userName);
    RoleUser getRoleUserFromUsername(String userName);
}
